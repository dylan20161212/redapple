package com.thtf.app.service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.thtf.app.domain.Notification;
import com.thtf.app.domain.NotificationRecord;
import com.thtf.app.service.dto.NotificationRecordDTO;

/**
 * Mapper for the entity NotificationRecord and its DTO NotificationRecordDTO.
 */
@Mapper(componentModel = "spring", uses = { NotificationMapper.class })
public interface NotificationRecordMapper {

	// @Mapping(source = "notification.id", target = "notificationId")
	// @Mapping(target = "firstName", ignore = true)
	// NotificationRecordDTO toDto(NotificationRecord notificationRecord);

	// @Mapping(source = "notificationId", target = "notification")
	// NotificationRecord toEntity(NotificationRecordDTO notificationRecordDTO);

	default List<NotificationRecord> toEntity(List<NotificationRecordDTO> dtoList) {
		List<NotificationRecord> eList = new ArrayList<>();
		for (NotificationRecordDTO dto : dtoList) {
			eList.add(toEntity(dto));
		}
		return eList;
	}

	default List<NotificationRecordDTO> toDto(List<NotificationRecord> eList) {
		List<NotificationRecordDTO> dtoList = new ArrayList<>();
		for (NotificationRecord e : eList) {
			dtoList.add(toDto(e));
		}
		return dtoList;
	}

	@Mapping(source = "notification.id", target = "notificationId")
	@Mapping(target = "firstName", ignore = true)
	default NotificationRecordDTO toDto(NotificationRecord entity) {
		return new NotificationRecordDTO(entity, "");
	}

	@Mapping(source = "notificationId", target = "notification")
	default NotificationRecord toEntity(NotificationRecordDTO notificationRecordDTO) {
		NotificationRecord n = new NotificationRecord();
		n.setId(notificationRecordDTO.getId());
		n.setUserLogin(notificationRecordDTO.getUserLogin());
		n.setReadFlag(notificationRecordDTO.getReadFlag());
		n.setRecordRemarks(notificationRecordDTO.getRecordRemarks());
		n.setReadDate(notificationRecordDTO.getReadDate());
		if (notificationRecordDTO.getNotificationId() != null) {
			Notification nf = new Notification();
			nf.setId(notificationRecordDTO.getNotificationId());
			n.setNotification(nf);
		}

		return n;
	}

	default NotificationRecord fromId(Long id) {
		if (id == null) {
			return null;
		}
		NotificationRecord notificationRecord = new NotificationRecord();
		notificationRecord.setId(id);
		return notificationRecord;
	}
}
