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
import com.thtf.deconfliction.domain.ConflictCase;
import com.thtf.deconfliction.domain.VUserProcessedCasesNumber;
import com.thtf.deconfliction.service.CaseProcessInfoService;
import com.thtf.deconfliction.service.dto.BusinessNumberDTO;
import com.thtf.deconfliction.service.dto.CaseProcessInfoDTO;
import com.thtf.deconfliction.service.dto.OrgCaseNumberDTO;
import com.thtf.deconfliction.service.dto.SatisfactionRateDTO;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing CaseProcessInfo.
 */
@RestController
@RequestMapping("/api")
public class CaseProcessInfoResource {

    private final Logger log = LoggerFactory.getLogger(CaseProcessInfoResource.class);

    private static final String ENTITY_NAME = "caseProcessInfo";

    private final CaseProcessInfoService caseProcessInfoService;

    public CaseProcessInfoResource(CaseProcessInfoService caseProcessInfoService) {
        this.caseProcessInfoService = caseProcessInfoService;
    }

    
    /**
     * POST  /case-process-infos : Create a new caseProcessInfo.
     *
     * @param caseProcessInfoDTO the caseProcessInfoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new caseProcessInfoDTO, or with status 400 (Bad Request) if the caseProcessInfo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/case-process-infos")
    @Timed
    public ResponseEntity<CaseProcessInfoDTO> createCaseProcessInfo(@RequestBody CaseProcessInfoDTO caseProcessInfoDTO) throws URISyntaxException {
        log.debug("REST request to save CaseProcessInfo : {}", caseProcessInfoDTO);
        if (caseProcessInfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new caseProcessInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CaseProcessInfoDTO result = caseProcessInfoService.save(caseProcessInfoDTO);
        return ResponseEntity.created(new URI("/api/case-process-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /case-process-infos : Updates an existing caseProcessInfo.
     *
     * @param caseProcessInfoDTO the caseProcessInfoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated caseProcessInfoDTO,
     * or with status 400 (Bad Request) if the caseProcessInfoDTO is not valid,
     * or with status 500 (Internal Server Error) if the caseProcessInfoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/case-process-infos")
    @Timed
    public ResponseEntity<CaseProcessInfoDTO> updateCaseProcessInfo(@RequestBody CaseProcessInfoDTO caseProcessInfoDTO) throws URISyntaxException {
        log.debug("REST request to update CaseProcessInfo : {}", caseProcessInfoDTO);
        if (caseProcessInfoDTO.getId() == null) {
            return createCaseProcessInfo(caseProcessInfoDTO);
        }
        CaseProcessInfoDTO result = caseProcessInfoService.save(caseProcessInfoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, caseProcessInfoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /case-process-infos : get all the caseProcessInfos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of caseProcessInfos in body
     */
    @GetMapping("/case-process-infos")
    @Timed
    public ResponseEntity<List<CaseProcessInfoDTO>> getAllCaseProcessInfos(Pageable pageable) {
        log.debug("REST request to get a page of CaseProcessInfos");
        Page<CaseProcessInfoDTO> page = caseProcessInfoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/case-process-infos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /case-process-infos/:id : get the "id" caseProcessInfo.
     *
     * @param id the id of the caseProcessInfoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the caseProcessInfoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/case-process-infos/{id}")
    @Timed
    public ResponseEntity<CaseProcessInfoDTO> getCaseProcessInfo(@PathVariable Long id) {
        log.debug("REST request to get CaseProcessInfo : {}", id);
        CaseProcessInfoDTO caseProcessInfoDTO = caseProcessInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(caseProcessInfoDTO));
    }
    
    /**
     * GET  /case-process-infos/:id : get the "id" caseProcessInfo.
     *
     * @param id the id of the caseProcessInfoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the caseProcessInfoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/case-process-infosx/{cid}")
    @Timed
    public ResponseEntity<Page<CaseProcessInfoDTO>> getCaseProcessInfoByCid(@PathVariable Long cid,@RequestParam Map<String, Object> params) {
        log.debug("REST request to get CaseProcessInfo : {}", cid);
        String filterscount = (String) params.get("filterscount");
        ConflictCase conflictCase = new ConflictCase();
        conflictCase.setId(cid);
    	if(filterscount==null || "".equals(filterscount)){
    		filterscount="0";
    	}
		params.put("filterscount", (Integer.parseInt(filterscount)+1)+"");
		params.put("filtervalue"+filterscount, conflictCase);
		params.put("filtercondition"+filterscount, "EQUAL");
		params.put("filterdatafield"+filterscount, "conflictCase");
		params.put("filteroperator"+filterscount, "0");
        Page<CaseProcessInfoDTO> page = caseProcessInfoService.findAll(params);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/conflict-casesx");
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }

    /**
     * DELETE  /case-process-infos/:id : delete the "id" caseProcessInfo.
     *
     * @param id the id of the caseProcessInfoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/case-process-infos/{id}")
    @Timed
    public ResponseEntity<Void> deleteCaseProcessInfo(@PathVariable Long id) {
        log.debug("REST request to delete CaseProcessInfo : {}", id);
        caseProcessInfoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    /**
     * GET  /audits : 获取各调解机构案件数量.
     *
     * @param fromDate the start of the time period of AuditEvents to get
     * @param toDate the end of the time period of AuditEvents to get
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of AuditEvents in body
     */
    @GetMapping("/case-process-infos-casenumber")
    @Timed
    public ResponseEntity<Page<OrgCaseNumberDTO>> getOrgCaseNumber(@RequestParam Map<String, Object> params) {
        Page<OrgCaseNumberDTO> page = caseProcessInfoService.findOrgCaseNumber(params);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/case-process-infos-casenumber");
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }
    
    /**
     * GET  /audits : 获取各业务案件数量.
     *
     * @param fromDate the start of the time period of AuditEvents to get
     * @param toDate the end of the time period of AuditEvents to get
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of AuditEvents in body
     */
    @GetMapping("/case-process-infos-busnumber")
    @Timed
    public ResponseEntity<Page<BusinessNumberDTO>> getBusinessNumber(@RequestParam Map<String, Object> params) {
        Page<BusinessNumberDTO> page = caseProcessInfoService.findBusinessNumber(params);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/case-process-infos-busnumber");
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }
    
    
    /**
     * GET  /audits : 获取用户处理过的案件数量.
     *
     * @param fromDate the start of the time period of AuditEvents to get
     * @param toDate the end of the time period of AuditEvents to get
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of AuditEvents in body
     */
    @GetMapping("/vuser-processed-cases-number")
    @Timed
    public ResponseEntity<Page<VUserProcessedCasesNumber>> getVUserProcessedCasesNumber(@RequestParam Map<String, Object> params) {
        Page<VUserProcessedCasesNumber> page = caseProcessInfoService.findVUserProcessedCasesNumber(params);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/vuser-processed-cases-number");
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }

    /**
     * GET  /audits : 获取满意率.
     *
     * @param fromDate the start of the time period of AuditEvents to get
     * @param toDate the end of the time period of AuditEvents to get
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of AuditEvents in body
     */
    @GetMapping("/case-process-infos-satrate")
    @Timed
    public ResponseEntity<Page<SatisfactionRateDTO>> getSatisfactionRate(@RequestParam Map<String, Object> params) {
        Page<SatisfactionRateDTO> page = caseProcessInfoService.findSatisfactionRate(params);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/case-process-infos-satrate");
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }
}
