package com.thtf.deconfliction.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thtf.deconfliction.service.dto.RelevantPersonDTO;

/**
 * Service Interface for managing RelevantPerson.
 */
public interface RelevantPersonService {

    /**
     * Save a relevantPerson.
     *
     * @param relevantPersonDTO the entity to save
     * @return the persisted entity
     */
    RelevantPersonDTO save(RelevantPersonDTO relevantPersonDTO);

    /**
     * Get all the relevantPeople.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RelevantPersonDTO> findAll(Pageable pageable);
    /**
     * Get all the relevantPeople.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RelevantPersonDTO> findAll(Map<String, Object> params);

    /**
     * Get the "id" relevantPerson.
     *
     * @param id the id of the entity
     * @return the entity
     */
    RelevantPersonDTO findOne(Long id);

    /**
     * Delete the "id" relevantPerson.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Get the relevantPeoples by condlictCaseid.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
	List<RelevantPersonDTO> findByCid(Long cid);
}
