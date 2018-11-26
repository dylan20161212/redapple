package com.thtf.deconfliction.service.mapper;

import org.mapstruct.Mapper;

import com.thtf.deconfliction.domain.EvaluateRule;
import com.thtf.deconfliction.service.dto.EvaluateRuleDTO;

/**
 * Mapper for the entity EvaluateRule and its DTO EvaluateRuleDTO.
 */
@Mapper(componentModel = "spring", uses = {EvaluateRuleItemMapper.class})
public interface EvaluateRuleMapper extends EntityMapper<EvaluateRuleDTO, EvaluateRule> {



    default EvaluateRule fromId(Long id) {
        if (id == null) {
            return null;
        }
        EvaluateRule evaluateRule = new EvaluateRule();
        evaluateRule.setId(id);
        return evaluateRule;
    }
}
