package com.thtf.app.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.thtf.app.domain.UserRoleOrganization;
import com.thtf.app.service.dto.UserRoleOrganizationDTO;

/**
 * Mapper for the entity UserRoleOrganization and its DTO UserRoleOrganizationDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, RoleMapper.class, OrganizationMapper.class})
public interface UserRoleOrganizationMapper extends EntityMapper<UserRoleOrganizationDTO, UserRoleOrganization> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    @Mapping(source = "role.id", target = "roleId")
    @Mapping(source = "role.roleName", target = "roleRoleName")
    @Mapping(source = "organization.id", target = "organizationId")
    @Mapping(source = "organization.orgName", target = "organizationOrgName")
    UserRoleOrganizationDTO toDto(UserRoleOrganization userRoleOrganization); 

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "roleId", target = "role")
    @Mapping(source = "organizationId", target = "organization")
    UserRoleOrganization toEntity(UserRoleOrganizationDTO userRoleOrganizationDTO);

    default UserRoleOrganization fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserRoleOrganization userRoleOrganization = new UserRoleOrganization();
        userRoleOrganization.setId(id);
        return userRoleOrganization;
    }
}
