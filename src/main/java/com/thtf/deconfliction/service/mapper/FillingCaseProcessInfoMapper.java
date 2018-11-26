package com.thtf.deconfliction.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.thtf.deconfliction.domain.FillingCaseProcessInfo;
import com.thtf.deconfliction.service.dto.FillingCaseProcessInfoDTO;

/**
 * Mapper for the entity FillingCaseProcessInfo and its DTO FillingCaseProcessInfoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FillingCaseProcessInfoMapper extends EntityMapper<FillingCaseProcessInfoDTO, FillingCaseProcessInfo> {


    @Mapping(target = "staff", ignore = true)
    @Mapping(target = "relevantPeople", ignore = true)
    FillingCaseProcessInfo toEntity(FillingCaseProcessInfoDTO fillingCaseProcessInfoDTO);

    default FillingCaseProcessInfo fromId(Long id) {
        if (id == null) {
            return null;
        }
        FillingCaseProcessInfo fillingCaseProcessInfo = new FillingCaseProcessInfo();
        fillingCaseProcessInfo.setId(id);
        return fillingCaseProcessInfo;
    }
}
