package com.example.security20.service.impl;

import com.example.security20.entity.Activity;
import com.example.security20.repository.ActivityRepository;
import com.example.security20.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    ActivityRepository activityRepository;

    @Override
    public List<Activity> getActivityByUserId(Long userId) {
        return activityRepository.getActivityByUserId(userId);
    }

    @Override
    public void saveActivity(Activity activity) {
        activityRepository.save(activity);
    }

    @Override
    public void deleteById(Long activityId) {
        activityRepository.deleteById(activityId);
    }
}
