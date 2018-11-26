package com.thtf.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thtf.app.service.dto.NotificationDTO;

/**
 * Service Interface for managing Notification.
 */
public interface NotificationService {

	/**
	 * Save a notification.
	 *
	 * @param notificationDTO
	 *            the entity to save
	 * @return the persisted entity
	 */
	NotificationDTO save(NotificationDTO notificationDTO);

	/**
	 * Get all the notifications.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */
	Page<NotificationDTO> findAll(Pageable pageable);

	/**
	 * 刘丽开发的通知公告功能
	 * 
	 * @param filters
	 * @return
	 */
	Page<NotificationDTO> findAll(Map<String, Object> filters);

	/**
	 * Get the "id" notification.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 */
	NotificationDTO findOne(Long id);

	/**
	 * Delete the "id" notification.
	 *
	 * @param id
	 *            the id of the entity
	 */
	int delete(Long id);

	/**
	 * 读取通知和公告
	 * 
	 * @param loginUser
	 * @return
	 */
	List<NotificationDTO> getNotificationsOfMine(String loginUser, String notifyType);

	/**
	 * 读取通知和公告
	 * 
	 * @param loginUser
	 * @return
	 */
	Page<NotificationDTO> getNotificationsOfMine(Map<String, Object> filters, String loginUser, String notifyType);
	
	/**
	 * 读取已读通知和公告
	 * 
	 * @param loginUser
	 * @return
	 */
	Page<NotificationDTO> getNotificationsOfMine(Map<String, Object> filters, String loginUser);
}
