package com.thtf.deconfliction.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thtf.deconfliction.domain.RelevantPerson;
import com.thtf.deconfliction.repository.RelevantPersonRepository;
import com.thtf.deconfliction.service.RelevantPersonService;
import com.thtf.deconfliction.service.dto.RelevantPersonDTO;
import com.thtf.deconfliction.service.mapper.RelevantPersonMapper;


/**
 * Service Implementation for managing RelevantPerson.
 */
@Service
@Transactional
public class RelevantPersonServiceImpl implements RelevantPersonService {

    private final Logger log = LoggerFactory.getLogger(RelevantPersonServiceImpl.class);

    private final RelevantPersonRepository relevantPersonRepository;

    private final RelevantPersonMapper relevantPersonMapper;

    public RelevantPersonServiceImpl(RelevantPersonRepository relevantPersonRepository, RelevantPersonMapper relevantPersonMapper) {
        this.relevantPersonRepository = relevantPersonRepository;
        this.relevantPersonMapper = relevantPersonMapper;
    }

    /**
     * Save a relevantPerson.
     *
     * @param relevantPersonDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RelevantPersonDTO save(RelevantPersonDTO relevantPersonDTO) {
        log.debug("Request to save RelevantPerson : {}", relevantPersonDTO);
        RelevantPerson relevantPerson = relevantPersonMapper.toEntity(relevantPersonDTO);
        relevantPerson = relevantPersonRepository.save(relevantPerson);
        return relevantPersonMapper.toDto(relevantPerson);
    }

    /**
     * Get all the relevantPeople.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RelevantPersonDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RelevantPeople");
        return relevantPersonRepository.findAll(pageable)
            .map(relevantPersonMapper::toDto);
    }

    /**
     * Get one relevantPerson by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public RelevantPersonDTO findOne(Long id) {
        log.debug("Request to get RelevantPerson : {}", id);
        RelevantPerson relevantPerson = relevantPersonRepository.findOneWithEagerRelationships(id);
        return relevantPersonMapper.toDto(relevantPerson);
    }

    /**
     * Delete the relevantPerson by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RelevantPerson : {}", id);
        relevantPersonRepository.deleteById(id);
    }

	@Override
	@Transactional(readOnly = true)
	public Page<RelevantPersonDTO> findAll(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return new PageImpl<>(relevantPersonRepository.findAll(params),
				null, relevantPersonRepository.getTotalRows(params)).map(relevantPersonMapper::toDto);
	}

	@Override
	@Transactional(readOnly = true)
	public List<RelevantPersonDTO> findByCid(Long cid) {
		// TODO Auto-generated method stub
		return relevantPersonMapper.toDto(relevantPersonRepository.findByCid(cid));
	}
}
