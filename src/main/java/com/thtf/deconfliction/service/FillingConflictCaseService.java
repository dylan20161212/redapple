package com.thtf.deconfliction.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thtf.deconfliction.service.dto.FillingConflictCaseDTO;

/**
 * Service Interface for managing FillingConflictCase.
 */
public interface FillingConflictCaseService {

    /**
     * Save a fillingConflictCase.
     *
     * @param fillingConflictCaseDTO the entity to save
     * @return the persisted entity
     */
    FillingConflictCaseDTO save(FillingConflictCaseDTO fillingConflictCaseDTO);

    /**
     * Save a fillingConflictCase.
     *
     * @param fillingConflictCaseDTO the entity to save
     * @return the persisted entity
     */
    FillingConflictCaseDTO archive(FillingConflictCaseDTO fillingConflictCaseDTO);
    
    /**
     * Get all the fillingConflictCases.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<FillingConflictCaseDTO> findAll(Pageable pageable);

    /**
     * Get all the fillingConflictCases.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<FillingConflictCaseDTO> findAll(Map<String, Object> params);

    /**
     * Get the "id" fillingConflictCase.
     *
     * @param id the id of the entity
     * @return the entity
     */
    FillingConflictCaseDTO findOne(Long id);

    /**
     * Get the "id" fillingConflictCase.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Map<String,Object> findOneById(Long id);

    /**
     * Delete the "id" fillingConflictCase.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    
     Page<FillingConflictCaseDTO> findBorrowedCases(Map<String,Object> filters);
}
