package com.thtf.app.service.mapper;

import org.mapstruct.Mapper;

import com.thtf.app.domain.Role;
import com.thtf.app.service.dto.RoleDTO;

/**
 * Mapper for the entity Role and its DTO RoleDTO.
 */
@Mapper(componentModel = "spring", uses = {ResourceMapper.class})
public interface RoleMapper extends EntityMapper<RoleDTO, Role> {



    default Role fromId(Long id) {
        if (id == null) {
            return null;
        }
        Role role = new Role();
        role.setId(id);
        return role;
    }
}
