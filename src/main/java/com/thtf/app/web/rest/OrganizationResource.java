package com.thtf.app.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.thtf.app.service.OrganizationService;
import com.thtf.app.service.dto.OrganizationDTO;
import com.thtf.app.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Organization.
 */
@RestController
@RequestMapping("/api")
public class OrganizationResource {

    private final Logger log = LoggerFactory.getLogger(OrganizationResource.class);

    private static final String ENTITY_NAME = "organization";

    private final OrganizationService organizationService;

    public OrganizationResource(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    /**
     * POST  /organizations : Create a new organization.
     *
     * @param organizationDTO the organizationDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new organizationDTO, or with status 400 (Bad Request) if the organization has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/organizations")
    @Timed
    public ResponseEntity<OrganizationDTO> createOrganization(@Valid @RequestBody OrganizationDTO organizationDTO) throws URISyntaxException {
        log.debug("REST request to save Organization : {}", organizationDTO);
        if (organizationDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new organization cannot already have an ID")).body(null);
        }
        OrganizationDTO result = organizationService.save(organizationDTO);
        return ResponseEntity.created(new URI("/api/organizations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /organizations : Updates an existing organization.
     *
     * @param organizationDTO the organizationDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated organizationDTO,
     * or with status 400 (Bad Request) if the organizationDTO is not valid,
     * or with status 500 (Internal Server Error) if the organizationDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/organizations")
    @Timed
    public ResponseEntity<OrganizationDTO> updateOrganization(@Valid @RequestBody OrganizationDTO organizationDTO) throws URISyntaxException {
        log.debug("REST request to update Organization : {}", organizationDTO);
        if (organizationDTO.getId() == null) {
            return createOrganization(organizationDTO);
        }
        OrganizationDTO result = organizationService.save(organizationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, organizationDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /organizations : get all the organizations.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of organizations in body
     */
    @GetMapping("/organizations")
    @Timed
    public List<OrganizationDTO> getAllOrganizations() {
        log.debug("REST request to get all Organizations");
        return organizationService.findOwn();
        }
    /**
     * GET  /organizations : get own 自己的，属于自己的  the organizations.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of organizations in body
     */
    @GetMapping("/organizationso")
    @Timed
    public List<OrganizationDTO> getOwnOrganizations() {
        log.debug("REST request to get all Organizations");
        return organizationService.findOwn();
        }
    
    /**
     * GET  /organizations : get all the organizations.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of organizations in body
     */
    @GetMapping("/organizationsx")
    @Timed
    public List<OrganizationDTO> getAllOrganizationsx(@RequestParam Map<String, String> params) {
        log.debug("REST request to get all Organizations");
//        return organizationService.findRootOrTree(params);
        return organizationService.findOwn();
        }
   
    @GetMapping("/organizationz")
    @Timed
    public List<OrganizationDTO> getAllOrganizationsz(@RequestParam Map<String, String> params) {
        log.debug("REST request to get all Organizations");
        return organizationService.queryTree(params);
    }

    /**
     * GET  /organizations/:id : get the "id" organization.
     *
     * @param id the id of the organizationDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the organizationDTO, or with status 404 (Not Found)
     */
    @GetMapping("/organizations/{id}")
    @Timed
    public ResponseEntity<OrganizationDTO> getOrganization(@PathVariable Long id) {
        log.debug("REST request to get Organization : {}", id);
        OrganizationDTO organizationDTO = organizationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(organizationDTO));
    }

    /**
     * DELETE  /organizations/:id : delete the "id" organization.
     *
     * @param id the id of the organizationDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/organizations/{id}")
    @Timed
    public ResponseEntity<Void> deleteOrganization(@PathVariable Long id) {
        log.debug("REST request to delete Organization : {}", id);
        organizationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
