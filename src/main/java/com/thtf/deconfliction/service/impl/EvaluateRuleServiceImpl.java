package com.thtf.deconfliction.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thtf.deconfliction.domain.EvaluateRule;
import com.thtf.deconfliction.repository.EvaluateRuleRepository;
import com.thtf.deconfliction.service.EvaluateRuleService;
import com.thtf.deconfliction.service.dto.EvaluateRuleDTO;
import com.thtf.deconfliction.service.mapper.EvaluateRuleMapper;


/**
 * Service Implementation for managing EvaluateRule.
 */
@Service
@Transactional
public class EvaluateRuleServiceImpl implements EvaluateRuleService {

    private final Logger log = LoggerFactory.getLogger(EvaluateRuleServiceImpl.class);

    private final EvaluateRuleRepository evaluateRuleRepository;

    private final EvaluateRuleMapper evaluateRuleMapper;

    public EvaluateRuleServiceImpl(EvaluateRuleRepository evaluateRuleRepository, EvaluateRuleMapper evaluateRuleMapper) {
        this.evaluateRuleRepository = evaluateRuleRepository;
        this.evaluateRuleMapper = evaluateRuleMapper;
    }

    /**
     * Save a evaluateRule.
     *
     * @param evaluateRuleDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EvaluateRuleDTO save(EvaluateRuleDTO evaluateRuleDTO) {
        log.debug("Request to save EvaluateRule : {}", evaluateRuleDTO);
        EvaluateRule evaluateRule = evaluateRuleMapper.toEntity(evaluateRuleDTO);
        evaluateRule = evaluateRuleRepository.save(evaluateRule);
        return evaluateRuleMapper.toDto(evaluateRule);
    }

    /**
     * Get all the evaluateRules.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EvaluateRuleDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EvaluateRules");
        return evaluateRuleRepository.findAll(pageable)
            .map(evaluateRuleMapper::toDto);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<EvaluateRuleDTO> findAll(Map<String,Object> filters) {
        log.debug("Request to get all EvaluateRules");
        return new PageImpl<EvaluateRule>(evaluateRuleRepository.findAll(filters), null,
        		evaluateRuleRepository.getTotalRows(filters)).map(evaluateRuleMapper::toDto);
    }

    /**
     * Get one evaluateRule by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public EvaluateRuleDTO findOne(Long id) {
        log.debug("Request to get EvaluateRule : {}", id);
        EvaluateRule evaluateRule = evaluateRuleRepository.findOneWithEagerRelationships(id);
        return evaluateRuleMapper.toDto(evaluateRule);
    }

    /**
     * Delete the evaluateRule by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EvaluateRule : {}", id);
        evaluateRuleRepository.deleteById(id);
    }
}
