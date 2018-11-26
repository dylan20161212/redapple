package com.thtf.deconfliction.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thtf.deconfliction.domain.CaseSalary;
import com.thtf.deconfliction.repository.CaseSalaryRepository;
import com.thtf.deconfliction.service.CaseSalaryService;
import com.thtf.deconfliction.service.dto.CaseSalaryDTO;
import com.thtf.deconfliction.service.mapper.CaseSalaryMapper;


/**
 * Service Implementation for managing CaseSalary.
 */
@Service
@Transactional
public class CaseSalaryServiceImpl implements CaseSalaryService {

    private final Logger log = LoggerFactory.getLogger(CaseSalaryServiceImpl.class);

    private final CaseSalaryRepository caseSalaryRepository;

    private final CaseSalaryMapper caseSalaryMapper;

    public CaseSalaryServiceImpl(CaseSalaryRepository caseSalaryRepository, CaseSalaryMapper caseSalaryMapper) {
        this.caseSalaryRepository = caseSalaryRepository;
        this.caseSalaryMapper = caseSalaryMapper;
    }

    /**
     * Save a caseSalary.
     *
     * @param caseSalaryDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CaseSalaryDTO save(CaseSalaryDTO caseSalaryDTO) {
        log.debug("Request to save CaseSalary : {}", caseSalaryDTO);
        CaseSalary caseSalary = caseSalaryMapper.toEntity(caseSalaryDTO);
        caseSalary = caseSalaryRepository.save(caseSalary);
        return caseSalaryMapper.toDto(caseSalary);
    }

    /**
     * Get all the caseSalaries.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CaseSalaryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CaseSalaries");
        return caseSalaryRepository.findAll(pageable)
            .map(caseSalaryMapper::toDto);
    }

    /**
     * Get one caseSalary by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CaseSalaryDTO findOne(Long id) {
        log.debug("Request to get CaseSalary : {}", id);
        CaseSalary caseSalary = caseSalaryRepository.findById(id).orElse(null);
        return caseSalaryMapper.toDto(caseSalary);
    }

    /**
     * Delete the caseSalary by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CaseSalary : {}", id);
        caseSalaryRepository.deleteById(id);
    }
}
