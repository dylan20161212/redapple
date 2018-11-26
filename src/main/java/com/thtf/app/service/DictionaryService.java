package com.thtf.app.service;

import java.util.List;
import java.util.Map;

import com.thtf.app.service.dto.DictionaryDTO;

/**
 * Service Interface for managing Dictionary.
 */
public interface DictionaryService {

    /**
     * Save a dictionary.
     *
     * @param dictionaryDTO the entity to save
     * @return the persisted entity
     */
    DictionaryDTO save(DictionaryDTO dictionaryDTO);

    /**
     *  Get all the dictionaries.
     *
     *  @return the list of entities
     */
    List<DictionaryDTO> findAll();

    /**
     *  Get the "id" dictionary.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    DictionaryDTO findOne(Long id);

    /**
     *  Delete the "id" dictionary.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    List<DictionaryDTO> findRootOrLeaf(Map<String, String> params);
    
    
    List <DictionaryDTO> findDicProperties(String param);
    
    
    List<DictionaryDTO> findDicsById(Long id,Boolean containedMe);
    
    DictionaryDTO findDicsById(Long id, String key, Boolean containedMe);
}
