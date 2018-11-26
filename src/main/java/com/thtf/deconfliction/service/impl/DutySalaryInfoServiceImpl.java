package com.thtf.deconfliction.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thtf.deconfliction.domain.DutySalaryInfo;
import com.thtf.deconfliction.repository.DutySalaryInfoRepository;
import com.thtf.deconfliction.service.DutySalaryInfoService;
import com.thtf.deconfliction.service.dto.DutySalaryInfoDTO;
import com.thtf.deconfliction.service.mapper.DutySalaryInfoMapper;


/**
 * Service Implementation for managing DutySalaryInfo.
 */
@Service
@Transactional
public class DutySalaryInfoServiceImpl implements DutySalaryInfoService {

    private final Logger log = LoggerFactory.getLogger(DutySalaryInfoServiceImpl.class);

    private final DutySalaryInfoRepository dutySalaryInfoRepository;

    private final DutySalaryInfoMapper dutySalaryInfoMapper;

    public DutySalaryInfoServiceImpl(DutySalaryInfoRepository dutySalaryInfoRepository, DutySalaryInfoMapper dutySalaryInfoMapper) {
        this.dutySalaryInfoRepository = dutySalaryInfoRepository;
        this.dutySalaryInfoMapper = dutySalaryInfoMapper;
    }

    /**
     * Save a dutySalaryInfo.
     *
     * @param dutySalaryInfoDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DutySalaryInfoDTO save(DutySalaryInfoDTO dutySalaryInfoDTO) {
        log.debug("Request to save DutySalaryInfo : {}", dutySalaryInfoDTO);
        DutySalaryInfo dutySalaryInfo = dutySalaryInfoMapper.toEntity(dutySalaryInfoDTO);
        dutySalaryInfo = dutySalaryInfoRepository.save(dutySalaryInfo);
        return dutySalaryInfoMapper.toDto(dutySalaryInfo);
    }

    /**
     * Get all the dutySalaryInfos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DutySalaryInfoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DutySalaryInfos");
        return dutySalaryInfoRepository.findAll(pageable)
            .map(dutySalaryInfoMapper::toDto);
    }

    /**
     * Get one dutySalaryInfo by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public DutySalaryInfoDTO findOne(Long id) {
        log.debug("Request to get DutySalaryInfo : {}", id);
        DutySalaryInfo dutySalaryInfo = dutySalaryInfoRepository.findById(id).orElse(null);
        return dutySalaryInfoMapper.toDto(dutySalaryInfo);
    }

    /**
     * Delete the dutySalaryInfo by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DutySalaryInfo : {}", id);
        dutySalaryInfoRepository.deleteById(id);
    }
}
