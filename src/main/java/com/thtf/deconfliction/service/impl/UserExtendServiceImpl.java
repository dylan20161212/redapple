package com.thtf.deconfliction.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thtf.app.domain.Role;
import com.thtf.app.domain.User;
import com.thtf.app.repository.OrganizationRepository;
import com.thtf.app.repository.RoleRepository;
import com.thtf.app.repository.UserRepository;
import com.thtf.app.repository.UserRoleOrganizationRepository;
import com.thtf.app.web.rest.errors.LoginAlreadyUsedException;
import com.thtf.deconfliction.domain.UserExtend;
import com.thtf.deconfliction.repository.UserExtendRepository;
import com.thtf.deconfliction.service.UserExtendService;
import com.thtf.deconfliction.service.dto.UserExtendDTO;
import com.thtf.deconfliction.service.mapper.UserExtendMapper;


/**
 * Service Implementation for managing UserExtend.
 */
@Service
@Transactional
public class UserExtendServiceImpl implements UserExtendService {

    private final Logger log = LoggerFactory.getLogger(UserExtendServiceImpl.class);

    private final UserExtendRepository userExtendRepository;
    
    private final UserRepository userRepository;
    
    private final RoleRepository roleRepository;

    private final UserExtendMapper userExtendMapper;
    
    private final UserRoleOrganizationRepository userRoleOrganizationRepository;
    
    private final OrganizationRepository organizationRepository;
  

    public UserExtendServiceImpl(UserExtendRepository userExtendRepository, UserExtendMapper userExtendMapper,UserRepository userRepository,UserRoleOrganizationRepository userRoleOrganizationRepository,RoleRepository roleRepository,OrganizationRepository organizationRepository) {
        this.userExtendRepository = userExtendRepository;
        this.userExtendMapper = userExtendMapper;
        this.userRepository = userRepository; 
        this.userRoleOrganizationRepository = userRoleOrganizationRepository;
        this.roleRepository = roleRepository;
        this.organizationRepository = organizationRepository;
    }

    /**
     * Save a userExtend.
     *
     * @param userExtendDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public UserExtendDTO save(UserExtendDTO userExtendDTO) {
    	if(userExtendDTO.getId() == null){
    	   List<User> users = this.userRepository.findByLogin(userExtendDTO.getLogin());
    	   List<UserExtend> userExtends = this.userExtendRepository.findByLogin(userExtendDTO.getLogin());
    	   if( users.size()>0 || userExtends.size()>0 ){
    		   throw new LoginAlreadyUsedException();
    	   }   	   
    	}
        log.debug("Request to save UserExtend : {}", userExtendDTO);
        UserExtend userExtend = userExtendMapper.toEntity(userExtendDTO);
        userExtend = userExtendRepository.save(userExtend);
        return userExtendMapper.toDto(userExtend);
    }
    
    //@Scheduled(cron = "0 0/2 * * * ?")
    public void syncUserExtendsToUser(){
    	String camandaRoleName = "camunda";
    	Set<Role> roleSet = this.roleRepository.findByRoleName(camandaRoleName);
    	if(roleSet.size() == 0){
    		return;
    	}
    	List<UserExtend> userExtendList = this.userExtendRepository.findAll();
    	List<User> userList = this.userRoleOrganizationRepository.findUsers(camandaRoleName);	
    	for(UserExtend userExtend : userExtendList){
    		User user = null;
    		boolean existed = false;
    		for(User user0: userList){
    			user = user0;
    			if(userExtend.getLogin().equals(user.getLogin())){
    				user.setEmail(userExtend.getEmail());
    				user.setRealName(userExtend.getName());
    				existed =true;
    				
    				break;
    			}
    		}
    		if(!existed){
    			user = new User();
    			user.setLogin(userExtend.getLogin());
    			user.setRoles(roleSet);
    			PasswordEncoder pe = new BCryptPasswordEncoder();
    			user.setPassword(pe.encode("admin"));
    			user.setActivated(true);
    			user.setOrganization(this.organizationRepository.getOne(Long.parseLong(userExtend.getOrganizationId())));
    			user.setOrganizationName(userExtend.getOrganizationName());
    			user.setEmail(userExtend.getEmail());
				user.setRealName(userExtend.getName());
				user.setLangKey("zh-cn");
    		}
    		this.userRepository.save(user);
    		
    	}
    		
    }
    
    /**
     * 同步user.id 到 userextend.userid
     */
    //@Scheduled(cron = "0 0/2 * * * ?")
    public void syncUpdateUserExtend(){
		String camandaRoleName = "camunda";
    	Set<Role> roleSet = this.roleRepository.findByRoleName(camandaRoleName);
    	if(roleSet.size() == 0){
    		return;
    	}
    	List<UserExtend> userExtendList = this.userExtendRepository.findAll();
    	List<User> userList = this.userRoleOrganizationRepository.findUsers(camandaRoleName);	
    	for(User user:userList){
    		for(UserExtend userExtend : userExtendList){
    			if(user.getLogin().equals(userExtend.getLogin())){
    				userExtend.setUserId(user.getId());
    				this.userExtendRepository.save(userExtend);
    				break;
    			}
    		}
    		
    	}
    	
	}
    
    
    
    
    private Boolean loginExisted(String login,boolean isAdd) {
        List<UserExtend> userExtendList = this.userExtendRepository.findByLogin(login);
        List<User > userList= this.userRepository.findByLogin(login);
        if(isAdd) {
        	return (userExtendList != null && userExtendList.size()>0) || (userList != null && userList.size()>0);
        }else {
        	return (userExtendList != null && userExtendList.size()>1) || (userList != null && userList.size()>1);
        }
    	
    }
    
    
    
    private Boolean emailExisted(String email,boolean isAdd) {
    	 List<UserExtend> userExtendList = this.userExtendRepository.findByEmail(email);
         List<User > userList= this.userRepository.findByEmail(email);
         if(isAdd) {
         	return userExtendList != null && userExtendList.size()>0 && userList != null && userList.size()>0;
         }else {
         	return userExtendList != null && userExtendList.size()>1 && userList != null && userList.size()>1;
         }
    	
    }

    /**
     * Get all the userExtends.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UserExtendDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UserExtends");
        return userExtendRepository.findAll(pageable)
            .map(userExtendMapper::toDto);
    }
    

    /**
     * Get one userExtend by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public UserExtendDTO findOne(Long id) {
        log.debug("Request to get UserExtend : {}", id);
        UserExtend userExtend = userExtendRepository.findById(id).orElse(null);
        return userExtendMapper.toDto(userExtend);
    }

    /**
     * Delete the userExtend by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserExtend : {}", id);
        userExtendRepository.deleteById(id);
    }

	@Override
	public Page<UserExtendDTO> findAll(Map<String, Object> filters) {
		return new PageImpl<UserExtend>(userExtendRepository.findAll(filters), null,
				userExtendRepository.getTotalRows(filters)).map(userExtendMapper::toDto);
	}
}
