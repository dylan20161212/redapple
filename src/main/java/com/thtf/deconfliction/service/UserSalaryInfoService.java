package com.thtf.deconfliction.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thtf.deconfliction.service.dto.UserSalaryInfoDTO;

/**
 * Service Interface for managing UserSalaryInfo.
 */
public interface UserSalaryInfoService {

    /**
     * Save a userSalaryInfo.
     *
     * @param userSalaryInfoDTO the entity to save
     * @return the persisted entity
     */
    UserSalaryInfoDTO save(UserSalaryInfoDTO userSalaryInfoDTO);

    /**
     * Get all the userSalaryInfos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<UserSalaryInfoDTO> findAll(Pageable pageable);

    /**
     * Get the "id" userSalaryInfo.
     *
     * @param id the id of the entity
     * @return the entity
     */
    UserSalaryInfoDTO findOne(Long id);

    /**
     * Delete the "id" userSalaryInfo.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

	Page<UserSalaryInfoDTO> findAll(Map<String, Object> filters);
	
	List<UserSalaryInfoDTO> findByUserExtendSalaryId(Long userExtendSalaryId);
}
