package com.thtf.deconfliction.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thtf.deconfliction.service.dto.CaseMediatorSalaryDTO;

/**
 * Service Interface for managing CaseMediatorSalary.
 */
public interface CaseMediatorSalaryService {

    /**
     * Save a caseMediatorSalary.
     *
     * @param caseMediatorSalaryDTO the entity to save
     * @return the persisted entity
     */
    CaseMediatorSalaryDTO save(CaseMediatorSalaryDTO caseMediatorSalaryDTO);

    /**
     * Get all the caseMediatorSalaries.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CaseMediatorSalaryDTO> findAll(Pageable pageable);

    /**
     * Get the "id" caseMediatorSalary.
     *
     * @param id the id of the entity
     * @return the entity
     */
    CaseMediatorSalaryDTO findOne(Long id);

    /**
     * Delete the "id" caseMediatorSalary.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
