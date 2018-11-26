package com.thtf.app.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thtf.app.service.dto.NotificationRecordDTO;

/**
 * Service Interface for managing NotificationRecord.
 */
public interface NotificationRecordService {

    /**
     * Save a notificationRecord.
     *
     * @param notificationRecordDTO the entity to save
     * @return the persisted entity
     */
    NotificationRecordDTO save(NotificationRecordDTO notificationRecordDTO);

    /**
     * Get all the notificationRecords.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<NotificationRecordDTO> findAll(Pageable pageable);

    /**
     * Get the "id" notificationRecord.
     *
     * @param id the id of the entity
     * @return the entity
     */
    NotificationRecordDTO findOne(Long id);

    /**
     * Delete the "id" notificationRecord.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    
    Page<NotificationRecordDTO> findAll(Map<String, Object> params);
    
    NotificationRecordDTO findByUserLoginAndNotificationId(String userLogin, Long id) 
    		throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;

	List<NotificationRecordDTO> findByNotificationId(Long nid);

	NotificationRecordDTO savex(NotificationRecordDTO notificationRecordDTO) 
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;
    
}
