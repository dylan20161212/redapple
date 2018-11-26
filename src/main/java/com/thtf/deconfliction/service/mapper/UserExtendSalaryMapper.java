package com.thtf.deconfliction.service.mapper;

import org.mapstruct.Mapper;

import com.thtf.deconfliction.domain.UserExtendSalary;
import com.thtf.deconfliction.service.dto.UserExtendSalaryDTO;

/**
 * Mapper for the entity UserExtendSalary and its DTO UserExtendSalaryDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UserExtendSalaryMapper extends EntityMapper<UserExtendSalaryDTO, UserExtendSalary> {



    default UserExtendSalary fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserExtendSalary userExtendSalary = new UserExtendSalary();
        userExtendSalary.setId(id);
        return userExtendSalary;
    }
}
