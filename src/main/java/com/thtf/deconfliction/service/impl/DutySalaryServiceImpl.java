package com.thtf.deconfliction.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thtf.deconfliction.domain.DutySalary;
import com.thtf.deconfliction.repository.DutySalaryRepository;
import com.thtf.deconfliction.service.DutySalaryService;
import com.thtf.deconfliction.service.dto.DutySalaryDTO;
import com.thtf.deconfliction.service.mapper.DutySalaryMapper;


/**
 * Service Implementation for managing DutySalary.
 */
@Service
@Transactional
public class DutySalaryServiceImpl implements DutySalaryService {

    private final Logger log = LoggerFactory.getLogger(DutySalaryServiceImpl.class);

    private final DutySalaryRepository dutySalaryRepository;

    private final DutySalaryMapper dutySalaryMapper;

    public DutySalaryServiceImpl(DutySalaryRepository dutySalaryRepository, DutySalaryMapper dutySalaryMapper) {
        this.dutySalaryRepository = dutySalaryRepository;
        this.dutySalaryMapper = dutySalaryMapper;
    }

    /**
     * Save a dutySalary.
     *
     * @param dutySalaryDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DutySalaryDTO save(DutySalaryDTO dutySalaryDTO) {
        log.debug("Request to save DutySalary : {}", dutySalaryDTO);
        DutySalary dutySalary = dutySalaryMapper.toEntity(dutySalaryDTO);
        dutySalary = dutySalaryRepository.save(dutySalary);
        return dutySalaryMapper.toDto(dutySalary);
    }

    /**
     * Get all the dutySalaries.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DutySalaryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DutySalaries");
        return dutySalaryRepository.findAll(pageable)
            .map(dutySalaryMapper::toDto);
    }
    
    /**
     * Get all the dutySalaries.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DutySalaryDTO> findAll( Map<String,Object> filters) {
        log.debug("Request to get all DutySalaries");
        return new PageImpl<DutySalary>(dutySalaryRepository.findAll(filters), null,
        		dutySalaryRepository.getTotalRows(filters)).map(dutySalaryMapper::toDto);
    }

    /**
     * Get one dutySalary by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public DutySalaryDTO findOne(Long id) {
        log.debug("Request to get DutySalary : {}", id);
        DutySalary dutySalary = dutySalaryRepository.findById(id).orElse(null);
        return dutySalaryMapper.toDto(dutySalary);
    }

    /**
     * Delete the dutySalary by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DutySalary : {}", id);
        dutySalaryRepository.deleteById(id);
    }
}
