package com.thtf.app.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.thtf.app.config.Constants;
import com.thtf.app.domain.User;
import com.thtf.app.repository.UserRepository;
import com.thtf.app.security.AuthoritiesConstants;
import com.thtf.app.security.SecurityUtils;
import com.thtf.app.service.MailService;
import com.thtf.app.service.UserService;
import com.thtf.app.service.dto.UserDTO;
import com.thtf.app.web.rest.errors.BadRequestAlertException;
import com.thtf.app.web.rest.errors.EmailAlreadyUsedException;
import com.thtf.app.web.rest.errors.InternalServerErrorException;
import com.thtf.app.web.rest.errors.LoginAlreadyUsedException;
import com.thtf.app.web.rest.errors.UltraViresException;
import com.thtf.app.web.rest.util.HeaderUtil;
import com.thtf.app.web.rest.util.PaginationUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing users.
 * <p>
 * This class accesses the User entity, and needs to fetch its collection of
 * authorities.
 * <p>
 * For a normal use-case, it would be better to have an eager relationship
 * between User and Authority, and send everything to the client side: there
 * would be no View Model and DTO, a lot less code, and an outer-join which
 * would be good for performance.
 * <p>
 * We use a View Model and a DTO for 3 reasons:
 * <ul>
 * <li>We want to keep a lazy association between the user and the authorities,
 * because people will quite often do relationships with the user, and we don't
 * want them to get the authorities all the time for nothing (for performance
 * reasons). This is the #1 goal: we should not impact our users' application
 * because of this use-case.</li>
 * <li>Not having an outer join causes n+1 requests to the database. This is not
 * a real issue as we have by default a second-level cache. This means on the
 * first HTTP call we do the n+1 requests, but then all authorities come from
 * the cache, so in fact it's much better than doing an outer join (which will
 * get lots of data from the database, for each HTTP call).</li>
 * <li>As this manages users, for security reasons, we'd rather have a DTO
 * layer.</li>
 * </ul>
 * <p>
 * Another option would be to have a specific JPA entity graph to handle this
 * case.
 */
@RestController
@RequestMapping("/api")
public class UserResource {

	private final Logger log = LoggerFactory.getLogger(UserResource.class);

	private final UserRepository userRepository;

	private final UserService userService;

	private final MailService mailService;

	public UserResource(UserRepository userRepository, UserService userService, MailService mailService) {

		this.userRepository = userRepository;
		this.userService = userService;
		this.mailService = mailService;
	}

	/**
	 * POST /users : Creates a new user.
	 * <p>
	 * Creates a new user if the login and email are not already used, and sends
	 * an mail with an activation link. The user needs to be activated on
	 * creation.
	 *
	 * @param userDTO
	 *            the user to create
	 * @return the ResponseEntity with status 201 (Created) and with body the
	 *         new user, or with status 400 (Bad Request) if the login or email
	 *         is already in use
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 * @throws BadRequestAlertException
	 *             400 (Bad Request) if the login or email is already in use
	 */
	@PostMapping("/users")
	@Timed
	// @Secured(AuthoritiesConstants.ADMIN)
	public ResponseEntity<User> createUser(@Valid @RequestBody UserDTO userDTO) throws URISyntaxException {
		log.debug("REST request to save User : {}", userDTO);

		if (userDTO.getId() != null) {
			throw new BadRequestAlertException("A new user cannot already have an ID", "userManagement", "idexists");
			// Lowercase the user login before comparing with database
		} else if (userRepository.findOneByLogin(userDTO.getLogin().toLowerCase()).isPresent()) {
			throw new LoginAlreadyUsedException();
		} else if (userRepository.findOneByEmailIgnoreCase(userDTO.getEmail()).isPresent()) {
			throw new EmailAlreadyUsedException();
		} else {
			User newUser = userService.createUser(userDTO);
			mailService.sendCreationEmail(newUser);
			return ResponseEntity.created(new URI("/api/users/" + newUser.getLogin()))
					.headers(HeaderUtil.createAlert("userManagement.created", newUser.getLogin())).body(newUser);
		}
	}

	/**
	 * PUT /users : Updates an existing User.
	 *
	 * @param userDTO
	 *            the user to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         user
	 * @throws EmailAlreadyUsedException
	 *             400 (Bad Request) if the email is already in use
	 * @throws LoginAlreadyUsedException
	 *             400 (Bad Request) if the login is already in use
	 */
	@PutMapping("/users")
	@Timed
	// @Secured(AuthoritiesConstants.ADMIN)
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO) {
		log.debug("REST request to update User : {}", userDTO);
		Optional<User> existingUser = userRepository.findOneByEmailIgnoreCase(userDTO.getEmail());
		if (existingUser.isPresent() && (!existingUser.get().getId().equals(userDTO.getId()))) {
			throw new EmailAlreadyUsedException();
		}
		existingUser = userRepository.findOneByLogin(userDTO.getLogin().toLowerCase());
		if (existingUser.isPresent() && (!existingUser.get().getId().equals(userDTO.getId()))) {
			throw new LoginAlreadyUsedException();
		}
		existingUser = userRepository.findUserByLoginAndOrganizationIn(userDTO.getLogin().toLowerCase(),
				userService.getMyOrgIds());
		if (!isOk(existingUser)) {
			// 不是权限范围之内的用户，不允许操作，前面两个已经判断了相关逻辑，此处之判断存在与否
			throw new UltraViresException();
		}

		Optional<UserDTO> updatedUser = userService.updateUser(userDTO);
		return ResponseUtil.wrapOrNotFound(updatedUser,
				HeaderUtil.createAlert("userManagement.updated", userDTO.getLogin()));
	}

	/**
	 * GET /users : get all users.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the ResponseEntity with status 200 (OK) and with body all users
	 */
	@GetMapping("/users")
	@Timed
	public ResponseEntity<List<UserDTO>> getAllUsers(Pageable pageable) {
		log.debug("REST request to get AllUsers : {}", pageable);
		final Page<UserDTO> page = userService.getAllManagedUsers(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/users");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	/**
	 * GET /users : get all users.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the ResponseEntity with status 200 (OK) and with body all users
	 */
	@GetMapping("/usersx")
	@Timed
	public ResponseEntity<Page<UserDTO>> getAllUsersx(@RequestParam Map<String, Object> params) {
		log.debug("REST request to get Usersx : {}", params);
		final Page<UserDTO> page = userService.getAllManagedUsers(params);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/usersx");
		return new ResponseEntity<>(page, headers, HttpStatus.OK);
	}

	/**
	 * @return a string list of the all of the roles
	 */
	@GetMapping("/users/authorities")
	@Timed
	// @Secured(AuthoritiesConstants.ADMIN)
	public List<String> getAuthorities() {
		return userService.getAuthorities();
	}

	/**
	 * GET /users/:login : get the "login" user.
	 *
	 * @param login
	 *            the login of the user to find
	 * @return the ResponseEntity with status 200 (OK) and with body the "login"
	 *         user, or with status 404 (Not Found)
	 */
	@GetMapping("/usersget/{login:" + Constants.LOGIN_REGEX + "}")
	@Timed
	public ResponseEntity<UserDTO> getUser(@PathVariable String login) {
		log.debug("REST request to get User : {}", login);
		// Optional<User> existingUser =
		// userRepository.findUserByLoginAndOrganizationIn(login.toLowerCase(),userService.getMyOrgIds());
		Optional<User> existingUser = userRepository.findOneByLogin(login);
		// 查询两遍，牺牲了性能
		// existingUser =
		// userRepository.findUserByLoginAndOrganizationIn(login.toLowerCase(),userService.getMyOrgIds());
		if (!isOk(existingUser)) {
			// 不是权限范围之内的用户，不允许操作
			throw new UltraViresException();
		}
		return ResponseUtil.wrapOrNotFound(userService.getUserWithRolesByLogin(login));
	}

	/**
	 * GET /users/:login : get the "login" user.
	 *
	 * @param login
	 *            the login of the user to find
	 * @return the ResponseEntity with status 200 (OK) and with body the "login"
	 *         user, or with status 404 (Not Found)
	 */
	// @GetMapping("/usersgetfirstnames/{logins}")
	// @Timed
	// public Page<UserDTO> getFirstNames(@PathVariable String[] logins) {
	// log.debug("REST request to get User : {}", logins.length);
	// return userService.findFirstNameByLoginIn(logins);
	// }

	/**
	 * DELETE /users/:login : delete the "login" User.
	 *
	 * @param login
	 *            the login of the user to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/users/{login:" + Constants.LOGIN_REGEX + "}")
	@Timed
	// @Secured(AuthoritiesConstants.ADMIN)
	public ResponseEntity<Void> deleteUser(@PathVariable String login) {
		log.debug("REST request to delete User: {}", login);
		final String userLogin = SecurityUtils.getCurrentUserLogin()
				.orElseThrow(() -> new InternalServerErrorException("Current user login not found"));
		Optional<User> existingUser = userRepository.findOneByLogin(login);
		// existingUser =
		// userRepository.findUserByLoginAndOrganizationIn(login.toLowerCase(),userService.getMyOrgIds());
		if (!isOk(existingUser)) {
			// 不是权限范围之内的用户，不允许操作
			throw new UltraViresException();
		}
		if (!userLogin.equals(login)) {
			userService.deleteUser(login);
		}
		return ResponseEntity.ok().headers(HeaderUtil.createAlert("userManagement.deleted", login)).build();
	}

	private boolean isOk(Optional<User> existingUser) {
		if (!existingUser.isPresent()) {
			// 不是权限范围之内的用户，不允许操作
			return false;
		} else {
			if (!userService.getMyOrgIds().contains(existingUser.get().getOrganization())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * DELETE /users/:id : delete the "id" User.
	 *
	 * @param id
	 *            the id of the user to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	// @DeleteMapping("/users/{id}")
	// @Timed
	// @Secured(AuthoritiesConstants.ADMIN)
	// public ResponseEntity<Void> deleteByid(@PathVariable String[] id) {
	// log.debug("REST request to delete User: {}", Arrays.toString(id));
	// userService.deleteById(id);
	// return
	// ResponseEntity.ok().headers(HeaderUtil.createAlert("userManagement.deleted",
	// Arrays.toString(id)))
	// .build();
	// }

	/**
	 * GET /users : update this users's orgid and orgname by userids.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the ResponseEntity with status 200 (OK) and with body all users
	 */
	@PutMapping("/users/orgchange/{userIds}")
	@Timed
	// @Secured(AuthoritiesConstants.ADMIN)
	public ResponseEntity<List<UserDTO>> updateByidAndOrgid(@PathVariable String[] userIds) {
		log.debug("REST request to get AllUsers : {}" + userIds.length);
		final Page<UserDTO> page = userService.updateByid(userIds);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/users/orgchange");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	/**
	 * 重置用户密码
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the ResponseEntity with status 200 (OK) and with body all users
	 */
	@PutMapping("/users/resetpass/{login:" + Constants.LOGIN_REGEX + "}")
	@Timed
	// @Secured(AuthoritiesConstants.ADMIN)
	public ResponseEntity<Void> resetPassword(@PathVariable String login) {
		log.debug("REST request to reset User: {}", login);
		userService.resetPassword(login);
		return ResponseEntity.ok().headers(HeaderUtil.createAlert("userManagement.reset", login)).build();
	}

	/**
	 * PUT /users : 切换组织机构与角色.
	 *
	 * @param userDTO
	 *            the user to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         user
	 */
	@PutMapping("/users/exchangeor/{selorgroleid}")
	@Timed
	public ResponseEntity<Void> exchangeOrgRole(@PathVariable Long selorgroleid) {
		log.debug("REST request to update User : {}", selorgroleid);
		User updatedUser = userService.exchangeOrgRole(selorgroleid);
		if (null != updatedUser) {
			return ResponseEntity.ok()
					.headers(HeaderUtil.createAlert("userOrgRole.exchanged", updatedUser.getSelOrgRoleId().toString()))
					.build();
		}
		return ResponseEntity.ok().headers(HeaderUtil.createAlert("userOrgRole.exchangeFail", "-1")).build();
	}

	@GetMapping("/userx/findUsers")
	public Page<UserDTO> findUsers(@RequestParam Map<String, Object> filters) {
		String orgIds = (String) filters.get("orgIds");
		List<Long> orgIdsList = null;
		if (orgIds != null && !orgIds.trim().equals("")) {
			orgIdsList = new ArrayList<Long>();
			String[] ids = orgIds.split(",");
			for(int i=0;i<ids.length;i++){
				orgIdsList.add(Long.parseLong(ids[i]));
			}
		}
		String roleIds = (String) filters.get("roleIds");
		List<Long> roleIdsList = null;
		if (roleIds != null && !roleIds.trim().equals("")) {
			roleIdsList = new ArrayList<Long>();
			String[] ids = roleIds.split(",");
			for(int i=0;i<ids.length;i++){
				roleIdsList.add(Long.parseLong(ids[i]));
			}
		}
		return this.userService.findUsers(orgIdsList, roleIdsList, filters);
	}
	
	@GetMapping("/user/id/{userId}")
	public User getUser(@PathVariable Long userId){
		return this.userRepository.findById(userId).orElse(null);
	}
}
