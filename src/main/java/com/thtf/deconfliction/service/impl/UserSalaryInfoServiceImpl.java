package com.thtf.deconfliction.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thtf.deconfliction.domain.UserSalaryInfo;
import com.thtf.deconfliction.repository.UserSalaryInfoRepository;
import com.thtf.deconfliction.service.UserSalaryInfoService;
import com.thtf.deconfliction.service.dto.UserSalaryInfoDTO;
import com.thtf.deconfliction.service.mapper.UserSalaryInfoMapper;


/**
 * Service Implementation for managing UserSalaryInfo.
 */
@Service
@Transactional
public class UserSalaryInfoServiceImpl implements UserSalaryInfoService {

    private final Logger log = LoggerFactory.getLogger(UserSalaryInfoServiceImpl.class);

    private final UserSalaryInfoRepository userSalaryInfoRepository;

    private final UserSalaryInfoMapper userSalaryInfoMapper;

    public UserSalaryInfoServiceImpl(UserSalaryInfoRepository userSalaryInfoRepository, UserSalaryInfoMapper userSalaryInfoMapper) {
        this.userSalaryInfoRepository = userSalaryInfoRepository;
        this.userSalaryInfoMapper = userSalaryInfoMapper;
    }

    /**
     * Save a userSalaryInfo.
     *
     * @param userSalaryInfoDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public UserSalaryInfoDTO save(UserSalaryInfoDTO userSalaryInfoDTO) {
        log.debug("Request to save UserSalaryInfo : {}", userSalaryInfoDTO);
        UserSalaryInfo userSalaryInfo = userSalaryInfoMapper.toEntity(userSalaryInfoDTO);
        userSalaryInfo = userSalaryInfoRepository.save(userSalaryInfo);
        return userSalaryInfoMapper.toDto(userSalaryInfo);
    }

    /**
     * Get all the userSalaryInfos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UserSalaryInfoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UserSalaryInfos");
        return userSalaryInfoRepository.findAll(pageable)
            .map(userSalaryInfoMapper::toDto);
    }

    /**
     * Get one userSalaryInfo by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public UserSalaryInfoDTO findOne(Long id) {
        log.debug("Request to get UserSalaryInfo : {}", id);
        UserSalaryInfo userSalaryInfo = userSalaryInfoRepository.findById(id).orElse(null);
        return userSalaryInfoMapper.toDto(userSalaryInfo);
    }

    /**
     * Delete the userSalaryInfo by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserSalaryInfo : {}", id);
        userSalaryInfoRepository.deleteById(id);
    }

	@Override
	public Page<UserSalaryInfoDTO> findAll(Map<String, Object> filters) {
		  return new PageImpl<UserSalaryInfo>(userSalaryInfoRepository.findAll(filters), null,
				  userSalaryInfoRepository.getTotalRows(filters)).map(userSalaryInfoMapper::toDto);
	}

	@Override
	public List<UserSalaryInfoDTO> findByUserExtendSalaryId(Long userExtendSalaryId) {
		
		return userSalaryInfoRepository.findByUserExtendSalaryId(userExtendSalaryId).stream().map(userSalaryInfoMapper::toDto).collect(Collectors.toList());
				
	}
}
