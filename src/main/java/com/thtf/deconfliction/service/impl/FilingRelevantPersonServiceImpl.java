package com.thtf.deconfliction.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thtf.deconfliction.domain.FilingRelevantPerson;
import com.thtf.deconfliction.repository.FilingRelevantPersonRepository;
import com.thtf.deconfliction.service.FilingRelevantPersonService;
import com.thtf.deconfliction.service.dto.FilingRelevantPersonDTO;
import com.thtf.deconfliction.service.mapper.FilingRelevantPersonMapper;


/**
 * Service Implementation for managing FilingRelevantPerson.
 */
@Service
@Transactional
public class FilingRelevantPersonServiceImpl implements FilingRelevantPersonService {

    private final Logger log = LoggerFactory.getLogger(FilingRelevantPersonServiceImpl.class);

    private final FilingRelevantPersonRepository filingRelevantPersonRepository;

    private final FilingRelevantPersonMapper filingRelevantPersonMapper;

    public FilingRelevantPersonServiceImpl(FilingRelevantPersonRepository filingRelevantPersonRepository, FilingRelevantPersonMapper filingRelevantPersonMapper) {
        this.filingRelevantPersonRepository = filingRelevantPersonRepository;
        this.filingRelevantPersonMapper = filingRelevantPersonMapper;
    }

    /**
     * Save a filingRelevantPerson.
     *
     * @param filingRelevantPersonDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FilingRelevantPersonDTO save(FilingRelevantPersonDTO filingRelevantPersonDTO) {
        log.debug("Request to save FilingRelevantPerson : {}", filingRelevantPersonDTO);
        FilingRelevantPerson filingRelevantPerson = filingRelevantPersonMapper.toEntity(filingRelevantPersonDTO);
        filingRelevantPerson = filingRelevantPersonRepository.save(filingRelevantPerson);
        return filingRelevantPersonMapper.toDto(filingRelevantPerson);
    }

    /**
     * Get all the filingRelevantPeople.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FilingRelevantPersonDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FilingRelevantPeople");
        return filingRelevantPersonRepository.findAll(pageable)
            .map(filingRelevantPersonMapper::toDto);
    }

    /**
     * Get one filingRelevantPerson by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public FilingRelevantPersonDTO findOne(Long id) {
        log.debug("Request to get FilingRelevantPerson : {}", id);
        FilingRelevantPerson filingRelevantPerson = filingRelevantPersonRepository.findOneWithEagerRelationships(id);
        return filingRelevantPersonMapper.toDto(filingRelevantPerson);
    }

    /**
     * Delete the filingRelevantPerson by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FilingRelevantPerson : {}", id);
        filingRelevantPersonRepository.deleteById(id);
    }

	@Override
    @Transactional(readOnly = true)
	public List<FilingRelevantPersonDTO> findByCid(Long cid) {
		return filingRelevantPersonMapper.toDto(filingRelevantPersonRepository.findByCid(cid));
	}
}
