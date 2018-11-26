package com.thtf.deconfliction.service.mapper;

import org.mapstruct.Mapper;

import com.thtf.deconfliction.domain.OfficeGroup;
import com.thtf.deconfliction.service.dto.OfficeGroupDTO;

/**
 * Mapper for the entity OfficeGroup and its DTO OfficeGroupDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OfficeGroupMapper extends EntityMapper<OfficeGroupDTO, OfficeGroup> {



    default OfficeGroup fromId(Long id) {
        if (id == null) {
            return null;
        }
        OfficeGroup officeGroup = new OfficeGroup();
        officeGroup.setId(id);
        return officeGroup;
    }
}
