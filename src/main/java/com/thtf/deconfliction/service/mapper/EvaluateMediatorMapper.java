package com.thtf.deconfliction.service.mapper;

import org.mapstruct.Mapper;

import com.thtf.deconfliction.domain.EvaluateMediator;
import com.thtf.deconfliction.service.dto.EvaluateMediatorDTO;

/**
 * Mapper for the entity EvaluateMediator and its DTO EvaluateMediatorDTO.
 */
@Mapper(componentModel = "spring", uses = {EvaluateRuleMapper.class})
public interface EvaluateMediatorMapper extends EntityMapper<EvaluateMediatorDTO, EvaluateMediator> {



    default EvaluateMediator fromId(Long id) {
        if (id == null) {
            return null;
        }
        EvaluateMediator evaluateMediator = new EvaluateMediator();
        evaluateMediator.setId(id);
        return evaluateMediator;
    }
}
