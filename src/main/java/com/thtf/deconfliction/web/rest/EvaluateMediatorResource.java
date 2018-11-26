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
import com.thtf.app.web.rest.util.FilterUtil;
import com.thtf.app.web.rest.util.HeaderUtil;
import com.thtf.app.web.rest.util.PaginationUtil;
import com.thtf.deconfliction.service.EvaluateMediatorService;
import com.thtf.deconfliction.service.dto.EvaluateMediatorDTO;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing EvaluateMediator.
 */
@RestController
@RequestMapping("/api")
public class EvaluateMediatorResource {

    private final Logger log = LoggerFactory.getLogger(EvaluateMediatorResource.class);

    private static final String ENTITY_NAME = "evaluateMediator";

    private final EvaluateMediatorService evaluateMediatorService;

    public EvaluateMediatorResource(EvaluateMediatorService evaluateMediatorService) {
        this.evaluateMediatorService = evaluateMediatorService;
    }

    /**
     * POST  /evaluate-mediators : Create a new evaluateMediator.
     *
     * @param evaluateMediatorDTO the evaluateMediatorDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new evaluateMediatorDTO, or with status 400 (Bad Request) if the evaluateMediator has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/evaluate-mediators")
    @Timed
    public ResponseEntity<EvaluateMediatorDTO> createEvaluateMediator(@RequestBody EvaluateMediatorDTO evaluateMediatorDTO) throws URISyntaxException {
        log.debug("REST request to save EvaluateMediator : {}", evaluateMediatorDTO);
        if (evaluateMediatorDTO.getId() != null) {
            throw new BadRequestAlertException("A new evaluateMediator cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EvaluateMediatorDTO result = evaluateMediatorService.save(evaluateMediatorDTO);
        return ResponseEntity.created(new URI("/api/evaluate-mediators/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    
    /**
     * POST  /evaluate-mediators : Create a new evaluateMediator.
     *
     * @param evaluateMediatorDTO the evaluateMediatorDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new evaluateMediatorDTO, or with status 400 (Bad Request) if the evaluateMediator has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/evaluate-mediatorsx")
    @Timed
    public ResponseEntity<EvaluateMediatorDTO> createEvaluateMediatorx(@RequestBody EvaluateMediatorDTO evaluateMediatorDTO) throws URISyntaxException {
        log.debug("REST request to save EvaluateMediator : {}", evaluateMediatorDTO);
        if (evaluateMediatorDTO.getId() != null) {
            throw new BadRequestAlertException("A new evaluateMediator cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EvaluateMediatorDTO result = evaluateMediatorService.create(evaluateMediatorDTO);
        return ResponseEntity.created(new URI("/api/evaluate-mediators/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /evaluate-mediators : Updates an existing evaluateMediator.
     *
     * @param evaluateMediatorDTO the evaluateMediatorDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated evaluateMediatorDTO,
     * or with status 400 (Bad Request) if the evaluateMediatorDTO is not valid,
     * or with status 500 (Internal Server Error) if the evaluateMediatorDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/evaluate-mediators")
    @Timed
    public ResponseEntity<EvaluateMediatorDTO> updateEvaluateMediator(@RequestBody EvaluateMediatorDTO evaluateMediatorDTO) throws URISyntaxException {
        log.debug("REST request to update EvaluateMediator : {}", evaluateMediatorDTO);
        if (evaluateMediatorDTO.getId() == null) {
            return createEvaluateMediator(evaluateMediatorDTO);
        }
        EvaluateMediatorDTO result = evaluateMediatorService.save(evaluateMediatorDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, evaluateMediatorDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /evaluate-mediators : get all the evaluateMediators.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of evaluateMediators in body
     */
    @GetMapping("/evaluate-mediators")
    @Timed
    public ResponseEntity<List<EvaluateMediatorDTO>> getAllEvaluateMediators(Pageable pageable) {
        log.debug("REST request to get a page of EvaluateMediators");
        Page<EvaluateMediatorDTO> page = evaluateMediatorService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/evaluate-mediators");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * GET  /evaluate-mediators : get all the evaluateMediators.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of evaluateMediators in body
     */
    @GetMapping("/evaluate-mediatorsx")
    @Timed
    public ResponseEntity<List<EvaluateMediatorDTO>> getAllEvaluateMediatorsx(@RequestParam Map<String,Object> filters) {
    	FilterUtil.setFilter(filters,"evaluateType","EQUAL","and");
        log.debug("REST request to get a page of EvaluateMediators");
        Page<EvaluateMediatorDTO> page = evaluateMediatorService.findAll(filters);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/evaluate-mediators");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

	

    /**
     * GET  /evaluate-mediators/:id : get the "id" evaluateMediator.
     *
     * @param id the id of the evaluateMediatorDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the evaluateMediatorDTO, or with status 404 (Not Found)
     */
    @GetMapping("/evaluate-mediators/{id}")
    @Timed
    public ResponseEntity<EvaluateMediatorDTO> getEvaluateMediator(@PathVariable Long id) {
        log.debug("REST request to get EvaluateMediator : {}", id);
        EvaluateMediatorDTO evaluateMediatorDTO = evaluateMediatorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(evaluateMediatorDTO));
    }

    /**
     * DELETE  /evaluate-mediators/:id : delete the "id" evaluateMediator.
     *
     * @param id the id of the evaluateMediatorDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/evaluate-mediators/{id}")
    @Timed
    public ResponseEntity<Void> deleteEvaluateMediator(@PathVariable Long id) {
        log.debug("REST request to delete EvaluateMediator : {}", id);
        evaluateMediatorService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
