package com.thtf.deconfliction.service.mapper;

import org.mapstruct.Mapper;

import com.thtf.deconfliction.domain.SatisfactionRate;
import com.thtf.deconfliction.service.dto.SatisfactionRateDTO;

/**
 * Mapper for the entity SatisfactionRate and its DTO SatisfactionRateDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SatisfactionRateMapper extends EntityMapper<SatisfactionRateDTO, SatisfactionRate> {

    default SatisfactionRate fromId(String id) {
        if (id == null) {
            return null;
        }
        SatisfactionRate satisfactionRate = new SatisfactionRate();
        satisfactionRate.setId(id);
        return satisfactionRate;
    }
}
