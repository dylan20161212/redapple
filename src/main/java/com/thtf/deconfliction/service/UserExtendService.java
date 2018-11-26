package com.thtf.deconfliction.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thtf.deconfliction.service.dto.UserExtendDTO;

/**
 * Service Interface for managing UserExtend.
 */
public interface UserExtendService {

    /**
     * Save a userExtend.
     *
     * @param userExtendDTO the entity to save
     * @return the persisted entity
     */
    UserExtendDTO save(UserExtendDTO userExtendDTO);

    /**
     * Get all the userExtends.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<UserExtendDTO> findAll(Pageable pageable);

    /**
     * Get the "id" userExtend.
     *
     * @param id the id of the entity
     * @return the entity
     */
    UserExtendDTO findOne(Long id);

    /**
     * Delete the "id" userExtend.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    
   Page<UserExtendDTO> findAll(Map<String, Object> filters);
}
