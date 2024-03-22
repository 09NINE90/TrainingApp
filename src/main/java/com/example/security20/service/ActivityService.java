package com.example.security20.service;

import com.example.security20.entity.Activity;

import java.util.List;

public interface ActivityService {
    public List<Activity> getActivityByUserId(Long userId);

    public void saveActivity(Activity activity);

    public void deleteById(Long activityId);
}
