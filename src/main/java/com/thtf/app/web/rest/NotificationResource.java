package com.thtf.app.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
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
import com.thtf.app.security.SecurityUtils;
import com.thtf.app.service.NotificationService;
import com.thtf.app.service.dto.NotificationDTO;
import com.thtf.app.web.rest.errors.BadRequestAlertException;
import com.thtf.app.web.rest.errors.InternalServerErrorException;
import com.thtf.app.web.rest.util.HeaderUtil;
import com.thtf.app.web.rest.util.PaginationUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Notification.
 */
@RestController
@RequestMapping("/api")
public class NotificationResource {

	private final Logger log = LoggerFactory.getLogger(NotificationResource.class);

	private static final String ENTITY_NAME = "notification";

	private final NotificationService notificationService;

	public NotificationResource(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	/**
	 * POST /notifications : Create a new notification.
	 *
	 * @param notificationDTO
	 *            the notificationDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the
	 *         new notificationDTO, or with status 400 (Bad Request) if the
	 *         notification has already an ID
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PostMapping("/notifications")
	@Timed
	public ResponseEntity<NotificationDTO> createNotification(@Valid @RequestBody NotificationDTO notificationDTO)
			throws URISyntaxException {
		log.debug("REST request to save Notification : {}", notificationDTO);
		if (notificationDTO.getId() != null) {
			throw new BadRequestAlertException("A new notification cannot already have an ID", ENTITY_NAME, "idexists");
		}
		NotificationDTO result = notificationService.save(notificationDTO);
		return ResponseEntity.created(new URI("/api/notifications/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT /notifications : Updates an existing notification.
	 *
	 * @param notificationDTO
	 *            the notificationDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         notificationDTO, or with status 400 (Bad Request) if the
	 *         notificationDTO is not valid, or with status 500 (Internal Server
	 *         Error) if the notificationDTO couldn't be updated
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PutMapping("/notifications")
	@Timed
	public ResponseEntity<NotificationDTO> updateNotification(@Valid @RequestBody NotificationDTO notificationDTO)
			throws URISyntaxException {
		log.debug("REST request to update Notification : {}", notificationDTO);
		if (notificationDTO.getId() == null) {
			return createNotification(notificationDTO);
		}
		NotificationDTO result = notificationService.save(notificationDTO);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, notificationDTO.getId().toString()))
				.body(result);
	}

	/**
	 * GET /notifications : get all the notifications.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of
	 *         notifications in body
	 */
	@GetMapping("/notifications")
	@Timed
	public ResponseEntity<List<NotificationDTO>> getAllNotifications(Pageable pageable) {
		log.debug("REST request to get a page of Notifications");
		Page<NotificationDTO> page = notificationService.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/notifications");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	/**
	 * GET /notifications : get all the notifications.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of
	 *         notifications in body
	 */
	@GetMapping("/notificationsx")
	@Timed
	public ResponseEntity<Page<NotificationDTO>> getAllNotificationsx(@RequestParam Map<String, Object> params) {
		String login = SecurityUtils.getCurrentUserLogin()
				.orElseThrow(() -> new InternalServerErrorException("Current user login not found"));// 获取当前用户名
		String filterscount = (String) params.get("filterscount");
		params.put("filterscount", (Integer.parseInt(filterscount) + 1) + "");
		params.put("filtervalue" + filterscount, login);
		params.put("filtercondition" + filterscount, "EQUAL");
		params.put("filterdatafield" + filterscount, "createdBy");
		params.put("filteroperator" + filterscount, "0");
		Page<NotificationDTO> page = notificationService.findAll(params);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/notificationsx");
		return new ResponseEntity<>(page, headers, HttpStatus.OK);
	}

	/**
	 * GET /notifications/:id : get the "id" notification.
	 *
	 * @param id
	 *            the id of the notificationDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         notificationDTO, or with status 404 (Not Found)
	 */
	@GetMapping("/notifications/{id}")
	@Timed
	public ResponseEntity<NotificationDTO> getNotification(@PathVariable Long id) {
		log.debug("REST request to get Notification : {}", id);
		NotificationDTO notificationDTO = notificationService.findOne(id);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(notificationDTO));
	}

	/**
	 * DELETE /notifications/:id : delete the "id" notification.
	 *
	 * @param id
	 *            the id of the notificationDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/notifications/{id}")
	@Timed
	public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
		log.debug("REST request to delete Notification : {}", id);
		notificationService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}

	/**
	 * 读取公告
	 * 
	 * @return
	 */
	@GetMapping("/notifications/ofmine")
	@Timed
	public ResponseEntity<List<NotificationDTO>> getNotificationsOfMine() {
		final String userLogin = SecurityUtils.getCurrentUserLogin()
				.orElseThrow(() -> new InternalServerErrorException("Current user login not found"));
		List<NotificationDTO> listN = new ArrayList<>();
		HttpHeaders headers = new HttpHeaders();
		Long tempCout = 0L;
		listN.addAll(notificationService.getNotificationsOfMine(userLogin, "notify"));
		tempCout = listN.size() - tempCout;
		headers.add("notifycount", tempCout.toString());
		listN.addAll(notificationService.getNotificationsOfMine(userLogin, "proclamation"));
		tempCout = listN.size() - tempCout;
		headers.add("proclamationcount", tempCout.toString());
		return new ResponseEntity<>(listN, headers, HttpStatus.OK);
	}

	/**
	 * GET /notificationn : 获取通知
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of
	 *         notifications in body
	 */
	@GetMapping("/notificationsn")
	@Timed
	public ResponseEntity<Page<NotificationDTO>> getAllNotificationsn(@RequestParam Map<String, Object> params) {
		// log.debug("REST request to get a page of Roles");
		final String userLogin = SecurityUtils.getCurrentUserLogin()
				.orElseThrow(() -> new InternalServerErrorException("Current user login not found"));
		Page<NotificationDTO> page = notificationService.getNotificationsOfMine(params, userLogin, "notify");
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/notificationsn");
		return new ResponseEntity<>(page, headers, HttpStatus.OK);
	}

	/**
	 * GET /notificationn : 获取公告
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of
	 *         notifications in body
	 */
	@GetMapping("/notificationsp")
	@Timed
	public ResponseEntity<Page<NotificationDTO>> getAllNotificationsp(@RequestParam Map<String, Object> params) {
		// log.debug("REST request to get a page of Roles");
		final String userLogin = SecurityUtils.getCurrentUserLogin()
				.orElseThrow(() -> new InternalServerErrorException("Current user login not found"));
		Page<NotificationDTO> page = notificationService.getNotificationsOfMine(params, userLogin, "proclamation");
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/notificationsp");
		return new ResponseEntity<>(page, headers, HttpStatus.OK);
	}
	
	/**
	 * GET /notificationn : 获取已读通知和公告
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of
	 *         notifications in body
	 */
	@GetMapping("/notificationsr")
	@Timed
	public ResponseEntity<Page<NotificationDTO>> getAllNotificationsr(@RequestParam Map<String, Object> params) {
		// log.debug("REST request to get a page of Roles");
		final String userLogin = SecurityUtils.getCurrentUserLogin()
				.orElseThrow(() -> new InternalServerErrorException("Current user login not found"));
		Page<NotificationDTO> page = notificationService.getNotificationsOfMine(params, userLogin);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/notificationsp");
		return new ResponseEntity<>(page, headers, HttpStatus.OK);
	}
}
