package com.thtf.deconfliction.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.thtf.deconfliction.domain.ConflictCase;
import com.thtf.deconfliction.service.dto.ConflictCaseDTO;

/**
 * Mapper for the entity ConflictCase and its DTO ConflictCaseDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ConflictCaseMapper extends EntityMapper<ConflictCaseDTO, ConflictCase> {


    @Mapping(target = "relevantPeople", ignore = true)
    ConflictCase toEntity(ConflictCaseDTO conflictCaseDTO);

    default ConflictCase fromId(Long id) {
        if (id == null) {
            return null;
        }
        ConflictCase conflictCase = new ConflictCase();
        conflictCase.setId(id);
        return conflictCase;
    }
}
