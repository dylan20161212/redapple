package com.thtf.deconfliction.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.thtf.deconfliction.domain.CaseProcessInfo;
import com.thtf.deconfliction.service.dto.CaseProcessInfoDTO;

/**
 * Mapper for the entity CaseProcessInfo and its DTO CaseProcessInfoDTO.
 */
@Mapper(componentModel = "spring", uses = {ConflictCaseMapper.class, UserExtendMapper.class})
public interface CaseProcessInfoMapper extends EntityMapper<CaseProcessInfoDTO, CaseProcessInfo> {

    @Mapping(source = "conflictCase.id", target = "conflictCaseId")
    @Mapping(source = "personalInfo.id", target = "personalInfoId")
    CaseProcessInfoDTO toDto(CaseProcessInfo caseProcessInfo);

    @Mapping(source = "conflictCaseId", target = "conflictCase")
    @Mapping(source = "personalInfoId", target = "personalInfo")
    CaseProcessInfo toEntity(CaseProcessInfoDTO caseProcessInfoDTO);

    default CaseProcessInfo fromId(Long id) {
        if (id == null) {
            return null;
        }
        CaseProcessInfo caseProcessInfo = new CaseProcessInfo();
        caseProcessInfo.setId(id);
        return caseProcessInfo;
    }
}
