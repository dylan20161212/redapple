package com.thtf.deconfliction.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thtf.deconfliction.service.dto.EvaluateMissionDTO;

/**
 * Service Interface for managing EvaluateMission.
 */
public interface EvaluateMissionService {

    /**
     * Save a evaluateMission.
     *
     * @param evaluateMissionDTO the entity to save
     * @return the persisted entity
     */
    EvaluateMissionDTO save(EvaluateMissionDTO evaluateMissionDTO);

    /**
     * Get all the evaluateMissions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EvaluateMissionDTO> findAll(Pageable pageable);

    /**
     * Get the "id" evaluateMission.
     *
     * @param id the id of the entity
     * @return the entity
     */
    EvaluateMissionDTO findOne(Long id);

    /**
     * Delete the "id" evaluateMission.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
