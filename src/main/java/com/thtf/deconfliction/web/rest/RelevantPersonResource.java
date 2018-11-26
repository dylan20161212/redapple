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
import com.thtf.deconfliction.service.RelevantPersonService;
import com.thtf.deconfliction.service.dto.RelevantPersonDTO;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing RelevantPerson.
 */
@RestController
@RequestMapping("/api")
public class RelevantPersonResource {

    private final Logger log = LoggerFactory.getLogger(RelevantPersonResource.class);

    private static final String ENTITY_NAME = "relevantPerson";

    private final RelevantPersonService relevantPersonService;

    public RelevantPersonResource(RelevantPersonService relevantPersonService) {
        this.relevantPersonService = relevantPersonService;
    }

    /**
     * POST  /relevant-people : Create a new relevantPerson.
     *
     * @param relevantPersonDTO the relevantPersonDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new relevantPersonDTO, or with status 400 (Bad Request) if the relevantPerson has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/relevant-people")
    @Timed
    public ResponseEntity<RelevantPersonDTO> createRelevantPerson(@RequestBody RelevantPersonDTO relevantPersonDTO) throws URISyntaxException {
        log.debug("REST request to save RelevantPerson : {}", relevantPersonDTO);
        if (relevantPersonDTO.getId() != null) {
            throw new BadRequestAlertException("A new relevantPerson cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RelevantPersonDTO result = relevantPersonService.save(relevantPersonDTO);
        return ResponseEntity.created(new URI("/api/relevant-people/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /relevant-people : Updates an existing relevantPerson.
     *
     * @param relevantPersonDTO the relevantPersonDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated relevantPersonDTO,
     * or with status 400 (Bad Request) if the relevantPersonDTO is not valid,
     * or with status 500 (Internal Server Error) if the relevantPersonDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/relevant-people")
    @Timed
    public ResponseEntity<RelevantPersonDTO> updateRelevantPerson(@RequestBody RelevantPersonDTO relevantPersonDTO) throws URISyntaxException {
        log.debug("REST request to update RelevantPerson : {}", relevantPersonDTO);
        if (relevantPersonDTO.getId() == null) {
            return createRelevantPerson(relevantPersonDTO);
        }
        RelevantPersonDTO result = relevantPersonService.save(relevantPersonDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, relevantPersonDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /relevant-people : get all the relevantPeople.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of relevantPeople in body
     */
    @GetMapping("/relevant-people")
    @Timed
    public ResponseEntity<List<RelevantPersonDTO>> getAllRelevantPeople(Pageable pageable) {
        log.debug("REST request to get a page of RelevantPeople");
        Page<RelevantPersonDTO> page = relevantPersonService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/relevant-people");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /relevant-people/:id : get the "id" relevantPerson.
     *
     * @param id the id of the relevantPersonDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the relevantPersonDTO, or with status 404 (Not Found)
     */
    @GetMapping("/relevant-people/{id}")
    @Timed
    public ResponseEntity<RelevantPersonDTO> getRelevantPerson(@PathVariable Long id) {
        log.debug("REST request to get RelevantPerson : {}", id);
        RelevantPersonDTO relevantPersonDTO = relevantPersonService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(relevantPersonDTO));
    }

    /**
     * GET  /relevant-people/:id : get the "id" relevantPerson.
     *
     * @param id the id of the relevantPersonDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the relevantPersonDTO, or with status 404 (Not Found)
     */
    @GetMapping("/relevant-peoplex/{cid}")
    @Timed
    public List<RelevantPersonDTO> getRelevantPersonByCid(@PathVariable Long cid) {
        log.debug("REST request to get RelevantPerson : {}", cid);
    	return relevantPersonService.findByCid(cid);
    }
    
    /**
     * GET  /relevant-people : get all the relevantPeople.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of relevantPeople in body
     */
    @GetMapping("/relevant-peoplex")
    @Timed
    public ResponseEntity<Page<RelevantPersonDTO>> getAllRelevantPeoplex(@RequestParam Map<String, Object> params) {
        log.debug("REST request to get a page of RelevantPeople");
        Page<RelevantPersonDTO> page = relevantPersonService.findAll(params);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/relevant-peoplex");
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }


    /**
     * DELETE  /relevant-people/:id : delete the "id" relevantPerson.
     *
     * @param id the id of the relevantPersonDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/relevant-people/{id}")
    @Timed
    public ResponseEntity<Void> deleteRelevantPerson(@PathVariable Long id) {
        log.debug("REST request to delete RelevantPerson : {}", id);
        relevantPersonService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
