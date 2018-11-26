package com.thtf.app.web.rest;

import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.thtf.app.domain.Notification;
import com.thtf.app.security.SecurityUtils;
import com.thtf.app.service.NotificationRecordService;
import com.thtf.app.service.dto.NotificationRecordDTO;
import com.thtf.app.web.rest.errors.BadRequestAlertException;
import com.thtf.app.web.rest.errors.InternalServerErrorException;
import com.thtf.app.web.rest.util.HeaderUtil;
import com.thtf.app.web.rest.util.PaginationUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing NotificationRecord.
 */
@RestController
@RequestMapping("/api")
public class NotificationRecordResource {

    private final Logger log = LoggerFactory.getLogger(NotificationRecordResource.class);

    private static final String ENTITY_NAME = "notificationRecord";

    private final NotificationRecordService notificationRecordService;
    
    public NotificationRecordResource(NotificationRecordService notificationRecordService) {
        this.notificationRecordService = notificationRecordService;
    }

    /**
     * POST  /notification-records : Create a new notificationRecord.
     *
     * @param notificationRecordDTO the notificationRecordDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new notificationRecordDTO, or with status 400 (Bad Request) if the notificationRecord has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/notification-records")
    @Timed
    public ResponseEntity<NotificationRecordDTO> createNotificationRecord(@Valid @RequestBody NotificationRecordDTO notificationRecordDTO) throws URISyntaxException {
        log.debug("REST request to save NotificationRecord : {}", notificationRecordDTO);
        if (notificationRecordDTO.getId() != null) {
            throw new BadRequestAlertException("A new notificationRecord cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NotificationRecordDTO result = notificationRecordService.save(notificationRecordDTO);
        return ResponseEntity.created(new URI("/api/notification-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    
    /**
     * POST  /notification-records : Create a new notificationRecord.
     *
     * @param notificationRecordDTO the notificationRecordDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new notificationRecordDTO, or with status 400 (Bad Request) if the notificationRecord has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     * @throws InstantiationException 
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     * @throws NoSuchMethodException 
     */
    @PostMapping("/notification-recordsx")
    @Timed
    public ResponseEntity<NotificationRecordDTO> createNotificationRecordx(@Valid @RequestBody NotificationRecordDTO notificationRecordDTO) 
    		throws URISyntaxException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        log.debug("REST request to save NotificationRecord : {}", notificationRecordDTO);
        if (notificationRecordDTO.getId() != null) {
            throw new BadRequestAlertException("A new notificationRecord cannot already have an ID", ENTITY_NAME, "idexists");
        }
        String userLogin = SecurityUtils.getCurrentUserLogin()
				.orElseThrow(() -> new InternalServerErrorException("Current user login not found"));// 获取当前用户名
        notificationRecordDTO.setUserLogin(userLogin);
        NotificationRecordDTO result = notificationRecordService.savex(notificationRecordDTO);
        return ResponseEntity.created(new URI("/api/notification-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /notification-records : Updates an existing notificationRecord.
     *
     * @param notificationRecordDTO the notificationRecordDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated notificationRecordDTO,
     * or with status 400 (Bad Request) if the notificationRecordDTO is not valid,
     * or with status 500 (Internal Server Error) if the notificationRecordDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/notification-records")
    @Timed
    public ResponseEntity<NotificationRecordDTO> updateNotificationRecord(@Valid @RequestBody NotificationRecordDTO notificationRecordDTO) throws URISyntaxException {
        log.debug("REST request to update NotificationRecord : {}", notificationRecordDTO);
        if (notificationRecordDTO.getId() == null) {
            return createNotificationRecord(notificationRecordDTO);
        }
        NotificationRecordDTO result = notificationRecordService.save(notificationRecordDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, notificationRecordDTO.getId().toString()))
            .body(result);
    }
    
    /**
     * PUT  /notification-records : Updates an existing notificationRecord.
     *
     * @param notificationRecordDTO the notificationRecordDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated notificationRecordDTO,
     * or with status 400 (Bad Request) if the notificationRecordDTO is not valid,
     * or with status 500 (Internal Server Error) if the notificationRecordDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     * @throws InstantiationException 
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     * @throws NoSuchMethodException 
     */
    @PutMapping("/notification-recordsx")
    @Timed
    public ResponseEntity<NotificationRecordDTO> updateNotificationRecordx(@Valid @RequestBody NotificationRecordDTO notificationRecordDTO) throws URISyntaxException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        log.debug("REST request to update NotificationRecord : {}", notificationRecordDTO);
        notificationRecordDTO.setReadDate(ZonedDateTime.now());
        if (notificationRecordDTO.getId() == null) {
            return createNotificationRecord(notificationRecordDTO);
        }
        NotificationRecordDTO result = notificationRecordService.savex(notificationRecordDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, notificationRecordDTO.getId().toString()))
            .body(result);
    }
    
    /**
     * GET  /notification-records : get all the notificationRecords.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of notificationRecords in body
     */
    @GetMapping("/notification-records")
    @Timed
    public ResponseEntity<List<NotificationRecordDTO>> getAllNotificationRecords(Pageable pageable) {
        Page<NotificationRecordDTO> page = notificationRecordService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/notification-records");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * GET  /notification-records : get all the notificationRecords.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of notificationRecords in body
     */
    @GetMapping("/notification-recordsx")
    @Timed
    public ResponseEntity<Page<NotificationRecordDTO>> getAllNotificationRecordsx(@RequestParam Map<String, Object> params) {
        log.debug("REST request to get a page of NotificationRecords");
		Page<NotificationRecordDTO> page = notificationRecordService.findAll(params);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/notification-recordsx");
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }
    
    /**
     * GET  /notification-records/:id : get the "notify_id" and userlogin notificationRecord.
     *
     * @param id the id of the notificationRecordDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the notificationRecordDTO, or with status 404 (Not Found)
     */
    @GetMapping("/notification-recordsy/{uid}")
    @Timed
    public List<NotificationRecordDTO> findRecordsByUId(@PathVariable Long uid,@RequestParam Map<String, Object> params) {
    	log.debug("REST request to get NotificationRecord : {}", uid);
    	String filterscount = (String) params.get("filterscount");
    	Notification notification = new Notification();
    	notification.setId(uid);
    	if(filterscount==null||"".equals(filterscount)){
    		filterscount="0";
    	}
		params.put("filterscount", (Integer.parseInt(filterscount)+1)+"");
		params.put("filtervalue"+filterscount, notification);
		params.put("filtercondition"+filterscount, "EQUAL");
		params.put("filterdatafield"+filterscount, "notification");
		params.put("filteroperator"+filterscount, "0");
    	return notificationRecordService.findAll(params).getContent();
    }
    
    /**
     * GET  /notification-records/:id : get the "otificationId" notificationRecord.
     *
     * @param id the id of the notificationRecordDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the notificationRecordDTO, or with status 404 (Not Found)
     * @throws InstantiationException 
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     * @throws NoSuchMethodException 
     */
    @GetMapping("/notification-recordsn/{notificationId}")
    @Timed
    public ResponseEntity<NotificationRecordDTO> getNotificationRecordByNidAndUserlogin(@PathVariable Long notificationId) 
    		throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        log.debug("REST request to get NotificationRecord : {}", notificationId);
        String userLogin = SecurityUtils.getCurrentUserLogin()
    			.orElseThrow(() -> new InternalServerErrorException("Current user login not found"));// 获取当前用户名
        NotificationRecordDTO notificationRecordDTO = notificationRecordService.findByUserLoginAndNotificationId(userLogin,notificationId);
        if(notificationRecordDTO == null){
        	notificationRecordDTO = new NotificationRecordDTO("0");
//        	notificationRecordDTO.setReadFlag("0");
        }
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(notificationRecordDTO));
    }
    
    /**
     * GET  /notification-records/:id : get the "id" notificationRecord.
     *
     * @param id the id of the notificationRecordDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the notificationRecordDTO, or with status 404 (Not Found)
     */
    @GetMapping("/notification-records/{id}")
    @Timed
    public ResponseEntity<NotificationRecordDTO> getNotificationRecord(@PathVariable Long id) {
        log.debug("REST request to get NotificationRecord : {}", id);
        NotificationRecordDTO notificationRecordDTO = notificationRecordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(notificationRecordDTO));
    }

    /**
     * DELETE  /notification-records/:id : delete the "id" notificationRecord.
     *
     * @param id the id of the notificationRecordDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/notification-records/{id}")
    @Timed
    public ResponseEntity<Void> deleteNotificationRecord(@PathVariable Long id) {
        log.debug("REST request to delete NotificationRecord : {}", id);
        notificationRecordService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
