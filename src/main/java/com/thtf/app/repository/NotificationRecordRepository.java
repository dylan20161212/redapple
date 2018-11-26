package com.thtf.app.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.thtf.app.domain.NotificationRecord;


/**
 * Spring Data JPA repository for the NotificationRecord entity.
 */
@Repository
public interface NotificationRecordRepository extends BaseRepository<NotificationRecord> {
	
	List<NotificationRecord> findByUserLoginAndReadFlag(String userLogin, String readFlag);
	
	NotificationRecord findByUserLoginAndNotificationId(String userLogin, Long id);

	List<NotificationRecord> findByNotificationId(Long nid);
	
	void deleteByNotificationId(Long nid);
}
