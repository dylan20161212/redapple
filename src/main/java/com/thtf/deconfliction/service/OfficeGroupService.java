package com.thtf.deconfliction.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thtf.deconfliction.service.dto.OfficeGroupDTO;

/**
 * Service Interface for managing OfficeGroup.
 */
public interface OfficeGroupService {

    /**
     * Save a officeGroup.
     *
     * @param officeGroupDTO the entity to save
     * @return the persisted entity
     */
    OfficeGroupDTO save(OfficeGroupDTO officeGroupDTO);

    /**
     * Get all the officeGroups.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OfficeGroupDTO> findAll(Pageable pageable);

    /**
     * Get the "id" officeGroup.
     *
     * @param id the id of the entity
     * @return the entity
     */
    OfficeGroupDTO findOne(Long id);

    /**
     * Delete the "id" officeGroup.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
