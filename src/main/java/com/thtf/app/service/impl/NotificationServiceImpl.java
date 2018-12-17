package com.thtf.app.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thtf.app.domain.Attachment;
import com.thtf.app.domain.Notification;
import com.thtf.app.domain.NotificationRecord;
import com.thtf.app.domain.User;
import com.thtf.app.domain.UserRoleOrganization;
import com.thtf.app.repository.AttachmentRepository;
import com.thtf.app.repository.NotificationRecordRepository;
import com.thtf.app.repository.NotificationRepository;
import com.thtf.app.repository.UserRepository;
import com.thtf.app.repository.UserRoleOrganizationRepository;
import com.thtf.app.security.SecurityUtils;
import com.thtf.app.service.NotificationService;
import com.thtf.app.service.dto.NotificationDTO;
import com.thtf.app.service.dto.NotificationRecordDTO;
import com.thtf.app.service.mapper.NotificationMapper;
import com.thtf.app.service.util.DtoTransferTool;
import com.thtf.app.web.rest.errors.InternalServerErrorException;
import com.thtf.app.web.rest.util.PaginationUtil;

/**
 * Service Implementation for managing Notification.
 */
@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

	private final Logger log = LoggerFactory.getLogger(NotificationServiceImpl.class);

	private final NotificationRecordRepository notificationRecordRepository;
	private final NotificationRepository notificationRepository;
	private final NotificationMapper notificationMapper;
	private final UserRepository userRepository;
	private final UserRoleOrganizationRepository userRoleOrganizationRepository;
	private final AttachmentRepository attachmentRepository;

	public NotificationServiceImpl(NotificationRepository notificationRepository, UserRepository userRepository,
			NotificationRecordRepository notificationRecordRepository, NotificationMapper notificationMapper,
			UserRoleOrganizationRepository userRoleOrganizationRepository, AttachmentRepository attachmentRepository) {
		this.notificationRepository = notificationRepository;
		this.notificationRecordRepository = notificationRecordRepository;
		this.notificationMapper = notificationMapper;
		this.userRepository = userRepository;
		this.userRoleOrganizationRepository = userRoleOrganizationRepository;
		this.attachmentRepository = attachmentRepository;
	}

	/**
	 * Save a notification.
	 *
	 * @param notificationDTO
	 *            the entity to save
	 * @return the persisted entity
	 */
	@Override
	@Transactional
	public NotificationDTO save(NotificationDTO notificationDTO) {
		log.debug("Request to save Notification : {}", notificationDTO);
		Notification notification = notificationMapper.toEntity(notificationDTO);

		Set<NotificationRecord> records = notification.getNotificationRecords();

		if (notification.getId() != null && !records.isEmpty()) {
			this.notificationRecordRepository.deleteByNotificationId(notification.getId());
		}
		String createBy = SecurityUtils.getCurrentUserLogin()
				.orElseThrow(() -> new InternalServerErrorException("Current user login not found"));
		String filesString = notification.getNotifyFiles();
		notification.setCreatedBy(createBy);
		notification.setNotifyFiles("[]");
		notification = notificationRepository.save(notification);
		if (!filesString.equals("[]")) {
			try {
				JSONArray filesArray = new JSONArray(filesString);
				List<Long> ids = new ArrayList<>();
				for (int i = 0; i < filesArray.length(); i++) {
					JSONObject file = filesArray.getJSONObject(i);
					ids.add(file.getLong("id"));
				}
				attachmentRepository.updateByIdNotIn(ids, "notificationtypes", notification.getId(), 0);
				attachmentRepository.updateByIdIn(ids, "notificationtypes", notification.getId());
			} catch (JSONException e) {
				log.error(e.getMessage());
			}
		} else {
			attachmentRepository.updateByRelatedId("notificationtypes", notification.getId(), 0);
		}
		if ("notify".equals(notification.getNotifyType()) && !records.isEmpty()) {
			for (NotificationRecord nr : records) {
				nr.setNotification(notification);
			}
			this.notificationRecordRepository.saveAll(records);
			notification.setNotificationRecords(records);
		}

		return notificationMapper.toDto(notification);
	}

	/**
	 * Get one notification by id.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public NotificationDTO findOne(Long id) {
		log.debug("Request to get Notification : {}", id);
		Notification notification = notificationRepository.findById(id).orElse(null);
		// String notifyFilesStr = getFiles(notification.getId());
		// notification.setNotifyFiles(notifyFilesStr);
		Set<NotificationRecord> rNotificationRecord = notification.getNotificationRecords();
		List<User> userLoginNames = userRepository.findByLoginIn(
				rNotificationRecord.stream().map(NotificationRecord::getUserLogin).collect(Collectors.toList()));
		Set<NotificationRecordDTO> rNotificationRecordDTO = new HashSet<>();
		for (NotificationRecord nr : rNotificationRecord) {
			rNotificationRecordDTO.add(new NotificationRecordDTO(nr,
					userLoginNames.stream().filter(user -> user.getLogin().equals(nr.getUserLogin()))
							.map(User::getRealName).findFirst().orElse("none")));

		}
		// 通知的Record对应的login，组成数组 listLogin
		// userRepository.findByLoginIn(listLogin) => userList
		// userList userName 放到 notificationRecordDTO
		//
		// List<Attachment> attachments =
		// attachmentRepository.findByRelatedIdAndFileTypeAndFileSize(id,
		// "notificationtypes", 0);
		// String notifyFilesStr = "";
		// if (attachments.size() > 0) {
		// JSONArray jsonArray = new JSONArray();
		// try {
		// for (int i = 0; i < attachments.size(); i++) {
		// JSONObject obj = new JSONObject();
		// obj.put("id", attachments.get(i).getId());
		// obj.put("file", attachments.get(i).getFileName());
		// obj.put("size", attachments.get(i).getFileSize());
		// jsonArray.put(i, obj);
		// }
		// } catch (JSONException e) {
		// log.error(e.getMessage(), e);
		// }
		// notifyFilesStr = jsonArray.toString();
		// notification.setNotifyFiles(notifyFilesStr);
		// } else {
		// notification.setNotifyFiles("[]");
		// }
		// return notificationMapper.toDto(notification);
		return new NotificationDTO(notification.getId(), notification.getStartDate(), notification.getEndDate(),
				notification.getNotifyType(), notification.getNotifyTitle(), notification.getNotifyContent(),
				notification.getNotifyStatus(), notification.getNotifyScope(), getFiles(notification.getId()),
				notification.getCreatedDate(), notification.getLastModifiedDate(), rNotificationRecordDTO);
	}

	/*
	 * Get all the notifications.
	 *
	 * @param pageable the pagination information
	 * 
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<NotificationDTO> findAll(Pageable pageable) {
		return notificationRepository.findAll(pageable).map(notificationMapper::toDto);
		// return notificationRepository.findAll(pageable).map(n->new
		// NotificationDTO(n.getId(), n.getStartDate(), n.getEndDate(),
		// n.getNotifyType(), n.getNotifyTitle(), n.getNotifyContent(),
		// n.getNotifyStatus(), n.getNotifyScope(), n.getNotifyFiles(),
		// n.getCreatedDate(), n.getLastModifiedDate(), null));
	}

	/**
	 * Delete the notification by id.
	 *
	 * @param id
	 *            the id of the entity
	 */
	@Transactional
	@Override
	public int delete(Long id) {
		// log.debug("Request to delete Notification : {}", id);
		notificationRecordRepository.deleteByNotificationId(id);
		return notificationRepository.deleteNotification(id, "草稿");
	}

	@Override
	@Transactional(readOnly = true)
	public Page<NotificationDTO> findAll(Map<String, Object> filters) {
		log.debug("Request to get all Notifications");
		// return new
		// PageImpl<NotificationDTO>(notificationMapper.toDto(notificationRepository.findAll(filters)),
		// null,
		// notificationRepository.getTotalRows(filters));
		List<Notification> nlist = notificationRepository.findAllNative(filters);
		List<NotificationDTO> nDTOlist = new ArrayList<>();
		for (Notification n : nlist) {
			// n.setNotifyFiles(getFiles(n.getId()));
			nDTOlist.add(new NotificationDTO(n.getId(), n.getStartDate(), n.getEndDate(), n.getNotifyType(),
					n.getNotifyTitle(), n.getNotifyContent(), n.getNotifyStatus(), n.getNotifyScope(),
					getFiles(n.getId()), n.getCreatedDate(), n.getLastModifiedDate(), null));
		}
		return new PageImpl<>(nDTOlist, PaginationUtil.getDefaultPageable(), notificationRepository.getRows(filters));
	}

	private String getFiles(Long nid) {
		List<Attachment> lattachment = attachmentRepository.findByRelatedIdAndFileTypeAndFileSize(nid,
				"notificationtypes", 0);
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
	public List<NotificationDTO> getNotificationsOfMine(String loginUser, String notifyType) {
		if ("notify".equals(notifyType)) {
			return DtoTransferTool.toDto(notificationRepository.readMyNotification(loginUser), NotificationDTO.class,
					"notificationRecords");
		} else if ("proclamation".equals(notifyType)) {
			// 已经读过的公告
			List<Notification> listProclamation = notificationRepository.readProclamation("proclamation", loginUser);
			List<Long> pIds = new ArrayList<>();
			if (listProclamation != null && !listProclamation.isEmpty()) {
				listProclamation.forEach(p -> pIds.add(p.getId()));
			} else {
				pIds.add(-1L);
			}
			String orgId = getMyOrgId(loginUser);
			return DtoTransferTool.toDto(notificationRepository.findProclamation(pIds, orgId), NotificationDTO.class,
					"notificationRecords");
		}
		return new ArrayList<>();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<NotificationDTO> getNotificationsOfMine(Map<String, Object> filters, String loginUser,
			String notifyType) {
		if ("notify".equals(notifyType)) {

			final List<BigInteger> theIds = notificationRepository.getNotificationId(notifyType, "0", loginUser);
			final List<Long> nIds = new ArrayList<>();
			for (BigInteger bigIntId : theIds) {
				nIds.add(bigIntId.longValue());
			}
			if (nIds.isEmpty()) {
				nIds.add(-1L);
			}
			// 开始设置过滤条件
			String filterscount = (String) filters.get("filterscount");
			if (filterscount == null || "".equals(filterscount)) {
				filterscount = "0";
			}
			// IN
			filters.put("filterscount", (Integer.parseInt(filterscount) + 1) + "");
			filters.put("filtervalue" + filterscount, nIds);
			filters.put("filtercondition" + filterscount, "IN");
			filters.put("filterdatafield" + filterscount, "id");
			filters.put("filteroperator" + filterscount, "0");
			// EQUAL
			filterscount = (Integer.parseInt(filterscount) + 1) + "";
			filters.put("filterscount", (Integer.parseInt(filterscount) + 1) + "");
			filters.put("filtervalue" + filterscount, "发布");
			filters.put("filtercondition" + filterscount, "EQUAL");
			filters.put("filterdatafield" + filterscount, "notifyStatus");
			filters.put("filteroperator" + filterscount, "0");
		} else if ("proclamation".equals(notifyType)) {
			// 已经读过的公告
			final List<BigInteger> theIds = notificationRepository.getNotificationId(notifyType, "1", loginUser);
			final List<Long> pIds = new ArrayList<>();// =
			for (BigInteger bigIntId : theIds) {
				pIds.add(bigIntId.longValue());
			}
			if (pIds.isEmpty()) {
				pIds.add(-1L);
			}
			String orgId = getMyOrgId(loginUser);
			String filterscount = (String) filters.get("filterscount");
			if (filterscount == null || "".equals(filterscount)) {
				filterscount = "0";
			}
			// NULL
			filters.put("filterscount", (Integer.parseInt(filterscount) + 1) + "");
			filters.put("filtervalue" + filterscount, pIds);
			filters.put("filtercondition" + filterscount, "NI");
			filters.put("filterdatafield" + filterscount, "id");
			filters.put("filteroperator" + filterscount, "0");
			// CONTAINS
			filterscount = (Integer.parseInt(filterscount) + 1) + "";
			filters.put("filterscount", (Integer.parseInt(filterscount) + 1) + "");
			filters.put("filtervalue" + filterscount, orgId);
			filters.put("filtercondition" + filterscount, "CONTAINS");
			filters.put("filterdatafield" + filterscount, "notifyScope");
			filters.put("filteroperator" + filterscount, "1");
			// NOT IN
			filterscount = (Integer.parseInt(filterscount) + 1) + "";
			filters.put("filterscount", (Integer.parseInt(filterscount) + 1) + "");
			filters.put("filtervalue" + filterscount, "NULL");
			filters.put("filtercondition" + filterscount, "NULL");
			filters.put("filterdatafield" + filterscount, "notifyScope");
			filters.put("filteroperator" + filterscount, "1");
			// EQUAL
			filterscount = (Integer.parseInt(filterscount) + 1) + "";
			filters.put("filterscount", (Integer.parseInt(filterscount) + 1) + "");
			filters.put("filtervalue" + filterscount, "proclamation");
			filters.put("filtercondition" + filterscount, "EQUAL");
			filters.put("filterdatafield" + filterscount, "notifyType");
			filters.put("filteroperator" + filterscount, "0");
			// EQUAL
			filterscount = (Integer.parseInt(filterscount) + 1) + "";
			filters.put("filterscount", (Integer.parseInt(filterscount) + 1) + "");
			filters.put("filtervalue" + filterscount, "发布");
			filters.put("filtercondition" + filterscount, "EQUAL");
			filters.put("filterdatafield" + filterscount, "notifyStatus");
			filters.put("filteroperator" + filterscount, "0");
		}
		List<Notification> nlist = notificationRepository.findAll(filters);
		for (Notification n : nlist) {
			n.setNotifyFiles(getFiles(n.getId()));
		}
		return new PageImpl<>(DtoTransferTool.toDto(nlist, NotificationDTO.class, "notificationRecords"), PaginationUtil.getDefaultPageable(),
				notificationRepository.getTotalRows(filters));
	}

	@Override
	@Transactional(readOnly = true)
	public Page<NotificationDTO> getNotificationsOfMine(Map<String, Object> filters, String loginUser) {
		// get read NotificationId
		final List<BigInteger> theIds = notificationRepository.getNotificationId("1", loginUser);
		final List<Long> rIds = new ArrayList<>();
		for (BigInteger bigIntId : theIds) {
			rIds.add(bigIntId.longValue());
		}
		if (rIds.isEmpty()) {
			rIds.add(-1L);
		}

		// 开始设置过滤条件
		String filterscount = (String) filters.get("filterscount");
		if (filterscount == null || "".equals(filterscount)) {
			filterscount = "0";
		}
		// IN
		filters.put("filterscount", (Integer.parseInt(filterscount) + 1) + "");
		filters.put("filtervalue" + filterscount, rIds);
		filters.put("filtercondition" + filterscount, "IN");
		filters.put("filterdatafield" + filterscount, "id");
		filters.put("filteroperator" + filterscount, "0");
		// EQUAL
		filterscount = (Integer.parseInt(filterscount) + 1) + "";
		filters.put("filterscount", (Integer.parseInt(filterscount) + 1) + "");
		filters.put("filtervalue" + filterscount, "发布");
		filters.put("filtercondition" + filterscount, "EQUAL");
		filters.put("filterdatafield" + filterscount, "notifyStatus");
		filters.put("filteroperator" + filterscount, "0");

		List<Notification> nlist = notificationRepository.findAll(filters);
		for (Notification n : nlist) {
			n.setNotifyFiles(getFiles(n.getId()));
		}
		return new PageImpl<>(DtoTransferTool.toDto(nlist, NotificationDTO.class, "notificationRecords"), null,
				notificationRepository.getTotalRows(filters));
	}

	private String getMyOrgId(String loginUser) {
		Optional<User> user = this.userRepository.findOneByLogin(loginUser);
		String orgId = "-1";
		if (user.isPresent()) {
			if (user.get().getSelOrgRoleId() != null) {
				UserRoleOrganization tempOr = userRoleOrganizationRepository.findById(user.get().getSelOrgRoleId()).orElse(null);
				if (tempOr != null) {
					if (tempOr.getOrganization() != null) {
						orgId = "%\"" + tempOr.getOrganization().getId().toString() + "\"%";
					}
				} else if (user.get().getOrganization() != null) {
					orgId = "%\"" + user.get().getOrganization().getId().toString() + "\"%";
				}
			} else if (user.get().getOrganization() != null) {
				orgId = "%\"" + user.get().getOrganization().getId().toString() + "\"%";
			}
		}
		return orgId;
	}
}
