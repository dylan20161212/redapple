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
import com.thtf.deconfliction.service.DutySalaryInfoService;
import com.thtf.deconfliction.service.dto.DutySalaryInfoDTO;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing DutySalaryInfo.
 */
@RestController
@RequestMapping("/api")
public class DutySalaryInfoResource {

    private final Logger log = LoggerFactory.getLogger(DutySalaryInfoResource.class);

    private static final String ENTITY_NAME = "dutySalaryInfo";

    private final DutySalaryInfoService dutySalaryInfoService;

    public DutySalaryInfoResource(DutySalaryInfoService dutySalaryInfoService) {
        this.dutySalaryInfoService = dutySalaryInfoService;
    }

    /**
     * POST  /duty-salary-infos : Create a new dutySalaryInfo.
     *
     * @param dutySalaryInfoDTO the dutySalaryInfoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new dutySalaryInfoDTO, or with status 400 (Bad Request) if the dutySalaryInfo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/duty-salary-infos")
    @Timed
    public ResponseEntity<DutySalaryInfoDTO> createDutySalaryInfo(@RequestBody DutySalaryInfoDTO dutySalaryInfoDTO) throws URISyntaxException {
        log.debug("REST request to save DutySalaryInfo : {}", dutySalaryInfoDTO);
        if (dutySalaryInfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new dutySalaryInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DutySalaryInfoDTO result = dutySalaryInfoService.save(dutySalaryInfoDTO);
        return ResponseEntity.created(new URI("/api/duty-salary-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /duty-salary-infos : Updates an existing dutySalaryInfo.
     *
     * @param dutySalaryInfoDTO the dutySalaryInfoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated dutySalaryInfoDTO,
     * or with status 400 (Bad Request) if the dutySalaryInfoDTO is not valid,
     * or with status 500 (Internal Server Error) if the dutySalaryInfoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/duty-salary-infos")
    @Timed
    public ResponseEntity<DutySalaryInfoDTO> updateDutySalaryInfo(@RequestBody DutySalaryInfoDTO dutySalaryInfoDTO) throws URISyntaxException {
        log.debug("REST request to update DutySalaryInfo : {}", dutySalaryInfoDTO);
        if (dutySalaryInfoDTO.getId() == null) {
            return createDutySalaryInfo(dutySalaryInfoDTO);
        }
        DutySalaryInfoDTO result = dutySalaryInfoService.save(dutySalaryInfoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, dutySalaryInfoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /duty-salary-infos : get all the dutySalaryInfos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of dutySalaryInfos in body
     */
    @GetMapping("/duty-salary-infos")
    @Timed
    public ResponseEntity<List<DutySalaryInfoDTO>> getAllDutySalaryInfos(Pageable pageable) {
        log.debug("REST request to get a page of DutySalaryInfos");
        Page<DutySalaryInfoDTO> page = dutySalaryInfoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/duty-salary-infos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /duty-salary-infos/:id : get the "id" dutySalaryInfo.
     *
     * @param id the id of the dutySalaryInfoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the dutySalaryInfoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/duty-salary-infos/{id}")
    @Timed
    public ResponseEntity<DutySalaryInfoDTO> getDutySalaryInfo(@PathVariable Long id) {
        log.debug("REST request to get DutySalaryInfo : {}", id);
        DutySalaryInfoDTO dutySalaryInfoDTO = dutySalaryInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(dutySalaryInfoDTO));
    }

    /**
     * DELETE  /duty-salary-infos/:id : delete the "id" dutySalaryInfo.
     *
     * @param id the id of the dutySalaryInfoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/duty-salary-infos/{id}")
    @Timed
    public ResponseEntity<Void> deleteDutySalaryInfo(@PathVariable Long id) {
        log.debug("REST request to delete DutySalaryInfo : {}", id);
        dutySalaryInfoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
