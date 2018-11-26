package com.thtf.deconfliction.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thtf.app.domain.User;
import com.thtf.app.repository.UserRepository;
import com.thtf.app.security.SecurityUtils;
import com.thtf.app.web.rest.errors.InternalServerErrorException;
import com.thtf.deconfliction.domain.FillingBorrow;
import com.thtf.deconfliction.repository.FillingBorrowRepository;
import com.thtf.deconfliction.service.CommonUtilService;
import com.thtf.deconfliction.service.FillingBorrowService;
import com.thtf.deconfliction.service.dto.FillingBorrowDTO;
import com.thtf.deconfliction.service.mapper.FillingBorrowMapper;;


/**
 * Service Implementation for managing FillingBorrow.
 */
@Service
@Transactional
public class FillingBorrowServiceImpl implements FillingBorrowService {

    private final Logger log = LoggerFactory.getLogger(FillingBorrowServiceImpl.class);

    private final FillingBorrowRepository fillingBorrowRepository;
    
    private final UserRepository userRepository;

    private final FillingBorrowMapper fillingBorrowMapper;
    
    private final CommonUtilService commonUtilService;

    public FillingBorrowServiceImpl(FillingBorrowRepository fillingBorrowRepository, FillingBorrowMapper fillingBorrowMapper,UserRepository userRepository,CommonUtilService commonUtilService) {
        this.fillingBorrowRepository = fillingBorrowRepository;
        this.fillingBorrowMapper = fillingBorrowMapper;
        this.userRepository = userRepository;
        this.commonUtilService = commonUtilService;
    }

    /**
     * Save a fillingBorrow.
     *
     * @param fillingBorrowDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FillingBorrowDTO save(FillingBorrowDTO fillingBorrowDTO) {
        log.debug("Request to save FillingBorrow : {}", fillingBorrowDTO);
        final String userLogin = SecurityUtils.getCurrentUserLogin()
				.orElseThrow(() -> new InternalServerErrorException("Current user login not found"));
        User user = userRepository.findOneByLogin(userLogin).orElse(null);
        fillingBorrowDTO.setMediatorId(user.getId());
        fillingBorrowDTO.setMediatorName(user.getFirstName());
        FillingBorrow fillingBorrow = fillingBorrowMapper.toEntity(fillingBorrowDTO);
        fillingBorrow = fillingBorrowRepository.save(fillingBorrow);
        return fillingBorrowMapper.toDto(fillingBorrow);
    }

    /**
     * Get all the fillingBorrows.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FillingBorrowDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FillingBorrows");
        return fillingBorrowRepository.findAll(pageable)
            .map(fillingBorrowMapper::toDto);
    }
    
    
    
    @Override
    @Transactional(readOnly = true)
    public Page<FillingBorrowDTO> findAll(Map<String,Object> filters) {
        log.debug("Request to get all FillingBorrowDTO");
        return new PageImpl<FillingBorrow>(fillingBorrowRepository.findAll(filters), null,
        		fillingBorrowRepository.getTotalRows(filters)).map(fillingBorrowMapper::toDto);
    }
    
   

    /**
     * Get one fillingBorrow by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public FillingBorrowDTO findOne(Long id) {
        log.debug("Request to get FillingBorrow : {}", id);
        FillingBorrow fillingBorrow = fillingBorrowRepository.findById(id).orElse(null);
        return fillingBorrowMapper.toDto(fillingBorrow);
    }

    /**
     * Delete the fillingBorrow by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FillingBorrow : {}", id);
        fillingBorrowRepository.deleteById(id);
    }
}
