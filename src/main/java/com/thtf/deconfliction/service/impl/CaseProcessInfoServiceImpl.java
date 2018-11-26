package com.thtf.deconfliction.service.impl;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thtf.app.domain.Attachment;
import com.thtf.app.domain.User;
import com.thtf.app.repository.AttachmentRepository;
import com.thtf.app.repository.UserRepository;
import com.thtf.deconfliction.domain.BusinessNumber;
import com.thtf.deconfliction.domain.CaseProcessInfo;
import com.thtf.deconfliction.domain.ConflictCase;
import com.thtf.deconfliction.domain.OrgCaseNumber;
import com.thtf.deconfliction.domain.SatisfactionRate;
import com.thtf.deconfliction.domain.VUserProcessedCasesNumber;
import com.thtf.deconfliction.repository.BusinessNumberRepository;
import com.thtf.deconfliction.repository.CaseProcessInfoRepository;
import com.thtf.deconfliction.repository.ConflictCaseRepository;
import com.thtf.deconfliction.repository.OrgCaseNumberRepository;
import com.thtf.deconfliction.repository.SatisfactionRateRepository;
import com.thtf.deconfliction.repository.VUserProcessedCasesNumberRepository;
import com.thtf.deconfliction.service.CaseProcessInfoService;
import com.thtf.deconfliction.service.dto.BusinessNumberDTO;
import com.thtf.deconfliction.service.dto.CaseProcessInfoDTO;
import com.thtf.deconfliction.service.dto.OrgCaseNumberDTO;
import com.thtf.deconfliction.service.dto.SatisfactionRateDTO;
import com.thtf.deconfliction.service.mapper.BusinessNumberMapper;
import com.thtf.deconfliction.service.mapper.CaseProcessInfoMapper;
import com.thtf.deconfliction.service.mapper.OrgCaseNumberMapper;
import com.thtf.deconfliction.service.mapper.SatisfactionRateMapper;
import com.thtf.deconfliction.service.util.CommonServiceUtil;


/**
 * Service Implementation for managing CaseProcessInfo.
 */
@Service
@Transactional
public class CaseProcessInfoServiceImpl implements CaseProcessInfoService {

    private final Logger log = LoggerFactory.getLogger(CaseProcessInfoServiceImpl.class);

    private final CaseProcessInfoRepository caseProcessInfoRepository;
    
    private final UserRepository userRepository;
    
    private final ConflictCaseRepository conflictCaseRepository;
    
    private final OrgCaseNumberRepository orgCaseNumberRepository;
    
    private final BusinessNumberRepository businessNumberRepository;
    
    private final SatisfactionRateRepository satisfactionRateRepository;
    
    private final AttachmentRepository attachmentRepository;

    private final CaseProcessInfoMapper caseProcessInfoMapper;
    
    private final OrgCaseNumberMapper orgCaseNumberMapper;
    
    private final BusinessNumberMapper businessNumberMapper;
    
    private final SatisfactionRateMapper satisfactionRateMapper;
    
    private final VUserProcessedCasesNumberRepository vUserProcessedCasesNumberRepository;
    
    public CaseProcessInfoServiceImpl(CaseProcessInfoRepository caseProcessInfoRepository,UserRepository userRepository,
    		OrgCaseNumberRepository orgCaseNumberRepository, ConflictCaseRepository conflictCaseRepository,
    		BusinessNumberRepository businessNumberRepository, CaseProcessInfoMapper caseProcessInfoMapper,
    		OrgCaseNumberMapper orgCaseNumberMapper,BusinessNumberMapper businessNumberMapper,
    		AttachmentRepository attachmentRepository,SatisfactionRateRepository satisfactionRateRepository,
    		SatisfactionRateMapper satisfactionRateMapper,VUserProcessedCasesNumberRepository vUserProcessedCasesNumberRepository) {
        this.caseProcessInfoRepository = caseProcessInfoRepository;
        this.userRepository = userRepository;
        this.conflictCaseRepository = conflictCaseRepository;
        this.orgCaseNumberRepository = orgCaseNumberRepository;
        this.businessNumberRepository = businessNumberRepository;
        this.satisfactionRateRepository = satisfactionRateRepository;
        this.caseProcessInfoMapper = caseProcessInfoMapper;
        this.orgCaseNumberMapper = orgCaseNumberMapper;
        this.businessNumberMapper = businessNumberMapper;
        this.satisfactionRateMapper = satisfactionRateMapper;
        this.attachmentRepository = attachmentRepository;
        this.vUserProcessedCasesNumberRepository = vUserProcessedCasesNumberRepository;
    }

    /**
     * Save a caseProcessInfo.
     *
     * @param caseProcessInfoDTO the entity to save
     * @return the persisted entity
     * @throws JSONException 
     */
//    @Override
//    public CaseProcessInfoDTO save(CaseProcessInfoDTO caseProcessInfoDTO) {
//        log.debug("Request to save CaseProcessInfo : {}", caseProcessInfoDTO);
//        User user = CommonServiceUtil.getCurrentLoginUser(this.userRepository);
//        CaseProcessInfo caseProcessInfo = caseProcessInfoMapper.toEntity(caseProcessInfoDTO);
//        caseProcessInfo.setmPersonId(user.getId());
//        caseProcessInfo.setmPersonName(user.getFirstName());
//        ConflictCase cc =  conflictCaseRepository.getOne(caseProcessInfoDTO.getConflictCaseId());
//        if(cc != null && this.isMeetStatusCondition(cc.getcStatus(), caseProcessInfoDTO.getmType())) {
//    	   cc.setcStatus(caseProcessInfoDTO.getmType());
//           conflictCaseRepository.save(cc);
//        }
//        caseProcessInfo = caseProcessInfoRepository.save(caseProcessInfo);
//        
//        return caseProcessInfoMapper.toDto(caseProcessInfo);
//    }

    
    
    private void setStatus(ConflictCase cc,CaseProcessInfoDTO caseProcessInfoDTO) throws JSONException{
    	String cStatus = cc.getcStatus();
    	String mType = caseProcessInfoDTO.getmType();
    	
    	if(cStatus != null && cStatus.equals("回退申请审核")){
    		String detailInfo = caseProcessInfoDTO.getDetailInfo();
        	JSONObject jsonObj = new JSONObject(detailInfo);
        	JSONObject isAgreeObj = (JSONObject) jsonObj.get("isAgree");
        	String isAgree = (String) isAgreeObj.get("value");
    		if(isAgree != null && isAgree.equals("1") ){
    			cc.setcStatus("诉调");
    		}else{
    			cc.setcStatus("调解");
    		}
    		
    	}else
    	if(cStatus != null && cStatus.equals("专家申请审核")){
    		cc.setcStatus("调解");
    	}else
    	if(cStatus != null && cStatus.equals("延期申请审核")){
    		cc.setcStatus("调解");
    	}else
    	if(cStatus != null && cStatus.equals("诉调") && mType != null && "诉讼申请".equals(mType)){
    		cc.setcStatus("诉讼");
    	}else
    	if(cStatus != null && cStatus.equals("诉调") && mType != null && "调解申请".equals(mType)){
    		cc.setcStatus("调解");
    	}else
    	if(cStatus != null && cStatus.equals("调解") && mType != null && "调解中".equals(mType)){
    		cc.setcStatus("调解");
    	}else
    	if(cStatus != null && cStatus.equals("调解") && mType != null && "回退申请".equals(mType)){
    		cc.setcStatus("回退申请审核");
    	}else
    	if(cStatus != null && cStatus.equals("调解") && mType != null && "专家申请".equals(mType)){
    		cc.setcStatus("专家申请审核");
    	}else
    	if(cStatus != null && cStatus.equals("调解") && mType != null && "延期申请".equals(mType)){
    		cc.setcStatus("延期申请审核");
    	}else
    	if(cStatus != null && cStatus.equals("调解") && mType != null && "结案".equals(mType)){
    		cc.setcStatus("结案");
    	}else{
    		throw  new RuntimeException("您的案件状态已经被处理！");
		}
    }

	
    

    /**
     * Get all the caseProcessInfos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CaseProcessInfoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CaseProcessInfos");
        return caseProcessInfoRepository.findAll(pageable)
            .map(caseProcessInfoMapper::toDto);
    }

    /**
     * Get one caseProcessInfo by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CaseProcessInfoDTO findOne(Long id) {
        log.debug("Request to get CaseProcessInfo : {}", id);
        CaseProcessInfo caseProcessInfo = caseProcessInfoRepository.findById(id).orElse(null);
        return caseProcessInfoMapper.toDto(caseProcessInfo);
    }

    /**
     * Delete the caseProcessInfo by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CaseProcessInfo : {}", id);
        caseProcessInfoRepository.deleteById(id);
    }

	@Override
	@Transactional(readOnly = true)
	public Page<CaseProcessInfoDTO> findAll(Map<String, Object> params) {
		// TODO Auto-generated method stub
		List<CaseProcessInfo> clist = caseProcessInfoRepository.findAll(params);
		for (CaseProcessInfo n : clist) {
			n.setmFiles(getFiles(n.getId()));
		}
		return new PageImpl<CaseProcessInfo>(clist, null,
			caseProcessInfoRepository.getTotalRows(params)).map(caseProcessInfoMapper::toDto);
	}

	@Override
	public CaseProcessInfoDTO save(CaseProcessInfoDTO caseProcessInfoDTO) {
		// TODO Auto-generated method stub
				log.debug("Request to save CaseProcessInfo : {}", caseProcessInfoDTO);
				User user = CommonServiceUtil.getCurrentLoginUser(this.userRepository);
				caseProcessInfoDTO.setmPersonId(user.getId());
				caseProcessInfoDTO.setmPersonName(user.getFirstName());
				String mFiles = caseProcessInfoDTO.getmFiles();
				caseProcessInfoDTO.setmFiles("[]");
		        CaseProcessInfo caseProcessInfo = caseProcessInfoMapper.toEntity(caseProcessInfoDTO);
		        
		        ConflictCase cc =  conflictCaseRepository.getOne(caseProcessInfoDTO.getConflictCaseId());
		        if(cc != null) {
		        	if("诉讼申请".equals(caseProcessInfoDTO.getmType()) || "调解申请".equals(caseProcessInfoDTO.getmType())){
		        		cc.setAcceptDate(ZonedDateTime.now());
		        		String detailInfo = caseProcessInfoDTO.getDetailInfo();
		        		try {
		        			JSONObject file = new JSONObject(detailInfo);
		        			cc.setDisputeType(file.getString("disputeType"));
		        			cc.setIsDifficult(file.getString("isDifficult"));
		        			cc.setDifficultLevel(file.getString("difficultLevel"));
		        			cc.setIsQuick(file.getString("isQuick"));
		        			cc.setCasePrediction(file.getString("casePrediction"));
		        			cc.setMediateOrgName(file.getString("mediateOrgName"));
		        			cc.setMediateOrgId(file.getLong("mediateOrgId"));
		        		} catch (JSONException e) {
		        			// TODO Auto-generated catch block
		        			e.printStackTrace();
		        		}
		        	}
		        	try {
						this.setStatus(cc, caseProcessInfoDTO);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	conflictCaseRepository.save(cc);
		        }
		        caseProcessInfo = caseProcessInfoRepository.save(caseProcessInfo);
		        if (mFiles != null && !mFiles.equals("[]")) {
					try {
						JSONArray filesArray = new JSONArray(mFiles);
						List<Long> ids = new ArrayList<>();
						for (int i = 0; i < filesArray.length(); i++) {
							JSONObject file = filesArray.getJSONObject(i);
							ids.add(file.getLong("id"));
						}
						attachmentRepository.updateByIdNotIn(ids, "processinfo", caseProcessInfo.getId(), 0);
						attachmentRepository.updateByIdIn(ids, "processinfo", caseProcessInfo.getId());
					} catch (JSONException e) {
						log.error(e.getMessage());
					}
				} else {
					attachmentRepository.updateByRelatedId("processinfo", caseProcessInfo.getId(), 0);
				}
		        
		        return caseProcessInfoMapper.toDto(caseProcessInfo);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<OrgCaseNumberDTO> findOrgCaseNumber(Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return new PageImpl<OrgCaseNumber>(orgCaseNumberRepository.findAll(filters), null,
				orgCaseNumberRepository.getTotalRows(filters)).map(orgCaseNumberMapper::toDto);
	}
	
	
	
	@Override
	@Transactional(readOnly = true)
	public Page<VUserProcessedCasesNumber> findVUserProcessedCasesNumber(Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return new PageImpl<VUserProcessedCasesNumber>(vUserProcessedCasesNumberRepository.findAll(filters), null,
				vUserProcessedCasesNumberRepository.getTotalRows(filters));
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<BusinessNumberDTO> findBusinessNumber(Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return new PageImpl<BusinessNumber>(businessNumberRepository.findAll(filters), null,
				businessNumberRepository.getTotalRows(filters)).map(businessNumberMapper::toDto);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<SatisfactionRateDTO> findSatisfactionRate(Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return new PageImpl<SatisfactionRate>(satisfactionRateRepository.findAll(filters), null,
				satisfactionRateRepository.getTotalRows(filters)).map(satisfactionRateMapper::toDto);
	}
	
	private String getFiles(Long nid) {
		List<Attachment> lattachment = attachmentRepository.findByRelatedIdAndFileTypeAndFileSize(nid,
				"processinfo", 0);
		String filesStr = "";
		if (lattachment.size() > 0) {
			JSONArray jsonArray = new JSONArray();
			try {
				for (int i = 0; i < lattachment.size(); i++) {
					JSONObject obj = new JSONObject();
					obj.put("id", lattachment.get(i).getId());
					obj.put("file", lattachment.get(i).getFileName());
					obj.put("size", lattachment.get(i).getFileSize());
					jsonArray.put(i, obj);
				}
			} catch (JSONException e) {
				log.error(e.getMessage(), e);
			}
			filesStr = jsonArray.toString();
		} else {
			filesStr = "[]";
		}
		return filesStr;
	}
	
}
