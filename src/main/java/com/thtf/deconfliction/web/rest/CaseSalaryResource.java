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
import com.thtf.deconfliction.service.CaseSalaryService;
import com.thtf.deconfliction.service.dto.CaseSalaryDTO;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing CaseSalary.
 */
@RestController
@RequestMapping("/api")
public class CaseSalaryResource {

    private final Logger log = LoggerFactory.getLogger(CaseSalaryResource.class);

    private static final String ENTITY_NAME = "caseSalary";

    private final CaseSalaryService caseSalaryService;

    public CaseSalaryResource(CaseSalaryService caseSalaryService) {
        this.caseSalaryService = caseSalaryService;
    }

    /**
     * POST  /case-salaries : Create a new caseSalary.
     *
     * @param caseSalaryDTO the caseSalaryDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new caseSalaryDTO, or with status 400 (Bad Request) if the caseSalary has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/case-salaries")
    @Timed
    public ResponseEntity<CaseSalaryDTO> createCaseSalary(@RequestBody CaseSalaryDTO caseSalaryDTO) throws URISyntaxException {
        log.debug("REST request to save CaseSalary : {}", caseSalaryDTO);
        if (caseSalaryDTO.getId() != null) {
            throw new BadRequestAlertException("A new caseSalary cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CaseSalaryDTO result = caseSalaryService.save(caseSalaryDTO);
        return ResponseEntity.created(new URI("/api/case-salaries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /case-salaries : Updates an existing caseSalary.
     *
     * @param caseSalaryDTO the caseSalaryDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated caseSalaryDTO,
     * or with status 400 (Bad Request) if the caseSalaryDTO is not valid,
     * or with status 500 (Internal Server Error) if the caseSalaryDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/case-salaries")
    @Timed
    public ResponseEntity<CaseSalaryDTO> updateCaseSalary(@RequestBody CaseSalaryDTO caseSalaryDTO) throws URISyntaxException {
        log.debug("REST request to update CaseSalary : {}", caseSalaryDTO);
        if (caseSalaryDTO.getId() == null) {
            return createCaseSalary(caseSalaryDTO);
        }
        CaseSalaryDTO result = caseSalaryService.save(caseSalaryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, caseSalaryDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /case-salaries : get all the caseSalaries.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of caseSalaries in body
     */
    @GetMapping("/case-salaries")
    @Timed
    public ResponseEntity<List<CaseSalaryDTO>> getAllCaseSalaries(Pageable pageable) {
        log.debug("REST request to get a page of CaseSalaries");
        Page<CaseSalaryDTO> page = caseSalaryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/case-salaries");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /case-salaries/:id : get the "id" caseSalary.
     *
     * @param id the id of the caseSalaryDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the caseSalaryDTO, or with status 404 (Not Found)
     */
    @GetMapping("/case-salaries/{id}")
    @Timed
    public ResponseEntity<CaseSalaryDTO> getCaseSalary(@PathVariable Long id) {
        log.debug("REST request to get CaseSalary : {}", id);
        CaseSalaryDTO caseSalaryDTO = caseSalaryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(caseSalaryDTO));
    }

    /**
     * DELETE  /case-salaries/:id : delete the "id" caseSalary.
     *
     * @param id the id of the caseSalaryDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/case-salaries/{id}")
    @Timed
    public ResponseEntity<Void> deleteCaseSalary(@PathVariable Long id) {
        log.debug("REST request to delete CaseSalary : {}", id);
        caseSalaryService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
