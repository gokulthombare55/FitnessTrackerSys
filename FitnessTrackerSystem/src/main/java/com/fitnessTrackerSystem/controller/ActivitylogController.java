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

import com.fitnessTrackerSystem.model.ActivityLog;
import com.fitnessTrackerSystem.service.ActivitylogService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/activity-logs")
public class ActivitylogController {

	@Autowired
	private ActivitylogService activityLogService;

	@Operation(summary = "Create an activity log", description = "Requires USER role")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Activity log created successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid request data") })
	@PostMapping("/{userId}/{workoutPlanId}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<ActivityLog> createActivityLog(@PathVariable Long userId, @PathVariable Long workoutPlanId,
			@Valid @RequestBody ActivityLog activityLog) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(activityLogService.createActivityLog(userId, workoutPlanId, activityLog));
	}

	@Operation(summary = "Get all activity logs for a user", description = "Requires USER or ADMIN role")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Activity logs retrieved successfully"),
			@ApiResponse(responseCode = "404", description = "User not found") })
	@GetMapping("/user/{userId}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<List<ActivityLog>> getActivityLogsByUser(@PathVariable Long userId) {
		return ResponseEntity.ok(activityLogService.getActivityLogsByUser(userId));
	}

	@Operation(summary = "Get activity log by ID", description = "Requires USER or ADMIN role")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Activity log found"),
			@ApiResponse(responseCode = "404", description = "Activity log not found") })
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<ActivityLog> getActivityLogById(@PathVariable Long id) {
		return ResponseEntity.ok(activityLogService.getActivityLogById(id));
	}

	@Operation(summary = "Update an activity log", description = "Requires USER role")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Activity log updated successfully"),
			@ApiResponse(responseCode = "404", description = "Activity log not found") })
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<ActivityLog> updateActivityLog(@PathVariable Long id,
			@Valid @RequestBody ActivityLog activityLog) {
		return ResponseEntity.ok(activityLogService.updateActivityLog(id, activityLog));
	}

	@Operation(summary = "Delete an activity log", description = "Requires ADMIN role")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Activity log deleted successfully"),
			@ApiResponse(responseCode = "404", description = "Activity log not found") })
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> deleteActivityLog(@PathVariable Long id) {
		activityLogService.deleteActivityLog(id);
		return ResponseEntity.noContent().build();
	}
}
