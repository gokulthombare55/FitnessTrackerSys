package com.fitnessTrackerSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fitnessTrackerSystem.exceptionhandler.ResourceNotFoundException;
import com.fitnessTrackerSystem.model.User;
import com.fitnessTrackerSystem.model.WorkoutPlan;
import com.fitnessTrackerSystem.repository.UserRepository;
import com.fitnessTrackerSystem.repository.WorkoutPlanRepository;

public class WorkoutPlanServiceImpl  implements WorkoutplanService{
	
	@Autowired
	private WorkoutPlanRepository workoutPlanRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public WorkoutPlan createWorkoutPlan(WorkoutPlan workoutPlan, Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
		workoutPlan.setUser(user);
		return workoutPlanRepository.save(workoutPlan);
	}

	@Override
	public List<WorkoutPlan> getAllWorkoutPlans() {
		return workoutPlanRepository.findAll();
	}

	@Override
	public WorkoutPlan getWorkoutPlanById(Long id) {
		return workoutPlanRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Workout Plan not found with ID: " + id));
	}

	@Override
	public WorkoutPlan updateWorkoutPlan(Long id, WorkoutPlan updatedWorkoutPlan) {
		WorkoutPlan existingPlan = getWorkoutPlanById(id);
		existingPlan.setName(updatedWorkoutPlan.getName());
		existingPlan.setDescription(updatedWorkoutPlan.getDescription());
		existingPlan.setDurationInDays(updatedWorkoutPlan.getDurationInDays());
		return workoutPlanRepository.save(existingPlan);
	}

	@Override
	public void deleteWorkoutPlan(Long id) {
		WorkoutPlan workoutPlan = getWorkoutPlanById(id);
		workoutPlanRepository.delete(workoutPlan);
	}
}
