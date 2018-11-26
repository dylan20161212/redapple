package com.thtf.deconfliction.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thtf.deconfliction.service.dto.GroupMemberDTO;

/**
 * Service Interface for managing GroupMember.
 */
public interface GroupMemberService {

    /**
     * Save a groupMember.
     *
     * @param groupMemberDTO the entity to save
     * @return the persisted entity
     */
    GroupMemberDTO save(GroupMemberDTO groupMemberDTO);

    /**
     * Get all the groupMembers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<GroupMemberDTO> findAll(Pageable pageable);

    /**
     * Get the "id" groupMember.
     *
     * @param id the id of the entity
     * @return the entity
     */
    GroupMemberDTO findOne(Long id);

    /**
     * Delete the "id" groupMember.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
