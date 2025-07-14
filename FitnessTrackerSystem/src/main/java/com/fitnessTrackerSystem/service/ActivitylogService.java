package com.fitnessTrackerSystem.service;

import java.util.List;

import com.fitnessTrackerSystem.model.ActivityLog;

public interface ActivitylogService {
	
	ActivityLog createActivityLog(Long userId, Long workoutPlanId, ActivityLog activityLog);

	List<ActivityLog> getActivityLogsByUser(Long userId);

	ActivityLog getActivityLogById(Long id);

	ActivityLog updateActivityLog(Long id, ActivityLog activityLog);

	void deleteActivityLog(Long id);
}
