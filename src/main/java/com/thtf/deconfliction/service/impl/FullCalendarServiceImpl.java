package com.thtf.deconfliction.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thtf.deconfliction.domain.FullCalendar;
import com.thtf.deconfliction.repository.FullCalendarRepository;
import com.thtf.deconfliction.service.FullCalendarService;
import com.thtf.deconfliction.service.dto.FullCalendarDTO;
import com.thtf.deconfliction.service.mapper.FullCalendarMapper;


/**
 * Service Implementation for managing FullCalendar.
 */
@Service
@Transactional
public class FullCalendarServiceImpl implements FullCalendarService {

    private final Logger log = LoggerFactory.getLogger(FullCalendarServiceImpl.class);

    private final FullCalendarRepository fullCalendarRepository;

    private final FullCalendarMapper fullCalendarMapper;

    public FullCalendarServiceImpl(FullCalendarRepository fullCalendarRepository, FullCalendarMapper fullCalendarMapper) {
        this.fullCalendarRepository = fullCalendarRepository;
        this.fullCalendarMapper = fullCalendarMapper;
    }

    /**
     * Save a fullCalendar.
     *
     * @param fullCalendarDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FullCalendarDTO save(FullCalendarDTO fullCalendarDTO) {
        log.debug("Request to save FullCalendar : {}", fullCalendarDTO);
        FullCalendar fullCalendar = fullCalendarMapper.toEntity(fullCalendarDTO);
        fullCalendar = fullCalendarRepository.save(fullCalendar);
        return fullCalendarMapper.toDto(fullCalendar);
    }

    /**
     * Get all the fullCalendars.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FullCalendarDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FullCalendars");
        return fullCalendarRepository.findAll(pageable)
            .map(fullCalendarMapper::toDto);
    }

    /**
     * Get all the fullCalendars.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FullCalendarDTO> findAll(Map<String, Object> filters) {
        log.debug("Request to get all FullCalendars");
        return new PageImpl<FullCalendar>(fullCalendarRepository.findAllNative(filters), null,
        		fullCalendarRepository.getRows(filters)).map(fullCalendarMapper::toDto);
    }

    /**
     * Get one fullCalendar by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public FullCalendarDTO findOne(Long id) {
        log.debug("Request to get FullCalendar : {}", id);
        FullCalendar fullCalendar = fullCalendarRepository.findById(id).orElse(null);
        return fullCalendarMapper.toDto(fullCalendar);
    }

    /**
     * Delete the fullCalendar by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FullCalendar : {}", id);
        fullCalendarRepository.deleteById(id);
    }
}
