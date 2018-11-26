package com.thtf.deconfliction.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thtf.deconfliction.domain.FillingCaseProcessInfo;
import com.thtf.deconfliction.repository.FillingCaseProcessInfoRepository;
import com.thtf.deconfliction.service.FillingCaseProcessInfoService;
import com.thtf.deconfliction.service.dto.FillingCaseProcessInfoDTO;
import com.thtf.deconfliction.service.mapper.FillingCaseProcessInfoMapper;

/**
 * Service Implementation for managing FillingCaseProcessInfo.
 */
@Service
@Transactional
public class FillingCaseProcessInfoServiceImpl implements FillingCaseProcessInfoService {

	private final Logger log = LoggerFactory.getLogger(FillingCaseProcessInfoServiceImpl.class);

	private final FillingCaseProcessInfoRepository fillingCaseProcessInfoRepository;

	private final FillingCaseProcessInfoMapper fillingCaseProcessInfoMapper;

	public FillingCaseProcessInfoServiceImpl(FillingCaseProcessInfoRepository fillingCaseProcessInfoRepository,
			FillingCaseProcessInfoMapper fillingCaseProcessInfoMapper) {
		this.fillingCaseProcessInfoRepository = fillingCaseProcessInfoRepository;
		this.fillingCaseProcessInfoMapper = fillingCaseProcessInfoMapper;
	}

	/**
	 * Save a fillingCaseProcessInfo.
	 *
	 * @param fillingCaseProcessInfoDTO
	 *            the entity to save
	 * @return the persisted entity
	 */
	@Override
	public FillingCaseProcessInfoDTO save(FillingCaseProcessInfoDTO fillingCaseProcessInfoDTO) {
		log.debug("Request to save FillingCaseProcessInfo : {}", fillingCaseProcessInfoDTO);
		FillingCaseProcessInfo fillingCaseProcessInfo = fillingCaseProcessInfoMapper
				.toEntity(fillingCaseProcessInfoDTO);
		fillingCaseProcessInfo = fillingCaseProcessInfoRepository.save(fillingCaseProcessInfo);
		return fillingCaseProcessInfoMapper.toDto(fillingCaseProcessInfo);
	}

	/**
	 * Get all the fillingCaseProcessInfos.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<FillingCaseProcessInfoDTO> findAll(Pageable pageable) {
		log.debug("Request to get all FillingCaseProcessInfos");
		return fillingCaseProcessInfoRepository.findAll(pageable).map(fillingCaseProcessInfoMapper::toDto);
	}

	/**
	 * Get one fillingCaseProcessInfo by id.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public FillingCaseProcessInfoDTO findOne(Long id) {
		log.debug("Request to get FillingCaseProcessInfo : {}", id);
		FillingCaseProcessInfo fillingCaseProcessInfo = fillingCaseProcessInfoRepository.findById(id).orElse(null);
		return fillingCaseProcessInfoMapper.toDto(fillingCaseProcessInfo);
	}

	/**
	 * Delete the fillingCaseProcessInfo by id.
	 *
	 * @param id
	 *            the id of the entity
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete FillingCaseProcessInfo : {}", id);
		fillingCaseProcessInfoRepository.deleteById(id);
	}

	@Override
	public Page<FillingCaseProcessInfoDTO> findAll(Map<String, Object> params) {
		return new PageImpl<FillingCaseProcessInfo>(fillingCaseProcessInfoRepository.findAll(params), null,
				fillingCaseProcessInfoRepository.getTotalRows(params)).map(fillingCaseProcessInfoMapper::toDto);
	}
}
