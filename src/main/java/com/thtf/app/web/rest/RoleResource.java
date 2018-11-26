package com.thtf.app.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
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
import com.thtf.app.domain.Role;
import com.thtf.app.repository.RoleRepository;
import com.thtf.app.security.SecurityUtils;
import com.thtf.app.service.dto.RoleDTO;
import com.thtf.app.service.mapper.ResourceMapper;
import com.thtf.app.service.mapper.RoleMapper;
import com.thtf.app.web.rest.errors.InternalServerErrorException;
import com.thtf.app.web.rest.errors.UltraViresException;
import com.thtf.app.web.rest.util.HeaderUtil;
import com.thtf.app.web.rest.util.PaginationUtil;

import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;

/**
 * REST controller for managing Role.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class RoleResource {

	private final Logger log = LoggerFactory.getLogger(RoleResource.class);

	private static final String ENTITY_NAME = "role";

	private final RoleRepository roleRepository;

	private final RoleMapper roleMapper;

	private final ResourceMapper resourceMapper;

	public RoleResource(RoleRepository roleRepository, RoleMapper roleMapper, ResourceMapper resourceMapper) {
		this.roleRepository = roleRepository;
		this.roleMapper = roleMapper;
		this.resourceMapper = resourceMapper;
	}

	/**
	 * POST /roles : Create a new role.
	 *
	 * @param roleDTO
	 *            the roleDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the
	 *         new roleDTO, or with status 400 (Bad Request) if the role has
	 *         already an ID
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PostMapping("/roles")
	@Timed
	public ResponseEntity<RoleDTO> createRole(@Valid @RequestBody RoleDTO roleDTO) throws URISyntaxException {
		log.debug("REST request to save Role : {}", roleDTO);
		if (roleDTO.getId() != null) {
			return ResponseEntity.badRequest().headers(
					HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new role cannot already have an ID"))
					.body(null);
		}
		Role role = roleMapper.toEntity(roleDTO);
		role = roleRepository.save(role);
		RoleDTO result = roleMapper.toDto(role);
		return ResponseEntity.created(new URI("/api/roles/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT /roles : Updates an existing role.
	 *
	 * @param roleDTO
	 *            the roleDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         roleDTO, or with status 400 (Bad Request) if the roleDTO is not
	 *         valid, or with status 500 (Internal Server Error) if the roleDTO
	 *         couldn't be updated
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@Transactional
	@PutMapping("/roles")
	@Timed
	public ResponseEntity<RoleDTO> updateRole(@Valid @RequestBody RoleDTO roleDTO) throws URISyntaxException {
		log.debug("REST request to update Role : {}", roleDTO);
		if (roleDTO.getId() == null) {
			return createRole(roleDTO);
		}
		final String userLogin = SecurityUtils.getCurrentUserLogin()
		.orElseThrow(() -> new InternalServerErrorException("Current user login not found"));
		List<Role> existingRole = roleRepository.findOneByIdAndCreatedby(roleDTO.getId(), userLogin);
		if(existingRole == null || existingRole.isEmpty()){
			// 不允许修改其他人创建的角色
			throw new UltraViresException();
		}
		// createdby is me
		Role role = roleMapper.toEntity(roleDTO);
		role = roleRepository.save(role);
		RoleDTO result = roleMapper.toDto(role);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, roleDTO.getId().toString()))
				.body(result);
	}

	/**
	 * GET /roles : get all the roles.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of roles in
	 *         body
	 */
	@GetMapping("/roles")
	@Timed
	public ResponseEntity<List<RoleDTO>> getAllRoles(@ApiParam Pageable pageable) {
		log.debug("REST request to get a page of Roles");
		Page<Role> page = roleRepository.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/roles");
		return new ResponseEntity<>(roleMapper.toDto(page.getContent()), headers, HttpStatus.OK);
	}

	/**
	 * GET /roles : get all the roles.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of roles in
	 *         body
	 */
	@GetMapping("/rolesx")
	@Timed
	public ResponseEntity<Page<RoleDTO>> getAllRolesx(@RequestParam Map<String, Object> params) {
		log.debug("REST request to get a page of Roles");
		// 开始设置过滤条件
//		final String userLogin = SecurityUtils.getCurrentUserLogin()
//				.orElseThrow(() -> new InternalServerErrorException("Current user login not found"));
//		String filterscount = (String) params.get("filterscount");
//		if (filterscount == null || "".equals(filterscount)) {
//			filterscount = "0";
//		}
//		// IN
//		params.put("filterscount", (Integer.parseInt(filterscount) + 1) + "");
//		params.put("filtervalue" + filterscount, userLogin);
//		params.put("filtercondition" + filterscount, "EQUAL");
//		params.put("filterdatafield" + filterscount, "createdBy");
//		params.put("filteroperator" + filterscount, "0");
		Page<RoleDTO> page = new PageImpl<Role>(roleRepository.findAllNative(params),PaginationUtil.getDefaultPageable(),
				roleRepository.getRows(params))
						.map(r -> new RoleDTO(r.getId(), r.getRoleName(), r.getRoleDescription(), r.getRoleFlag(),
								r.getRoleEffDate(), r.getRoleExpDate(),
								r.getResources().stream().map(resourceMapper::toDto).collect(Collectors.toSet()),
								r.getCreatedBy(), r.getCreatedDate(), r.getLastModifiedBy(), r.getLastModifiedDate()));
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/rolesx");
		return new ResponseEntity<>(page, headers, HttpStatus.OK);
	}

	/**
	 * GET /roles/:id : get the "id" role.
	 *
	 * @param id
	 *            the id of the roleDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         roleDTO, or with status 404 (Not Found)
	 */
	@GetMapping("/roles/{id}")
	@Timed
	public ResponseEntity<RoleDTO> getRole(@PathVariable Long id) {
		log.debug("REST request to get Role : {}", id);
		final String userLogin = SecurityUtils.getCurrentUserLogin()
				.orElseThrow(() -> new InternalServerErrorException("Current user login not found"));
		List<Role> existingRole = roleRepository.findOneByIdAndCreatedby(id, userLogin);
		if(existingRole == null || existingRole.isEmpty()){
			// 不允许修改其他人创建的角色
			throw new UltraViresException();
		}
		Role role = roleRepository.findOneWithEagerRelationships(id);
		RoleDTO roleDTO = roleMapper.toDto(role);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(roleDTO));
	}

	/**
	 * DELETE /roles/:id : delete the "id" role.
	 *
	 * @param id
	 *            the id of the roleDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 * @throws Exception
	 */
	@DeleteMapping("/roles/{id}")
	@Timed
	public ResponseEntity<String> deleteRole(@PathVariable Long id) {
		log.debug("REST request to delete Role : {}", id);
		try {
			roleRepository.deleteById(id);
			roleRepository.flush();
		} catch (DataIntegrityViolationException e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return ResponseEntity.status(500).body("此角色已分配给用户，请先解除分配！");
		}
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}
}
