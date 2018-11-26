package com.thtf.app.service.mapper;

import org.mapstruct.Mapper;

import com.thtf.app.domain.Attachment;
import com.thtf.app.service.dto.AttachmentDTO;

/**
 * Mapper for the entity Attachment and its DTO AttachmentDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AttachmentMapper extends EntityMapper<AttachmentDTO, Attachment> {



    default Attachment fromId(Long id) {
        if (id == null) {
            return null;
        }
        Attachment attachment = new Attachment();
        attachment.setId(id);
        return attachment;
    }
}
