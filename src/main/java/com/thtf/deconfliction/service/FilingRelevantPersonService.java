package com.thtf.deconfliction.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thtf.deconfliction.service.dto.FilingRelevantPersonDTO;

/**
 * Service Interface for managing FilingRelevantPerson.
 */
public interface FilingRelevantPersonService {

    /**
     * Save a filingRelevantPerson.
     *
     * @param filingRelevantPersonDTO the entity to save
     * @return the persisted entity
     */
    FilingRelevantPersonDTO save(FilingRelevantPersonDTO filingRelevantPersonDTO);

    /**
     * Get all the filingRelevantPeople.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<FilingRelevantPersonDTO> findAll(Pageable pageable);

    /**
     * Get the "id" filingRelevantPerson.
     *
     * @param id the id of the entity
     * @return the entity
     */
    FilingRelevantPersonDTO findOne(Long id);

    /**
     * Delete the "id" filingRelevantPerson.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Get the "id" filingRelevantPerson.
     *
     * @param id the id of the entity
     * @return the entity
     */
    List<FilingRelevantPersonDTO> findByCid(Long cid);
}
