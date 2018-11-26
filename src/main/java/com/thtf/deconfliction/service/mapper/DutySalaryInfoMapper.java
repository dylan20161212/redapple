package com.thtf.deconfliction.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.thtf.deconfliction.domain.DutySalaryInfo;
import com.thtf.deconfliction.service.dto.DutySalaryInfoDTO;

/**
 * Mapper for the entity DutySalaryInfo and its DTO DutySalaryInfoDTO.
 */
@Mapper(componentModel = "spring", uses = {DutySalaryMapper.class})
public interface DutySalaryInfoMapper extends EntityMapper<DutySalaryInfoDTO, DutySalaryInfo> {

    @Mapping(source = "dutySalary.id", target = "dutySalaryId")
    DutySalaryInfoDTO toDto(DutySalaryInfo dutySalaryInfo);

    @Mapping(source = "dutySalaryId", target = "dutySalary")
    DutySalaryInfo toEntity(DutySalaryInfoDTO dutySalaryInfoDTO);

    default DutySalaryInfo fromId(Long id) {
        if (id == null) {
            return null;
        }
        DutySalaryInfo dutySalaryInfo = new DutySalaryInfo();
        dutySalaryInfo.setId(id);
        return dutySalaryInfo;
    }
}
