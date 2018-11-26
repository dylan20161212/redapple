package com.thtf.deconfliction.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thtf.deconfliction.service.dto.EvaluateRuleDTO;

/**
 * Service Interface for managing EvaluateRule.
 */
public interface EvaluateRuleService {

    /**
     * Save a evaluateRule.
     *
     * @param evaluateRuleDTO the entity to save
     * @return the persisted entity
     */
    EvaluateRuleDTO save(EvaluateRuleDTO evaluateRuleDTO);

    /**
     * Get all the evaluateRules.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EvaluateRuleDTO> findAll(Pageable pageable);
    
    Page<EvaluateRuleDTO> findAll(Map<String,Object> filters);

    /**
     * Get the "id" evaluateRule.
     *
     * @param id the id of the entity
     * @return the entity
     */
    EvaluateRuleDTO findOne(Long id);

    /**
     * Delete the "id" evaluateRule.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
