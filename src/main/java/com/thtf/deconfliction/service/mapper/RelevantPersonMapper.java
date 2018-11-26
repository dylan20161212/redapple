package com.thtf.deconfliction.service.mapper;

import org.mapstruct.Mapper;

import com.thtf.deconfliction.domain.RelevantPerson;
import com.thtf.deconfliction.service.dto.RelevantPersonDTO;

/**
 * Mapper for the entity RelevantPerson and its DTO RelevantPersonDTO.
 */
@Mapper(componentModel = "spring", uses = {ConflictCaseMapper.class, CaseProcessInfoMapper.class})
public interface RelevantPersonMapper extends EntityMapper<RelevantPersonDTO, RelevantPerson> {



    default RelevantPerson fromId(Long id) {
        if (id == null) {
            return null;
        }
        RelevantPerson relevantPerson = new RelevantPerson();
        relevantPerson.setId(id);
        return relevantPerson;
    }
}
