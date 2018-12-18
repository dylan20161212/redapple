package com.thtf.app.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.thtf.app.domain.Organization;
import com.thtf.app.domain.User;
import com.thtf.app.domain.UserRoleOrganization;
import com.thtf.app.repository.OrganizationRepository;
import com.thtf.app.repository.UserRepository;
import com.thtf.app.repository.UserRoleOrganizationRepository;
import com.thtf.app.security.SecurityUtils;
import com.thtf.app.service.dto.UserRoleOrganizationDTO;
import com.thtf.app.service.mapper.UserRoleOrganizationMapper;
import com.thtf.app.web.rest.errors.InternalServerErrorException;
import com.thtf.app.web.rest.errors.UltraViresException;
import com.thtf.app.web.rest.util.HeaderUtil;
import com.thtf.app.web.rest.util.PaginationUtil;

import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;

/**
 * REST controller for managing UserRoleOrganization.
 */
@RestController
@RequestMapping("/api")
public class UserRoleOrganizationResource {

	private final Logger log = LoggerFactory.getLogger(UserRoleOrganizationResource.class);

	private static final String ENTITY_NAME = "userRoleOrganization";

	private final UserRoleOrganizationRepository userRoleOrganizationRepository;

	private final UserRoleOrganizationMapper userRoleOrganizationMapper;

	private final UserRepository userRepository;
	
	private final OrganizationRepository organizationRepository;

	public UserRoleOrganizationResource(UserRoleOrganizationRepository userRoleOrganizationRepository,
			UserRoleOrganizationMapper userRoleOrganizationMapper, UserRepository userRepository,
			OrganizationRepository organizationRepository) {
		this.userRoleOrganizationRepository = userRoleOrganizationRepository;
		this.userRoleOrganizationMapper = userRoleOrganizationMapper;
		this.userRepository = userRepository;
		this.organizationRepository = organizationRepository;
	}

	/**
	 * POST /user-role-organizations : Create a new userRoleOrganization.
	 *
	 * @param userRoleOrganizationDTO
	 *            the userRoleOrganizationDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the
	 *         new userRoleOrganizationDTO, or with status 400 (Bad Request) if
	 *         the userRoleOrganization has already an ID
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@Transactional
	@PostMapping("/user-role-organizations")
	@Timed
	public ResponseEntity<UserRoleOrganizationDTO> createUserRoleOrganization(
			@Valid @RequestBody UserRoleOrganizationDTO userRoleOrganizationDTO) throws URISyntaxException {
		log.debug("REST request to save UserRoleOrganization : {}", userRoleOrganizationDTO);
		// 先判断权限是否合理，如果组织机构不是当前登录人所管理的范围、或者用户主要的组织机构不在当前用户所管理的范围均抛出异常

		User tempUser = userRepository.findById(userRoleOrganizationDTO.getUserId()).orElse(null);
		List<Organization> rOrgs = this.getMyOrgIds();
		int ok = 0;
		for(Organization o : rOrgs){
			if(o.getId().equals(tempUser.getOrganization().getId())){
				// 用户满足
				ok++;
			}
			if(o.getId().equals(userRoleOrganizationDTO.getOrganizationId())){
				// 机构满足
				ok++;
			}
		}
		if(ok<2){
			// 无权限
			throw new UltraViresException();
		}
		
		List<UserRoleOrganization> exists = this.userRoleOrganizationRepository.findByUserIdAndRoleIdAndOrganizationId(
				userRoleOrganizationDTO.getUserId(), userRoleOrganizationDTO.getRoleId(),
				userRoleOrganizationDTO.getOrganizationId());

		if (exists != null && !exists.isEmpty()) {
			userRoleOrganizationDTO.setId(exists.get(0).getId());
		} else {
			exists = this.userRoleOrganizationRepository.findByUserIdAndRoleIdAndOrganizationIdIsNull(
					userRoleOrganizationDTO.getUserId(), userRoleOrganizationDTO.getRoleId());
			if (exists != null && !exists.isEmpty()) {
				// 如果存在为空的组织机构，需要判断用户的组织机构是否为空，或者用户的组织机构是否与新增的相同，如果相同则跳过
//				User tempUser = userRepository.findOne(userRoleOrganizationDTO.getUserId());
				if (tempUser.getOrganization() != null) {
					if (tempUser.getOrganization().getId().longValue() == userRoleOrganizationDTO.getOrganizationId()
							.longValue()) {
						// 目前的组织机构与新增的组织机构相同
						userRoleOrganizationDTO.setId(exists.get(0).getId());
					}
				} else {
					// 用户的组织机构还是空，不需要判断，直接认为是相同
					userRoleOrganizationDTO.setId(exists.get(0).getId());
				}
			}
		}
		if (userRoleOrganizationDTO.getId() != null) {
			return ResponseEntity.created(new URI("/api/user-role-organizations/" + userRoleOrganizationDTO.getId()))
					.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME,
							userRoleOrganizationDTO.getId().toString()))
					.body(userRoleOrganizationDTO);
		}
		UserRoleOrganization userRoleOrganization = userRoleOrganizationMapper.toEntity(userRoleOrganizationDTO);
		userRoleOrganization = userRoleOrganizationRepository.save(userRoleOrganization);
		UserRoleOrganizationDTO result = userRoleOrganizationMapper.toDto(userRoleOrganization);
		return ResponseEntity.created(new URI("/api/user-role-organizations/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT /user-role-organizations : Updates an existing userRoleOrganization.
	 *
	 * @param userRoleOrganizationDTO
	 *            the userRoleOrganizationDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         userRoleOrganizationDTO, or with status 400 (Bad Request) if the
	 *         userRoleOrganizationDTO is not valid, or with status 500
	 *         (Internal Server Error) if the userRoleOrganizationDTO couldn't
	 *         be updated
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PutMapping("/user-role-organizations")
	@Timed
	public ResponseEntity<UserRoleOrganizationDTO> updateUserRoleOrganization(
			@Valid @RequestBody UserRoleOrganizationDTO userRoleOrganizationDTO) throws URISyntaxException {
		log.debug("REST request to update UserRoleOrganization : {}", userRoleOrganizationDTO);
		// xxii
		// List<UserRoleOrganization> exists =
		// this.userRoleOrganizationRepository.findByUserIdAndRoleIdAndOrganizationId(userRoleOrganizationDTO.getUserId(),userRoleOrganizationDTO.getRoleId(),userRoleOrganizationDTO.getOrganizationId());
		if (userRoleOrganizationDTO.getId() == null) {
			return createUserRoleOrganization(userRoleOrganizationDTO);
		}

		UserRoleOrganization userRoleOrganization = userRoleOrganizationMapper.toEntity(userRoleOrganizationDTO);
		userRoleOrganization = userRoleOrganizationRepository.save(userRoleOrganization);
		UserRoleOrganizationDTO result = userRoleOrganizationMapper.toDto(userRoleOrganization);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userRoleOrganizationDTO.getId().toString()))
				.body(result);
	}

	/**
	 * GET /user-role-organizations : get all the userRoleOrganizations.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of
	 *         userRoleOrganizations in body
	 */
	@GetMapping("/user-role-organizations")
	@Timed
	public ResponseEntity<List<UserRoleOrganizationDTO>> getAllUserRoleOrganizations(@ApiParam Pageable pageable) {
		log.debug("REST request to get a page of UserRoleOrganizations");
		Page<UserRoleOrganization> page = userRoleOrganizationRepository.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/user-role-organizations");
		return new ResponseEntity<>(userRoleOrganizationMapper.toDto(page.getContent()), headers, HttpStatus.OK);
	}

	/**
	 * GET /user-role-organizations : get mine the userRoleOrganizations.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of
	 *         userRoleOrganizations in body
	 */
	@GetMapping("/user-role-organizationsmy")
	@Timed
	public ResponseEntity<List<UserRoleOrganizationDTO>> getAllUserRoleOrganizationsMy(@ApiParam Pageable pageable) {
		log.debug("REST request to get a page of UserRoleOrganizations");
		User tempUser = SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneByLogin).orElse(null);
		List<UserRoleOrganization> listUserRoleOrganization = new ArrayList<>();
		HttpHeaders headers = new HttpHeaders();
		if (null != tempUser) {
			listUserRoleOrganization = userRoleOrganizationRepository.findByUserIdOrderByIdAsc(tempUser.getId());
			for (UserRoleOrganization uRoleOrg : listUserRoleOrganization) {
				if (uRoleOrg.getOrganization() == null) {
					Organization tempOrg = new Organization();
//					tempOrg.setOrgName(tempUser.getOrganizationName() != null ? tempUser.getOrganizationName() : "");
					uRoleOrg.setOrganization(tempOrg);
				}
			}
			if (tempUser.getSelOrgRoleId() != null) {
				headers.add("seluserorgrole", tempUser.getSelOrgRoleId().toString());
			} else {
				if (listUserRoleOrganization.size() > 0) {
					headers.add("seluserorgrole", listUserRoleOrganization.get(0).getId().toString());
				}
			}
		}
		return new ResponseEntity<>(userRoleOrganizationMapper.toDto(listUserRoleOrganization), headers, HttpStatus.OK);
	}

	/**
	 * GET /user-role-organizations : get all the userRoleOrganizations.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of
	 *         userRoleOrganizations in body
	 */

	// @GetMapping("/user-role-organizationsx")
	// @Timed
	// public ResponseEntity<List<UserRoleOrganizationDTO>>
	// getAllUserRoleOrganizationsx(@RequestParam Map<String, String> params) {
	// log.debug("REST request to get a page of UserRoleOrganizations");
	//// Page<UserRoleOrganization> page =
	// userRoleOrganizationRepository.findAll(pageable);
	// final Page<UserRoleOrganization> page = new
	// PageImpl<UserRoleOrganization>(userRoleOrganizationRepository.findAll(params),null,userRoleOrganizationRepository.getTotalRows(params));
	// HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page,
	// "/api/user-role-organizations");
	// return new
	// ResponseEntity<>(userRoleOrganizationMapper.toDto(page.getContent()),
	// headers, HttpStatus.OK);
	// }

	/**
	 * GET /user-role-organizations : get all the userRoleOrganizations.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of
	 *         userRoleOrganizations in body
	 */

	@GetMapping("/user-role-organizationsx")
	@Timed
	public ResponseEntity<List<UserRoleOrganizationDTO>> getAllUserRoleOrganizationsx(
			@RequestParam Map<String, Object> params) {
		log.debug("REST request to get a page of UserRoleOrganizations");
		// Page<UserRoleOrganization> page =
		// userRoleOrganizationRepository.findAll(pageable);
		final Page<UserRoleOrganization> page = new PageImpl<>(userRoleOrganizationRepository.findAll(params), null,
				userRoleOrganizationRepository.getTotalRows(params));
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/user-role-organizations");
		return new ResponseEntity<>(userRoleOrganizationMapper.toDto(page.getContent()), headers, HttpStatus.OK);
	}

	/**
	 * GET /user-role-organizations : get all the userRoleOrganizations.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of
	 *         userRoleOrganizations in body
	 */

	@GetMapping("/user-role-organizationsx/{userId}")
	@Timed
	public ResponseEntity<List<UserRoleOrganizationDTO>> getUserRoleOrganizationsByUserId(
			@RequestParam Map<String, Object> params, @PathVariable Long userId) {
		log.debug("REST request to get a page of UserRoleOrganizations" + userId);
		// Page<UserRoleOrganization> page =
		// userRoleOrganizationRepository.findAll(pageable);
		final Page<UserRoleOrganization> page = new PageImpl<>(
				userRoleOrganizationRepository.findByUserIdOrderByIdAsc(userId), null,
				userRoleOrganizationRepository.getTotalRows(params));
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/user-role-organizationsx");
		return new ResponseEntity<>(userRoleOrganizationMapper.toDto(page.getContent()), headers, HttpStatus.OK);
	}

	/**
	 * GET /user-role-organizations/:id : get the "id" userRoleOrganization.
	 *
	 * @param id
	 *            the id of the userRoleOrganizationDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         userRoleOrganizationDTO, or with status 404 (Not Found)
	 */
	@GetMapping("/user-role-organizations/{id}")
	@Timed
	public ResponseEntity<UserRoleOrganizationDTO> getUserRoleOrganization(@PathVariable Long id) {
		log.debug("REST request to get UserRoleOrganization : {}", id);
		UserRoleOrganization userRoleOrganization = userRoleOrganizationRepository.findById(id).orElse(null);
		UserRoleOrganizationDTO userRoleOrganizationDTO = userRoleOrganizationMapper.toDto(userRoleOrganization);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(userRoleOrganizationDTO));
	}

	/**
	 * DELETE /user-role-organizations/:id : delete the "id"
	 * userRoleOrganization.
	 *
	 * @param id
	 *            the id of the userRoleOrganizationDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/user-role-organizations/{id}")
	@Timed
	public ResponseEntity<Void> deleteUserRoleOrganization(@PathVariable Long id) {
		log.debug("REST request to delete UserRoleOrganization : {}", id);
		UserRoleOrganization userRoleOrganization = userRoleOrganizationRepository.findById(id).orElse(null);
		// 先判断权限是否合理，如果组织机构不是当前登录人所管理的范围、或者用户主要的组织机构不在当前用户所管理的范围均抛出异常

		Organization tempOrg = userRoleOrganization.getUser().getOrganization();
		List<Organization> rOrgs = this.getMyOrgIds();
		int ok = 0;
		for(Organization o : rOrgs){
			if(o.getId().equals(tempOrg.getId())){
				// 用户满足
				ok++;
				break;
			}
		}
		if(ok<1){
			// 无权限
			throw new UltraViresException();
		}
		userRoleOrganizationRepository.deleteById(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}
	
	/**
	 * 获取我当前有权查看的组织机构
	 * 
	 * @param loginUser
	 * @return
	 */
	private List<Organization> getMyOrgIds() {
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
}
