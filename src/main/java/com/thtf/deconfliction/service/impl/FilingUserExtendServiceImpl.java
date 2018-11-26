package com.thtf.deconfliction.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thtf.deconfliction.domain.FilingUserExtend;
import com.thtf.deconfliction.repository.FilingUserExtendRepository;
import com.thtf.deconfliction.service.FilingUserExtendService;
import com.thtf.deconfliction.service.dto.FilingUserExtendDTO;
import com.thtf.deconfliction.service.mapper.FilingUserExtendMapper;


/**
 * Service Implementation for managing FilingUserExtend.
 */
@Service
@Transactional
public class FilingUserExtendServiceImpl implements FilingUserExtendService {

    private final Logger log = LoggerFactory.getLogger(FilingUserExtendServiceImpl.class);

    private final FilingUserExtendRepository filingUserExtendRepository;

    private final FilingUserExtendMapper filingUserExtendMapper;

    public FilingUserExtendServiceImpl(FilingUserExtendRepository filingUserExtendRepository, FilingUserExtendMapper filingUserExtendMapper) {
        this.filingUserExtendRepository = filingUserExtendRepository;
        this.filingUserExtendMapper = filingUserExtendMapper;
    }

    /**
     * Save a filingUserExtend.
     *
     * @param filingUserExtendDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FilingUserExtendDTO save(FilingUserExtendDTO filingUserExtendDTO) {
        log.debug("Request to save FilingUserExtend : {}", filingUserExtendDTO);
        FilingUserExtend filingUserExtend = filingUserExtendMapper.toEntity(filingUserExtendDTO);
        filingUserExtend = filingUserExtendRepository.save(filingUserExtend);
        return filingUserExtendMapper.toDto(filingUserExtend);
    }

    /**
     * Get all the filingUserExtends.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FilingUserExtendDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FilingUserExtends");
        return filingUserExtendRepository.findAll(pageable)
            .map(filingUserExtendMapper::toDto);
    }

    /**
     * Get one filingUserExtend by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public FilingUserExtendDTO findOne(Long id) {
        log.debug("Request to get FilingUserExtend : {}", id);
        FilingUserExtend filingUserExtend = filingUserExtendRepository.findOneWithEagerRelationships(id);
        return filingUserExtendMapper.toDto(filingUserExtend);
    }

    /**
     * Delete the filingUserExtend by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FilingUserExtend : {}", id);
        filingUserExtendRepository.deleteById(id);
    }
}
