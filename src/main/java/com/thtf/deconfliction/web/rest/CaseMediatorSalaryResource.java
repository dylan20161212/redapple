package com.thtf.deconfliction.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.thtf.app.web.rest.errors.BadRequestAlertException;
import com.thtf.app.web.rest.util.HeaderUtil;
import com.thtf.app.web.rest.util.PaginationUtil;
import com.thtf.deconfliction.service.CaseMediatorSalaryService;
import com.thtf.deconfliction.service.dto.CaseMediatorSalaryDTO;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing CaseMediatorSalary.
 */
@RestController
@RequestMapping("/api")
public class CaseMediatorSalaryResource {

    private final Logger log = LoggerFactory.getLogger(CaseMediatorSalaryResource.class);

    private static final String ENTITY_NAME = "caseMediatorSalary";

    private final CaseMediatorSalaryService caseMediatorSalaryService;

    public CaseMediatorSalaryResource(CaseMediatorSalaryService caseMediatorSalaryService) {
        this.caseMediatorSalaryService = caseMediatorSalaryService;
    }

    /**
     * POST  /case-mediator-salaries : Create a new caseMediatorSalary.
     *
     * @param caseMediatorSalaryDTO the caseMediatorSalaryDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new caseMediatorSalaryDTO, or with status 400 (Bad Request) if the caseMediatorSalary has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/case-mediator-salaries")
    @Timed
    public ResponseEntity<CaseMediatorSalaryDTO> createCaseMediatorSalary(@RequestBody CaseMediatorSalaryDTO caseMediatorSalaryDTO) throws URISyntaxException {
        log.debug("REST request to save CaseMediatorSalary : {}", caseMediatorSalaryDTO);
        if (caseMediatorSalaryDTO.getId() != null) {
            throw new BadRequestAlertException("A new caseMediatorSalary cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CaseMediatorSalaryDTO result = caseMediatorSalaryService.save(caseMediatorSalaryDTO);
        return ResponseEntity.created(new URI("/api/case-mediator-salaries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /case-mediator-salaries : Updates an existing caseMediatorSalary.
     *
     * @param caseMediatorSalaryDTO the caseMediatorSalaryDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated caseMediatorSalaryDTO,
     * or with status 400 (Bad Request) if the caseMediatorSalaryDTO is not valid,
     * or with status 500 (Internal Server Error) if the caseMediatorSalaryDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/case-mediator-salaries")
    @Timed
    public ResponseEntity<CaseMediatorSalaryDTO> updateCaseMediatorSalary(@RequestBody CaseMediatorSalaryDTO caseMediatorSalaryDTO) throws URISyntaxException {
        log.debug("REST request to update CaseMediatorSalary : {}", caseMediatorSalaryDTO);
        if (caseMediatorSalaryDTO.getId() == null) {
            return createCaseMediatorSalary(caseMediatorSalaryDTO);
        }
        CaseMediatorSalaryDTO result = caseMediatorSalaryService.save(caseMediatorSalaryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, caseMediatorSalaryDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /case-mediator-salaries : get all the caseMediatorSalaries.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of caseMediatorSalaries in body
     */
    @GetMapping("/case-mediator-salaries")
    @Timed
    public ResponseEntity<List<CaseMediatorSalaryDTO>> getAllCaseMediatorSalaries(Pageable pageable) {
        log.debug("REST request to get a page of CaseMediatorSalaries");
        Page<CaseMediatorSalaryDTO> page = caseMediatorSalaryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/case-mediator-salaries");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /case-mediator-salaries/:id : get the "id" caseMediatorSalary.
     *
     * @param id the id of the caseMediatorSalaryDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the caseMediatorSalaryDTO, or with status 404 (Not Found)
     */
    @GetMapping("/case-mediator-salaries/{id}")
    @Timed
    public ResponseEntity<CaseMediatorSalaryDTO> getCaseMediatorSalary(@PathVariable Long id) {
        log.debug("REST request to get CaseMediatorSalary : {}", id);
        CaseMediatorSalaryDTO caseMediatorSalaryDTO = caseMediatorSalaryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(caseMediatorSalaryDTO));
    }

    /**
     * DELETE  /case-mediator-salaries/:id : delete the "id" caseMediatorSalary.
     *
     * @param id the id of the caseMediatorSalaryDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/case-mediator-salaries/{id}")
    @Timed
    public ResponseEntity<Void> deleteCaseMediatorSalary(@PathVariable Long id) {
        log.debug("REST request to delete CaseMediatorSalary : {}", id);
        caseMediatorSalaryService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
