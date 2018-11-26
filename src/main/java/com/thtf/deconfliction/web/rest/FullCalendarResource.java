package com.thtf.deconfliction.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.thtf.app.web.rest.errors.BadRequestAlertException;
import com.thtf.app.web.rest.util.HeaderUtil;
import com.thtf.app.web.rest.util.PaginationUtil;
import com.thtf.deconfliction.service.FullCalendarService;
import com.thtf.deconfliction.service.dto.FullCalendarDTO;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing FullCalendar.
 */
@RestController
@RequestMapping("/api")
public class FullCalendarResource {

    private final Logger log = LoggerFactory.getLogger(FullCalendarResource.class);

    private static final String ENTITY_NAME = "fullCalendar";

    private final FullCalendarService fullCalendarService;

    public FullCalendarResource(FullCalendarService fullCalendarService) {
        this.fullCalendarService = fullCalendarService;
    }

    /**
     * POST  /full-calendars : Create a new fullCalendar.
     *
     * @param fullCalendarDTO the fullCalendarDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new fullCalendarDTO, or with status 400 (Bad Request) if the fullCalendar has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/full-calendars")
    @Timed
    public ResponseEntity<FullCalendarDTO> createFullCalendar(@RequestBody FullCalendarDTO fullCalendarDTO) throws URISyntaxException {
        log.debug("REST request to save FullCalendar : {}", fullCalendarDTO);
        if (fullCalendarDTO.getId() != null) {
            throw new BadRequestAlertException("A new fullCalendar cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FullCalendarDTO result = fullCalendarService.save(fullCalendarDTO);
        return ResponseEntity.created(new URI("/api/full-calendars/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /full-calendars : Updates an existing fullCalendar.
     *
     * @param fullCalendarDTO the fullCalendarDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated fullCalendarDTO,
     * or with status 400 (Bad Request) if the fullCalendarDTO is not valid,
     * or with status 500 (Internal Server Error) if the fullCalendarDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/full-calendars")
    @Timed
    public ResponseEntity<FullCalendarDTO> updateFullCalendar(@RequestBody FullCalendarDTO fullCalendarDTO) throws URISyntaxException {
        log.debug("REST request to update FullCalendar : {}", fullCalendarDTO);
        if (fullCalendarDTO.getId() == null) {
            return createFullCalendar(fullCalendarDTO);
        }
        FullCalendarDTO result = fullCalendarService.save(fullCalendarDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, fullCalendarDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /full-calendars : get all the fullCalendars.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of fullCalendars in body
     */
    @GetMapping("/full-calendars")
    @Timed
    public ResponseEntity<List<FullCalendarDTO>> getAllFullCalendars(Pageable pageable) {
        log.debug("REST request to get a page of FullCalendars");
        Page<FullCalendarDTO> page = fullCalendarService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/full-calendars");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * GET  /full-calendars : get all the fullCalendars.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of fullCalendars in body
     */
    @GetMapping("/full-calendarsx")
    @Timed
    public ResponseEntity<Page<FullCalendarDTO>> getAllFullCalendarsx(@RequestParam Map<String, Object> params) {
        log.debug("REST request to get a page of FullCalendars");
        Page<FullCalendarDTO> page = fullCalendarService.findAll(params);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/full-calendarsx");
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }

    /**
     * GET  /full-calendars/:id : get the "id" fullCalendar.
     *
     * @param id the id of the fullCalendarDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the fullCalendarDTO, or with status 404 (Not Found)
     */
    @GetMapping("/full-calendars/{id}")
    @Timed
    public ResponseEntity<FullCalendarDTO> getFullCalendar(@PathVariable Long id) {
        log.debug("REST request to get FullCalendar : {}", id);
        FullCalendarDTO fullCalendarDTO = fullCalendarService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(fullCalendarDTO));
    }

    /**
     * DELETE  /full-calendars/:id : delete the "id" fullCalendar.
     *
     * @param id the id of the fullCalendarDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/full-calendars/{id}")
    @Timed
    public ResponseEntity<Void> deleteFullCalendar(@PathVariable Long id) {
        log.debug("REST request to delete FullCalendar : {}", id);
        fullCalendarService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
