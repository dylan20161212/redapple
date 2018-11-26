package com.thtf.deconfliction.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thtf.deconfliction.service.dto.FilingUserExtendDTO;

/**
 * Service Interface for managing FilingUserExtend.
 */
public interface FilingUserExtendService {

    /**
     * Save a filingUserExtend.
     *
     * @param filingUserExtendDTO the entity to save
     * @return the persisted entity
     */
    FilingUserExtendDTO save(FilingUserExtendDTO filingUserExtendDTO);

    /**
     * Get all the filingUserExtends.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<FilingUserExtendDTO> findAll(Pageable pageable);

    /**
     * Get the "id" filingUserExtend.
     *
     * @param id the id of the entity
     * @return the entity
     */
    FilingUserExtendDTO findOne(Long id);

    /**
     * Delete the "id" filingUserExtend.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
