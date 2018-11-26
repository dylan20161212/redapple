package com.thtf.deconfliction.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thtf.deconfliction.domain.VUserProcessedCasesNumber;
import com.thtf.deconfliction.service.dto.BusinessNumberDTO;
import com.thtf.deconfliction.service.dto.CaseProcessInfoDTO;
import com.thtf.deconfliction.service.dto.OrgCaseNumberDTO;
import com.thtf.deconfliction.service.dto.SatisfactionRateDTO;

/**
 * Service Interface for managing CaseProcessInfo.
 */
public interface CaseProcessInfoService {

    /**
     * Save a caseProcessInfo.
     *
     * @param caseProcessInfoDTO the entity to save
     * @return the persisted entity
     */
    CaseProcessInfoDTO save(CaseProcessInfoDTO caseProcessInfoDTO);

    /**
     * Get all the caseProcessInfos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CaseProcessInfoDTO> findAll(Pageable pageable);
    

    /**
     * Get the "id" caseProcessInfo.
     *
     * @param id the id of the entity
     * @return the entity
     */
    CaseProcessInfoDTO findOne(Long id);

    /**
     * Delete the "id" caseProcessInfo.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    
    /**
     * Get all the caseProcessInfos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CaseProcessInfoDTO> findAll(Map<String, Object> params);
    
    Page<OrgCaseNumberDTO> findOrgCaseNumber(Map<String, Object> filters);
    
    Page<BusinessNumberDTO> findBusinessNumber(Map<String, Object> filters);
    
    Page<SatisfactionRateDTO> findSatisfactionRate(Map<String, Object> filters);
    
    Page<VUserProcessedCasesNumber> findVUserProcessedCasesNumber(Map<String, Object> filters);
}
