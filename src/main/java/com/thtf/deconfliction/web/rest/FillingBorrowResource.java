package com.thtf.deconfliction.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

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
import com.thtf.deconfliction.service.FillingBorrowService;
import com.thtf.deconfliction.service.dto.FillingBorrowDTO;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing FillingBorrow.
 */
@RestController
@RequestMapping("/api")
public class FillingBorrowResource {

    private final Logger log = LoggerFactory.getLogger(FillingBorrowResource.class);

    private static final String ENTITY_NAME = "fillingBorrow";

    private final FillingBorrowService fillingBorrowService;

    public FillingBorrowResource(FillingBorrowService fillingBorrowService) {
        this.fillingBorrowService = fillingBorrowService;
    }

    /**
     * POST  /filling-borrows : Create a new fillingBorrow.
     *
     * @param fillingBorrowDTO the fillingBorrowDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new fillingBorrowDTO, or with status 400 (Bad Request) if the fillingBorrow has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/filling-borrows")
    @Timed
    public ResponseEntity<FillingBorrowDTO> createFillingBorrow(@Valid @RequestBody FillingBorrowDTO fillingBorrowDTO) throws URISyntaxException {
        log.debug("REST request to save FillingBorrow : {}", fillingBorrowDTO);
        if (fillingBorrowDTO.getId() != null) {
            throw new BadRequestAlertException("A new fillingBorrow cannot already have an ID", ENTITY_NAME, "idexists");
        }
        fillingBorrowDTO.setBorrowFlag("001");//borrow out
        FillingBorrowDTO result = fillingBorrowService.save(fillingBorrowDTO);
        return ResponseEntity.created(new URI("/api/filling-borrows/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /filling-borrows : Updates an existing fillingBorrow.
     *
     * @param fillingBorrowDTO the fillingBorrowDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated fillingBorrowDTO,
     * or with status 400 (Bad Request) if the fillingBorrowDTO is not valid,
     * or with status 500 (Internal Server Error) if the fillingBorrowDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/filling-borrows")
    @Timed
    public ResponseEntity<FillingBorrowDTO> updateFillingBorrow(@Valid @RequestBody FillingBorrowDTO fillingBorrowDTO) throws URISyntaxException {
        log.debug("REST request to update FillingBorrow : {}", fillingBorrowDTO);
        if (fillingBorrowDTO.getId() == null) {
            return createFillingBorrow(fillingBorrowDTO);
        }
        fillingBorrowDTO.setBorrowFlag("101");//give back
        FillingBorrowDTO result = fillingBorrowService.save(fillingBorrowDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, fillingBorrowDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /filling-borrows : get all the fillingBorrows.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of fillingBorrows in body
     */
    @GetMapping("/filling-borrows")
    @Timed
    public ResponseEntity<List<FillingBorrowDTO>> getAllFillingBorrows(Pageable pageable) {
        log.debug("REST request to get a page of FillingBorrows");
        Page<FillingBorrowDTO> page = fillingBorrowService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/filling-borrows");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * GET  /filling-borrows : get all the fillingBorrows.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of fillingBorrows in body
     */
    @GetMapping("/filling-borrowsx")
    @Timed
    public ResponseEntity<List<FillingBorrowDTO>> getAllFillingBorrowsx(@RequestParam Map<String,Object> filters) {
        log.debug("REST request to get a page of FillingBorrows");
        Page<FillingBorrowDTO> page = fillingBorrowService.findAll(filters);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/filling-borrowsx");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    
    
    

    /**
     * GET  /filling-borrows/:id : get the "id" fillingBorrow.
     *
     * @param id the id of the fillingBorrowDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the fillingBorrowDTO, or with status 404 (Not Found)
     */
    @GetMapping("/filling-borrows/{id}")
    @Timed
    public ResponseEntity<FillingBorrowDTO> getFillingBorrow(@PathVariable Long id) {
        log.debug("REST request to get FillingBorrow : {}", id);
        FillingBorrowDTO fillingBorrowDTO = fillingBorrowService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(fillingBorrowDTO));
    }

    /**
     * DELETE  /filling-borrows/:id : delete the "id" fillingBorrow.
     *
     * @param id the id of the fillingBorrowDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/filling-borrows/{id}")
    @Timed
    public ResponseEntity<Void> deleteFillingBorrow(@PathVariable Long id) {
        log.debug("REST request to delete FillingBorrow : {}", id);
        fillingBorrowService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
