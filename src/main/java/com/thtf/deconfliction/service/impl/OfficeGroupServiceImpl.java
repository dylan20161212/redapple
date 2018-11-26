package com.thtf.deconfliction.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thtf.deconfliction.domain.OfficeGroup;
import com.thtf.deconfliction.repository.OfficeGroupRepository;
import com.thtf.deconfliction.service.OfficeGroupService;
import com.thtf.deconfliction.service.dto.OfficeGroupDTO;
import com.thtf.deconfliction.service.mapper.OfficeGroupMapper;


/**
 * Service Implementation for managing OfficeGroup.
 */
@Service
@Transactional
public class OfficeGroupServiceImpl implements OfficeGroupService {

    private final Logger log = LoggerFactory.getLogger(OfficeGroupServiceImpl.class);

    private final OfficeGroupRepository officeGroupRepository;

    private final OfficeGroupMapper officeGroupMapper;

    public OfficeGroupServiceImpl(OfficeGroupRepository officeGroupRepository, OfficeGroupMapper officeGroupMapper) {
        this.officeGroupRepository = officeGroupRepository;
        this.officeGroupMapper = officeGroupMapper;
    }

    /**
     * Save a officeGroup.
     *
     * @param officeGroupDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OfficeGroupDTO save(OfficeGroupDTO officeGroupDTO) {
        log.debug("Request to save OfficeGroup : {}", officeGroupDTO);
        OfficeGroup officeGroup = officeGroupMapper.toEntity(officeGroupDTO);
        officeGroup = officeGroupRepository.save(officeGroup);
        return officeGroupMapper.toDto(officeGroup);
    }

    /**
     * Get all the officeGroups.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OfficeGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OfficeGroups");
        return officeGroupRepository.findAll(pageable)
            .map(officeGroupMapper::toDto);
    }

    /**
     * Get one officeGroup by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public OfficeGroupDTO findOne(Long id) {
        log.debug("Request to get OfficeGroup : {}", id);
        OfficeGroup officeGroup = officeGroupRepository.findById(id).orElse(null);
        return officeGroupMapper.toDto(officeGroup);
    }

    /**
     * Delete the officeGroup by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OfficeGroup : {}", id);
        officeGroupRepository.deleteById(id);
    }
}
