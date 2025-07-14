package com.fitnessTrackerSystem.workoutplan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fitnessTrackerSystem.exceptionhandler.ResourceNotFoundException;
import com.fitnessTrackerSystem.model.User;
import com.fitnessTrackerSystem.model.WorkoutPlan;
import com.fitnessTrackerSystem.repository.UserRepository;
import com.fitnessTrackerSystem.repository.WorkoutPlanRepository;
import com.fitnessTrackerSystem.service.WorkoutplanService;

@ExtendWith(MockitoExtension.class)
public class WorkoutPlanServiceTest {
	@InjectMocks
	private WorkoutplanService workoutPlanService;

	@Mock
	private WorkoutPlanRepository workoutPlanRepository;

	@Mock
	private UserRepository userRepository;

	@Test
	void testCreateWorkoutPlan_Success() {

		User mockUser = new User();
		mockUser.setId(1L);

		WorkoutPlan mockPlan = new WorkoutPlan();
		mockPlan.setName("Test Plan");
		mockPlan.setDescription("Test Description");
		mockPlan.setDurationInDays(30);

		when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));
		when(workoutPlanRepository.save(any(WorkoutPlan.class))).thenReturn(mockPlan);

		WorkoutPlan createdPlan = workoutPlanService.createWorkoutPlan(mockPlan, 1L);

		assertNotNull(createdPlan);
		assertEquals("Test Plan", createdPlan.getName());

		verify(workoutPlanRepository, times(1)).save(any(WorkoutPlan.class));
	}

	@Test
	void testGetWorkoutPlanById_Found() {

		WorkoutPlan mockPlan = new WorkoutPlan();
		mockPlan.setId(1L);
		mockPlan.setName("Test Plan");

		when(workoutPlanRepository.findById(1L)).thenReturn(Optional.of(mockPlan));

		WorkoutPlan foundPlan = workoutPlanService.getWorkoutPlanById(1L);

		assertNotNull(foundPlan);
		assertEquals("Test Plan", foundPlan.getName());
	}

	@Test
	void testGetWorkoutPlanById_NotFound() {

		when(workoutPlanRepository.findById(1L)).thenReturn(Optional.empty());

		Exception exception = assertThrows(ResourceNotFoundException.class,
				() -> workoutPlanService.getWorkoutPlanById(1L));

		assertEquals("Workout Plan not found with ID: 1", exception.getMessage());
	}

	@Test
	void testUpdateWorkoutPlan_Success() {

		WorkoutPlan existingPlan = new WorkoutPlan();
		existingPlan.setId(1L);
		existingPlan.setName("Old Plan");

		WorkoutPlan updatedPlan = new WorkoutPlan();
		updatedPlan.setName("Updated Plan");

		when(workoutPlanRepository.findById(1L)).thenReturn(Optional.of(existingPlan));
		when(workoutPlanRepository.save(any(WorkoutPlan.class))).thenReturn(updatedPlan);

		WorkoutPlan result = workoutPlanService.updateWorkoutPlan(1L, updatedPlan);

		assertEquals("Updated Plan", result.getName());
		verify(workoutPlanRepository, times(1)).save(existingPlan);
	}

	@Test
	void testDeleteWorkoutPlan_Success() {

		WorkoutPlan mockPlan = new WorkoutPlan();
		mockPlan.setId(1L);

		when(workoutPlanRepository.findById(1L)).thenReturn(Optional.of(mockPlan));

		workoutPlanService.deleteWorkoutPlan(1L);

		verify(workoutPlanRepository, times(1)).delete(mockPlan);
	}
}
