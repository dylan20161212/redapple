package com.thtf.app.service.mapper;

import org.mapstruct.Mapper;

import com.thtf.app.domain.Notification;
import com.thtf.app.service.dto.NotificationDTO;

/**
 * Mapper for the entity Notification and its DTO NotificationDTO.
 */
@Mapper(componentModel = "spring", uses = {NotificationRecordMapper.class})
public interface NotificationMapper extends EntityMapper<NotificationDTO, Notification> {



    default Notification fromId(Long id) {
        if (id == null) {
            return null;
        }
        Notification notification = new Notification();
        notification.setId(id);
        return notification;
    }
}
