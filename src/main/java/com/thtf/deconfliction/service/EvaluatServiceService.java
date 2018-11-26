package com.thtf.deconfliction.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thtf.deconfliction.service.dto.EvaluatServiceDTO;

/**
 * Service Interface for managing EvaluatService.
 */
public interface EvaluatServiceService {

    /**
     * Save a evaluatService.
     *
     * @param evaluatServiceDTO the entity to save
     * @return the persisted entity
     */
    EvaluatServiceDTO save(EvaluatServiceDTO evaluatServiceDTO);

    /**
     * Get all the evaluatServices.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EvaluatServiceDTO> findAll(Pageable pageable);

    /**
     * Get the "id" evaluatService.
     *
     * @param id the id of the entity
     * @return the entity
     */
    EvaluatServiceDTO findOne(Long id);

    /**
     * Delete the "id" evaluatService.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
