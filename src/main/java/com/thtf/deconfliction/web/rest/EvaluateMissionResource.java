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
import com.thtf.deconfliction.service.EvaluateMissionService;
import com.thtf.deconfliction.service.dto.EvaluateMissionDTO;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing EvaluateMission.
 */
@RestController
@RequestMapping("/api")
public class EvaluateMissionResource {

    private final Logger log = LoggerFactory.getLogger(EvaluateMissionResource.class);

    private static final String ENTITY_NAME = "evaluateMission";

    private final EvaluateMissionService evaluateMissionService;

    public EvaluateMissionResource(EvaluateMissionService evaluateMissionService) {
        this.evaluateMissionService = evaluateMissionService;
    }

    /**
     * POST  /evaluate-missions : Create a new evaluateMission.
     *
     * @param evaluateMissionDTO the evaluateMissionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new evaluateMissionDTO, or with status 400 (Bad Request) if the evaluateMission has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/evaluate-missions")
    @Timed
    public ResponseEntity<EvaluateMissionDTO> createEvaluateMission(@RequestBody EvaluateMissionDTO evaluateMissionDTO) throws URISyntaxException {
        log.debug("REST request to save EvaluateMission : {}", evaluateMissionDTO);
        if (evaluateMissionDTO.getId() != null) {
            throw new BadRequestAlertException("A new evaluateMission cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EvaluateMissionDTO result = evaluateMissionService.save(evaluateMissionDTO);
        return ResponseEntity.created(new URI("/api/evaluate-missions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /evaluate-missions : Updates an existing evaluateMission.
     *
     * @param evaluateMissionDTO the evaluateMissionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated evaluateMissionDTO,
     * or with status 400 (Bad Request) if the evaluateMissionDTO is not valid,
     * or with status 500 (Internal Server Error) if the evaluateMissionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/evaluate-missions")
    @Timed
    public ResponseEntity<EvaluateMissionDTO> updateEvaluateMission(@RequestBody EvaluateMissionDTO evaluateMissionDTO) throws URISyntaxException {
        log.debug("REST request to update EvaluateMission : {}", evaluateMissionDTO);
        if (evaluateMissionDTO.getId() == null) {
            return createEvaluateMission(evaluateMissionDTO);
        }
        EvaluateMissionDTO result = evaluateMissionService.save(evaluateMissionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, evaluateMissionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /evaluate-missions : get all the evaluateMissions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of evaluateMissions in body
     */
    @GetMapping("/evaluate-missions")
    @Timed
    public ResponseEntity<List<EvaluateMissionDTO>> getAllEvaluateMissions(Pageable pageable) {
        log.debug("REST request to get a page of EvaluateMissions");
        Page<EvaluateMissionDTO> page = evaluateMissionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/evaluate-missions");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /evaluate-missions/:id : get the "id" evaluateMission.
     *
     * @param id the id of the evaluateMissionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the evaluateMissionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/evaluate-missions/{id}")
    @Timed
    public ResponseEntity<EvaluateMissionDTO> getEvaluateMission(@PathVariable Long id) {
        log.debug("REST request to get EvaluateMission : {}", id);
        EvaluateMissionDTO evaluateMissionDTO = evaluateMissionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(evaluateMissionDTO));
    }

    /**
     * DELETE  /evaluate-missions/:id : delete the "id" evaluateMission.
     *
     * @param id the id of the evaluateMissionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/evaluate-missions/{id}")
    @Timed
    public ResponseEntity<Void> deleteEvaluateMission(@PathVariable Long id) {
        log.debug("REST request to delete EvaluateMission : {}", id);
        evaluateMissionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
