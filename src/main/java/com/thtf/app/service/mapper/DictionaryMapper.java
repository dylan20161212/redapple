package com.thtf.app.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.thtf.app.domain.Dictionary;
import com.thtf.app.service.dto.DictionaryDTO;

/**
 * Mapper for the entity Dictionary and its DTO DictionaryDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DictionaryMapper extends EntityMapper<DictionaryDTO, Dictionary> {

    @Mapping(source = "upper.id", target = "upperId")
    DictionaryDTO toDto(Dictionary dictionary); 

    @Mapping(source = "upperId", target = "upper")
    Dictionary toEntity(DictionaryDTO dictionaryDTO);

    default Dictionary fromId(Long id) {
        if (id == null) {
            return null;
        }
        Dictionary dictionary = new Dictionary();
        dictionary.setId(id);
        return dictionary;
    }
}
