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
import com.thtf.deconfliction.service.UserSalaryInfoService;
import com.thtf.deconfliction.service.dto.UserSalaryInfoDTO;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing UserSalaryInfo.
 */
@RestController
@RequestMapping("/api")
public class UserSalaryInfoResource {

    private final Logger log = LoggerFactory.getLogger(UserSalaryInfoResource.class);

    private static final String ENTITY_NAME = "userSalaryInfo";

    private final UserSalaryInfoService userSalaryInfoService;

    public UserSalaryInfoResource(UserSalaryInfoService userSalaryInfoService) {
        this.userSalaryInfoService = userSalaryInfoService;
    }

    /**
     * POST  /user-salary-infos : Create a new userSalaryInfo.
     *
     * @param userSalaryInfoDTO the userSalaryInfoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userSalaryInfoDTO, or with status 400 (Bad Request) if the userSalaryInfo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/user-salary-infos")
    @Timed
    public ResponseEntity<UserSalaryInfoDTO> createUserSalaryInfo(@RequestBody UserSalaryInfoDTO userSalaryInfoDTO) throws URISyntaxException {
        log.debug("REST request to save UserSalaryInfo : {}", userSalaryInfoDTO);
        if (userSalaryInfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new userSalaryInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserSalaryInfoDTO result = userSalaryInfoService.save(userSalaryInfoDTO);
        return ResponseEntity.created(new URI("/api/user-salary-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /user-salary-infos : Updates an existing userSalaryInfo.
     *
     * @param userSalaryInfoDTO the userSalaryInfoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated userSalaryInfoDTO,
     * or with status 400 (Bad Request) if the userSalaryInfoDTO is not valid,
     * or with status 500 (Internal Server Error) if the userSalaryInfoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/user-salary-infos")
    @Timed
    public ResponseEntity<UserSalaryInfoDTO> updateUserSalaryInfo(@RequestBody UserSalaryInfoDTO userSalaryInfoDTO) throws URISyntaxException {
        log.debug("REST request to update UserSalaryInfo : {}", userSalaryInfoDTO);
        if (userSalaryInfoDTO.getId() == null) {
            return createUserSalaryInfo(userSalaryInfoDTO);
        }
        UserSalaryInfoDTO result = userSalaryInfoService.save(userSalaryInfoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userSalaryInfoDTO.getId().toString()))
            .body(result);
    }
    
    
    

    /**
     * GET  /user-salary-infos : get all the userSalaryInfos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of userSalaryInfos in body
     */
    @GetMapping("/user-salary-infos")
    @Timed
    public ResponseEntity<List<UserSalaryInfoDTO>> getAllUserSalaryInfos(Pageable pageable) {
        log.debug("REST request to get a page of UserSalaryInfos");
        Page<UserSalaryInfoDTO> page = userSalaryInfoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/user-salary-infos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    @GetMapping("/user-salary-infosx")
    @Timed
    public ResponseEntity<List<UserSalaryInfoDTO>> getAllUserSalaryInfos(@RequestParam Map<String,Object> filters) {
        log.debug("REST request to get a page of UserSalaryInfos");
        //FilterUtil.setFilter(filters, "userExtendSalary.id", "EQUAL", "and");
        Page<UserSalaryInfoDTO> page = userSalaryInfoService.findAll(filters);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/user-salary-infosx");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    
    @GetMapping("/userExtendSalary/user-salary-infos")
    @Timed
    public ResponseEntity<List<UserSalaryInfoDTO>> getByUserExtendSalaryId(@RequestParam Long userExtendSalaryId) {
        log.debug("REST request to get a page of UserSalaryInfos");
        //FilterUtil.setFilter(filters, "userExtendSalary.id", "EQUAL", "and");
        List<UserSalaryInfoDTO> list = userSalaryInfoService.findByUserExtendSalaryId(userExtendSalaryId);
        //HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/user-salary-infosx");
        return new ResponseEntity<>(list, null, HttpStatus.OK);
    }
    
    

    /**
     * GET  /user-salary-infos/:id : get the "id" userSalaryInfo.
     *
     * @param id the id of the userSalaryInfoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the userSalaryInfoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/user-salary-infos/{id}")
    @Timed
    public ResponseEntity<UserSalaryInfoDTO> getUserSalaryInfo(@PathVariable Long id) {
        log.debug("REST request to get UserSalaryInfo : {}", id);
        UserSalaryInfoDTO userSalaryInfoDTO = userSalaryInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(userSalaryInfoDTO));
    }

    /**
     * DELETE  /user-salary-infos/:id : delete the "id" userSalaryInfo.
     *
     * @param id the id of the userSalaryInfoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/user-salary-infos/{id}")
    @Timed
    public ResponseEntity<Void> deleteUserSalaryInfo(@PathVariable Long id) {
        log.debug("REST request to delete UserSalaryInfo : {}", id);
        userSalaryInfoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
