package com.thtf.deconfliction.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.thtf.deconfliction.domain.CaseMediatorSalary;
import com.thtf.deconfliction.service.dto.CaseMediatorSalaryDTO;

/**
 * Mapper for the entity CaseMediatorSalary and its DTO CaseMediatorSalaryDTO.
 */
@Mapper(componentModel = "spring", uses = {UserExtendMapper.class})
public interface CaseMediatorSalaryMapper extends EntityMapper<CaseMediatorSalaryDTO, CaseMediatorSalary> {

    @Mapping(source = "caseSalary.id", target = "caseSalaryId")
    CaseMediatorSalaryDTO toDto(CaseMediatorSalary caseMediatorSalary);

    @Mapping(source = "caseSalaryId", target = "caseSalary")
    CaseMediatorSalary toEntity(CaseMediatorSalaryDTO caseMediatorSalaryDTO);

    default CaseMediatorSalary fromId(Long id) {
        if (id == null) {
            return null;
        }
        CaseMediatorSalary caseMediatorSalary = new CaseMediatorSalary();
        caseMediatorSalary.setId(id);
        return caseMediatorSalary;
    }
}
