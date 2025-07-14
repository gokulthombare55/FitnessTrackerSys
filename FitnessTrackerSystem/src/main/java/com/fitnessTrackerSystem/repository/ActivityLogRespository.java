package com.fitnessTrackerSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fitnessTrackerSystem.model.ActivityLog;
import com.fitnessTrackerSystem.model.User;

public interface ActivityLogRespository extends JpaRepository<ActivityLog, Long>{
	 List<ActivityLog> findByUser(User user);
}
