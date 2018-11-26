package com.thtf.deconfliction.service.mapper;

import org.mapstruct.Mapper;

import com.thtf.deconfliction.domain.FilingUserExtend;
import com.thtf.deconfliction.service.dto.FilingUserExtendDTO;

/**
 * Mapper for the entity FilingUserExtend and its DTO FilingUserExtendDTO.
 */
@Mapper(componentModel = "spring", uses = {FillingConflictCaseMapper.class, FillingCaseProcessInfoMapper.class})
public interface FilingUserExtendMapper extends EntityMapper<FilingUserExtendDTO, FilingUserExtend> {



    default FilingUserExtend fromId(Long id) {
        if (id == null) {
            return null;
        }
        FilingUserExtend filingUserExtend = new FilingUserExtend();
        filingUserExtend.setId(id);
        return filingUserExtend;
    }
}
