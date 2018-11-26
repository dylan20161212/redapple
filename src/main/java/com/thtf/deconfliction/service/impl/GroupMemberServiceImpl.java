package com.thtf.deconfliction.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thtf.deconfliction.domain.GroupMember;
import com.thtf.deconfliction.repository.GroupMemberRepository;
import com.thtf.deconfliction.service.GroupMemberService;
import com.thtf.deconfliction.service.dto.GroupMemberDTO;
import com.thtf.deconfliction.service.mapper.GroupMemberMapper;


/**
 * Service Implementation for managing GroupMember.
 */
@Service
@Transactional
public class GroupMemberServiceImpl implements GroupMemberService {

    private final Logger log = LoggerFactory.getLogger(GroupMemberServiceImpl.class);

    private final GroupMemberRepository groupMemberRepository;

    private final GroupMemberMapper groupMemberMapper;

    public GroupMemberServiceImpl(GroupMemberRepository groupMemberRepository, GroupMemberMapper groupMemberMapper) {
        this.groupMemberRepository = groupMemberRepository;
        this.groupMemberMapper = groupMemberMapper;
    }

    /**
     * Save a groupMember.
     *
     * @param groupMemberDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public GroupMemberDTO save(GroupMemberDTO groupMemberDTO) {
        log.debug("Request to save GroupMember : {}", groupMemberDTO);
        GroupMember groupMember = groupMemberMapper.toEntity(groupMemberDTO);
        groupMember = groupMemberRepository.save(groupMember);
        return groupMemberMapper.toDto(groupMember);
    }

    /**
     * Get all the groupMembers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GroupMemberDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GroupMembers");
        return groupMemberRepository.findAll(pageable)
            .map(groupMemberMapper::toDto);
    }

    /**
     * Get one groupMember by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public GroupMemberDTO findOne(Long id) {
        log.debug("Request to get GroupMember : {}", id);
        GroupMember groupMember = groupMemberRepository.findById(id).orElse(null);
        return groupMemberMapper.toDto(groupMember);
    }

    /**
     * Delete the groupMember by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete GroupMember : {}", id);
        groupMemberRepository.deleteById(id);
    }
}
