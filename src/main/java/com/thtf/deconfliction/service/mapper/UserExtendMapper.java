package com.thtf.deconfliction.service.mapper;

import org.mapstruct.Mapper;

import com.thtf.deconfliction.domain.UserExtend;
import com.thtf.deconfliction.service.dto.UserExtendDTO;

/**
 * Mapper for the entity UserExtend and its DTO UserExtendDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UserExtendMapper extends EntityMapper<UserExtendDTO, UserExtend> {



    default UserExtend fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserExtend userExtend = new UserExtend();
        userExtend.setId(id);
        return userExtend;
    }
}
