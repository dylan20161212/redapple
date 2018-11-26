package com.thtf.deconfliction.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thtf.deconfliction.service.dto.EvaluateRuleItemDTO;

/**
 * Service Interface for managing EvaluateRuleItem.
 */
public interface EvaluateRuleItemService {

    /**
     * Save a evaluateRuleItem.
     *
     * @param evaluateRuleItemDTO the entity to save
     * @return the persisted entity
     */
    EvaluateRuleItemDTO save(EvaluateRuleItemDTO evaluateRuleItemDTO);

    /**
     * Get all the evaluateRuleItems.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EvaluateRuleItemDTO> findAll(Pageable pageable);
    
    Page<EvaluateRuleItemDTO> findAll(Map<String,Object> filters);

    /**
     * Get the "id" evaluateRuleItem.
     *
     * @param id the id of the entity
     * @return the entity
     */
    EvaluateRuleItemDTO findOne(Long id);

    /**
     * Delete the "id" evaluateRuleItem.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
