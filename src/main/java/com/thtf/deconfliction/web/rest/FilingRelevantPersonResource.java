package com.thtf.deconfliction.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
import com.thtf.deconfliction.service.FilingRelevantPersonService;
import com.thtf.deconfliction.service.dto.FilingRelevantPersonDTO;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing FilingRelevantPerson.
 */
@RestController
@RequestMapping("/api")
public class FilingRelevantPersonResource {

    private final Logger log = LoggerFactory.getLogger(FilingRelevantPersonResource.class);

    private static final String ENTITY_NAME = "filingRelevantPerson";

    private final FilingRelevantPersonService filingRelevantPersonService;

    public FilingRelevantPersonResource(FilingRelevantPersonService filingRelevantPersonService) {
        this.filingRelevantPersonService = filingRelevantPersonService;
    }

    /**
     * POST  /filing-relevant-people : Create a new filingRelevantPerson.
     *
     * @param filingRelevantPersonDTO the filingRelevantPersonDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new filingRelevantPersonDTO, or with status 400 (Bad Request) if the filingRelevantPerson has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/filing-relevant-people")
    @Timed
    public ResponseEntity<FilingRelevantPersonDTO> createFilingRelevantPerson(@Valid @RequestBody FilingRelevantPersonDTO filingRelevantPersonDTO) throws URISyntaxException {
        log.debug("REST request to save FilingRelevantPerson : {}", filingRelevantPersonDTO);
        if (filingRelevantPersonDTO.getId() != null) {
            throw new BadRequestAlertException("A new filingRelevantPerson cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FilingRelevantPersonDTO result = filingRelevantPersonService.save(filingRelevantPersonDTO);
        return ResponseEntity.created(new URI("/api/filing-relevant-people/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /filing-relevant-people : Updates an existing filingRelevantPerson.
     *
     * @param filingRelevantPersonDTO the filingRelevantPersonDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated filingRelevantPersonDTO,
     * or with status 400 (Bad Request) if the filingRelevantPersonDTO is not valid,
     * or with status 500 (Internal Server Error) if the filingRelevantPersonDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/filing-relevant-people")
    @Timed
    public ResponseEntity<FilingRelevantPersonDTO> updateFilingRelevantPerson(@Valid @RequestBody FilingRelevantPersonDTO filingRelevantPersonDTO) throws URISyntaxException {
        log.debug("REST request to update FilingRelevantPerson : {}", filingRelevantPersonDTO);
        if (filingRelevantPersonDTO.getId() == null) {
            return createFilingRelevantPerson(filingRelevantPersonDTO);
        }
        FilingRelevantPersonDTO result = filingRelevantPersonService.save(filingRelevantPersonDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, filingRelevantPersonDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /filing-relevant-people : get all the filingRelevantPeople.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of filingRelevantPeople in body
     */
    @GetMapping("/filing-relevant-people")
    @Timed
    public ResponseEntity<List<FilingRelevantPersonDTO>> getAllFilingRelevantPeople(Pageable pageable) {
        log.debug("REST request to get a page of FilingRelevantPeople");
        Page<FilingRelevantPersonDTO> page = filingRelevantPersonService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/filing-relevant-people");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /filing-relevant-people/:id : get the "id" filingRelevantPerson.
     *
     * @param id the id of the filingRelevantPersonDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the filingRelevantPersonDTO, or with status 404 (Not Found)
     */
    @GetMapping("/filing-relevant-people/{id}")
    @Timed
    public ResponseEntity<FilingRelevantPersonDTO> getFilingRelevantPerson(@PathVariable Long id) {
        log.debug("REST request to get FilingRelevantPerson : {}", id);
        FilingRelevantPersonDTO filingRelevantPersonDTO = filingRelevantPersonService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(filingRelevantPersonDTO));
    }

    /**
     * GET  /filing-relevant-people/:id : get the "id" filingRelevantPerson.
     *
     * @param id the id of the filingRelevantPersonDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the filingRelevantPersonDTO, or with status 404 (Not Found)
     */
    @GetMapping("/filing-relevant-peoplex/{cid}")
    @Timed
    public List<FilingRelevantPersonDTO> getFilingRelevantPersonByCid(@PathVariable Long cid) {
        log.debug("REST request to get FilingRelevantPersonByCid : {}", cid);
        // FilingRelevantPersonDTO filingRelevantPersonDTO = filingRelevantPersonService.findByCid(id);
        return filingRelevantPersonService.findByCid(cid);
    }

    /**
     * DELETE  /filing-relevant-people/:id : delete the "id" filingRelevantPerson.
     *
     * @param id the id of the filingRelevantPersonDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/filing-relevant-people/{id}")
    @Timed
    public ResponseEntity<Void> deleteFilingRelevantPerson(@PathVariable Long id) {
        log.debug("REST request to delete FilingRelevantPerson : {}", id);
        filingRelevantPersonService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
