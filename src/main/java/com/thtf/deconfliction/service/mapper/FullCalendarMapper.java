package com.thtf.deconfliction.service.mapper;

import org.mapstruct.Mapper;

import com.thtf.deconfliction.domain.FullCalendar;
import com.thtf.deconfliction.service.dto.FullCalendarDTO;

/**
 * Mapper for the entity FullCalendar and its DTO FullCalendarDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FullCalendarMapper extends EntityMapper<FullCalendarDTO, FullCalendar> {



    default FullCalendar fromId(Long id) {
        if (id == null) {
            return null;
        }
        FullCalendar fullCalendar = new FullCalendar();
        fullCalendar.setId(id);
        return fullCalendar;
    }
}
