package com.thtf.deconfliction.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thtf.deconfliction.service.dto.CaseSalaryDTO;

/**
 * Service Interface for managing CaseSalary.
 */
public interface CaseSalaryService {

    /**
     * Save a caseSalary.
     *
     * @param caseSalaryDTO the entity to save
     * @return the persisted entity
     */
    CaseSalaryDTO save(CaseSalaryDTO caseSalaryDTO);

    /**
     * Get all the caseSalaries.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CaseSalaryDTO> findAll(Pageable pageable);

    /**
     * Get the "id" caseSalary.
     *
     * @param id the id of the entity
     * @return the entity
     */
    CaseSalaryDTO findOne(Long id);

    /**
     * Delete the "id" caseSalary.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
