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
import com.thtf.deconfliction.service.DutySalaryService;
import com.thtf.deconfliction.service.dto.DutySalaryDTO;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing DutySalary.
 */
@RestController
@RequestMapping("/api")
public class DutySalaryResource {

    private final Logger log = LoggerFactory.getLogger(DutySalaryResource.class);

    private static final String ENTITY_NAME = "dutySalary";

    private final DutySalaryService dutySalaryService;

    public DutySalaryResource(DutySalaryService dutySalaryService) {
        this.dutySalaryService = dutySalaryService;
    }

    /**
     * POST  /duty-salaries : Create a new dutySalary.
     *
     * @param dutySalaryDTO the dutySalaryDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new dutySalaryDTO, or with status 400 (Bad Request) if the dutySalary has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/duty-salaries")
    @Timed
    public ResponseEntity<DutySalaryDTO> createDutySalary(@RequestBody DutySalaryDTO dutySalaryDTO) throws URISyntaxException {
        log.debug("REST request to save DutySalary : {}", dutySalaryDTO);
        if (dutySalaryDTO.getId() != null) {
            throw new BadRequestAlertException("A new dutySalary cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DutySalaryDTO result = dutySalaryService.save(dutySalaryDTO);
        return ResponseEntity.created(new URI("/api/duty-salaries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /duty-salaries : Updates an existing dutySalary.
     *
     * @param dutySalaryDTO the dutySalaryDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated dutySalaryDTO,
     * or with status 400 (Bad Request) if the dutySalaryDTO is not valid,
     * or with status 500 (Internal Server Error) if the dutySalaryDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/duty-salaries")
    @Timed
    public ResponseEntity<DutySalaryDTO> updateDutySalary(@RequestBody DutySalaryDTO dutySalaryDTO) throws URISyntaxException {
        log.debug("REST request to update DutySalary : {}", dutySalaryDTO);
        if (dutySalaryDTO.getId() == null) {
            return createDutySalary(dutySalaryDTO);
        }
        DutySalaryDTO result = dutySalaryService.save(dutySalaryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, dutySalaryDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /duty-salaries : get all the dutySalaries.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of dutySalaries in body
     */
    @GetMapping("/duty-salaries")
    @Timed
    public ResponseEntity<List<DutySalaryDTO>> getAllDutySalaries(Pageable pageable) {
        log.debug("REST request to get a page of DutySalaries");
        Page<DutySalaryDTO> page = dutySalaryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/duty-salaries");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    
    /**
     * GET  /duty-salaries : get all the dutySalaries.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of dutySalaries in body
     */
    @GetMapping("/duty-salariesx")
    @Timed
    public ResponseEntity<List<DutySalaryDTO>> getAllDutySalariesx(@RequestParam Map<String,Object> filters) {
        log.debug("REST request to get a page of DutySalaries");
        Page<DutySalaryDTO> page = dutySalaryService.findAll(filters);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/duty-salariesx");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    

    /**
     * GET  /duty-salaries/:id : get the "id" dutySalary.
     *
     * @param id the id of the dutySalaryDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the dutySalaryDTO, or with status 404 (Not Found)
     */
    @GetMapping("/duty-salaries/{id}")
    @Timed
    public ResponseEntity<DutySalaryDTO> getDutySalary(@PathVariable Long id) {
        log.debug("REST request to get DutySalary : {}", id);
        DutySalaryDTO dutySalaryDTO = dutySalaryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(dutySalaryDTO));
    }

    /**
     * DELETE  /duty-salaries/:id : delete the "id" dutySalary.
     *
     * @param id the id of the dutySalaryDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/duty-salaries/{id}")
    @Timed
    public ResponseEntity<Void> deleteDutySalary(@PathVariable Long id) {
        log.debug("REST request to delete DutySalary : {}", id);
        dutySalaryService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
