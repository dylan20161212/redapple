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
import com.thtf.deconfliction.service.OfficeGroupService;
import com.thtf.deconfliction.service.dto.OfficeGroupDTO;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing OfficeGroup.
 */
@RestController
@RequestMapping("/api")
public class OfficeGroupResource {

    private final Logger log = LoggerFactory.getLogger(OfficeGroupResource.class);

    private static final String ENTITY_NAME = "officeGroup";

    private final OfficeGroupService officeGroupService;

    public OfficeGroupResource(OfficeGroupService officeGroupService) {
        this.officeGroupService = officeGroupService;
    }

    /**
     * POST  /office-groups : Create a new officeGroup.
     *
     * @param officeGroupDTO the officeGroupDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new officeGroupDTO, or with status 400 (Bad Request) if the officeGroup has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/office-groups")
    @Timed
    public ResponseEntity<OfficeGroupDTO> createOfficeGroup(@RequestBody OfficeGroupDTO officeGroupDTO) throws URISyntaxException {
        log.debug("REST request to save OfficeGroup : {}", officeGroupDTO);
        if (officeGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new officeGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OfficeGroupDTO result = officeGroupService.save(officeGroupDTO);
        return ResponseEntity.created(new URI("/api/office-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /office-groups : Updates an existing officeGroup.
     *
     * @param officeGroupDTO the officeGroupDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated officeGroupDTO,
     * or with status 400 (Bad Request) if the officeGroupDTO is not valid,
     * or with status 500 (Internal Server Error) if the officeGroupDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/office-groups")
    @Timed
    public ResponseEntity<OfficeGroupDTO> updateOfficeGroup(@RequestBody OfficeGroupDTO officeGroupDTO) throws URISyntaxException {
        log.debug("REST request to update OfficeGroup : {}", officeGroupDTO);
        if (officeGroupDTO.getId() == null) {
            return createOfficeGroup(officeGroupDTO);
        }
        OfficeGroupDTO result = officeGroupService.save(officeGroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, officeGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /office-groups : get all the officeGroups.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of officeGroups in body
     */
    @GetMapping("/office-groups")
    @Timed
    public ResponseEntity<List<OfficeGroupDTO>> getAllOfficeGroups(Pageable pageable) {
        log.debug("REST request to get a page of OfficeGroups");
        Page<OfficeGroupDTO> page = officeGroupService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/office-groups");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /office-groups/:id : get the "id" officeGroup.
     *
     * @param id the id of the officeGroupDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the officeGroupDTO, or with status 404 (Not Found)
     */
    @GetMapping("/office-groups/{id}")
    @Timed
    public ResponseEntity<OfficeGroupDTO> getOfficeGroup(@PathVariable Long id) {
        log.debug("REST request to get OfficeGroup : {}", id);
        OfficeGroupDTO officeGroupDTO = officeGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(officeGroupDTO));
    }

    /**
     * DELETE  /office-groups/:id : delete the "id" officeGroup.
     *
     * @param id the id of the officeGroupDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/office-groups/{id}")
    @Timed
    public ResponseEntity<Void> deleteOfficeGroup(@PathVariable Long id) {
        log.debug("REST request to delete OfficeGroup : {}", id);
        officeGroupService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
