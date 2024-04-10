package com.example.security20.controller;

import com.example.security20.dto.CustomUserDetails;
import com.example.security20.entity.Nutrition;
import com.example.security20.entity.NutritionPlan;
import com.example.security20.entity.WeekNutrition;
import com.example.security20.entity.WorkoutPlan;
import com.example.security20.service.NutritionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/api/v1")
public class NutritionController {
    @Autowired
    NutritionService nutritionService;

    @GetMapping("/deleteNutrition/{nutritionId}")
    public String deleteNutrition(@PathVariable("nutritionId") Long nutritionId){
        nutritionService.deleteById(nutritionId);
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
        Calendar myDate = new GregorianCalendar();
        String dateStr = nutrition.getDate().replace("T","  ");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd H:m", Locale.ENGLISH);
        Date date = format.parse(dateStr);
        myDate.setTime(date);
        int myDayOfWeek = myDate.get(Calendar.DAY_OF_WEEK);

        double calories =  Math.ceil((nutrition.getCalories() * nutrition.getWeight() / 100) * 100) / 100;
        double proteins =  Math.ceil((nutrition.getProteins() * nutrition.getWeight() / 100) * 100) / 100;
        double fats = Math.ceil((nutrition.getFats() * nutrition.getWeight() / 100) * 100) / 100;
        double carbohydrates = Math.ceil((nutrition.getCarbohydrates() * nutrition.getWeight() / 100) * 100) / 100;
        System.out.println(calories);
        WeekNutrition weekNutrition;
        WeekNutrition lastWeekNutrition = nutritionService.getLastWeekNutritionByUserId(userId);
        int countDaysOfWeek;
        if (lastWeekNutrition == null){
            weekNutrition = new WeekNutrition();
            addWeek(weekNutrition, userId, myDayOfWeek, 0, 1, calories, proteins, fats, carbohydrates);
        } else{
            if(lastWeekNutrition.getNumDayOfWeek() == 1 && myDayOfWeek == 2){
                weekNutrition = new WeekNutrition();
                countDaysOfWeek = 1;
                addWeek(weekNutrition, userId, myDayOfWeek, lastWeekNutrition.getNumOfWeek(), countDaysOfWeek,calories, proteins, fats, carbohydrates);

            }else {
                if (lastWeekNutrition.getNumDayOfWeek() == myDayOfWeek){
                    countDaysOfWeek = lastWeekNutrition.getCountDaysOfWeek();
                }else {
                    countDaysOfWeek = lastWeekNutrition.getCountDaysOfWeek() + 1;
                }
                nutritionService.updateWeekNutrition(userId, myDayOfWeek,
                        lastWeekNutrition.getNumOfWeek(),
                        countDaysOfWeek,
                        lastWeekNutrition.getSumCalories() + calories,
                        lastWeekNutrition.getSumProteins() + proteins,
                        lastWeekNutrition.getSumFats() + fats,
                        lastWeekNutrition.getSumCarbohydrates() + carbohydrates);
            }
        }
        WeekNutrition lastWeekNutrition1 = nutritionService.getLastWeekNutritionByUserId(userId);
        nutrition.setWeekId(lastWeekNutrition1.getId());
        nutrition.setUserId(userId);
        nutrition.setCalories(calories);
        nutrition.setProteins(proteins);
        nutrition.setFats(fats);
        nutrition.setCarbohydrates(carbohydrates);
        nutrition.setDate(dateStr);

        nutritionService.saveNutrition(nutrition);
        return "redirect:/api/v1/getNutritionPage";
    }
    private void addWeek(WeekNutrition weekNutrition, Long userId, int numDayOfWeek, int numOfWeek, int countDaysOfWeek, Double sumCalories, Double sumProteins, Double sumFats, Double sumCarbohydrates){
        weekNutrition.setNumOfWeek(numOfWeek + 1);
        weekNutrition.setNumDayOfWeek(numDayOfWeek);
        weekNutrition.setCountDaysOfWeek(countDaysOfWeek);
        weekNutrition.setUserId(userId);
        weekNutrition.setSumCalories(sumCalories);
        weekNutrition.setSumProteins(sumProteins);
        weekNutrition.setSumFats(sumFats);
        weekNutrition.setSumCarbohydrates(sumCarbohydrates);
        nutritionService.saveWeekNutrition(weekNutrition);
    }

    private void setNutritionPage(Model model, Long id){
        List<Nutrition> nutritionList = nutritionService.getNutritionByUserId(id);
        WeekNutrition lastWeekNutrition = nutritionService.getLastWeekNutritionByUserId(id);
        List<NutritionPlan> nutritionPlanList = nutritionService.getNutritionPlanByUserId(id);
        NutritionPlan nutritionPlan = nutritionService.getLastNutritionPlanByUserId(id);
        if (lastWeekNutrition != null){
            model.addAttribute("calories" , Math.round(lastWeekNutrition.getSumCalories()/lastWeekNutrition.getCountDaysOfWeek()));
            model.addAttribute("proteins" , Math.round(lastWeekNutrition.getSumProteins()/lastWeekNutrition.getCountDaysOfWeek()));
            model.addAttribute("fats" , Math.round(lastWeekNutrition.getSumFats()/lastWeekNutrition.getCountDaysOfWeek()));
            model.addAttribute("carbohydrates" , Math.round(lastWeekNutrition.getSumCarbohydrates()/lastWeekNutrition.getCountDaysOfWeek()));
            model.addAttribute("week", lastWeekNutrition.getNumOfWeek());
        }
        if (!nutritionPlanList.isEmpty()){
            model.addAttribute("nutritionPlanList", nutritionPlanList);
        }
        if (nutritionPlan != null){
            model.addAttribute("nutritionPlan", nutritionPlan);
        }
        model.addAttribute("nutritionList", nutritionList);
    }
}
