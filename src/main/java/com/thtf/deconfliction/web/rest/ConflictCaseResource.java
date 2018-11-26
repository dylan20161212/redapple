package com.thtf.deconfliction.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
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
import com.thtf.deconfliction.service.CommonUtilService;
import com.thtf.deconfliction.service.ConflictCaseService;
import com.thtf.deconfliction.service.dto.ConflictCaseDTO;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing ConflictCase.
 */
@RestController
@RequestMapping("/api")
public class ConflictCaseResource {

    private final Logger log = LoggerFactory.getLogger(ConflictCaseResource.class);

    private static final String ENTITY_NAME = "conflictCase";

    private final ConflictCaseService conflictCaseService;
    
    private final CommonUtilService commonUtilService;

    public ConflictCaseResource(ConflictCaseService conflictCaseService,CommonUtilService commonUtilService) {
        this.conflictCaseService = conflictCaseService;
        this.commonUtilService = commonUtilService;
    }

    /**
     * POST  /conflict-cases : Create a new conflictCase.
     *
     * @param conflictCaseDTO the conflictCaseDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new conflictCaseDTO, or with status 400 (Bad Request) if the conflictCase has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/conflict-cases")
    @Timed
    public ResponseEntity<ConflictCaseDTO> createConflictCase(@RequestBody ConflictCaseDTO conflictCaseDTO) throws URISyntaxException {
        log.debug("REST request to save ConflictCase : {}", conflictCaseDTO);
        if (conflictCaseDTO.getId() != null) {
            throw new BadRequestAlertException("A new conflictCase cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ConflictCaseDTO result = conflictCaseService.save(conflictCaseDTO);
        return ResponseEntity.created(new URI("/api/conflict-cases/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /conflict-cases : Updates an existing conflictCase.
     *
     * @param conflictCaseDTO the conflictCaseDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated conflictCaseDTO,
     * or with status 400 (Bad Request) if the conflictCaseDTO is not valid,
     * or with status 500 (Internal Server Error) if the conflictCaseDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/conflict-cases")
    @Timed
    public ResponseEntity<ConflictCaseDTO> updateConflictCase(@RequestBody ConflictCaseDTO conflictCaseDTO) throws URISyntaxException {
        log.debug("REST request to update ConflictCase : {}", conflictCaseDTO);
        if (conflictCaseDTO.getId() == null) {
            return createConflictCase(conflictCaseDTO);
        }
        ConflictCaseDTO result = conflictCaseService.save(conflictCaseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, conflictCaseDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /conflict-cases : get all the conflictCases.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of conflictCases in body
     */
    @GetMapping("/conflict-cases")
    @Timed
    public ResponseEntity<List<ConflictCaseDTO>> getAllConflictCases(Pageable pageable) {
        log.debug("REST request to get a page of ConflictCases");
        Page<ConflictCaseDTO> page = conflictCaseService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/conflict-cases");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * GET  /conflict-cases : get all the conflictCases.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of conflictCases in body
     */
    @GetMapping("/conflict-casesx")
    @Timed
    public ResponseEntity<Page<ConflictCaseDTO>> getAllConflictCasesx(@RequestParam Map<String, Object> params) {
        log.debug("REST request to get a page of ConflictCases");
        Page<ConflictCaseDTO> page = conflictCaseService.findAll(params);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/conflict-casesx");
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }
    
    /**
     * GET  /conflict-cases : get all the conflictCases.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of conflictCases in body
     */
    @GetMapping("/conflict-casesy")
    @Timed
    public ResponseEntity<Page<ConflictCaseDTO>> getAllConflictCasesy(@RequestParam Map<String, Object> filters) {
        log.debug("REST request to get a page of ConflictCases");
        FilterUtil.setFilter(filters, "cStatus", "EQUAL", "and");
        if(filters.get("cStatus").equals("诉调")){
        } else{
        	FilterUtil.setFilter(filters, "mediateOrgId", "IN", "and", this.commonUtilService.getCurrentLoginOrgIds());
        }
        Page<ConflictCaseDTO> page = conflictCaseService.findAll(filters);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/conflict-casesx");
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }
   

    /**
     * GET  /conflict-cases/:id : get the "id" conflictCase.
     *
     * @param id the id of the conflictCaseDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the conflictCaseDTO, or with status 404 (Not Found)
     */
    @GetMapping("/conflict-cases/{id}")
    @Timed
    public ResponseEntity<ConflictCaseDTO> getConflictCase(@PathVariable Long id) {
        log.debug("REST request to get ConflictCase : {}", id);
        ConflictCaseDTO conflictCaseDTO = conflictCaseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(conflictCaseDTO));
    }
    
    /**
     * GET  /conflict-cases/:id : get the "id" conflictCase.
     *
     * @param id the id of the conflictCaseDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the conflictCaseDTO, or with status 404 (Not Found)
     */
    @GetMapping("/conflict-cases-rp/{id}")
    @Timed
    public ResponseEntity<ConflictCaseDTO> getConflictCaseById(@PathVariable Long id) {
        log.debug("REST request to get ConflictCase : {}", id);
        ConflictCaseDTO conflictCaseDTO = conflictCaseService.findOneById(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(conflictCaseDTO));
    }
    
    /**
     * GET  /audits : get a page of AuditEvents between the fromDate and toDate.
     *
     * @param fromDate the start of the time period of AuditEvents to get
     * @param toDate the end of the time period of AuditEvents to get
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of AuditEvents in body
     */
    @GetMapping("/conflict-casescs")
    @Timed
    public ResponseEntity<List<ConflictCaseDTO>> getByStatus(@RequestParam(value = "cStatus") String cStatus, Pageable pageable) {
    	log.debug("REST request to findbycstatus cStatus : {}", cStatus);
    	Page<ConflictCaseDTO> page = null;
    	if(cStatus.equals("全部")) {
    		page = conflictCaseService.findByMediateOrgNameIsNotNull(pageable);
    	} else if(cStatus.equals("全部案件")){
    		page = conflictCaseService.findAll(pageable);
    	} else {
    		page = conflictCaseService.findByStatus(cStatus,pageable);
    	}
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/conflict-cases");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * GET  /audits : 获取申请人相关案件.
     *
     * @param fromDate the start of the time period of AuditEvents to get
     * @param toDate the end of the time period of AuditEvents to get
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of AuditEvents in body
     */
    @GetMapping("/conflict-cases-mycases")
    @Timed
    public ResponseEntity<List<ConflictCaseDTO>> getMyCases(Pageable pageable) {
        log.debug("REST request to get a page of RelevantPeople");
        Page<ConflictCaseDTO> page = conflictCaseService.getMyCases(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/relevant-people-mycases");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * DELETE  /conflict-cases/:id : delete the "id" conflictCase.
     *
     * @param id the id of the conflictCaseDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/conflict-cases/{id}")
    @Timed
    public ResponseEntity<Void> deleteConflictCase(@PathVariable Long id) {
        log.debug("REST request to delete ConflictCase : {}", id);
        conflictCaseService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    /**
     * GET  /conflict-cases : get all the conflictCases.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of conflictCases in body
     */
    @GetMapping("/conflict-casesm")
    @Timed
    public ResponseEntity<Page<ConflictCaseDTO>> getAllConflictCasesm(@RequestParam Map<String, Object> params) {
        log.debug("REST request to get a page of ConflictCases");
        Page<ConflictCaseDTO> page = conflictCaseService.findAll(params);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/conflict-casesm");
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }
    
    
    @GetMapping("/conflict-cases-mine")
    @Timed
    public ResponseEntity<Page<ConflictCaseDTO>>  getMyProcessedCases(Map<String,Object> filters) {
    	Page<ConflictCaseDTO> page = this.conflictCaseService.getMyProcessedCases(filters);
    	 HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/conflict-cases-mine");
         return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }
    
    @GetMapping("/conflict-cases-count-mine")
    @Timed
    public ResponseEntity<Map<String,Long>>  getMyProcessedCasesCount(Map<String,Object> filters) {
    	 FilterUtil.setFilter(filters, "cStatus", "IN", "and", "结案");
    	 Long endCaseCount = this.conflictCaseService.getMyProcessedCasesCount(filters);
    	 filters.remove("cStatus");
    	 FilterUtil.setFilter(filters, "cStatus", "NOT IN", "and", "结案");
    	 Long doingCaseCount = this.conflictCaseService.getMyProcessedCasesCount(filters);
    	 
    	 Map<String,Long> map = new HashMap<String,Long>();
    	 map.put("endCaseCount", endCaseCount);
    	 map.put("doingCaseCount", doingCaseCount);
    	 
    	 
    	 
    	 return new ResponseEntity<>(map, null, HttpStatus.OK);
        
    }
    
}
