package com.thtf.deconfliction.service.impl;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;
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
import com.thtf.app.security.SecurityUtils;
import com.thtf.app.service.DictionaryService;
import com.thtf.app.service.dto.DictionaryDTO;
import com.thtf.app.web.rest.errors.InternalServerErrorException;
import com.thtf.app.web.rest.util.FilterUtil;
import com.thtf.deconfliction.domain.CaseProcessInfo;
import com.thtf.deconfliction.domain.ConflictCase;
import com.thtf.deconfliction.domain.FilingRelevantPerson;
import com.thtf.deconfliction.domain.FillingBorrow;
import com.thtf.deconfliction.domain.FillingCaseProcessInfo;
import com.thtf.deconfliction.domain.FillingConflictCase;
import com.thtf.deconfliction.domain.RelevantPerson;
import com.thtf.deconfliction.repository.CaseProcessInfoRepository;
import com.thtf.deconfliction.repository.ConflictCaseRepository;
import com.thtf.deconfliction.repository.FilingRelevantPersonRepository;
import com.thtf.deconfliction.repository.FillingBorrowRepository;
import com.thtf.deconfliction.repository.FillingCaseProcessInfoRepository;
import com.thtf.deconfliction.repository.FillingConflictCaseRepository;
import com.thtf.deconfliction.service.CommonUtilService;
import com.thtf.deconfliction.service.FillingConflictCaseService;
import com.thtf.deconfliction.service.dto.FillingConflictCaseDTO;
import com.thtf.deconfliction.service.mapper.FillingConflictCaseMapper;

/**
 * Service Implementation for managing FillingConflictCase.
 */
@Service
@Transactional
public class FillingConflictCaseServiceImpl implements FillingConflictCaseService {

	private final Logger log = LoggerFactory.getLogger(FillingConflictCaseServiceImpl.class);

	private final FillingConflictCaseRepository fillingConflictCaseRepository;

	private final ConflictCaseRepository conflictCaseRepository;

	private final FilingRelevantPersonRepository filingRelevantPersonRepository;

	private final CaseProcessInfoRepository caseProcessInfoRepository;

	private final FillingCaseProcessInfoRepository fillingCaseProcessInfoRepository;

	private final FillingConflictCaseMapper fillingConflictCaseMapper;

	private final CommonUtilService commonUtilService;

	private final FillingBorrowRepository fillingBorrowRepository;

	private final DictionaryService dictionaryService;

	public FillingConflictCaseServiceImpl(FillingConflictCaseRepository fillingConflictCaseRepository,
			FillingConflictCaseMapper fillingConflictCaseMapper,
			FilingRelevantPersonRepository filingRelevantPersonRepository,
			CaseProcessInfoRepository caseProcessInfoRepository,
			FillingCaseProcessInfoRepository fillingCaseProcessInfoRepository,
			ConflictCaseRepository conflictCaseRepository, CommonUtilService commonUtilService,
			FillingBorrowRepository fillingBorrowRepository, DictionaryService dictionaryService) {
		this.fillingConflictCaseRepository = fillingConflictCaseRepository;
		this.fillingConflictCaseMapper = fillingConflictCaseMapper;
		this.conflictCaseRepository = conflictCaseRepository;
		this.filingRelevantPersonRepository = filingRelevantPersonRepository;
		this.caseProcessInfoRepository = caseProcessInfoRepository;
		this.fillingCaseProcessInfoRepository = fillingCaseProcessInfoRepository;
		this.commonUtilService = commonUtilService;
		this.fillingBorrowRepository = fillingBorrowRepository;
		this.dictionaryService = dictionaryService;
	}

	/**
	 * Save a fillingConflictCase.
	 *
	 * @param fillingConflictCaseDTO
	 *            the entity to save
	 * @return the persisted entity
	 */
	@Override
	public FillingConflictCaseDTO save(FillingConflictCaseDTO fillingConflictCaseDTO) {
		log.debug("Request to save FillingConflictCase : {}", fillingConflictCaseDTO);
		FillingConflictCase fillingConflictCase = fillingConflictCaseMapper.toEntity(fillingConflictCaseDTO);
		fillingConflictCase = fillingConflictCaseRepository.save(fillingConflictCase);
		return fillingConflictCaseMapper.toDto(fillingConflictCase);
	}

	/**
	 * Archive a fillingConflictCase.
	 *
	 * @param fillingConflictCaseDTO
	 *            the entity to save
	 * @return the persisted entity
	 */
	@Override
	public FillingConflictCaseDTO archive(FillingConflictCaseDTO fillingConflictCaseDTO) {
		log.debug("Request to save FillingConflictCase : {}", fillingConflictCaseDTO);
		FillingConflictCase fillingConflictCase = fillingConflictCaseMapper.toEntity(fillingConflictCaseDTO);
		ConflictCase c = conflictCaseRepository.findById(fillingConflictCase.getoFilingCaseId()).orElse(null);
		final String userLogin = SecurityUtils.getCurrentUserLogin()
				.orElseThrow(() -> new InternalServerErrorException("Current user login not found"));
		if (c != null) {
			fillingConflictCase.setAcceptDate(c.getAcceptDate());
			fillingConflictCase.setApplyDate(c.getApplyDate());
			// fillingConflictCase.setcDescription(c.getcDescription());
			fillingConflictCase.setcName(c.getcName());
			fillingConflictCase.setcStatus(c.getcStatus());
			String dicValue = c.getcType() != null ? c.getcType() : "";
			DictionaryDTO ds = dictionaryService.findDicsById(707L, dicValue, false);
			if (null != ds) {
				dicValue = ds.getDicValue() != null ? ds.getDicValue() : "";
			}
			fillingConflictCase.setcType(dicValue);
			fillingConflictCase.setDealOpinion(c.getDealOpinion());
			fillingConflictCase.setDutyofficerName(c.getDutyofficerName());
			fillingConflictCase.setEndDate(c.getEndDate());
			fillingConflictCase.setFeedbackType(c.getFeedbackType());
			fillingConflictCase.setFeedbackValue(c.getFeedbackValue());
			dicValue = c.getInfoSource() != null ? c.getInfoSource() : "";
			ds = dictionaryService.findDicsById(131L, dicValue, false);
			if (null != ds) {
				dicValue = ds.getDicValue() != null ? ds.getDicValue() : "";
			}
			fillingConflictCase.setInfoSource(dicValue);
			fillingConflictCase.setFilingDate(ZonedDateTime.now());
			fillingConflictCase.setFilingStaff(userLogin);
			fillingConflictCase.setOcommunityStreet(c.getOcommunityStreet());
			fillingConflictCase.setOcityState(c.getOcityState());
			fillingConflictCase.setOccurrenceDate(c.getOccurrenceDate());
			fillingConflictCase.setOccurProvince(c.getOccurProvince());
			fillingConflictCase.setMediateOrgId(c.getMediateOrgId());
			fillingConflictCase.setMediateOrgName(c.getMediateOrgName());
			fillingConflictCase.setMyAppeal(c.getMyAppeal());
			fillingConflictCase.setOareaCounty(c.getOareaCounty());
			fillingConflictCase.setOareaId(c.getOareaId());
			fillingConflictCase.setRecorderName(c.getRecorderName());
			dicValue = c.getDisputeType() != null ? c.getDisputeType() : "";
			ds = dictionaryService.findDicsById(707L, dicValue, false);
			if (null != ds) {
				dicValue = ds.getDicValue() != null ? ds.getDicValue() : "";
			}
			fillingConflictCase.setDisputeType(dicValue);
			// fillingConflictCase.setStaffName(staffName);
			// fillingConflictCase.setRelevantPeople(conflictCase.getRelevantPeople());
		}
		fillingConflictCase = fillingConflictCaseRepository.save(fillingConflictCase);
		Set<RelevantPerson> rp = c.getRelevantPeople();
		Set<FilingRelevantPerson> frp = new HashSet<>();
		Set<FillingConflictCase> fc = new HashSet<>();
		fc.add(fillingConflictCase);
		if (null != rp && !rp.isEmpty()) {
			for (RelevantPerson item : rp) {
				FilingRelevantPerson tempFp = new FilingRelevantPerson();
				tempFp.setoPersonId(item.getId());
				tempFp.setAddress(item.getAddress());
				tempFp.setaGroup(item.getaGroup());
				tempFp.setAreaCounty(item.getAreaCounty());
				tempFp.setAreaId(item.getAreaId());
				tempFp.setaType(item.getaType());
				tempFp.setCityState(item.getCityState());
				tempFp.setCommunityStreet(item.getCommunityStreet());
				tempFp.setCompany(item.getCompany());
				tempFp.setGender(item.getGender());
				tempFp.setWellsProvince(item.getWellsProvince());
				tempFp.setTelephone(item.getTelephone());
				tempFp.setSocialCreditType(item.getSocialCreditType());
				tempFp.setSocialCreditCode(item.getSocialCreditCode());
				tempFp.setProxyName(item.getProxyName());
				tempFp.setProxyId(item.getPrincipalId());
				tempFp.setName(item.getName());
				tempFp.setLegalRepresentation(item.getLegalRepresentation());
				tempFp.setJob(item.getJob());
				tempFp.setIdType(item.getIdType());
				tempFp.setIdNumber(item.getIdNumber());
				tempFp.setFilingDate(ZonedDateTime.now());
				tempFp.setFilingStaff(userLogin);
				tempFp.setFilingCases(fc);
				frp.add(tempFp);
			}
			filingRelevantPersonRepository.saveAll(frp);
		}

		Set<CaseProcessInfo> cp = caseProcessInfoRepository.findCaseProcessInfoByCid(c.getId());
		Set<FillingCaseProcessInfo> fcp = new HashSet<>();
		if (null != cp && !cp.isEmpty()) {
			for (CaseProcessInfo itemcp : cp) {
				FillingCaseProcessInfo tempfcp = new FillingCaseProcessInfo();
				tempfcp.setoProcessId(itemcp.getId());
				tempfcp.setAppraise(itemcp.getAppraise());
				tempfcp.setBeginDateTime(itemcp.getBeginDateTime());
				tempfcp.setEndDateTime(itemcp.getEndDateTime());
				tempfcp.setEvaluatDateTime(itemcp.getEvaluatDateTime());
				tempfcp.setFilingStaff(userLogin);
				tempfcp.setFilingDate(ZonedDateTime.now());
				tempfcp.setmAddress(itemcp.getmAddress());
				tempfcp.setmEvaluat(itemcp.getmEvaluat());
				tempfcp.setmPersonId(itemcp.getmPersonId());
				tempfcp.setmPersonName(itemcp.getmPersonName());
				tempfcp.setmRecordContent(itemcp.getmRecordContent());
				tempfcp.setmResultContent(itemcp.getmResultContent());
				tempfcp.setmScore(itemcp.getmScore());
				tempfcp.setmType(itemcp.getmType());
				tempfcp.setParticipant(itemcp.getParticipant());
				tempfcp.setRecorder(itemcp.getRecorder());
				tempfcp.setRespondents(itemcp.getRespondents());
				// tempfcp.setStaff(itemcp.getmScore());
				tempfcp.setFilingCaseId(fillingConflictCase.getId());
				fcp.add(tempfcp);
			}
			fillingCaseProcessInfoRepository.saveAll(fcp);
		}

		return fillingConflictCaseMapper.toDto(fillingConflictCase);
	}

	/**
	 * Get all the fillingConflictCases.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<FillingConflictCaseDTO> findAll(Pageable pageable) {
		log.debug("Request to get all FillingConflictCases");
		return fillingConflictCaseRepository.findAll(pageable).map(fillingConflictCaseMapper::toDto);
	}

	/**
	 * Get one fillingConflictCase by id.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public FillingConflictCaseDTO findOne(Long id) {
		log.debug("Request to get FillingConflictCase : {}", id);
		FillingConflictCase fillingConflictCase = fillingConflictCaseRepository.findById(id).orElse(null);
		return fillingConflictCaseMapper.toDto(fillingConflictCase);
	}

	/**
	 * Delete the fillingConflictCase by id.
	 *
	 * @param id
	 *            the id of the entity
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete FillingConflictCase : {}", id);
		fillingConflictCaseRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<FillingConflictCaseDTO> findAll(Map<String, Object> params) {
		log.debug("Request to get all FillingConflictCases");
		return new PageImpl<FillingConflictCase>(fillingConflictCaseRepository.findAll(params), null,
				fillingConflictCaseRepository.getTotalRows(params)).map(fillingConflictCaseMapper::toDto);
	}

	@Override
	public Map<String, Object> findOneById(Long id) {
		log.debug("Request to get FillingConflictCase : {}", id);
		FillingConflictCase fillingConflictCase = fillingConflictCaseRepository.findById(id).orElse(null);
		Map<String, Object> filingcaseInfo = new HashMap<>();
		filingcaseInfo.put("case", fillingConflictCase);
		filingcaseInfo.put("persion", fillingConflictCase.getRelevantPeople());
		filingcaseInfo.put("processinfo", fillingCaseProcessInfoRepository.findFCaseProcessInfoByFilingCaseId(id));
		return filingcaseInfo;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<FillingConflictCaseDTO> findBorrowedCases(Map<String, Object> filters) {
		log.debug("Request to get all FillingBorrowDTO");
		User user = this.commonUtilService.getCurrentLoginUser();
		Map<String, Object> filtersBorrowed = new HashMap<String, Object>();

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		FilterUtil.setFilter(filtersBorrowed, "borrowIdCode", "EQUAL", "and", user.getLogin());
		FilterUtil.setFilter(filtersBorrowed, "beginDate", "LESS_THAN_OR_EQUAL", "and", sf.format(new Date()));
		FilterUtil.setFilter(filtersBorrowed, "endDate", "GREATER_THAN_OR_EQUAL", "and", sf.format(new Date()));
		List<Long> ids = this.fillingBorrowRepository.findAll(filtersBorrowed).stream()
				.map(FillingBorrow::getFillingConflictCase).map(FillingConflictCase::getId)
				.collect(Collectors.toList());
		if (ids.isEmpty()) {
			ids.add(-1L);
		}
		FilterUtil.setFilter(filters, "id", "IN", "and", ids);
		// log.debug(filters.toString());

		long count = fillingConflictCaseRepository.getTotalRows(filters);
		return new PageImpl<FillingConflictCase>(this.fillingConflictCaseRepository.findAll(filters), null, count)
				.map(fillingConflictCaseMapper::toDto);
	}
}
