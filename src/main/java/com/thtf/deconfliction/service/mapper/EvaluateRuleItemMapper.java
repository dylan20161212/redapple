package com.thtf.deconfliction.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.thtf.deconfliction.domain.EvaluateRuleItem;
import com.thtf.deconfliction.service.dto.EvaluateRuleItemDTO;

/**
 * Mapper for the entity EvaluateRuleItem and its DTO EvaluateRuleItemDTO.
 */
@Mapper(componentModel = "spring", uses = {EvaluateMediatorMapper.class})
public interface EvaluateRuleItemMapper extends EntityMapper<EvaluateRuleItemDTO, EvaluateRuleItem> {

    @Mapping(source = "mediator.id", target = "mediatorId")
    EvaluateRuleItemDTO toDto(EvaluateRuleItem evaluateRuleItem);

    @Mapping(source = "mediatorId", target = "mediator")
    EvaluateRuleItem toEntity(EvaluateRuleItemDTO evaluateRuleItemDTO);

    default EvaluateRuleItem fromId(Long id) {
        if (id == null) {
            return null;
        }
        EvaluateRuleItem evaluateRuleItem = new EvaluateRuleItem();
        evaluateRuleItem.setId(id);
        return evaluateRuleItem;
    }
}
