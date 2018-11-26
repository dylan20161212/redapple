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
import com.thtf.deconfliction.service.GroupMemberService;
import com.thtf.deconfliction.service.dto.GroupMemberDTO;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing GroupMember.
 */
@RestController
@RequestMapping("/api")
public class GroupMemberResource {

    private final Logger log = LoggerFactory.getLogger(GroupMemberResource.class);

    private static final String ENTITY_NAME = "groupMember";

    private final GroupMemberService groupMemberService;

    public GroupMemberResource(GroupMemberService groupMemberService) {
        this.groupMemberService = groupMemberService;
    }

    /**
     * POST  /group-members : Create a new groupMember.
     *
     * @param groupMemberDTO the groupMemberDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new groupMemberDTO, or with status 400 (Bad Request) if the groupMember has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/group-members")
    @Timed
    public ResponseEntity<GroupMemberDTO> createGroupMember(@RequestBody GroupMemberDTO groupMemberDTO) throws URISyntaxException {
        log.debug("REST request to save GroupMember : {}", groupMemberDTO);
        if (groupMemberDTO.getId() != null) {
            throw new BadRequestAlertException("A new groupMember cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GroupMemberDTO result = groupMemberService.save(groupMemberDTO);
        return ResponseEntity.created(new URI("/api/group-members/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /group-members : Updates an existing groupMember.
     *
     * @param groupMemberDTO the groupMemberDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated groupMemberDTO,
     * or with status 400 (Bad Request) if the groupMemberDTO is not valid,
     * or with status 500 (Internal Server Error) if the groupMemberDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/group-members")
    @Timed
    public ResponseEntity<GroupMemberDTO> updateGroupMember(@RequestBody GroupMemberDTO groupMemberDTO) throws URISyntaxException {
        log.debug("REST request to update GroupMember : {}", groupMemberDTO);
        if (groupMemberDTO.getId() == null) {
            return createGroupMember(groupMemberDTO);
        }
        GroupMemberDTO result = groupMemberService.save(groupMemberDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, groupMemberDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /group-members : get all the groupMembers.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of groupMembers in body
     */
    @GetMapping("/group-members")
    @Timed
    public ResponseEntity<List<GroupMemberDTO>> getAllGroupMembers(Pageable pageable) {
        log.debug("REST request to get a page of GroupMembers");
        Page<GroupMemberDTO> page = groupMemberService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/group-members");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /group-members/:id : get the "id" groupMember.
     *
     * @param id the id of the groupMemberDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the groupMemberDTO, or with status 404 (Not Found)
     */
    @GetMapping("/group-members/{id}")
    @Timed
    public ResponseEntity<GroupMemberDTO> getGroupMember(@PathVariable Long id) {
        log.debug("REST request to get GroupMember : {}", id);
        GroupMemberDTO groupMemberDTO = groupMemberService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(groupMemberDTO));
    }

    /**
     * DELETE  /group-members/:id : delete the "id" groupMember.
     *
     * @param id the id of the groupMemberDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/group-members/{id}")
    @Timed
    public ResponseEntity<Void> deleteGroupMember(@PathVariable Long id) {
        log.debug("REST request to delete GroupMember : {}", id);
        groupMemberService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
