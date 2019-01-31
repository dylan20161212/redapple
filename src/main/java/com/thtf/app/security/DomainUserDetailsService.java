package com.thtf.app.security;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thtf.app.domain.User;
import com.thtf.app.repository.UserRepository;
import com.thtf.app.repository.UserRoleOrganizationRepository;
import com.thtf.app.service.UserService;



/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

	private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);

	private final UserRepository userRepository;

	private final UserRoleOrganizationRepository userRoleOrganizationRepository;
	
	@Autowired
	private  UserService userService;

	public DomainUserDetailsService(UserRepository userRepository,
			UserRoleOrganizationRepository userRoleOrganizationRepository) {
		this.userRepository = userRepository;
		this.userRoleOrganizationRepository = userRoleOrganizationRepository;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(final String login) {
		log.debug("Authenticating {}", login);
		String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
		Optional<User> userByEmailFromDatabase = userRepository.findOneByEmailIgnoreCase(lowercaseLogin);
		return userByEmailFromDatabase.map(user -> createSpringSecurityUser(lowercaseLogin, user)).orElseGet(() -> {
			Optional<User> userByLoginFromDatabase = userRepository.findOneByLoginIgnoreCase(lowercaseLogin);
			return userByLoginFromDatabase.map(user -> createSpringSecurityUser(lowercaseLogin, user))
					.orElseThrow(() -> new UsernameNotFoundException(
							"User " + lowercaseLogin + " was not found in the " + "database"));
		});
	}

	private org.springframework.security.core.userdetails.User createSpringSecurityUser(String lowercaseLogin,
			User user) {
		if (!user.getActivated()) {
			throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated");
		}
//		Set<Resource> tempResources = new HashSet<>();
//		if (user.getSelOrgRoleId() != null) {
//			UserRoleOrganization userRoleOrganization = userRoleOrganizationRepository.findByRoleId(user.getSelOrgRoleId());
//			if (null != userRoleOrganization) {
//				tempResources.addAll(userRoleOrganization.getRole().getResources());
//			} else {
//				List<UserRoleOrganization> listUserRoleOrganization = userRoleOrganizationRepository
//						.findByUserIdOrderByIdAsc(user.getId());
//				if (!listUserRoleOrganization.isEmpty()) {
//					tempResources.addAll(listUserRoleOrganization.get(0).getRole().getResources());
//				}else{
//					throw new UserNotActivatedException("User " + lowercaseLogin + " no role");
//				}
//			}
//		} else {
//			List<UserRoleOrganization> listUserRoleOrganization = userRoleOrganizationRepository
//					.findByUserIdOrderByIdAsc(user.getId());
//			if (!listUserRoleOrganization.isEmpty()) {
//				tempResources.addAll(listUserRoleOrganization.get(0).getRole().getResources());
//			}else{
//				throw new UserNotActivatedException("User " + lowercaseLogin + " no role");
//			}
//		}
		
		List<CustomGrantedAuthority> grantedAuthorities = this.userService.getAuthorities(user).stream().map(CustomGrantedAuthority::new)
				.collect(Collectors.toList());
		return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
				grantedAuthorities);
	}

	
}
