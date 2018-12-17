package com.thtf.deconfliction.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

import com.thtf.app.domain.User;
import com.thtf.app.web.rest.errors.CustomParameterizedException;
import com.thtf.app.web.rest.util.FilterUtil;
import com.thtf.deconfliction.domain.CaseProcessInfo;
import com.thtf.deconfliction.domain.ConflictCase;
import com.thtf.deconfliction.domain.DutySalary;
import com.thtf.deconfliction.domain.UserExtend;
import com.thtf.deconfliction.domain.UserExtendSalary;
import com.thtf.deconfliction.domain.UserSalaryInfo;
import com.thtf.deconfliction.repository.CaseProcessInfoRepository;
import com.thtf.deconfliction.repository.DutySalaryRepository;
import com.thtf.deconfliction.repository.UserExtendRepository;
import com.thtf.deconfliction.repository.UserExtendSalaryRepository;
import com.thtf.deconfliction.repository.UserSalaryInfoRepository;
import com.thtf.deconfliction.service.CommonUtilService;
import com.thtf.deconfliction.service.UserExtendSalaryService;
import com.thtf.deconfliction.service.dto.UserExtendSalaryDTO;
import com.thtf.deconfliction.service.mapper.UserExtendSalaryMapper;


/**
 * Service Implementation for managing UserExtendSalary.
 */
@Service
@Transactional
public class UserExtendSalaryServiceImpl implements UserExtendSalaryService {

    private final Logger log = LoggerFactory.getLogger(UserExtendSalaryServiceImpl.class);

    private final UserExtendSalaryRepository userExtendSalaryRepository;

    private final UserExtendSalaryMapper userExtendSalaryMapper;
    
    private final UserExtendRepository userExtendRepository;
    
    private final DutySalaryRepository dutySalaryRepository;
    
    private final CaseProcessInfoRepository caseProcessInfoRepository;
    
    private final UserSalaryInfoRepository userSalaryInfoRepository;
    private final CommonUtilService commonUtilService;
    
    private User loginUser;

    public UserExtendSalaryServiceImpl(UserExtendSalaryRepository userExtendSalaryRepository, UserExtendSalaryMapper userExtendSalaryMapper,UserExtendRepository userExtendRepository,DutySalaryRepository dutySalaryRepository,CaseProcessInfoRepository caseProcessInfoRepository,UserSalaryInfoRepository userSalaryInfoRepository,CommonUtilService commonUtilService) {
        this.userExtendSalaryRepository = userExtendSalaryRepository;
        this.userExtendSalaryMapper = userExtendSalaryMapper;
        this.userExtendRepository = userExtendRepository;
        this.dutySalaryRepository = dutySalaryRepository;
        this.caseProcessInfoRepository = caseProcessInfoRepository;
        this.userSalaryInfoRepository = userSalaryInfoRepository;
        this.commonUtilService = commonUtilService;
       
    }

    /**
     * Save a userExtendSalary.
     *
     * @param userExtendSalaryDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public UserExtendSalaryDTO save(UserExtendSalaryDTO userExtendSalaryDTO) {
        log.debug("Request to save UserExtendSalary : {}", userExtendSalaryDTO);
        UserExtendSalary userExtendSalary = userExtendSalaryMapper.toEntity(userExtendSalaryDTO);
        userExtendSalary = userExtendSalaryRepository.save(userExtendSalary);
        return userExtendSalaryMapper.toDto(userExtendSalary);
    }

    /**
     * Get all the userExtendSalaries.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UserExtendSalaryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UserExtendSalaries");
        return userExtendSalaryRepository.findAll(pageable)
            .map(userExtendSalaryMapper::toDto);
    }

    /**
     * Get one userExtendSalary by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public UserExtendSalaryDTO findOne(Long id) {
        log.debug("Request to get UserExtendSalary : {}", id);
        UserExtendSalary userExtendSalary = userExtendSalaryRepository.findById(id).orElse(null);
        return userExtendSalaryMapper.toDto(userExtendSalary);
    }

    /**
     * Delete the userExtendSalary by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserExtendSalary : {}", id);
        userExtendSalaryRepository.deleteById(id);
    }
    
    
    @Override
    //@Scheduled(cron = "0 15 10 15 * ?")
//    @Scheduled(cron = "0 0/3 * * * ?")
    public Boolean computAndSaveUserSalary() throws JSONException {
    	if(this.isGenerateLastMonth()){
    		return false;
    	}
    	try{
    		this.loginUser = commonUtilService.getCurrentLoginUser();
    	}catch(Exception e){
    		log.warn("thejhi's camanda's method ‘computAndSaveUserSalary’ is executed by anonymoususer!");
    	}
    	
    	List<UserExtend> userextendList = this.userExtendRepository.findAll();
    	List<DutySalary> dutySalaryList = this.dutySalaryRepository.findAll();
    	Map<String,Object> filters = new HashMap<String,Object>();
    	
    	FilterUtil.setFilter(filters, "mType", "EQUAL", "and", "结案");
    	FilterUtil.setFilter(filters, "endDateTime", "GREATER_THAN_OR_EQUAL", "and", this.getLastMonthFirstDay());
    	FilterUtil.setFilter(filters, "endDateTime", "LESS_THAN_OR_EQUAL", "and", this.getLastMonthLastDay());
    	
    	List<CaseProcessInfo> caseProcessInfoList = this.caseProcessInfoRepository.findAll(filters);
    	
    	for(UserExtend userextend:userextendList) {
    		DutySalary ds = this.getDutySalary(userextend, dutySalaryList);
    		if(ds == null){
    			throw new CustomParameterizedException("username:"+userextend.getName()+",userId:"+userextend.getUserId()+" ,Level: "+userextend.getmLevel()+" 's Duty Salary not exists!,please add one" );
    		}
    		List<ConflictCase> processedCases = this.getProcessedCaseList(userextend, caseProcessInfoList);	
    		List<UserSalaryInfo> userSalaryInfoList = this.getUserSalaryInfoList(userextend,ds, processedCases, ds.getdDetailInfo());		
    		UserExtendSalary ues = this.getUserExtendSalary(userSalaryInfoList,ds,userextend);		
    		this.saveSalaryInfoList(userSalaryInfoList,ues);
    		
    	}
    	 return true;	
    }
    
    
    private Boolean isGenerateLastMonth(){
    	Map<String,Object> filters = new HashMap<String,Object>();
    	FilterUtil.setFilter(filters, "sMonth", "EQUAL", "and",this.getUpMonth());
    	return this.userExtendSalaryRepository.getTotalRows(filters)>0;
    }
    
    
    
	private String getUpMonth(){
    	Calendar calendar = Calendar.getInstance();
    	return calendar.get(Calendar.MONTH)+"";
    }
    
    
    private String getLastMonthFirstDay(){
    	Calendar calendar = Calendar.getInstance();
    	calendar.add(Calendar.MONTH, -1);
    	calendar.set(Calendar.DAY_OF_MONTH, 1);
    	calendar.set(Calendar.HOUR_OF_DAY, 0);
    	// 分
    	calendar.set(Calendar.MINUTE, 0);
    	// 秒
    	calendar.set(Calendar.SECOND, 0);
    	// 毫秒
    	calendar.set(Calendar.MILLISECOND, 0);

    	return this.getDate(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");
    	
    }
    
    
    
    private String getLastMonthLastDay(){
    	Calendar calendar = Calendar.getInstance();    	
    	calendar.set(Calendar.DAY_OF_MONTH, 0);
    	calendar.set(Calendar.HOUR_OF_DAY, 23);
    	// 分
    	calendar.set(Calendar.MINUTE, 59);
    	// 秒
    	calendar.set(Calendar.SECOND, 59);
    	// 毫秒
    	calendar.set(Calendar.MILLISECOND, 999);

    	return this.getDate(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");
    	
    }
    
    
    private void saveSalaryInfoList(List<UserSalaryInfo> userSalaryInfoList, UserExtendSalary ues) {		
    	UserExtendSalary userExtendSalary = this.userExtendSalaryRepository.save(ues); 	
    	for(UserSalaryInfo userSalaryInfo : userSalaryInfoList) {
    		userSalaryInfo.setUserExtendSalary(userExtendSalary);
    		userSalaryInfo = this.userSalaryInfoRepository.save(userSalaryInfo);
    	
    	}
	}

	private UserExtendSalary getUserExtendSalary(List<UserSalaryInfo> userSalaryInfoList,DutySalary ds,UserExtend userextend) {
		
    	UserExtendSalary ues = new UserExtendSalary();
    	ues.setMdutyCode(ds.getDutyCode());
    	ues.setMdutyName(ds.getDutyName());
    	Long total = 0L;
    	for(UserSalaryInfo userSalaryInfo:userSalaryInfoList) {
    		total+= userSalaryInfo.getsItemPrice()*userSalaryInfo.getsItemAmount();
    	}
    	ues.setsFlag("0");//0 代表工资未发放，1代表已发放
    	ues.setsTotal(total);
    	ues.setUserExtendId(userextend.getId());
    	ues.setUserName(userextend.getName());
    	if(this.loginUser !=null && this.loginUser.getId() != null){
    		ues.setsStaffId(this.loginUser.getId());
        	ues.setsStaffName(this.loginUser.getRealName());
    	}
    	
    	ues.setsMonth(this.getUpMonth());
    	return ues;
	}
    
    private String getDate(Date date,String format) {
    	SimpleDateFormat sf = new SimpleDateFormat(format);
    	return sf.format(date);
    }

	private List<UserSalaryInfo> getUserSalaryInfoList(UserExtend userExtend,DutySalary ds,List<ConflictCase> processedCases,String jsonArray ) throws JSONException {
    	
    	
    	JSONArray ja = new JSONArray(jsonArray);
	
		List<UserSalaryInfo> list = new ArrayList<UserSalaryInfo>();
		for(int i=0;i<ja.length();i++) {
			UserSalaryInfo uesi = new UserSalaryInfo();
			JSONObject jo1 = ja.getJSONObject(i);
			uesi.setMdutyCode(ds.getDutyCode());
			uesi.setMdutyName(ds.getDutyName());
			uesi.setUserExtendId(userExtend.getId());
			uesi.setUserName(userExtend.getName());
			
			uesi.setsItemName(jo1.getString("text"));
			uesi.setsItemPrice(Long.parseLong(jo1.getString("value")));			
			if(jo1.getString("key")!= null && !this.isFeeOfCase(jo1.getString("key"))) {				
				uesi.setsItemAmount(1L);
			}else if(jo1.getString("key")!= null && this.isFeeOfCase(jo1.getString("key"))) {
				uesi.setsItemAmount(this.getCaseCount(jo1.getString("key"),processedCases));
			}else{
				uesi.setsItemAmount(0L);
			}
			list.add(uesi);
		}
		return list;
		
    }
	
	private boolean isFeeOfCase(String key){
		return key.matches("^\\d+$");//费用的key用数字表示
	}
    
    private Long getCaseCount(String difficultLevelKey, List<ConflictCase> processedCases) {
	    Long count = 0L;
	    for(ConflictCase conflictCase : processedCases) {
	    	if(conflictCase.getDifficultLevel().equals(difficultLevelKey)) {
	    		count++;
	    	}
	    }
		return count;
	}

//	private JSONObject getJSONOBJByKey(String item,String jsonArray) throws JSONException {
//    	JSONObject jo = new JSONObject();
//		JSONArray ja = jo.getJSONArray(jsonArray);
//		JSONObject returnObj = null;;
//		for(int i=0;i<ja.length();i++) {
//			JSONObject jo1 = ja.getJSONObject(i);
//			if(item !=null && jo1.get("key")!= null && item.equals((String)jo1.get("key"))) {
//				returnObj = jo1;
//				break;
//			}
//			
//		}
//		return returnObj;
//    }
    
    private DutySalary getDutySalary(UserExtend userextend,List<DutySalary> dutySalaryList) {
    	DutySalary ds = null;
    	for(DutySalary dutySalary:dutySalaryList) {
    		if(userextend.getmLevel()!= null && userextend.getmLevel().equals(dutySalary.getDutyCode())){
    			ds = dutySalary;
    			break;
    		}
    	}
    	return ds;
    }
    
    
    private List<ConflictCase> getProcessedCaseList(UserExtend ue,List<CaseProcessInfo> caseProcessInfoList){
    	
    	List<ConflictCase> list = new ArrayList<ConflictCase>();
    	if(ue.getUserId()== null) {
    		return list;
    	}
    	if(caseProcessInfoList != null && caseProcessInfoList.size()>0) {
    		 list = caseProcessInfoList.stream().filter(caseProcessInfo -> caseProcessInfo.getmPersonId().longValue() == ue.getUserId().longValue())
    				.map(CaseProcessInfo::getConflictCase).collect(Collectors.toList());
    	}
    	return list;
    }

	@Override
	public Page<UserExtendSalaryDTO> findAll(Map<String, Object> filters) {

        return new PageImpl<UserExtendSalary>(userExtendSalaryRepository.findAll(filters), null,
        		userExtendSalaryRepository.getTotalRows(filters)).map(userExtendSalaryMapper::toDto);
	}
	
	

    
   
}
