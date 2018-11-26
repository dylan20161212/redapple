package com.thtf.deconfliction.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.thtf.deconfliction.domain.FillingBorrow;
import com.thtf.deconfliction.service.dto.FillingBorrowDTO;

/**
 * Mapper for the entity FillingBorrow and its DTO FillingBorrowDTO.
 */
@Mapper(componentModel = "spring", uses = {FillingConflictCaseMapper.class})
public interface FillingBorrowMapper extends EntityMapper<FillingBorrowDTO, FillingBorrow> {

    @Mapping(source = "fillingConflictCase.id", target = "fillingConflictCaseId")
    @Mapping(source = "fillingConflictCase.cName", target = "fillingConflictCaseCName")
    FillingBorrowDTO toDto(FillingBorrow fillingBorrow);

    @Mapping(source = "fillingConflictCaseId", target = "fillingConflictCase")
    FillingBorrow toEntity(FillingBorrowDTO fillingBorrowDTO);

    default FillingBorrow fromId(Long id) {
        if (id == null) {
            return null;
        }
        FillingBorrow fillingBorrow = new FillingBorrow();
        fillingBorrow.setId(id);
        return fillingBorrow;
    }
}
