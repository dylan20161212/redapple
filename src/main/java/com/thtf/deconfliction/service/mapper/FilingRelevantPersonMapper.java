package com.thtf.deconfliction.service.mapper;

import org.mapstruct.Mapper;

import com.thtf.deconfliction.domain.FilingRelevantPerson;
import com.thtf.deconfliction.service.dto.FilingRelevantPersonDTO;

/**
 * Mapper for the entity FilingRelevantPerson and its DTO FilingRelevantPersonDTO.
 */
@Mapper(componentModel = "spring", uses = {FillingConflictCaseMapper.class, FillingCaseProcessInfoMapper.class})
public interface FilingRelevantPersonMapper extends EntityMapper<FilingRelevantPersonDTO, FilingRelevantPerson> {



    default FilingRelevantPerson fromId(Long id) {
        if (id == null) {
            return null;
        }
        FilingRelevantPerson filingRelevantPerson = new FilingRelevantPerson();
        filingRelevantPerson.setId(id);
        return filingRelevantPerson;
    }
}
