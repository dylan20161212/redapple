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
import com.thtf.deconfliction.service.EvaluateRuleService;
import com.thtf.deconfliction.service.dto.EvaluateRuleDTO;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing EvaluateRule.
 */
@RestController
@RequestMapping("/api")
public class EvaluateRuleResource {

    private final Logger log = LoggerFactory.getLogger(EvaluateRuleResource.class);

    private static final String ENTITY_NAME = "evaluateRule";

    private final EvaluateRuleService evaluateRuleService;

    public EvaluateRuleResource(EvaluateRuleService evaluateRuleService) {
        this.evaluateRuleService = evaluateRuleService;
    }

    /**
     * POST  /evaluate-rules : Create a new evaluateRule.
     *
     * @param evaluateRuleDTO the evaluateRuleDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new evaluateRuleDTO, or with status 400 (Bad Request) if the evaluateRule has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/evaluate-rules")
    @Timed
    public ResponseEntity<EvaluateRuleDTO> createEvaluateRule(@RequestBody EvaluateRuleDTO evaluateRuleDTO) throws URISyntaxException {
        log.debug("REST request to save EvaluateRule : {}", evaluateRuleDTO);
        if (evaluateRuleDTO.getId() != null) {
            throw new BadRequestAlertException("A new evaluateRule cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EvaluateRuleDTO result = evaluateRuleService.save(evaluateRuleDTO);
        return ResponseEntity.created(new URI("/api/evaluate-rules/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /evaluate-rules : Updates an existing evaluateRule.
     *
     * @param evaluateRuleDTO the evaluateRuleDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated evaluateRuleDTO,
     * or with status 400 (Bad Request) if the evaluateRuleDTO is not valid,
     * or with status 500 (Internal Server Error) if the evaluateRuleDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/evaluate-rules")
    @Timed
    public ResponseEntity<EvaluateRuleDTO> updateEvaluateRule(@RequestBody EvaluateRuleDTO evaluateRuleDTO) throws URISyntaxException {
        log.debug("REST request to update EvaluateRule : {}", evaluateRuleDTO);
        if (evaluateRuleDTO.getId() == null) {
            return createEvaluateRule(evaluateRuleDTO);
        }
        EvaluateRuleDTO result = evaluateRuleService.save(evaluateRuleDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, evaluateRuleDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /evaluate-rules : get all the evaluateRules.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of evaluateRules in body
     */
    @GetMapping("/evaluate-rules")
    @Timed
    public ResponseEntity<List<EvaluateRuleDTO>> getAllEvaluateRules(Pageable pageable) {
        log.debug("REST request to get a page of EvaluateRules");
        Page<EvaluateRuleDTO> page = evaluateRuleService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/evaluate-rules");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    @GetMapping("/evaluate-rulesx")
    @Timed
    public ResponseEntity<List<EvaluateRuleDTO>> getAllEvaluateRules(@RequestParam Map<String,Object> filters) {
        log.debug("REST request to get a page of EvaluateRules");
        FilterUtil.setFilter(filters,"rState","EQUAL","and");
        Page<EvaluateRuleDTO> page = evaluateRuleService.findAll(filters);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/evaluate-rules");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /evaluate-rules/:id : get the "id" evaluateRule.
     *
     * @param id the id of the evaluateRuleDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the evaluateRuleDTO, or with status 404 (Not Found)
     */
    @GetMapping("/evaluate-rules/{id}")
    @Timed
    public ResponseEntity<EvaluateRuleDTO> getEvaluateRule(@PathVariable Long id) {
        log.debug("REST request to get EvaluateRule : {}", id);
        EvaluateRuleDTO evaluateRuleDTO = evaluateRuleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(evaluateRuleDTO));
    }

    /**
     * DELETE  /evaluate-rules/:id : delete the "id" evaluateRule.
     *
     * @param id the id of the evaluateRuleDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/evaluate-rules/{id}")
    @Timed
    public ResponseEntity<Void> deleteEvaluateRule(@PathVariable Long id) {
        log.debug("REST request to delete EvaluateRule : {}", id);
        evaluateRuleService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
