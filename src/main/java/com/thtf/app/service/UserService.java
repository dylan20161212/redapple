package com.thtf.app.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thtf.app.config.Constants;
import com.thtf.app.domain.Authority;
import com.thtf.app.domain.Organization;
import com.thtf.app.domain.Resource;
import com.thtf.app.domain.Role;
import com.thtf.app.domain.User;
import com.thtf.app.domain.UserRoleOrganization;
import com.thtf.app.repository.AuthorityRepository;
import com.thtf.app.repository.OrganizationRepository;
import com.thtf.app.repository.UserRepository;
import com.thtf.app.repository.UserRoleOrganizationRepository;
import com.thtf.app.security.AuthoritiesConstants;
import com.thtf.app.security.SecurityUtils;
import com.thtf.app.service.dto.RoleDTO;
import com.thtf.app.service.dto.UserDTO;
import com.thtf.app.service.mapper.RoleMapper;
import com.thtf.app.service.util.RandomUtil;
import com.thtf.app.web.rest.errors.InternalServerErrorException;
import com.thtf.app.web.rest.util.PaginationUtil;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class UserService {

	private final Logger log = LoggerFactory.getLogger(UserService.class);

	private final UserRepository userRepository;

	private final OrganizationRepository organizationRepository;

	private final PasswordEncoder passwordEncoder;

	private final AuthorityRepository authorityRepository;

	private final CacheManager cacheManager;

	private final RoleMapper roleMapper;

	private final UserRoleOrganizationRepository userRoleOrganizationRepository;

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
			AuthorityRepository authorityRepository, CacheManager cacheManager,
			OrganizationRepository organizationRepository, RoleMapper roleMapper,
			UserRoleOrganizationRepository userRoleOrganizationRepository) {
		this.userRepository = userRepository;
		this.organizationRepository = organizationRepository;
		this.passwordEncoder = passwordEncoder;
		this.authorityRepository = authorityRepository;
		this.cacheManager = cacheManager;
		this.roleMapper = roleMapper;
		this.userRoleOrganizationRepository = userRoleOrganizationRepository;
	}

//	public Optional<User> activateRegistration(String key) {
//		log.debug("Activating user for activation key {}", key);
//		return userRepository.findOneByActivationKey(key).map(user -> {
//			// activate given user for the registration key.
//			user.setActivated(true);
////			user.setActivationKey(null);
//			cacheManager.getCache(UserRepository.USERS_BY_LOGIN_CACHE).evict(user.getLogin());
//			cacheManager.getCache(UserRepository.USERS_BY_EMAIL_CACHE).evict(user.getEmail());
//			log.debug("Activated user: {}", user);
//			return user;
//		});
//	}

//	public Optional<User> completePasswordReset(String newPassword, String key) {
//		log.debug("Reset user password for reset key {}", key);
//
//		return userRepository.findOneByResetKey(key)
//				.filter(user -> user.getResetDate().isAfter(Instant.now().minusSeconds(86400))).map(user -> {
//					user.setPassword(passwordEncoder.encode(newPassword));
//					user.setResetKey(null);
//					user.setResetDate(null);
//					cacheManager.getCache(UserRepository.USERS_BY_LOGIN_CACHE).evict(user.getLogin());
//					cacheManager.getCache(UserRepository.USERS_BY_EMAIL_CACHE).evict(user.getEmail());
//					return user;
//				});
//	}

//	public Optional<User> requestPasswordReset(String mail) {
//		return userRepository.findOneByEmailIgnoreCase(mail).filter(User::getActivated).map(user -> {
//			user.setResetKey(RandomUtil.generateResetKey());
//			user.setResetDate(Instant.now());
//			cacheManager.getCache(UserRepository.USERS_BY_LOGIN_CACHE).evict(user.getLogin());
//			cacheManager.getCache(UserRepository.USERS_BY_EMAIL_CACHE).evict(user.getEmail());
//			return user;
//		});
//	}

	public User registerUser(UserDTO userDTO, String password) {

		User newUser = new User();
		Authority authority = authorityRepository.findById(AuthoritiesConstants.USER).orElse(null);
		Set<Authority> authorities = new HashSet<>();
		String encryptedPassword = passwordEncoder.encode(password);
		newUser.setLogin(userDTO.getLogin());
		// new user gets initially a generated password
		newUser.setPassword(encryptedPassword);
		newUser.setRealName(userDTO.getRealName());
		newUser.setEmail(userDTO.getEmail());
		newUser.setImageUrl(userDTO.getImageUrl());
		newUser.setLangKey(userDTO.getLangKey());
		// new user is not active
		newUser.setActivated(false);
		// new user gets registration key
		authorities.add(authority);
		// newUser.setAuthorities(authorities);
		Role roleForRegister = new Role();
		roleForRegister.setId(5L);
		Set<Role> roles = new HashSet<>();
		roles.add(roleForRegister);
		// newUser.setRoles(userDTO.getRoles().stream().map(roleMapper::toEntity).collect(Collectors.toSet()));
		newUser.setRoles(roles);
		userRepository.save(newUser);
		cacheManager.getCache(UserRepository.USERS_BY_LOGIN_CACHE).evict(newUser.getLogin());
		cacheManager.getCache(UserRepository.USERS_BY_EMAIL_CACHE).evict(newUser.getEmail());
		log.debug("Created Information for User: {}", newUser);
		return newUser;
	}

	public User createUser(UserDTO userDTO) {
		User user = new User();
		user.setLogin(userDTO.getLogin());
		user.setRealName(userDTO.getRealName());
		user.setEmail(userDTO.getEmail());
		user.setImageUrl(userDTO.getImageUrl());
		// user.setOrganization(organizationRepository.findOne(userDTO.getOrganizationId()));
		if (null != userDTO.getOrganizationId()) {
			user.setOrganization(organizationRepository.findById(userDTO.getOrganizationId()).orElse(null));
		} else {
			user.setOrganization(null);// 应该设置为当前登录用户所在的组织结构
		}
//		user.setOrganizationName(userDTO.getOrganizationName());
		if (userDTO.getLangKey() == null) {
			user.setLangKey(Constants.DEFAULT_LANGUAGE); // default language
		} else {
			user.setLangKey(userDTO.getLangKey());
		}
		// if (userDTO.getAuthorities() != null) {
		// Set<Authority> authorities = userDTO.getAuthorities().stream()
		// .map(authorityRepository::findOne)
		// .collect(Collectors.toSet());
		// user.setAuthorities(authorities);
		// }
		String encryptedPassword = passwordEncoder.encode(RandomUtil.generatePassword());
		user.setPassword(encryptedPassword);
//		user.setResetKey(RandomUtil.generateResetKey());
//		user.setResetDate(Instant.now());
		user.setActivated(userDTO.isActivated());
		// user.setRoles(userDTO.getRoles().stream().map(r ->
		// roleMapper.toEntity(r)).collect(Collectors.toSet()));
		userRepository.save(user);
		this.userRoleOrganizationRepository
				.saveAll(this.getNeedAdded(userDTO, new ArrayList<UserRoleOrganization>(), user));
		cacheManager.getCache(UserRepository.USERS_BY_LOGIN_CACHE).evict(user.getLogin());
		cacheManager.getCache(UserRepository.USERS_BY_EMAIL_CACHE).evict(user.getEmail());
		log.debug("Created Information for User: {}", user);
		return user;
	}

	/**
	 * Update basic information (first name, last name, email, language) for the
	 * current user.
	 *
	 * @param firstName
	 *            first name of user
	 * @param lastName
	 *            last name of user
	 * @param email
	 *            email id of user
	 * @param langKey
	 *            language key
	 * @param imageUrl
	 *            image URL of user
	 */
	public void updateUser(String realName, String email, String langKey, String imageUrl) {
		SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneByLogin).ifPresent(user -> {
			user.setRealName(realName);
			user.setEmail(email);
			user.setLangKey(langKey);
			user.setImageUrl(imageUrl);
			cacheManager.getCache(UserRepository.USERS_BY_LOGIN_CACHE).evict(user.getLogin());
			cacheManager.getCache(UserRepository.USERS_BY_EMAIL_CACHE).evict(user.getEmail());
			log.debug("Changed Information for User: {}", user);
		});
	}

	/**
	 * Update all information for a specific user, and return the modified user.
	 *
	 * @param userDTO
	 *            user to update
	 * @return updated user
	 */
	@Transactional
	public Optional<UserDTO> updateUser(UserDTO userDTO) {

		User user = userRepository.findById(userDTO.getId()).orElse(null);
		List<UserRoleOrganization> exists = this.userRoleOrganizationRepository.findByUserId(user.getId());
		user.setLogin(userDTO.getLogin());
		user.setRealName(userDTO.getRealName());
		user.setEmail(userDTO.getEmail());
		user.setImageUrl(userDTO.getImageUrl());
		user.setActivated(userDTO.isActivated());
		user.setLangKey(userDTO.getLangKey());
		if (userDTO.getOrganizationId() != null) {
			user.setOrganization(organizationRepository.findById(userDTO.getOrganizationId()).orElse(null));
		}
//		user.setOrganizationName(userDTO.getOrganizationName());
		cacheManager.getCache(UserRepository.USERS_BY_LOGIN_CACHE).evict(user.getLogin());
		cacheManager.getCache(UserRepository.USERS_BY_EMAIL_CACHE).evict(user.getEmail());
		log.debug("Changed Information for User: {}", user);
		user = this.userRepository.save(user);
		this.userRoleOrganizationRepository.saveAll(this.getNeedAdded(userDTO, exists, user));
		return Optional.of(user).map(UserDTO::new);
	}

	private List<UserRoleOrganization> getNeedAdded(UserDTO userDTO, List<UserRoleOrganization> exists, User user) {
		List<UserRoleOrganization> added = new ArrayList<>();
		Set<Role> dtoRoles = userDTO.getRoles().stream().map(roleMapper::toEntity).collect(Collectors.toSet());
		for (Role role : dtoRoles) {
			boolean isContianed = false;
			for (UserRoleOrganization userRoleOrganization : exists) {
				if (userRoleOrganization.getOrganization() == null) {
					userRoleOrganization.setOrganization(user.getOrganization());
				}
				if (role.getId() == userRoleOrganization.getRole().getId() && (userDTO.getOrganizationId()
						.longValue() == userRoleOrganization.getOrganization().getId().longValue())) {
					isContianed = true;
					break;
				}
			}
			if (!isContianed) {
				UserRoleOrganization userRoleOrganization = new UserRoleOrganization();
				userRoleOrganization.setRole(role);
				userRoleOrganization.setUser(user);
				if (userDTO.getOrganizationId() != null) {
					userRoleOrganization
							.setOrganization(this.organizationRepository.findById(userDTO.getOrganizationId()).orElse(null));
				} else {
					userRoleOrganization.setOrganization(null);
				}

				added.add(userRoleOrganization);
			}
		}
		return added;
	}

	// （组织机构为空的-传过来的用户组织机构）差集
	@SuppressWarnings("unused")
	private List<UserRoleOrganization> getNeedDeleted(UserDTO userDTO, List<UserRoleOrganization> exists) {
		List<UserRoleOrganization> deleted = new ArrayList<>();
		for (UserRoleOrganization userRoleOrganization : exists) {
			if (notContained(userRoleOrganization, userDTO)) {

				deleted.add(userRoleOrganization);

			}
		}
		return deleted;
	}

	private boolean notContained(UserRoleOrganization userRoleOrganization, UserDTO userDTO) {
		boolean contained = false;
		Set<RoleDTO> roles = userDTO.getRoles();
		for (RoleDTO roleDTO : roles) {
			if (roleDTO.getId() == userRoleOrganization.getRole().getId()
					&& userDTO.getOrganizationId() == userRoleOrganization.getOrganization().getId()) {
				contained = true;
				break;
			}
		}
		return !contained;
	}

	@Transactional
	public void deleteUser(String login) {
		userRepository.findOneByLogin(login).ifPresent(user -> {
			userRepository.delete(user);
			cacheManager.getCache(UserRepository.USERS_BY_LOGIN_CACHE).evict(user.getLogin());
			cacheManager.getCache(UserRepository.USERS_BY_EMAIL_CACHE).evict(user.getEmail());
			log.debug("Deleted User: {}", user);
		});
	}

	@Transactional
	public void resetPassword(String login) {
		userRepository.findOneByLogin(login).ifPresent(user -> {
			String encryptedPassword = passwordEncoder.encode("admin");
			user.setPassword(encryptedPassword);
			userRepository.save(user);
			cacheManager.getCache(UserRepository.USERS_BY_LOGIN_CACHE).evict(user.getLogin());
			cacheManager.getCache(UserRepository.USERS_BY_EMAIL_CACHE).evict(user.getEmail());
			log.debug("Reset Password User: {}", user);
		});
	}

	@Transactional
	public void deleteById(String[] id) {

		List<Long> lId = new ArrayList<>();
		// String[] id = id.split(",");
		long l = id.length;
		for (int i = 0; i < l; i++) {
			lId.add(Long.parseLong(id[i]));
			log.debug("Deleted Users: {}", id[i]);
		}
		List<User> users = userRepository.findByIdIn(lId);
		// 判断用户属于哪个组织机构，当前登录的用户是否有权限删除
		userRepository.deleteAll(users);
		log.debug("Deleted Users: {}", users);
		// userRepository.findOneByLogin(id).ifPresent(user -> {
		// userRepository.delete(user);
		// cacheManager.getCache(USERS_CACHE).evict(id);
		// log.debug("Deleted User: {}", user);
		// });
	}

	public void changePassword(String password) {
		SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneByLogin).ifPresent(user -> {
			String encryptedPassword = passwordEncoder.encode(password);
			user.setPassword(encryptedPassword);
			cacheManager.getCache(UserRepository.USERS_BY_LOGIN_CACHE).evict(user.getLogin());
			cacheManager.getCache(UserRepository.USERS_BY_EMAIL_CACHE).evict(user.getEmail());
			log.debug("Changed password for User: {}", user);
		});
	}

	@Transactional(readOnly = true)
	public Page<UserDTO> getAllManagedUsers(Map<String, Object> filters) {
		// 开始设置过滤条件
//		List<Organization> orgList = this.getMyOrgIds();
		List<Organization> orgList = new ArrayList<Organization>();
		Organization org = new Organization();
				org.setId(41L);
		orgList.add(org);
		List<Long> lOrgIds = orgList.stream().map(Organization::getId).collect(Collectors.toList());

		String filterscount = (String) filters.get("filterscount");
		if (filterscount == null || "".equals(filterscount)) {
			filterscount = "0";
		}
		filters.put("filterscount", (Integer.parseInt(filterscount) + 1) + "");
		filters.put("filtervalue" + filterscount, lOrgIds);
		filters.put("filtercondition" + filterscount, "IN");
		filters.put("filterdatafield" + filterscount, "organization");
		filters.put("filteroperator" + filterscount, "0");
		List<User> userList = userRepository.findAllNative(filters);
		for (User user : userList) {
			Organization org4 = user.getOrganization();
			org4.getOrgName();
			
		}
		return new PageImpl<User>(userList, PaginationUtil.getDefaultPageable(),
				userRepository.getRows(filters)).map(UserDTO::new);
	}

	@Transactional(readOnly = true)
	public Page<UserDTO> getAllManagedUsers(Pageable pageable) {
		return userRepository.findAllByLoginNot(pageable, Constants.ANONYMOUS_USER).map(UserDTO::new);
	}

	@Transactional(readOnly = true)
	public Optional<User> getUserWithAuthoritiesByLogin(String login) {
		return userRepository.getByLogin(login);
	}

	@Transactional(readOnly = true)
	public Optional<UserDTO> getUserWithRolesByLogin(String login) {

		return userRepository.findOneByLogin(login).map(u -> new UserDTO(u.getId(), u.getLogin(), u.getRealName(), u.getEmail(), u.getActivated(), u.getImageUrl(), u.getLangKey(), u.getCreatedBy(),
				u.getCreatedDate(), u.getLastModifiedBy(), u.getLastModifiedDate(), null,
				this.getCurrentLoginRole(u).stream()
						.map(r -> new RoleDTO(r.getId(), r.getRoleName(), r.getRoleDescription(), r.getRoleFlag(),
								r.getRoleEffDate(), r.getRoleExpDate(), null, r.getCreatedBy(), r.getCreatedDate(),
								r.getLastModifiedBy(), r.getLastModifiedDate()))
						.collect(Collectors.toSet()),
				u.getOrganization() == null ? null : u.getOrganization().getId(),u.getOrganization()==null?null:u.getOrganization().getOrgName()));

	}

	private Set<Role> getCurrentLoginRole(User user) {

		List<UserRoleOrganization> list = userRoleOrganizationRepository.findByUserIdOrderByIdAsc(user.getId());
		Set<Role> roles = new HashSet<>();
		for (UserRoleOrganization userRoleOrganization : list) {
			// if ((userRoleOrganization.getOrganization() ==
			// user.getOrganization()
			//
			// ) || (userRoleOrganization.getOrganization()!=null &&
			// user.getOrganization()!=null &&
			// userRoleOrganization.getOrganization().getId() ==
			// user.getOrganization().getId())) {
			// roles.add(userRoleOrganization.getRole());
			// }

			if ((userRoleOrganization.getOrganization() == null) || (userRoleOrganization.getOrganization() != null
					&& userRoleOrganization.getOrganization().equals(user.getOrganization()))) {
				roles.add(userRoleOrganization.getRole());
			}
		}

		return roles;
	}

	@Transactional(readOnly = true)
	public Optional<User> getUserWithAuthorities(Long id) {
		return userRepository.findOneWithAuthoritiesById(id);
	}

	@Transactional(readOnly = true)
	public Optional<User> getUserWithAuthorities() {
		return SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneWithAuthoritiesByLogin);
	}

	@Transactional(readOnly = true)
	public Optional<User> getUserWithAuthoritiesR() {
		User tempUser = SecurityUtils.getCurrentUserLogin().flatMap(userRepository::getByLogin).orElse(null);
		// 获取当前用户角色->通过角色获取权限
		if (null != tempUser) {
			Set<Resource> tempResources = new HashSet<>();
			tempUser.getRoles().forEach(role -> tempResources.addAll(role.getResources()));
			// 设置权限
			Set<Authority> authorities = new HashSet<>();
			for (Resource res : tempResources) {
				Authority auth = new Authority();

				if (null != res.getResOperate() && !"".equals(res.getResOperate())) {
					auth.setName(res.getResRouterLink() + ":" + res.getResOperate());
				} else {
					auth.setName(res.getResRouterLink());
				}
				authorities.add(auth);
			}

			tempUser.setAuthorities(authorities);
		}
		return Optional.of(tempUser);
	}

	@Transactional(readOnly = true)
	public UserDTO getUserDTOWithAuthorities() {
		UserDTO tempUdto = new UserDTO();
		User tempUser = SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneByLogin).orElse(null);
		// 获取当前用户组织机构角色selOrgRoleId->通过角色获取权限
		if (null != tempUser) {
			Set<Resource> tempResources = new HashSet<>();
			if (tempUser.getSelOrgRoleId() != null) {
				UserRoleOrganization userRoleOrganization = userRoleOrganizationRepository
						.findById(tempUser.getSelOrgRoleId()).orElse(null);
				if (null != userRoleOrganization) {
					tempResources.addAll(userRoleOrganization.getRole().getResources());
				} else {
					List<UserRoleOrganization> listUserRoleOrganization = userRoleOrganizationRepository
							.findByUserIdOrderByIdAsc(tempUser.getId());
					if (!listUserRoleOrganization.isEmpty()) {
						tempResources.addAll(listUserRoleOrganization.get(0).getRole().getResources());
					}
				}
			} else {
				List<UserRoleOrganization> listUserRoleOrganization = userRoleOrganizationRepository
						.findByUserIdOrderByIdAsc(tempUser.getId());
				if (!listUserRoleOrganization.isEmpty()) {
					tempResources.addAll(listUserRoleOrganization.get(0).getRole().getResources());
				}
			}
			// 设置权限
			Set<String> authorities = new HashSet<>();
			for (Resource res : tempResources) {
				String auth = new String();

				if (null != res.getResOperate() && !"".equals(res.getResOperate())) {
					auth = res.getResRouterLink() + ":" + res.getResOperate();
				} else {
					auth = res.getResRouterLink();
				}
				authorities.add(auth);
			}

			tempUdto = new UserDTO(tempUser.getId(), tempUser.getLogin(), tempUser.getRealName(), tempUser.getEmail(), tempUser.getActivated(), tempUser.getImageUrl(),
					tempUser.getLangKey(), tempUser.getCreatedBy(), tempUser.getCreatedDate(),
					tempUser.getLastModifiedBy(), tempUser.getLastModifiedDate(), authorities, null,
					// u.getRoles().stream()
					// .map((r) -> new RoleDTO(r.getId(), r.getRoleName(),
					// r.getRoleDescription(), r.getRoleFlag(),
					// r.getRoleEffDate(), r.getRoleExpDate(), null,
					// r.getCreatedBy(), r.getCreatedDate(),
					// r.getLastModifiedBy(), r.getLastModifiedDate()))
					// .collect(Collectors.toSet()),
					tempUser.getOrganization() == null ? null : tempUser.getOrganization().getId(),
							tempUser.getOrganization() == null ? null : tempUser.getOrganization().getOrgName()
					);
		}
		return tempUdto;
	}

	@Transactional(readOnly = true)
	public Optional<User> getUserWithAuthoritiesO() {
		User tempUser = SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneByLogin).orElse(null);
		// 获取当前用户组织机构角色selOrgRoleId->通过角色获取权限
		if (null != tempUser) {
			Set<Resource> tempResources = new HashSet<>();
			if (tempUser.getSelOrgRoleId() != null) {
				UserRoleOrganization userRoleOrganization = userRoleOrganizationRepository
						.findById(tempUser.getSelOrgRoleId()).orElse(null);
				if (null != userRoleOrganization) {
					tempResources.addAll(userRoleOrganization.getRole().getResources());
				} else {
					List<UserRoleOrganization> listUserRoleOrganization = userRoleOrganizationRepository
							.findByUserIdOrderByIdAsc(tempUser.getId());
					if (!listUserRoleOrganization.isEmpty()) {
						tempResources.addAll(listUserRoleOrganization.get(0).getRole().getResources());
					}
				}
			} else {
				List<UserRoleOrganization> listUserRoleOrganization = userRoleOrganizationRepository
						.findByUserIdOrderByIdAsc(tempUser.getId());
				if (!listUserRoleOrganization.isEmpty()) {
					tempResources.addAll(listUserRoleOrganization.get(0).getRole().getResources());
				}
			}
			// 设置权限
			Set<Authority> authorities = new HashSet<>();
			for (Resource res : tempResources) {
				Authority auth = new Authority();

				if (null != res.getResOperate() && !"".equals(res.getResOperate())) {
					auth.setName(res.getResRouterLink() + ":" + res.getResOperate());
				} else {
					auth.setName(res.getResRouterLink());
				}
				authorities.add(auth);
			}

			tempUser.setAuthorities(authorities);
		}
		return Optional.of(tempUser);
	}

	/**
	 * Not activated users should be automatically deleted after 3 days.
	 * <p>
	 * This is scheduled to get fired everyday, at 01:00 (am).
	 */
	// @Scheduled(cron = "0 0 1 * * ?")
	public void removeNotActivatedUsers() {
		List<User> users = userRepository
				.findAllByActivatedIsFalseAndCreatedDateBefore(Instant.now().minus(3, ChronoUnit.DAYS));
		for (User user : users) {
			log.debug("Deleting not activated user {}", user.getLogin());
			userRepository.delete(user);
			cacheManager.getCache(UserRepository.USERS_BY_LOGIN_CACHE).evict(user.getLogin());
			cacheManager.getCache(UserRepository.USERS_BY_EMAIL_CACHE).evict(user.getEmail());
		}
	}

	/**
	 * @return a list of all the authorities
	 */
	public List<String> getAuthorities() {
		return authorityRepository.findAll().stream().map(Authority::getName).collect(Collectors.toList());
	}

	/**
	 * @return a list of id in ids
	 */
	@Transactional
	public Page<UserDTO> updateByid(String[] ids) {
		List<Long> lId = new ArrayList<>();
		long l = ids.length;
		if (l > 0) {
			Long orgId = Long.parseLong(ids[0]);
			for (int i = 1; i < l; i++) {
				lId.add(Long.parseLong(ids[i]));
			}
			String orgName = organizationRepository.findById(orgId).map(Organization::getOrgName).orElse(null);
			userRepository.updateUserByidIn(orgId, orgName, lId);
		}
		return new PageImpl<User>(userRepository.findByIdIn(lId)).map(UserDTO::new);
	}

	/**
	 * @return 切换组织结构与角色
	 */
	@Transactional
	public User exchangeOrgRole(Long selorgroleid) {
		User tempUser = SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneByLogin).orElse(null);
		if (null != tempUser) {
			UserRoleOrganization tempUserRoleOrganization = userRoleOrganizationRepository.findById(selorgroleid).orElse(null);
			if (null != tempUserRoleOrganization) {
				// 成功
				if (tempUserRoleOrganization.getUser().equals(tempUser)) {
					tempUser.setSelOrgRoleId(selorgroleid);
					userRepository.save(tempUser);
				} else {
					tempUser = null;
				}
			} else {
				tempUser = null;
			}
		}
		return tempUser;
	}

	/**
	 * 获取我当前对应的组织机构id
	 * 
	 * @param loginUser
	 * @return
	 */
	public Organization getMyOrgId() {
		final String userLogin = SecurityUtils.getCurrentUserLogin()
				.orElseThrow(() -> new InternalServerErrorException("Current user login not found"));
		Optional<User> user = this.userRepository.findOneByLogin(userLogin);
		Organization orgId = null;
		if (user.isPresent()) {
			if (user.get().getSelOrgRoleId() != null) {
				UserRoleOrganization tempOr = userRoleOrganizationRepository.findById(user.get().getSelOrgRoleId()).orElse(null);
				if (tempOr != null) {
					if (tempOr.getOrganization() != null) {
						orgId = tempOr.getOrganization();
					}
				} else if (user.get().getOrganization() != null) {
					orgId = user.get().getOrganization();
				}
			} else if (user.get().getOrganization() != null) {
				orgId = user.get().getOrganization();
			}
		}
		return orgId;
	}

	/**
	 * 获取我当前有权查看的组织机构
	 * 
	 * @param loginUser
	 * @return
	 */
	public List<Organization> getMyOrgIds() {
		final String userLogin = SecurityUtils.getCurrentUserLogin()
				.orElseThrow(() -> new InternalServerErrorException("Current user login not found"));
		Optional<User> user = this.userRepository.findOneByLogin(userLogin);
		List<Organization> orgList = new ArrayList<>();
		if (user.isPresent()) {
			if (user.get().getSelOrgRoleId() != null) {
				UserRoleOrganization tempOr = userRoleOrganizationRepository.findById(user.get().getSelOrgRoleId()).orElse(null);
				if (tempOr != null) {
					if (tempOr.getOrganization() != null) {
						orgList.add(tempOr.getOrganization());
					}
				} else if (user.get().getOrganization() != null) {
					orgList.add(user.get().getOrganization());
				}
			} else if (user.get().getOrganization() != null) {
				orgList.add(user.get().getOrganization());
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

	public Page<UserDTO> findFirstNameByLoginIn(String[] logins) {
		List<String> ls = new ArrayList<>();
		for (int i = 0; i < logins.length; i++) {
			ls.add(logins[i]);
		}
		return new PageImpl<User>(userRepository.findByLoginIn(ls)).map(UserDTO::new);
	}

	public Page<UserDTO> findUsers(List<Long> orgIds, List<Long> roleIds, Map<String, Object> filters) {

		if ((orgIds == null) && (roleIds == null )) {
			return this.getAllManagedUsers(filters);
		}
		List<Long> userIds = null;
		if ((orgIds != null) && (roleIds != null)) {
			userIds = this.userRepository.getUserIds(orgIds, roleIds);
		} else if (roleIds != null ) {

			userIds = this.userRepository.getUserIds2(roleIds);
		} else if (orgIds != null ) {

			userIds = this.userRepository.getUserIds1(orgIds);
		}

		if (userIds == null || userIds.size() == 0) {
			return new PageImpl<User>(new ArrayList<User>(), null, userRepository.getRows(filters))
					.map(UserDTO::new);
		}

		String filterscount = (String) filters.get("filterscount");
		if (filterscount == null || "".equals(filterscount)) {
			filterscount = "0";
		}
		// IN
		filters.put("filterscount", (Integer.parseInt(filterscount) + 1) + "");
		filters.put("filtervalue" + filterscount, userIds);
		filters.put("filtercondition" + filterscount, "IN");
		filters.put("filterdatafield" + filterscount, "id");
		filters.put("filteroperator" + filterscount, "0");

		return this.getAllManagedUsers(filters);
	}

}
