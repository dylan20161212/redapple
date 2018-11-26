package com.thtf.deconfliction.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thtf.deconfliction.domain.EvaluatService;
import com.thtf.deconfliction.repository.EvaluatServiceRepository;
import com.thtf.deconfliction.service.EvaluatServiceService;
import com.thtf.deconfliction.service.dto.EvaluatServiceDTO;
import com.thtf.deconfliction.service.mapper.EvaluatServiceMapper;


/**
 * Service Implementation for managing EvaluatService.
 */
@Service
@Transactional
public class EvaluatServiceServiceImpl implements EvaluatServiceService {

    private final Logger log = LoggerFactory.getLogger(EvaluatServiceServiceImpl.class);

    private final EvaluatServiceRepository evaluatServiceRepository;

    private final EvaluatServiceMapper evaluatServiceMapper;

    public EvaluatServiceServiceImpl(EvaluatServiceRepository evaluatServiceRepository, EvaluatServiceMapper evaluatServiceMapper) {
        this.evaluatServiceRepository = evaluatServiceRepository;
        this.evaluatServiceMapper = evaluatServiceMapper;
    }

    /**
     * Save a evaluatService.
     *
     * @param evaluatServiceDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EvaluatServiceDTO save(EvaluatServiceDTO evaluatServiceDTO) {
        log.debug("Request to save EvaluatService : {}", evaluatServiceDTO);
        EvaluatService evaluatService = evaluatServiceMapper.toEntity(evaluatServiceDTO);
        evaluatService = evaluatServiceRepository.save(evaluatService);
        return evaluatServiceMapper.toDto(evaluatService);
    }

    /**
     * Get all the evaluatServices.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EvaluatServiceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EvaluatServices");
        return evaluatServiceRepository.findAll(pageable)
            .map(evaluatServiceMapper::toDto);
    }

    /**
     * Get one evaluatService by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public EvaluatServiceDTO findOne(Long id) {
        log.debug("Request to get EvaluatService : {}", id);
        EvaluatService evaluatService = evaluatServiceRepository.findById(id).orElse(null);
        return evaluatServiceMapper.toDto(evaluatService);
    }

    /**
     * Delete the evaluatService by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EvaluatService : {}", id);
        evaluatServiceRepository.deleteById(id);
    }
}
