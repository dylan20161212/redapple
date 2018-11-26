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
import com.thtf.deconfliction.service.FilingUserExtendService;
import com.thtf.deconfliction.service.dto.FilingUserExtendDTO;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing FilingUserExtend.
 */
@RestController
@RequestMapping("/api")
public class FilingUserExtendResource {

    private final Logger log = LoggerFactory.getLogger(FilingUserExtendResource.class);

    private static final String ENTITY_NAME = "filingUserExtend";

    private final FilingUserExtendService filingUserExtendService;

    public FilingUserExtendResource(FilingUserExtendService filingUserExtendService) {
        this.filingUserExtendService = filingUserExtendService;
    }

    /**
     * POST  /filing-user-extends : Create a new filingUserExtend.
     *
     * @param filingUserExtendDTO the filingUserExtendDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new filingUserExtendDTO, or with status 400 (Bad Request) if the filingUserExtend has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/filing-user-extends")
    @Timed
    public ResponseEntity<FilingUserExtendDTO> createFilingUserExtend(@Valid @RequestBody FilingUserExtendDTO filingUserExtendDTO) throws URISyntaxException {
        log.debug("REST request to save FilingUserExtend : {}", filingUserExtendDTO);
        if (filingUserExtendDTO.getId() != null) {
            throw new BadRequestAlertException("A new filingUserExtend cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FilingUserExtendDTO result = filingUserExtendService.save(filingUserExtendDTO);
        return ResponseEntity.created(new URI("/api/filing-user-extends/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /filing-user-extends : Updates an existing filingUserExtend.
     *
     * @param filingUserExtendDTO the filingUserExtendDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated filingUserExtendDTO,
     * or with status 400 (Bad Request) if the filingUserExtendDTO is not valid,
     * or with status 500 (Internal Server Error) if the filingUserExtendDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/filing-user-extends")
    @Timed
    public ResponseEntity<FilingUserExtendDTO> updateFilingUserExtend(@Valid @RequestBody FilingUserExtendDTO filingUserExtendDTO) throws URISyntaxException {
        log.debug("REST request to update FilingUserExtend : {}", filingUserExtendDTO);
        if (filingUserExtendDTO.getId() == null) {
            return createFilingUserExtend(filingUserExtendDTO);
        }
        FilingUserExtendDTO result = filingUserExtendService.save(filingUserExtendDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, filingUserExtendDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /filing-user-extends : get all the filingUserExtends.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of filingUserExtends in body
     */
    @GetMapping("/filing-user-extends")
    @Timed
    public ResponseEntity<List<FilingUserExtendDTO>> getAllFilingUserExtends(Pageable pageable) {
        log.debug("REST request to get a page of FilingUserExtends");
        Page<FilingUserExtendDTO> page = filingUserExtendService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/filing-user-extends");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /filing-user-extends/:id : get the "id" filingUserExtend.
     *
     * @param id the id of the filingUserExtendDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the filingUserExtendDTO, or with status 404 (Not Found)
     */
    @GetMapping("/filing-user-extends/{id}")
    @Timed
    public ResponseEntity<FilingUserExtendDTO> getFilingUserExtend(@PathVariable Long id) {
        log.debug("REST request to get FilingUserExtend : {}", id);
        FilingUserExtendDTO filingUserExtendDTO = filingUserExtendService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(filingUserExtendDTO));
    }

    /**
     * DELETE  /filing-user-extends/:id : delete the "id" filingUserExtend.
     *
     * @param id the id of the filingUserExtendDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/filing-user-extends/{id}")
    @Timed
    public ResponseEntity<Void> deleteFilingUserExtend(@PathVariable Long id) {
        log.debug("REST request to delete FilingUserExtend : {}", id);
        filingUserExtendService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
