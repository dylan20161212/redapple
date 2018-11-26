package com.thtf.deconfliction.service.mapper;

import org.mapstruct.Mapper;

import com.thtf.deconfliction.domain.EvaluateMission;
import com.thtf.deconfliction.service.dto.EvaluateMissionDTO;

/**
 * Mapper for the entity EvaluateMission and its DTO EvaluateMissionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EvaluateMissionMapper extends EntityMapper<EvaluateMissionDTO, EvaluateMission> {



    default EvaluateMission fromId(Long id) {
        if (id == null) {
            return null;
        }
        EvaluateMission evaluateMission = new EvaluateMission();
        evaluateMission.setId(id);
        return evaluateMission;
    }
}
