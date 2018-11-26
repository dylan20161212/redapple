package com.thtf.deconfliction.service.mapper;

import org.mapstruct.Mapper;

import com.thtf.deconfliction.domain.BusinessNumber;
import com.thtf.deconfliction.service.dto.BusinessNumberDTO;

/**
 * Mapper for the entity BusinessNumber and its DTO BusinessNumberDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BusinessNumberMapper extends EntityMapper<BusinessNumberDTO, BusinessNumber> {

    default BusinessNumber fromId(String id) {
        if (id == null) {
            return null;
        }
        BusinessNumber businessNumber = new BusinessNumber();
        businessNumber.setId(id);
        return businessNumber;
    }
}
