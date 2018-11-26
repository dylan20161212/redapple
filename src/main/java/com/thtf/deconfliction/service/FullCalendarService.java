package com.thtf.deconfliction.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thtf.deconfliction.service.dto.FullCalendarDTO;

/**
 * Service Interface for managing FullCalendar.
 */
public interface FullCalendarService {

    /**
     * Save a fullCalendar.
     *
     * @param fullCalendarDTO the entity to save
     * @return the persisted entity
     */
    FullCalendarDTO save(FullCalendarDTO fullCalendarDTO);

    /**
     * Get all the fullCalendars.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<FullCalendarDTO> findAll(Pageable pageable);

    /**
     * Get all the fullCalendars.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<FullCalendarDTO> findAll(Map<String, Object> filters);

    /**
     * Get the "id" fullCalendar.
     *
     * @param id the id of the entity
     * @return the entity
     */
    FullCalendarDTO findOne(Long id);

    /**
     * Delete the "id" fullCalendar.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
