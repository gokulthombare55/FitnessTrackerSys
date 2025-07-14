package com.fitnessTrackerSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="WORKOUTPLANS")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkoutPlan {
	@Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Workout plan name is required")
	private String name;
	
	@NotBlank(message = "Description is required")
	private String description;
	
	@Min(value = 1, message = "Duration must be at least 1 day")
	private int durationInDays;
	
	@ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
	private User user;
}
