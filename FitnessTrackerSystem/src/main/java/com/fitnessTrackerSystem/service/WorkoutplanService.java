package com.fitnessTrackerSystem.service;

import java.util.List;

import com.fitnessTrackerSystem.model.WorkoutPlan;

public interface WorkoutplanService {
	WorkoutPlan createWorkoutPlan(WorkoutPlan workoutPlan, Long userId);

	List<WorkoutPlan> getAllWorkoutPlans();

	WorkoutPlan getWorkoutPlanById(Long id);

	WorkoutPlan updateWorkoutPlan(Long id, WorkoutPlan updatedWorkoutPlan);

	void deleteWorkoutPlan(Long id);
}
