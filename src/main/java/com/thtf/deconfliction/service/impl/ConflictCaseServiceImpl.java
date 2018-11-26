package com.thtf.deconfliction.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thtf.app.domain.User;
import com.thtf.app.repository.UserRepository;
import com.thtf.app.web.rest.util.FilterUtil;
import com.thtf.deconfliction.domain.CaseProcessInfo;
import com.thtf.deconfliction.domain.ConflictCase;
import com.thtf.deconfliction.domain.RelevantPerson;
import com.thtf.deconfliction.repository.CaseProcessInfoRepository;
import com.thtf.deconfliction.repository.ConflictCaseRepository;
import com.thtf.deconfliction.repository.RelevantPersonRepository;
import com.thtf.deconfliction.service.CommonUtilService;
import com.thtf.deconfliction.service.ConflictCaseService;
import com.thtf.deconfliction.service.dto.ConflictCaseDTO;
import com.thtf.deconfliction.service.dto.RelevantPersonDTO;
import com.thtf.deconfliction.service.mapper.ConflictCaseMapper;
import com.thtf.deconfliction.service.mapper.RelevantPersonMapper;
import com.thtf.deconfliction.service.util.CommonServiceUtil;


/**
 * Service Implementation for managing ConflictCase.
 */
@Service
@Transactional
public class ConflictCaseServiceImpl implements ConflictCaseService {

    private final Logger log = LoggerFactory.getLogger(ConflictCaseServiceImpl.class);

    private final ConflictCaseRepository conflictCaseRepository;
    
    private final UserRepository userRepository;
    
    private final RelevantPersonRepository relevantPersonRepository;
    
    private final ConflictCaseMapper conflictCaseMapper;
    
    private final RelevantPersonMapper relevantPersonMapper;
    
    private final CaseProcessInfoRepository caseProcessInfoRepository;
    
    private final CommonUtilService commonUtilService;
  
    
    public ConflictCaseServiceImpl(ConflictCaseRepository conflictCaseRepository, UserRepository userRepository,
    		RelevantPersonRepository relevantPersonRepository, ConflictCaseMapper conflictCaseMapper,
    		RelevantPersonMapper relevantPersonMapper,CaseProcessInfoRepository caseProcessInfoRepository,
    		CommonUtilService commonUtilService) {
        this.conflictCaseRepository = conflictCaseRepository;
        this.userRepository = userRepository;
        this.relevantPersonRepository = relevantPersonRepository;
        this.conflictCaseMapper = conflictCaseMapper;
        this.relevantPersonMapper = relevantPersonMapper;
        this.caseProcessInfoRepository = caseProcessInfoRepository;
        this.commonUtilService = commonUtilService;
    }

    /**
     * Save a conflictCase.
     *
     * @param conflictCaseDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ConflictCaseDTO save(ConflictCaseDTO conflictCaseDTO) {
        log.debug("Request to save ConflictCase : {}", conflictCaseDTO);
        ConflictCase conflictCase = conflictCaseMapper.toEntity(conflictCaseDTO);
        conflictCase = conflictCaseRepository.save(conflictCase);
        return conflictCaseMapper.toDto(conflictCase);
    }

    /**
     * Get all the conflictCases.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ConflictCaseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ConflictCases");
        return conflictCaseRepository.findAll(pageable)
            .map(conflictCaseMapper::toDto);
    }

    /**
     * Get all the conflictCases.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ConflictCaseDTO> findAll(Map<String, Object> filters) {
        log.debug("Request to get all ConflictCases");
        return new PageImpl<ConflictCase>(conflictCaseRepository.findAll(filters), Pageable.unpaged(),
        		conflictCaseRepository.getTotalRows(filters)).map(conflictCaseMapper::toDto);
    }
    
    /**
     * Get one conflictCase by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ConflictCaseDTO findOne(Long id) {
        log.debug("Request to get ConflictCase : {}", id);
        ConflictCase conflictCase = conflictCaseRepository.findById(id).orElse(null);
        return conflictCaseMapper.toDto(conflictCase);
    }

    /**
     * Delete the conflictCase by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ConflictCase : {}", id);
        conflictCaseRepository.deleteById(id);
    }

	@Override
	public Page<ConflictCaseDTO> findByStatus(String cStatus, Pageable pageable) {
		// TODO Auto-generated method stub
		log.debug("Request to get all ConflictCases+++: {}", cStatus);
        return conflictCaseRepository.findByCStatus(cStatus,pageable)
            .map(conflictCaseMapper::toDto);
	}
	
	@Override
	public Page<ConflictCaseDTO> findByMediateOrgNameIsNotNull(Pageable pageable) {
		// TODO Auto-generated method stub
        return conflictCaseRepository.findByMediateOrgNameIsNotNull(pageable)
            .map(conflictCaseMapper::toDto);
	}

	/**
     * Get one conflictCase by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ConflictCaseDTO findOneById(Long id) {
        log.debug("Request to get ConflictCase : {}", id);
        ConflictCase c = conflictCaseRepository.findById(id).orElse(null);
        Set<RelevantPerson> relevantPeople = c.getRelevantPeople();
        Set<RelevantPersonDTO> relevantPeopleDto = new HashSet<>();
        for (RelevantPerson r : relevantPeople) {
        	relevantPeopleDto.add(relevantPersonMapper.toDto(r));
		}
        return new ConflictCaseDTO(c.getId(), c.getcType(),c.getOccurrenceDate(),
				c.getApplyDate(),c.getAcceptDate(),c.getEndDate(),c.getcName(),c.getcDescription(),
				c.getFeedbackType(),c.getFeedbackValue(),c.getInfoSource(),c.getMyAppeal(),
				c.getOccurProvince(),c.getOcityState(),c.getOareaCounty(),c.getOcommunityStreet(),
				c.getOareaId(),c.getMediateOrgId(),c.getMediateOrgName(),c.getDutyofficerName(),
				c.getRecorderName(),c.getRemarks(),c.getcStatus(),c.getDealOpinion(),c.getUpperId(),
				c.getDisputeType(),c.getIsDifficult(),c.getDifficultLevel(),c.getIsQuick(),c.getCasePrediction(),
				relevantPeopleDto);
    }
    
    @Override
	public Page<ConflictCaseDTO> getMyCases(Pageable pageable) {
		User user = CommonServiceUtil.getCurrentLoginUser(this.userRepository);
		List<RelevantPerson> relevantPersons = relevantPersonRepository.findByIdNumberAndAGroup(user.getLogin(),"01");
		if(relevantPersons != null){
			List<Long>ids = new ArrayList<Long>();
			for(RelevantPerson r : relevantPersons){
				Set<ConflictCase> conflictCase = r.getConflictCases();
				List<Long>id = conflictCase.stream().map(ConflictCase::getId).collect(Collectors.toList());
				ids.addAll(id);
			}
			Page<ConflictCase> conflictCases= conflictCaseRepository.findByIdIn(pageable,ids);
			return conflictCases.map(conflictCaseMapper::toDto);
		}
		return null;
	}
    
   @Override 
   public Page<ConflictCaseDTO> getMyProcessedCases(Map<String,Object> filters) {
	   User user = this.commonUtilService.getCurrentLoginUser();
   	   Map<String,Object> procoessedFilters = new HashMap<String,Object>();
       
//       SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       FilterUtil.setFilter(filters, "mPersonId", "EQUAL", "and", user.getId());
       FilterUtil.setFilter(procoessedFilters, "beginDateTime", "GREATER_THAN_OR_EQUAL", "and", filters.get("beginTime"));
       FilterUtil.setFilter(procoessedFilters, "beginDateTime", "LESS_THAN_OR_EQUAL", "and", filters.get("endTime"));
   	
   	   List<Long> ids = this.caseProcessInfoRepository.findAll(procoessedFilters).stream().map(CaseProcessInfo::getId).collect(Collectors.toList());
        FilterUtil.setFilter(filters, "id", "IN", "and", ids);
     
        
        long count = this.conflictCaseRepository.getTotalRows(filters);
        return new PageImpl<ConflictCase>(this.conflictCaseRepository.findAll(filters), null,
        		count).map(this.conflictCaseMapper::toDto);
    }
    
    @Override
    public Long getMyProcessedCasesCount(Map<String,Object> filters) {
    	User user = this.commonUtilService.getCurrentLoginUser();
    	Map<String,Object> procoessedFilters = new HashMap<String,Object>();
        
//        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        FilterUtil.setFilter(filters, "mPersonId", "EQUAL", "and", user.getId());
        FilterUtil.setFilter(procoessedFilters, "beginDateTime", "GREATER_THAN_OR_EQUAL", "and", filters.get("beginTime"));
        FilterUtil.setFilter(procoessedFilters, "beginDateTime", "LESS_THAN_OR_EQUAL", "and", filters.get("endTime"));
    	
    	List<Long> ids = this.caseProcessInfoRepository.findAll(procoessedFilters).stream().map(CaseProcessInfo::getId).collect(Collectors.toList());
    	if(ids.isEmpty()) {
        	ids.add(-1L);
        }
        FilterUtil.setFilter(filters, "id", "IN", "and", ids);
     //   log.debug(filters.toString());
        
        return this.conflictCaseRepository.getTotalRows(filters);
    }
}
