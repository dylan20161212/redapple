package com.thtf.deconfliction.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thtf.deconfliction.service.dto.DutySalaryInfoDTO;

/**
 * Service Interface for managing DutySalaryInfo.
 */
public interface DutySalaryInfoService {

    /**
     * Save a dutySalaryInfo.
     *
     * @param dutySalaryInfoDTO the entity to save
     * @return the persisted entity
     */
    DutySalaryInfoDTO save(DutySalaryInfoDTO dutySalaryInfoDTO);

    /**
     * Get all the dutySalaryInfos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<DutySalaryInfoDTO> findAll(Pageable pageable);

    /**
     * Get the "id" dutySalaryInfo.
     *
     * @param id the id of the entity
     * @return the entity
     */
    DutySalaryInfoDTO findOne(Long id);

    /**
     * Delete the "id" dutySalaryInfo.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
