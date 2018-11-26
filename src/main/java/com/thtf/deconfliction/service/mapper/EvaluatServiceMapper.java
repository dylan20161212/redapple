package com.thtf.deconfliction.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.thtf.deconfliction.domain.EvaluatService;
import com.thtf.deconfliction.service.dto.EvaluatServiceDTO;

/**
 * Mapper for the entity EvaluatService and its DTO EvaluatServiceDTO.
 */
@Mapper(componentModel = "spring", uses = {ConflictCaseMapper.class})
public interface EvaluatServiceMapper extends EntityMapper<EvaluatServiceDTO, EvaluatService> {

    @Mapping(source = "conflictCase.id", target = "conflictCaseId")
    EvaluatServiceDTO toDto(EvaluatService evaluatService);

    @Mapping(source = "conflictCaseId", target = "conflictCase")
    EvaluatService toEntity(EvaluatServiceDTO evaluatServiceDTO);

    default EvaluatService fromId(Long id) {
        if (id == null) {
            return null;
        }
        EvaluatService evaluatService = new EvaluatService();
        evaluatService.setId(id);
        return evaluatService;
    }
}
