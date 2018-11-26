package com.thtf.deconfliction.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thtf.deconfliction.domain.EvaluateRuleItem;
import com.thtf.deconfliction.repository.EvaluateRuleItemRepository;
import com.thtf.deconfliction.service.EvaluateRuleItemService;
import com.thtf.deconfliction.service.dto.EvaluateRuleItemDTO;
import com.thtf.deconfliction.service.mapper.EvaluateRuleItemMapper;


/**
 * Service Implementation for managing EvaluateRuleItem.
 */
@Service
@Transactional
public class EvaluateRuleItemServiceImpl implements EvaluateRuleItemService {

    private final Logger log = LoggerFactory.getLogger(EvaluateRuleItemServiceImpl.class);

    private final EvaluateRuleItemRepository evaluateRuleItemRepository;

    private final EvaluateRuleItemMapper evaluateRuleItemMapper;

    public EvaluateRuleItemServiceImpl(EvaluateRuleItemRepository evaluateRuleItemRepository, EvaluateRuleItemMapper evaluateRuleItemMapper) {
        this.evaluateRuleItemRepository = evaluateRuleItemRepository;
        this.evaluateRuleItemMapper = evaluateRuleItemMapper;
    }

    /**
     * Save a evaluateRuleItem.
     *
     * @param evaluateRuleItemDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EvaluateRuleItemDTO save(EvaluateRuleItemDTO evaluateRuleItemDTO) {
        log.debug("Request to save EvaluateRuleItem : {}", evaluateRuleItemDTO);
        EvaluateRuleItem evaluateRuleItem = evaluateRuleItemMapper.toEntity(evaluateRuleItemDTO);
        evaluateRuleItem = evaluateRuleItemRepository.save(evaluateRuleItem);
        return evaluateRuleItemMapper.toDto(evaluateRuleItem);
    }

    /**
     * Get all the evaluateRuleItems.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EvaluateRuleItemDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EvaluateRuleItems");
        return evaluateRuleItemRepository.findAll(pageable)
            .map(evaluateRuleItemMapper::toDto);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<EvaluateRuleItemDTO> findAll(Map<String,Object> filters) {
        log.debug("Request to get all EvaluateRules");
        return new PageImpl<EvaluateRuleItem>(evaluateRuleItemRepository.findAll(filters), null,
        		evaluateRuleItemRepository.getTotalRows(filters)).map(evaluateRuleItemMapper::toDto);
    }

    /**
     * Get one evaluateRuleItem by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public EvaluateRuleItemDTO findOne(Long id) {
        log.debug("Request to get EvaluateRuleItem : {}", id);
        EvaluateRuleItem evaluateRuleItem = evaluateRuleItemRepository.findById(id).orElse(null);
        return evaluateRuleItemMapper.toDto(evaluateRuleItem);
    }

    /**
     * Delete the evaluateRuleItem by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EvaluateRuleItem : {}", id);
        evaluateRuleItemRepository.deleteById(id);
    }
}
