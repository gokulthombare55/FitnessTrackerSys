package com.fitnessTrackerSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitnessTrackerSystem.model.WorkoutPlan;
import com.fitnessTrackerSystem.service.WorkoutplanService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/workout-plans")
public class WorkoutPlanController {

	@Autowired
	private WorkoutplanService workoutPlanService;

	@Operation(summary = "Create a new workout plan", description = "Requires USER or ADMIN role")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Workout plan created successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid request data") })
	@PostMapping("/{userId}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<WorkoutPlan> createWorkoutPlan(@PathVariable Long userId,
			@Valid @RequestBody WorkoutPlan workoutPlan) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(workoutPlanService.createWorkoutPlan(workoutPlan, userId));
	}

	@Operation(summary = "Get all workout plans", description = "Requires USER or ADMIN role")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Workout plans retrieved successfully") })
	@GetMapping
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<List<WorkoutPlan>> getAllWorkoutPlans() {
		return ResponseEntity.ok(workoutPlanService.getAllWorkoutPlans());
	}

	@Operation(summary = "Get workout plan by ID", description = "Requires USER or ADMIN role")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Workout plan found"),
			@ApiResponse(responseCode = "404", description = "Workout plan not found") })
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<WorkoutPlan> getWorkoutPlanById(@PathVariable Long id) {
		return ResponseEntity.ok(workoutPlanService.getWorkoutPlanById(id));
	}

	@Operation(summary = "Update a workout plan", description = "Requires USER or ADMIN role")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Workout plan updated successfully"),
			@ApiResponse(responseCode = "404", description = "Workout plan not found") })
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<WorkoutPlan> updateWorkoutPlan(@PathVariable Long id,
			@Valid @RequestBody WorkoutPlan workoutPlan) {
		return ResponseEntity.ok(workoutPlanService.updateWorkoutPlan(id, workoutPlan));
	}

	@Operation(summary = "Delete a workout plan", description = "Requires ADMIN role")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Workout plan deleted successfully"),
			@ApiResponse(responseCode = "404", description = "Workout plan not found") })
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> deleteWorkoutPlan(@PathVariable Long id) {
		workoutPlanService.deleteWorkoutPlan(id);
		return ResponseEntity.noContent().build();
	}
}
