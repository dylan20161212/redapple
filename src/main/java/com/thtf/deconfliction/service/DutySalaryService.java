package com.thtf.deconfliction.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thtf.deconfliction.service.dto.DutySalaryDTO;

/**
 * Service Interface for managing DutySalary.
 */
public interface DutySalaryService {

    /**
     * Save a dutySalary.
     *
     * @param dutySalaryDTO the entity to save
     * @return the persisted entity
     */
    DutySalaryDTO save(DutySalaryDTO dutySalaryDTO);

    /**
     * Get all the dutySalaries.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<DutySalaryDTO> findAll(Pageable pageable);
    
    public Page<DutySalaryDTO> findAll( Map<String,Object> filters);

    /**
     * Get the "id" dutySalary.
     *
     * @param id the id of the entity
     * @return the entity
     */
    DutySalaryDTO findOne(Long id);

    /**
     * Delete the "id" dutySalary.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
