package com.thtf.deconfliction.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.thtf.app.domain.Organization;
import com.thtf.app.domain.User;
import com.thtf.app.repository.OrganizationRepository;
import com.thtf.app.repository.UserRepository;
import com.thtf.app.repository.UserRoleOrganizationRepository;
import com.thtf.app.security.SecurityUtils;
import com.thtf.app.service.UserService;
import com.thtf.app.web.rest.errors.InternalServerErrorException;

@Service
public class CommonUtilService {

	private final UserRepository userRepository;
	private final UserService userService;

	public CommonUtilService(UserRepository userRepository,
			UserRoleOrganizationRepository userRoleOrganizationRepository,
			OrganizationRepository organizationRepository, UserService userService) {
		super();
		this.userRepository = userRepository;
		this.userService = userService;
	}

	public User getCurrentLoginUser() {
		final String userLogin = SecurityUtils.getCurrentUserLogin()
				.orElseThrow(() -> new InternalServerErrorException("Current user login not found"));
		User user = this.userRepository.findOneByLogin(userLogin).orElse(null);
		return user;
	}

	public List<Long> getCurrentLoginOrgIds() {
		List<Organization> orgList = this.getMyOrgs();
		List<Long> lOrgIds = orgList.stream().map(Organization::getId).collect(Collectors.toList());
		return lOrgIds;
	}

	public List<Organization> getMyOrgs() {
		return this.userService.getMyOrgIds();
	}
}
