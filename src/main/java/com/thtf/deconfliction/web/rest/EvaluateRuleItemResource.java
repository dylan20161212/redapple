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
import com.thtf.deconfliction.service.EvaluateRuleItemService;
import com.thtf.deconfliction.service.dto.EvaluateRuleItemDTO;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing EvaluateRuleItem.
 */
@RestController
@RequestMapping("/api")
public class EvaluateRuleItemResource {

    private final Logger log = LoggerFactory.getLogger(EvaluateRuleItemResource.class);

    private static final String ENTITY_NAME = "evaluateRuleItem";

    private final EvaluateRuleItemService evaluateRuleItemService;

    public EvaluateRuleItemResource(EvaluateRuleItemService evaluateRuleItemService) {
        this.evaluateRuleItemService = evaluateRuleItemService;
    }

    /**
     * POST  /evaluate-rule-items : Create a new evaluateRuleItem.
     *
     * @param evaluateRuleItemDTO the evaluateRuleItemDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new evaluateRuleItemDTO, or with status 400 (Bad Request) if the evaluateRuleItem has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/evaluate-rule-items")
    @Timed
    public ResponseEntity<EvaluateRuleItemDTO> createEvaluateRuleItem(@RequestBody EvaluateRuleItemDTO evaluateRuleItemDTO) throws URISyntaxException {
        log.debug("REST request to save EvaluateRuleItem : {}", evaluateRuleItemDTO);
        if (evaluateRuleItemDTO.getId() != null) {
            throw new BadRequestAlertException("A new evaluateRuleItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EvaluateRuleItemDTO result = evaluateRuleItemService.save(evaluateRuleItemDTO);
        return ResponseEntity.created(new URI("/api/evaluate-rule-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /evaluate-rule-items : Updates an existing evaluateRuleItem.
     *
     * @param evaluateRuleItemDTO the evaluateRuleItemDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated evaluateRuleItemDTO,
     * or with status 400 (Bad Request) if the evaluateRuleItemDTO is not valid,
     * or with status 500 (Internal Server Error) if the evaluateRuleItemDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/evaluate-rule-items")
    @Timed
    public ResponseEntity<EvaluateRuleItemDTO> updateEvaluateRuleItem(@RequestBody EvaluateRuleItemDTO evaluateRuleItemDTO) throws URISyntaxException {
        log.debug("REST request to update EvaluateRuleItem : {}", evaluateRuleItemDTO);
        if (evaluateRuleItemDTO.getId() == null) {
            return createEvaluateRuleItem(evaluateRuleItemDTO);
        }
        EvaluateRuleItemDTO result = evaluateRuleItemService.save(evaluateRuleItemDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, evaluateRuleItemDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /evaluate-rule-items : get all the evaluateRuleItems.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of evaluateRuleItems in body
     */
    @GetMapping("/evaluate-rule-items")
    @Timed
    public ResponseEntity<List<EvaluateRuleItemDTO>> getAllEvaluateRuleItems(Pageable pageable) {
        log.debug("REST request to get a page of EvaluateRuleItems");
        Page<EvaluateRuleItemDTO> page = evaluateRuleItemService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/evaluate-rule-items");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    
    @GetMapping("/evaluate-rule-itemsx")
    @Timed
    public ResponseEntity<List<EvaluateRuleItemDTO>> getAllEvaluateRuleItems(@RequestParam Map<String,Object> filters) {
        log.debug("REST request to get a page of EvaluateRuleItems");
        FilterUtil.setFilter(filters,"evaluateType","EQUAL","and");
        Page<EvaluateRuleItemDTO> page = evaluateRuleItemService.findAll(filters);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/evaluate-rule-items");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /evaluate-rule-items/:id : get the "id" evaluateRuleItem.
     *
     * @param id the id of the evaluateRuleItemDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the evaluateRuleItemDTO, or with status 404 (Not Found)
     */
    @GetMapping("/evaluate-rule-items/{id}")
    @Timed
    public ResponseEntity<EvaluateRuleItemDTO> getEvaluateRuleItem(@PathVariable Long id) {
        log.debug("REST request to get EvaluateRuleItem : {}", id);
        EvaluateRuleItemDTO evaluateRuleItemDTO = evaluateRuleItemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(evaluateRuleItemDTO));
    }

    /**
     * DELETE  /evaluate-rule-items/:id : delete the "id" evaluateRuleItem.
     *
     * @param id the id of the evaluateRuleItemDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/evaluate-rule-items/{id}")
    @Timed
    public ResponseEntity<Void> deleteEvaluateRuleItem(@PathVariable Long id) {
        log.debug("REST request to delete EvaluateRuleItem : {}", id);
        evaluateRuleItemService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
