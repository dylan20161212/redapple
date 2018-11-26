package com.thtf.deconfliction.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thtf.deconfliction.service.dto.FillingBorrowDTO;

/**
 * Service Interface for managing FillingBorrow.
 */
public interface FillingBorrowService {

    /**
     * Save a fillingBorrow.
     *
     * @param fillingBorrowDTO the entity to save
     * @return the persisted entity
     */
    FillingBorrowDTO save(FillingBorrowDTO fillingBorrowDTO);

    /**
     * Get all the fillingBorrows.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<FillingBorrowDTO> findAll(Pageable pageable);

    /**
     * Get the "id" fillingBorrow.
     *
     * @param id the id of the entity
     * @return the entity
     */
    FillingBorrowDTO findOne(Long id);

    /**
     * Delete the "id" fillingBorrow.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

	Page<FillingBorrowDTO> findAll(Map<String, Object> filters);
}
