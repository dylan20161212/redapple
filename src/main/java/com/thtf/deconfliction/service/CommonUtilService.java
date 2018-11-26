package com.thtf.deconfliction.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.thtf.app.domain.Organization;
import com.thtf.app.domain.User;
import com.thtf.app.domain.UserRoleOrganization;
import com.thtf.app.repository.OrganizationRepository;
import com.thtf.app.repository.UserRepository;
import com.thtf.app.repository.UserRoleOrganizationRepository;
import com.thtf.app.security.SecurityUtils;
import com.thtf.app.web.rest.errors.InternalServerErrorException;

@Service
public class CommonUtilService {
	
	
	private final UserRepository userRepository;
	
	private  final UserRoleOrganizationRepository userRoleOrganizationRepository;
	
	private final OrganizationRepository organizationRepository;
	
	
	
	public CommonUtilService(UserRepository userRepository,
			UserRoleOrganizationRepository userRoleOrganizationRepository,
			OrganizationRepository organizationRepository) {
		super();
		this.userRepository = userRepository;
		this.userRoleOrganizationRepository = userRoleOrganizationRepository;
		
		this.organizationRepository = organizationRepository;
	}


	public  User getCurrentLoginUser() {
		final String userLogin = SecurityUtils.getCurrentUserLogin()
				.orElseThrow(() -> new InternalServerErrorException("Current user login not found"));
		
        User user = this.userRepository.findOneByLogin(userLogin).orElse(null);
		return user;
	}
	
	
	public  List<Long> getCurrentLoginOrgIds() {
		List<Organization> orgList = this.getMyOrgs();
		List<Long> lOrgIds = orgList.stream().map(Organization::getId).collect(Collectors.toList());
		return lOrgIds;
	}
	
	
	public  List<Organization> getMyOrgs() {
		final String userLogin = SecurityUtils.getCurrentUserLogin()
				.orElseThrow(() -> new InternalServerErrorException("Current user login not found"));
		User user = userRepository.findOneByLogin(userLogin).orElse(null);
		List<Organization> orgList = new ArrayList<>();
		if (user != null) {
			if (user.getSelOrgRoleId() != null) {
				UserRoleOrganization tempOr = userRoleOrganizationRepository.findById(user.getSelOrgRoleId()).orElse(null);
				if (tempOr != null) {
					if (tempOr.getOrganization() != null) {
						orgList.add(tempOr.getOrganization());
					}
				} else if (user.getOrganization() != null) {
					orgList.add(user.getOrganization());
				}
			} else if (user.getOrganization() != null) {
				orgList.add(user.getOrganization());
			}
			int i = 0;
			while (i < orgList.size()) {
				orgList.addAll(organizationRepository.findByUpper(orgList.get(i)));
				i++;
			}
		}
		// return
		// orgList.stream().map(Organization::getId).collect(Collectors.toList());
		return orgList;
	}
}
