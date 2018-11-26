package com.thtf.deconfliction.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thtf.deconfliction.domain.CaseMediatorSalary;
import com.thtf.deconfliction.repository.CaseMediatorSalaryRepository;
import com.thtf.deconfliction.service.CaseMediatorSalaryService;
import com.thtf.deconfliction.service.dto.CaseMediatorSalaryDTO;
import com.thtf.deconfliction.service.mapper.CaseMediatorSalaryMapper;


/**
 * Service Implementation for managing CaseMediatorSalary.
 */
@Service
@Transactional
public class CaseMediatorSalaryServiceImpl implements CaseMediatorSalaryService {

    private final Logger log = LoggerFactory.getLogger(CaseMediatorSalaryServiceImpl.class);

    private final CaseMediatorSalaryRepository caseMediatorSalaryRepository;

    private final CaseMediatorSalaryMapper caseMediatorSalaryMapper;

    public CaseMediatorSalaryServiceImpl(CaseMediatorSalaryRepository caseMediatorSalaryRepository, CaseMediatorSalaryMapper caseMediatorSalaryMapper) {
        this.caseMediatorSalaryRepository = caseMediatorSalaryRepository;
        this.caseMediatorSalaryMapper = caseMediatorSalaryMapper;
    }

    /**
     * Save a caseMediatorSalary.
     *
     * @param caseMediatorSalaryDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CaseMediatorSalaryDTO save(CaseMediatorSalaryDTO caseMediatorSalaryDTO) {
        log.debug("Request to save CaseMediatorSalary : {}", caseMediatorSalaryDTO);
        CaseMediatorSalary caseMediatorSalary = caseMediatorSalaryMapper.toEntity(caseMediatorSalaryDTO);
        caseMediatorSalary = caseMediatorSalaryRepository.save(caseMediatorSalary);
        return caseMediatorSalaryMapper.toDto(caseMediatorSalary);
    }

    /**
     * Get all the caseMediatorSalaries.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CaseMediatorSalaryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CaseMediatorSalaries");
        return caseMediatorSalaryRepository.findAll(pageable)
            .map(caseMediatorSalaryMapper::toDto);
    }

    /**
     * Get one caseMediatorSalary by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CaseMediatorSalaryDTO findOne(Long id) {
        log.debug("Request to get CaseMediatorSalary : {}", id);
        CaseMediatorSalary caseMediatorSalary = caseMediatorSalaryRepository.findById(id).orElse(null);
        return caseMediatorSalaryMapper.toDto(caseMediatorSalary);
    }

    /**
     * Delete the caseMediatorSalary by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CaseMediatorSalary : {}", id);
        caseMediatorSalaryRepository.deleteById(id);
    }
}
