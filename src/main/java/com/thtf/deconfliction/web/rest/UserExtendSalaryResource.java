package com.thtf.deconfliction.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONException;
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
import com.thtf.deconfliction.service.UserExtendSalaryService;
import com.thtf.deconfliction.service.dto.UserExtendSalaryDTO;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing UserExtendSalary.
 */
@RestController
@RequestMapping("/api")
public class UserExtendSalaryResource {

    private final Logger log = LoggerFactory.getLogger(UserExtendSalaryResource.class);

    private static final String ENTITY_NAME = "userExtendSalary";

    private final UserExtendSalaryService userExtendSalaryService;

    public UserExtendSalaryResource(UserExtendSalaryService userExtendSalaryService) {
        this.userExtendSalaryService = userExtendSalaryService;
    }

    /**
     * POST  /user-extend-salaries : Create a new userExtendSalary.
     *
     * @param userExtendSalaryDTO the userExtendSalaryDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userExtendSalaryDTO, or with status 400 (Bad Request) if the userExtendSalary has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/user-extend-salaries")
    @Timed
    public ResponseEntity<UserExtendSalaryDTO> createUserExtendSalary(@RequestBody UserExtendSalaryDTO userExtendSalaryDTO) throws URISyntaxException {
        log.debug("REST request to save UserExtendSalary : {}", userExtendSalaryDTO);
        if (userExtendSalaryDTO.getId() != null) {
            throw new BadRequestAlertException("A new userExtendSalary cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserExtendSalaryDTO result = userExtendSalaryService.save(userExtendSalaryDTO);
        return ResponseEntity.created(new URI("/api/user-extend-salaries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /user-extend-salaries : Updates an existing userExtendSalary.
     *
     * @param userExtendSalaryDTO the userExtendSalaryDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated userExtendSalaryDTO,
     * or with status 400 (Bad Request) if the userExtendSalaryDTO is not valid,
     * or with status 500 (Internal Server Error) if the userExtendSalaryDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/user-extend-salaries")
    @Timed
    public ResponseEntity<UserExtendSalaryDTO> updateUserExtendSalary(@RequestBody UserExtendSalaryDTO userExtendSalaryDTO) throws URISyntaxException {
        log.debug("REST request to update UserExtendSalary : {}", userExtendSalaryDTO);
        if (userExtendSalaryDTO.getId() == null) {
            return createUserExtendSalary(userExtendSalaryDTO);
        }
        UserExtendSalaryDTO result = userExtendSalaryService.save(userExtendSalaryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userExtendSalaryDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /user-extend-salaries : get all the userExtendSalaries.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of userExtendSalaries in body
     */
    @GetMapping("/user-extend-salaries")
    @Timed
    public ResponseEntity<List<UserExtendSalaryDTO>> getAllUserExtendSalaries(Pageable pageable) {
        log.debug("REST request to get a page of UserExtendSalaries");
        Page<UserExtendSalaryDTO> page = userExtendSalaryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/user-extend-salaries");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    
    /**
     * GET  /user-extend-salaries : get all the userExtendSalaries.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of userExtendSalaries in body
     */
    @GetMapping("/user-extend-salariesx")
    @Timed
    public ResponseEntity<List<UserExtendSalaryDTO>> getAllUserExtendSalariesx(@RequestParam Map<String,Object> filters) {
        log.debug("REST request to get a page of UserExtendSalaries");
//        FilterUtil.setFilter(filters, "userExtendSalaryId", "EQUAL", "and");
        Page<UserExtendSalaryDTO> page = userExtendSalaryService.findAll(filters);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/user-extend-salariesx");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    
    
    
    
    /**
     * GET  /user-extend-salaries : get all the userExtendSalaries.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of userExtendSalaries in body
     * @throws JSONException 
     */
    @GetMapping("/user-extend-salaries/generate")
    @Timed
    public ResponseEntity<Map<String,Object>> generateExtendSalaries(Pageable pageable) throws JSONException {
        //String ret = "ok";
        Map<String,Object> retMap = new HashMap<String,Object>();
        retMap.put("generated", true);
        retMap.put("content", "generated success!");
        
	    	if(!this.userExtendSalaryService.computAndSaveUserSalary()){
	    		retMap.put("generated", false);
	            retMap.put("content", "can't generated because existed!!");
	    		//ret = "can't generated because existed!";
	        }
	    	
        
    	
        return new ResponseEntity<>(retMap, null, HttpStatus.OK);
    }
    

    /**
     * GET  /user-extend-salaries/:id : get the "id" userExtendSalary.
     *
     * @param id the id of the userExtendSalaryDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the userExtendSalaryDTO, or with status 404 (Not Found)
     */
    @GetMapping("/user-extend-salaries/{id}")
    @Timed
    public ResponseEntity<UserExtendSalaryDTO> getUserExtendSalary(@PathVariable Long id) {
        log.debug("REST request to get UserExtendSalary : {}", id);
        UserExtendSalaryDTO userExtendSalaryDTO = userExtendSalaryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(userExtendSalaryDTO));
    }

    /**
     * DELETE  /user-extend-salaries/:id : delete the "id" userExtendSalary.
     *
     * @param id the id of the userExtendSalaryDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/user-extend-salaries/{id}")
    @Timed
    public ResponseEntity<Void> deleteUserExtendSalary(@PathVariable Long id) {
        log.debug("REST request to delete UserExtendSalary : {}", id);
        userExtendSalaryService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
