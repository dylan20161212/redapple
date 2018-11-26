package com.thtf.deconfliction.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thtf.deconfliction.service.dto.ConflictCaseDTO;

/**
 * Service Interface for managing ConflictCase.
 */
public interface ConflictCaseService {

    /**
     * Save a conflictCase.
     *
     * @param conflictCaseDTO the entity to save
     * @return the persisted entity
     */
    ConflictCaseDTO save(ConflictCaseDTO conflictCaseDTO);

    /**
     * Get all the conflictCases.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ConflictCaseDTO> findAll(Pageable pageable);
    
    /**
     * Get all the conflictCases.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ConflictCaseDTO> findAll(Map<String, Object> filters);
    
    /**
     * Get all the conflictCases.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ConflictCaseDTO> findByStatus(String cStatus,Pageable pageable);

    /**
     * Get the "id" conflictCase.
     *
     * @param id the id of the entity
     * @return the entity
     */
    ConflictCaseDTO findOne(Long id);

    /**
     * Delete the "id" conflictCase.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    
    /**
     * 返回含有相关人员的案件.
     *
     * @param id the id of the entity
     * @return the entity
     */
    ConflictCaseDTO findOneById(Long id);

	Page<ConflictCaseDTO> findByMediateOrgNameIsNotNull(Pageable pageable);
	
	Page<ConflictCaseDTO> getMyCases(Pageable pageable);

	Page<ConflictCaseDTO> getMyProcessedCases(Map<String,Object> filters);
	
	Long getMyProcessedCasesCount(Map<String,Object> filters);
}
