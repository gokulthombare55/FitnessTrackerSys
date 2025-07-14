package com.fitnessTrackerSystem.activitylog;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fitnessTrackerSystem.model.ActivityLog;
import com.fitnessTrackerSystem.repository.ActivityLogRespository;
import com.fitnessTrackerSystem.service.ActivitylogService;

@ExtendWith(MockitoExtension.class)
public class ActivitylogServiceTest {
	@Mock
	private ActivityLogRespository activityLogRepository;

	@InjectMocks
	private ActivitylogService activityLogService;

	private ActivityLog activityLog;

	@Test
	void testCreateActivityLog() {
		when(activityLogRepository.save(any(ActivityLog.class))).thenReturn(activityLog);

		ActivityLog savedActivityLog = activityLogService.createActivityLog(101L, 201L, activityLog);

		assertNotNull(savedActivityLog);
		assertEquals(activityLog.getId(), savedActivityLog.getId());
		verify(activityLogRepository, times(1)).save(activityLog);
	}

	@Test
	void testGetActivityLogById() {
		when(activityLogRepository.findById(1L)).thenReturn(Optional.of(activityLog));

		ActivityLog foundActivityLog = activityLogService.getActivityLogById(1L);

		assertNotNull(foundActivityLog);
		assertEquals(activityLog.getId(), foundActivityLog.getId());
		verify(activityLogRepository, times(1)).findById(1L);
	}

	@Test
	void testDeleteActivityLog() {
		when(activityLogRepository.existsById(1L)).thenReturn(true);
		doNothing().when(activityLogRepository).deleteById(1L);

		activityLogService.deleteActivityLog(1L);

		verify(activityLogRepository, times(1)).existsById(1L);
		verify(activityLogRepository, times(1)).deleteById(1L);
	}
}
