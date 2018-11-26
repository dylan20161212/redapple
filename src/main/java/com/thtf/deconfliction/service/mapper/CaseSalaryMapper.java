package com.thtf.deconfliction.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.thtf.deconfliction.domain.CaseSalary;
import com.thtf.deconfliction.service.dto.CaseSalaryDTO;

/**
 * Mapper for the entity CaseSalary and its DTO CaseSalaryDTO.
 */
@Mapper(componentModel = "spring", uses = {ConflictCaseMapper.class})
public interface CaseSalaryMapper extends EntityMapper<CaseSalaryDTO, CaseSalary> {

    @Mapping(source = "conflictCase.id", target = "conflictCaseId")
    CaseSalaryDTO toDto(CaseSalary caseSalary);

    @Mapping(source = "conflictCaseId", target = "conflictCase")
    CaseSalary toEntity(CaseSalaryDTO caseSalaryDTO);

    default CaseSalary fromId(Long id) {
        if (id == null) {
            return null;
        }
        CaseSalary caseSalary = new CaseSalary();
        caseSalary.setId(id);
        return caseSalary;
    }
}
