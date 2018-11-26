package com.thtf.deconfliction.service;

import java.util.Map;

import org.json.JSONException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thtf.app.web.rest.errors.CustomParameterizedException;
import com.thtf.deconfliction.service.dto.UserExtendSalaryDTO;

/**
 * Service Interface for managing UserExtendSalary.
 */
public interface UserExtendSalaryService {

    /**
     * Save a userExtendSalary.
     *
     * @param userExtendSalaryDTO the entity to save
     * @return the persisted entity
     */
    UserExtendSalaryDTO save(UserExtendSalaryDTO userExtendSalaryDTO);

    /**
     * Get all the userExtendSalaries.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<UserExtendSalaryDTO> findAll(Pageable pageable);

    /**
     * Get the "id" userExtendSalary.
     *
     * @param id the id of the entity
     * @return the entity
     */
    UserExtendSalaryDTO findOne(Long id);

    /**
     * Delete the "id" userExtendSalary.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    
    Boolean computAndSaveUserSalary() throws JSONException ,CustomParameterizedException;

	Page<UserExtendSalaryDTO> findAll(Map<String, Object> filters);
}
