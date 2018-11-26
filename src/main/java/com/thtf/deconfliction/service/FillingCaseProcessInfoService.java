package com.thtf.deconfliction.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thtf.deconfliction.service.dto.FillingCaseProcessInfoDTO;

/**
 * Service Interface for managing FillingCaseProcessInfo.
 */
public interface FillingCaseProcessInfoService {

    /**
     * Save a fillingCaseProcessInfo.
     *
     * @param fillingCaseProcessInfoDTO the entity to save
     * @return the persisted entity
     */
    FillingCaseProcessInfoDTO save(FillingCaseProcessInfoDTO fillingCaseProcessInfoDTO);

    /**
     * Get all the fillingCaseProcessInfos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<FillingCaseProcessInfoDTO> findAll(Pageable pageable);

    /**
     * Get all the fillingCaseProcessInfos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<FillingCaseProcessInfoDTO> findAll(Map<String, Object> params);

    /**
     * Get the "id" fillingCaseProcessInfo.
     *
     * @param id the id of the entity
     * @return the entity
     */
    FillingCaseProcessInfoDTO findOne(Long id);

    /**
     * Delete the "id" fillingCaseProcessInfo.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
