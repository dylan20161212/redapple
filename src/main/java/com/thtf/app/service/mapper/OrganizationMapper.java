package com.thtf.app.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.thtf.app.domain.Organization;
import com.thtf.app.service.dto.OrganizationDTO;

/**
 * Mapper for the entity Organization and its DTO OrganizationDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OrganizationMapper extends EntityMapper <OrganizationDTO, Organization> {

    @Mapping(source = "upper.id", target = "upperId")
    OrganizationDTO toDto(Organization organization); 

    @Mapping(source = "upperId", target = "upper")
    Organization toEntity(OrganizationDTO organizationDTO); 
    default Organization fromId(Long id) {
        if (id == null) {
            return null;
        }
        Organization organization = new Organization();
        organization.setId(id);
        return organization;
    }
}
