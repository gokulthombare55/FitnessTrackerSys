package com.fitnessTrackerSystem.service;

import com.fitnessTrackerSystem.model.User;

public interface UserService {
	User createUser(User user);

	User getUserById(Long id);

	User getUserByEmail(String email);
}
