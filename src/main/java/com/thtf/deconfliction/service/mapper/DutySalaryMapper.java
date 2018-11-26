package com.thtf.deconfliction.service.mapper;

import org.mapstruct.Mapper;

import com.thtf.deconfliction.domain.DutySalary;
import com.thtf.deconfliction.service.dto.DutySalaryDTO;

/**
 * Mapper for the entity DutySalary and its DTO DutySalaryDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DutySalaryMapper extends EntityMapper<DutySalaryDTO, DutySalary> {



    default DutySalary fromId(Long id) {
        if (id == null) {
            return null;
        }
        DutySalary dutySalary = new DutySalary();
        dutySalary.setId(id);
        return dutySalary;
    }
}
