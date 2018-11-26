package com.thtf.deconfliction.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thtf.deconfliction.domain.EvaluateMission;
import com.thtf.deconfliction.repository.EvaluateMissionRepository;
import com.thtf.deconfliction.service.EvaluateMissionService;
import com.thtf.deconfliction.service.dto.EvaluateMissionDTO;
import com.thtf.deconfliction.service.mapper.EvaluateMissionMapper;


/**
 * Service Implementation for managing EvaluateMission.
 */
@Service
@Transactional
public class EvaluateMissionServiceImpl implements EvaluateMissionService {

    private final Logger log = LoggerFactory.getLogger(EvaluateMissionServiceImpl.class);

    private final EvaluateMissionRepository evaluateMissionRepository;

    private final EvaluateMissionMapper evaluateMissionMapper;

    public EvaluateMissionServiceImpl(EvaluateMissionRepository evaluateMissionRepository, EvaluateMissionMapper evaluateMissionMapper) {
        this.evaluateMissionRepository = evaluateMissionRepository;
        this.evaluateMissionMapper = evaluateMissionMapper;
    }

    /**
     * Save a evaluateMission.
     *
     * @param evaluateMissionDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EvaluateMissionDTO save(EvaluateMissionDTO evaluateMissionDTO) {
        log.debug("Request to save EvaluateMission : {}", evaluateMissionDTO);
        EvaluateMission evaluateMission = evaluateMissionMapper.toEntity(evaluateMissionDTO);
        evaluateMission = evaluateMissionRepository.save(evaluateMission);
        return evaluateMissionMapper.toDto(evaluateMission);
    }

    /**
     * Get all the evaluateMissions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EvaluateMissionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EvaluateMissions");
        return evaluateMissionRepository.findAll(pageable)
            .map(evaluateMissionMapper::toDto);
    }

    /**
     * Get one evaluateMission by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public EvaluateMissionDTO findOne(Long id) {
        log.debug("Request to get EvaluateMission : {}", id);
        EvaluateMission evaluateMission = evaluateMissionRepository.findById(id).orElse(null);
        return evaluateMissionMapper.toDto(evaluateMission);
    }

    /**
     * Delete the evaluateMission by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EvaluateMission : {}", id);
        evaluateMissionRepository.deleteById(id);
    }
}
