package com.thtf.deconfliction.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.thtf.deconfliction.domain.FillingConflictCase;
import com.thtf.deconfliction.service.dto.FillingConflictCaseDTO;

/**
 * Mapper for the entity FillingConflictCase and its DTO FillingConflictCaseDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FillingConflictCaseMapper extends EntityMapper<FillingConflictCaseDTO, FillingConflictCase> {


    @Mapping(target = "staff", ignore = true)
    @Mapping(target = "relevantPeople", ignore = true)
    FillingConflictCase toEntity(FillingConflictCaseDTO fillingConflictCaseDTO);

    default FillingConflictCase fromId(Long id) {
        if (id == null) {
            return null;
        }
        FillingConflictCase fillingConflictCase = new FillingConflictCase();
        fillingConflictCase.setId(id);
        return fillingConflictCase;
    }
}
