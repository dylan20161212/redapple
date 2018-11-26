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
import com.thtf.deconfliction.service.UserExtendService;
import com.thtf.deconfliction.service.dto.UserExtendDTO;

import io.github.jhipster.web.util.ResponseUtil;


/**
 * REST controller for managing UserExtend.
 */
@RestController
@RequestMapping("/api")
public class UserExtendResource {

    private final Logger log = LoggerFactory.getLogger(UserExtendResource.class);

    private static final String ENTITY_NAME = "userExtend";

    private final UserExtendService userExtendService;

    public UserExtendResource(UserExtendService userExtendService) {
        this.userExtendService = userExtendService;
    }

    /**
     * POST  /user-extends : Create a new userExtend.
     *
     * @param userExtendDTO the userExtendDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userExtendDTO, or with status 400 (Bad Request) if the userExtend has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/user-extends")
    @Timed
    public ResponseEntity<UserExtendDTO> createUserExtend(@RequestBody UserExtendDTO userExtendDTO) throws URISyntaxException {
        log.debug("REST request to save UserExtend : {}", userExtendDTO);
        if (userExtendDTO.getId() != null) {
            throw new BadRequestAlertException("A new userExtend cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserExtendDTO result = userExtendService.save(userExtendDTO);
        return ResponseEntity.created(new URI("/api/user-extends/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /user-extends : Updates an existing userExtend.
     *
     * @param userExtendDTO the userExtendDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated userExtendDTO,
     * or with status 400 (Bad Request) if the userExtendDTO is not valid,
     * or with status 500 (Internal Server Error) if the userExtendDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/user-extends")
    @Timed
    public ResponseEntity<UserExtendDTO> updateUserExtend(@RequestBody UserExtendDTO userExtendDTO) throws URISyntaxException {
        log.debug("REST request to update UserExtend : {}", userExtendDTO);
        if (userExtendDTO.getId() == null) {
            return createUserExtend(userExtendDTO);
        }
        UserExtendDTO result = userExtendService.save(userExtendDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userExtendDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /user-extends : get all the userExtends.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of userExtends in body
     */
    @GetMapping("/user-extends")
    @Timed
    public ResponseEntity<List<UserExtendDTO>> getAllUserExtends(Pageable pageable) {
        log.debug("REST request to get a page of UserExtends");
        Page<UserExtendDTO> page = userExtendService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/user-extends");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    

    
   
    /**
     * GET  /user-extends : get all the userExtends.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of userExtends in body
     */
    @GetMapping("/user-extendsx")
    @Timed
    public ResponseEntity<List<UserExtendDTO>> getAllUserExtendsx(@RequestParam Map<String,Object>filters) {
        log.debug("REST request to get a page of UserExtends");
        Page<UserExtendDTO> page = userExtendService.findAll(filters);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/user-extends");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /user-extends/:id : get the "id" userExtend.
     *
     * @param id the id of the userExtendDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the userExtendDTO, or with status 404 (Not Found)
     */
    @GetMapping("/user-extends/{id}")
    @Timed
    public ResponseEntity<UserExtendDTO> getUserExtend(@PathVariable Long id) {
        log.debug("REST request to get UserExtend : {}", id);
        UserExtendDTO userExtendDTO = userExtendService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(userExtendDTO));
    }

    /**
     * DELETE  /user-extends/:id : delete the "id" userExtend.
     *
     * @param id the id of the userExtendDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/user-extends/{id}")
    @Timed
    public ResponseEntity<Void> deleteUserExtend(@PathVariable Long id) {
        log.debug("REST request to delete UserExtend : {}", id);
        userExtendService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
