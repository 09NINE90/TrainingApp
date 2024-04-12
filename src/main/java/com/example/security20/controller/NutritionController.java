package com.example.security20.controller;

import com.example.security20.dto.CustomUserDetails;
import com.example.security20.entity.Nutrition;
import com.example.security20.entity.NutritionPlan;
import com.example.security20.entity.WeekNutrition;
import com.example.security20.service.NutritionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping("/api/v1")
public class NutritionController {
    @Autowired
    NutritionService nutritionService;

    @GetMapping("/deleteNutrition/{nutritionId}")
    public String deleteNutrition(@PathVariable("nutritionId") Long nutritionId){
//        nutritionService.deleteById(nutritionId);
        return "redirect:/api/v1/getNutritionPage";
    }
    @GetMapping("/deleteNutritionPlan/{nutritionPlanId}/{userId}")
    public String deleteNutritionPlan(@PathVariable("nutritionPlanId") Long nutritionPlanId, @PathVariable("userId") Long userId){
        nutritionService.deletePlanById(nutritionPlanId);
        return "redirect:/api/v1/getNutritionPage/" + userId;
    }

    @GetMapping("/setNutritionPlan/{userId}")
    public String createNutritionPlan(@PathVariable("userId") Long userId, Model model){
        model.addAttribute("userId", userId);
        model.addAttribute("nutritionPlan", new NutritionPlan());
        return "nutritionPlanForm";
    }

    @PostMapping("/setNutritionPlan/{userId}")
    public String createNutritionPlan(@PathVariable("userId") Long userId, @ModelAttribute("nutritionPlan") NutritionPlan nutritionPlan){
        nutritionPlan.setUserId(userId);
        nutritionService.saveNutritionPlan(nutritionPlan);
        return "redirect:/api/v1/getNutritionPage/" + userId;
    }
    @GetMapping("/getNutritionPage")
    public String getWorkoutPage(Authentication authentication, Model model, @ModelAttribute("nutrition") Nutrition nutrition){
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();
        setNutritionPage(model, userId);
        return "nutritionPage";
    }

    @GetMapping("/getNutritionPage/{userId}")
    public String getWorkoutPage(Model model, @PathVariable("userId") Long id, @ModelAttribute("nutrition") Nutrition nutrition){
        model.addAttribute("userId", id);
        setNutritionPage(model, id);
        return "nutritionPage";
    }
    @GetMapping("/addNutrition")
    public void addNutrition(Model model){
        model.addAttribute("nutrition",new Nutrition());
    }
    @PostMapping("/addNutrition")
    public String addNutrition(Authentication authentication, @ModelAttribute("nutrition") Nutrition nutrition) throws ParseException {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();

        Date date =  nutrition.getDate();
        List<Date> dateList = getWeekStartEnd(date);
        Date startOfWeekDate = dateList.get(0);
        Date endOfWeekDate = dateList.get(1);
        Date myDate = dateList.get(2);
        String checkDays = myDate.toString().split(" ")[2];
        double weightFactor = nutrition.getWeight() / 100.0;
        double calories =  Math.ceil(nutrition.getCalories() * weightFactor * 10) /10;
        double proteins =  Math.ceil(nutrition.getProteins() * weightFactor * 10) /10;
        double fats = Math.ceil(nutrition.getFats() * weightFactor * 10) /10;
        double carbohydrates =  Math.ceil(nutrition.getCarbohydrates() * weightFactor * 10) /10;

        WeekNutrition weekNutrition;
        WeekNutrition lastWeekNutrition = nutritionService.findLastWeekNutritionByUserIdAndWeekStart(userId, startOfWeekDate);
        if (lastWeekNutrition == null){
            weekNutrition = new WeekNutrition();
            addWeek(weekNutrition, userId, startOfWeekDate, checkDays, endOfWeekDate, myDate, calories, proteins, fats, carbohydrates);
        } else {
            if (checkDateInWeekRange(lastWeekNutrition.getWeekStart(), lastWeekNutrition.getWeekEnd(), myDate)) {
                int countDays;
                String result;
                List<String> stringList = new ArrayList<>(List.of(lastWeekNutrition.getCheckDays().split(",")));
                if (!stringList.contains(checkDays)) {
                    countDays = lastWeekNutrition.getCountDaysOfWeek() + 1;
                    stringList.add(checkDays);
                    StringJoiner joiner = new StringJoiner(",");
                    for (String str : stringList) {
                        joiner.add(str);
                    }
                    result = joiner.toString();
                } else {
                    countDays = lastWeekNutrition.getCountDaysOfWeek();
                    result = lastWeekNutrition.getCheckDays();
                }
                nutritionService.updateWeekNutrition(userId,
                        countDays,
                        myDate,
                        startOfWeekDate,
                        result,
                        calories,
                        proteins,
                        fats,
                        carbohydrates);
            }
            else {
                weekNutrition = new WeekNutrition();
                addWeek(weekNutrition, userId, startOfWeekDate, checkDays, endOfWeekDate, myDate, calories, proteins, fats, carbohydrates);
            }
        }

        WeekNutrition lastWeekNutrition1 = nutritionService.findLastWeekNutritionByUserIdAndWeekStart(userId, startOfWeekDate);
        nutrition.setWeekId(lastWeekNutrition1.getId());
        nutrition.setUserId(userId);
        nutrition.setCalories(calories);
        nutrition.setProteins(proteins);
        nutrition.setFats(fats);
        nutrition.setCarbohydrates(carbohydrates);
        nutrition.setDate(date);

        nutritionService.saveNutrition(nutrition);
        return "redirect:/api/v1/getNutritionPage";
    }
    private void addWeek(WeekNutrition weekNutrition, Long userId, Date start, String checkDays, Date end, Date lastDate, Double sumCalories, Double sumProteins, Double sumFats, Double sumCarbohydrates){
        weekNutrition.setWeekStart(start);
        weekNutrition.setWeekEnd(end);
        weekNutrition.setLastDate(lastDate);
        weekNutrition.setCheckDays(checkDays);
        weekNutrition.setCountDaysOfWeek(1);
        weekNutrition.setUserId(userId);
        weekNutrition.setSumCalories(sumCalories);
        weekNutrition.setSumProteins(sumProteins);
        weekNutrition.setSumFats(sumFats);
        weekNutrition.setSumCarbohydrates(sumCarbohydrates);
        nutritionService.saveWeekNutrition(weekNutrition);
    }

    private void setNutritionPage(Model model, Long id){
        List<Nutrition> nutritionList = nutritionService.getNutritionByUserId(id);
        WeekNutrition lastWeekNutrition = nutritionService.findLastWeekNutritionByUserId(id);
        List<NutritionPlan> nutritionPlanList = nutritionService.getNutritionPlanByUserId(id);
        NutritionPlan nutritionPlan = nutritionService.getLastNutritionPlanByUserId(id);
        if (lastWeekNutrition != null){
            model.addAttribute("calories" , Math.round(lastWeekNutrition.getSumCalories()/lastWeekNutrition.getCountDaysOfWeek()));
            model.addAttribute("proteins" , Math.round(lastWeekNutrition.getSumProteins()/lastWeekNutrition.getCountDaysOfWeek()));
            model.addAttribute("fats" , Math.round(lastWeekNutrition.getSumFats()/lastWeekNutrition.getCountDaysOfWeek()));
            model.addAttribute("carbohydrates" , Math.round(lastWeekNutrition.getSumCarbohydrates()/lastWeekNutrition.getCountDaysOfWeek()));
            model.addAttribute("startWeek", lastWeekNutrition.getWeekStart());
            model.addAttribute("endWeek", lastWeekNutrition.getWeekEnd());
        }
        if (!nutritionPlanList.isEmpty()){
            model.addAttribute("nutritionPlanList", nutritionPlanList);
        }
        if (nutritionPlan != null){
            model.addAttribute("nutritionPlan", nutritionPlan);
        }
        model.addAttribute("nutritionList", nutritionList);
    }

    private List<Date> getWeekStartEnd(Date date) {
        // Преобразуем объект Date в LocalDateTime
        LocalDateTime dateTime = Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        // Извлекаем из LocalDateTime только дату
        LocalDate localDate = dateTime.toLocalDate();
        // Определяем день недели для данной даты
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        // Находим начало недели
        LocalDate startOfWeek = localDate.minusDays(dayOfWeek.getValue() - 1);
        // Находим конец недели
        LocalDate endOfWeek = startOfWeek.plusDays(6);

        Date startOfWeekDate = Date.from(startOfWeek.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endOfWeekDate = Date.from(endOfWeek.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date myDay = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        List<Date> dateList = new ArrayList<>();
        dateList.add(startOfWeekDate);
        dateList.add(endOfWeekDate);
        dateList.add(myDay);
        return dateList;
    }

    private boolean checkDateInWeekRange(Date startWeek, Date endWeek, Date myDate){
        return !myDate.before(startWeek) && !myDate.after(endWeek);
    }
}
