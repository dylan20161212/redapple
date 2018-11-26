package com.thtf.deconfliction.service.mapper;

import org.mapstruct.Mapper;

import com.thtf.deconfliction.domain.OrgCaseNumber;
import com.thtf.deconfliction.service.dto.OrgCaseNumberDTO;

/**
 * Mapper for the entity OrgCaseNumber and its DTO OrgCaseNumberDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OrgCaseNumberMapper extends EntityMapper<OrgCaseNumberDTO, OrgCaseNumber> {

    default OrgCaseNumber fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrgCaseNumber orgCaseNumber = new OrgCaseNumber();
        orgCaseNumber.setId(id);
        return orgCaseNumber;
    }
}
