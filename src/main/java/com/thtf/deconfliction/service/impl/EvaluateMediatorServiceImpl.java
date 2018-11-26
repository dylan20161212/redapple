package com.thtf.deconfliction.service.impl;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thtf.deconfliction.domain.EvaluateMediator;
import com.thtf.deconfliction.domain.EvaluateRule;
import com.thtf.deconfliction.domain.EvaluateRuleItem;
import com.thtf.deconfliction.repository.EvaluateMediatorRepository;
import com.thtf.deconfliction.repository.EvaluateRuleItemRepository;
import com.thtf.deconfliction.repository.EvaluateRuleRepository;
import com.thtf.deconfliction.service.EvaluateMediatorService;
import com.thtf.deconfliction.service.dto.EvaluateMediatorDTO;
import com.thtf.deconfliction.service.mapper.EvaluateMediatorMapper;


/**
 * Service Implementation for managing EvaluateMediator.
 */
@Service
@Transactional
public class EvaluateMediatorServiceImpl implements EvaluateMediatorService {

    private final Logger log = LoggerFactory.getLogger(EvaluateMediatorServiceImpl.class);

    private final EvaluateMediatorRepository evaluateMediatorRepository;
    
    private final EvaluateRuleRepository evaluateRuleRepository;
    
    private final EvaluateRuleItemRepository evaluateRuleItemRepository;

    private final EvaluateMediatorMapper evaluateMediatorMapper;

    public EvaluateMediatorServiceImpl(EvaluateMediatorRepository evaluateMediatorRepository, EvaluateMediatorMapper evaluateMediatorMapper,EvaluateRuleRepository evaluateRuleRepository,EvaluateRuleItemRepository evaluateRuleItemRepository) {
        this.evaluateMediatorRepository = evaluateMediatorRepository;
        this.evaluateMediatorMapper = evaluateMediatorMapper;
        this.evaluateRuleRepository = evaluateRuleRepository;
        this.evaluateRuleItemRepository = evaluateRuleItemRepository;
    }

    /**
     * Save a evaluateMediator.
     *
     * @param evaluateMediatorDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EvaluateMediatorDTO save(EvaluateMediatorDTO evaluateMediatorDTO) {
        log.debug("Request to save EvaluateMediator : {}", evaluateMediatorDTO);
        EvaluateMediator evaluateMediator = evaluateMediatorMapper.toEntity(evaluateMediatorDTO);
        evaluateMediator = evaluateMediatorRepository.save(evaluateMediator);
        return evaluateMediatorMapper.toDto(evaluateMediator);
    }
    
    /**
     * create a evaluateMediator.
     *
     * @param evaluateMediatorDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EvaluateMediatorDTO create(EvaluateMediatorDTO evaluateMediatorDTO) {
        log.debug("Request to save EvaluateMediator : {}", evaluateMediatorDTO);
        EvaluateMediator evaluateMediator = evaluateMediatorMapper.toEntity(evaluateMediatorDTO);
        Set<EvaluateRule> rules = evaluateMediator.getRules();
        Iterator<EvaluateRule> iterator = rules.iterator();
        
        while(iterator.hasNext()) {
        	EvaluateRule rule = iterator.next();
        	Set<EvaluateRuleItem> items = rule.getItems();
        	Iterator<EvaluateRuleItem> i = items.iterator();
        	while(i.hasNext()) {
        		EvaluateRuleItem item = i.next();
        		item = this.evaluateRuleItemRepository.save(item);
        	}
        	rule = this.evaluateRuleRepository.save(rule);
        }
        
        evaluateMediator = evaluateMediatorRepository.save(evaluateMediator);
        return evaluateMediatorMapper.toDto(evaluateMediator);
    }
    
    

    /**
     * Get all the evaluateMediators.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EvaluateMediatorDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EvaluateMediators");
        return evaluateMediatorRepository.findAll(pageable)
            .map(evaluateMediatorMapper::toDto);
    }
    
    /**
     * Get all the evaluateMediators.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EvaluateMediatorDTO> findAll(Map<String,Object> filters) {
        log.debug("Request to get all EvaluateMediators");
        
        return new PageImpl<EvaluateMediator>(evaluateMediatorRepository.findAll(filters), null,
        		evaluateMediatorRepository.getTotalRows(filters)).map(evaluateMediatorMapper::toDto);

    }

    /**
     * Get one evaluateMediator by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public EvaluateMediatorDTO findOne(Long id) {
        log.debug("Request to get EvaluateMediator : {}", id);
        EvaluateMediator evaluateMediator = evaluateMediatorRepository.findOneWithEagerRelationships(id);
        return evaluateMediatorMapper.toDto(evaluateMediator);
    }

    /**
     * Delete the evaluateMediator by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EvaluateMediator : {}", id);
        evaluateMediatorRepository.deleteById(id);
    }
}
