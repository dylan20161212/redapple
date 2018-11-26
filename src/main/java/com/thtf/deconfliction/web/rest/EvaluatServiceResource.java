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
import com.thtf.deconfliction.service.EvaluatServiceService;
import com.thtf.deconfliction.service.dto.EvaluatServiceDTO;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing EvaluatService.
 */
@RestController
@RequestMapping("/api")
public class EvaluatServiceResource {

    private final Logger log = LoggerFactory.getLogger(EvaluatServiceResource.class);

    private static final String ENTITY_NAME = "evaluatService";

    private final EvaluatServiceService evaluatServiceService;

    public EvaluatServiceResource(EvaluatServiceService evaluatServiceService) {
        this.evaluatServiceService = evaluatServiceService;
    }

    /**
     * POST  /evaluat-services : Create a new evaluatService.
     *
     * @param evaluatServiceDTO the evaluatServiceDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new evaluatServiceDTO, or with status 400 (Bad Request) if the evaluatService has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/evaluat-services")
    @Timed
    public ResponseEntity<EvaluatServiceDTO> createEvaluatService(@Valid @RequestBody EvaluatServiceDTO evaluatServiceDTO) throws URISyntaxException {
        log.debug("REST request to save EvaluatService : {}", evaluatServiceDTO);
        if (evaluatServiceDTO.getId() != null) {
            throw new BadRequestAlertException("A new evaluatService cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EvaluatServiceDTO result = evaluatServiceService.save(evaluatServiceDTO);
        return ResponseEntity.created(new URI("/api/evaluat-services/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /evaluat-services : Updates an existing evaluatService.
     *
     * @param evaluatServiceDTO the evaluatServiceDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated evaluatServiceDTO,
     * or with status 400 (Bad Request) if the evaluatServiceDTO is not valid,
     * or with status 500 (Internal Server Error) if the evaluatServiceDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/evaluat-services")
    @Timed
    public ResponseEntity<EvaluatServiceDTO> updateEvaluatService(@Valid @RequestBody EvaluatServiceDTO evaluatServiceDTO) throws URISyntaxException {
        log.debug("REST request to update EvaluatService : {}", evaluatServiceDTO);
        if (evaluatServiceDTO.getId() == null) {
            return createEvaluatService(evaluatServiceDTO);
        }
        EvaluatServiceDTO result = evaluatServiceService.save(evaluatServiceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, evaluatServiceDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /evaluat-services : get all the evaluatServices.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of evaluatServices in body
     */
    @GetMapping("/evaluat-services")
    @Timed
    public ResponseEntity<List<EvaluatServiceDTO>> getAllEvaluatServices(Pageable pageable) {
        log.debug("REST request to get a page of EvaluatServices");
        Page<EvaluatServiceDTO> page = evaluatServiceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/evaluat-services");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /evaluat-services/:id : get the "id" evaluatService.
     *
     * @param id the id of the evaluatServiceDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the evaluatServiceDTO, or with status 404 (Not Found)
     */
    @GetMapping("/evaluat-services/{id}")
    @Timed
    public ResponseEntity<EvaluatServiceDTO> getEvaluatService(@PathVariable Long id) {
        log.debug("REST request to get EvaluatService : {}", id);
        EvaluatServiceDTO evaluatServiceDTO = evaluatServiceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(evaluatServiceDTO));
    }

    /**
     * DELETE  /evaluat-services/:id : delete the "id" evaluatService.
     *
     * @param id the id of the evaluatServiceDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/evaluat-services/{id}")
    @Timed
    public ResponseEntity<Void> deleteEvaluatService(@PathVariable Long id) {
        log.debug("REST request to delete EvaluatService : {}", id);
        evaluatServiceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
