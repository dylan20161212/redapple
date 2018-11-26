package com.thtf.app.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thtf.app.domain.Attachment;
import com.thtf.app.domain.Notification;
import com.thtf.app.domain.NotificationRecord;
import com.thtf.app.repository.AttachmentRepository;
import com.thtf.app.repository.NotificationRecordRepository;
import com.thtf.app.service.NotificationRecordService;
import com.thtf.app.service.dto.NotificationRecordDTO;
import com.thtf.app.service.mapper.NotificationRecordMapper;
import com.thtf.app.service.util.DtoTransferTool;


/**
 * Service Implementation for managing NotificationRecord.
 */
@Service
@Transactional
public class NotificationRecordServiceImpl implements NotificationRecordService {

    private final Logger log = LoggerFactory.getLogger(NotificationRecordServiceImpl.class);

    private final NotificationRecordRepository notificationRecordRepository;
    
    private final NotificationRecordMapper notificationRecordMapper;
    
    private final AttachmentRepository attachmentRepository;
    
    public NotificationRecordServiceImpl(NotificationRecordRepository notificationRecordRepository,
    		NotificationRecordMapper notificationRecordMapper, AttachmentRepository attachmentRepository) {
        this.notificationRecordRepository = notificationRecordRepository;
        this.notificationRecordMapper = notificationRecordMapper;
        this.attachmentRepository = attachmentRepository;
    }

    /**
     * Save a notificationRecord.
     *
     * @param notificationRecordDTO the entity to save
     * @return the persisted entity
     */
    @Override
    @Transactional
    public NotificationRecordDTO save(NotificationRecordDTO notificationRecordDTO) {
        log.debug("Request to save NotificationRecord : {}", notificationRecordDTO);
        NotificationRecord notificationRecord = notificationRecordMapper.toEntity(notificationRecordDTO);
        notificationRecord = notificationRecordRepository.save(notificationRecord);
        return notificationRecordMapper.toDto(notificationRecord);
    }
    
    /**
     * Save a notificationRecord.
     *
     * @param notificationRecordDTO the entity to save
     * @return the persisted entity
     * @throws InstantiationException 
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     * @throws NoSuchMethodException 
     */
    @Override
    @Transactional
    public NotificationRecordDTO savex(NotificationRecordDTO notificationRecordDTO) 
    		throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        log.debug("Request to save NotificationRecord : {}", notificationRecordDTO);
        NotificationRecord notificationRecord = notificationRecordMapper.toEntity(notificationRecordDTO);
        notificationRecord = notificationRecordRepository.save(notificationRecord);
        return DtoTransferTool.toDto(notificationRecord,NotificationRecordDTO.class);
    }


    /**
     * Get all the notificationRecords.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<NotificationRecordDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NotificationRecords");
        return notificationRecordRepository.findAll(pageable)
            .map(notificationRecordMapper::toDto);
    }

    /**
     * Get one notificationRecord by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public NotificationRecordDTO findOne(Long id) {
        log.debug("Request to get NotificationRecord : {}", id);
        NotificationRecord notificationRecord = notificationRecordRepository.findById(id).orElse(null);
        return notificationRecordMapper.toDto(notificationRecord);
    }

    /**
     * Delete the notificationRecord by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete NotificationRecord : {}", id);
        notificationRecordRepository.deleteById(id);
    }

	@Override
	@Transactional(readOnly = true)
	public Page<NotificationRecordDTO> findAll(Map<String, Object> filters) {
        log.debug("Request to get all NotificationRecords");
        return new PageImpl<>(DtoTransferTool.toDto(notificationRecordRepository.findAll(filters), NotificationRecordDTO.class,
				"notification"), null, notificationRecordRepository.getTotalRows(filters));
	}

	@Override
	@Transactional(readOnly = true)
	public NotificationRecordDTO findByUserLoginAndNotificationId(String userLogin, Long nid) {
		NotificationRecord nr = notificationRecordRepository.findByUserLoginAndNotificationId(userLogin,nid);
		Notification n = new Notification();
		n.setNotifyFiles(getFiles(nid));
		if(nr == null) {
			nr = new NotificationRecord();
		}
		nr.setNotification(n);
		return notificationRecordMapper.toDto(nr);
	}
	
	private String getFiles(Long nid){
		List<Attachment> lattachment = attachmentRepository.findByRelatedIdAndFileTypeAndFileSize(nid, "notificationtypes", 0);
		String notifyFilesStr = "";
		if (lattachment.size() > 0) {
			JSONArray jsonArray = new JSONArray();
			try {
				for (int i = 0; i < lattachment.size(); i++) {
					JSONObject obj = new JSONObject();
					obj.put("id", lattachment.get(i).getId());
					obj.put("file", lattachment.get(i).getFileName());
					obj.put("size", lattachment.get(i).getFileSize());
					jsonArray.put(i, obj);
				}
			} catch (JSONException e) {
				log.error(e.getMessage(), e);
			}
			notifyFilesStr = jsonArray.toString();
		} else {
			notifyFilesStr = "[]";
		}
		return notifyFilesStr;
	}
	
	@Override
	public List<NotificationRecordDTO> findByNotificationId(Long nid) {
		return notificationRecordMapper.toDto(notificationRecordRepository.findByNotificationId(nid));
	}

	
	
	
}
