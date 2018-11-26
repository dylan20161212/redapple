package com.thtf.deconfliction.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thtf.deconfliction.service.dto.EvaluateMediatorDTO;

/**
 * Service Interface for managing EvaluateMediator.
 */
public interface EvaluateMediatorService {

    /**
     * Save a evaluateMediator.
     *
     * @param evaluateMediatorDTO the entity to save
     * @return the persisted entity
     */
    EvaluateMediatorDTO save(EvaluateMediatorDTO evaluateMediatorDTO);

    /**
     * Get all the evaluateMediators.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EvaluateMediatorDTO> findAll(Pageable pageable);

    /**
     * Get the "id" evaluateMediator.
     *
     * @param id the id of the entity
     * @return the entity
     */
    EvaluateMediatorDTO findOne(Long id);

    /**
     * Delete the "id" evaluateMediator.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    
    EvaluateMediatorDTO create(EvaluateMediatorDTO evaluateMediatorDTO);
    
    Page<EvaluateMediatorDTO> findAll(Map<String,Object> filters);
}
