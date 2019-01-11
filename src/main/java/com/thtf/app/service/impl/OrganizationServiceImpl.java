package com.thtf.app.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thtf.app.domain.Organization;
import com.thtf.app.domain.User;
import com.thtf.app.domain.UserRoleOrganization;
import com.thtf.app.repository.OrganizationRepository;
import com.thtf.app.repository.UserRepository;
import com.thtf.app.repository.UserRoleOrganizationRepository;
import com.thtf.app.security.SecurityUtils;
import com.thtf.app.service.OrganizationService;
import com.thtf.app.service.dto.OrganizationDTO;
import com.thtf.app.service.mapper.OrganizationMapper;
import com.thtf.app.web.rest.errors.InternalServerErrorException;

/**
 * Service Implementation for managing Organization.
 */
@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {

	private final Logger log = LoggerFactory.getLogger(OrganizationServiceImpl.class);

	private final UserRepository userRepository;

	private final OrganizationRepository organizationRepository;

	private final OrganizationMapper organizationMapper;

	private final UserRoleOrganizationRepository userRoleOrganizationRepository;

	private static final String UPPERID = "upperId";

	public OrganizationServiceImpl(OrganizationRepository organizationRepository, OrganizationMapper organizationMapper,
			UserRepository userRepository,
			UserRoleOrganizationRepository userRoleOrganizationRepository) {
		this.organizationRepository = organizationRepository;
		this.organizationMapper = organizationMapper;
		this.userRepository = userRepository;
		this.userRoleOrganizationRepository = userRoleOrganizationRepository;
	}

	/**
	 * Save a organization.
	 *
	 * @param organizationDTO
	 *            the entity to save
	 * @return the persisted entity
	 */
	@Override
	public OrganizationDTO save(OrganizationDTO organizationDTO) {
		log.debug("Request to save Organization : {}", organizationDTO);
		Organization organization = organizationMapper.toEntity(organizationDTO);
		organization = organizationRepository.save(organization);
		if(organizationDTO.getUpperId()!=null) {			
			Organization parent = organizationRepository.getOne(organizationDTO.getUpperId()) ;
			parent.setIsLeaf(false);
			organizationRepository.save(parent) ;
		}
		return organizationMapper.toDto(organization);
	}

	/**
	 * Get all the organizations.
	 *
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public List<OrganizationDTO> findAll() {
		log.debug("Request to get all Organizations");
		return organizationRepository.findAll().stream().map(organizationMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	/**
	 * Get own the organizations.
	 *
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public List<OrganizationDTO> findOwn() {
		log.debug("Request to get own Organizations");
		final String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new InternalServerErrorException("Current user login not found"));
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
//		return orgList.stream().map(Organization::getId).collect(Collectors.toList());
		return orgList.stream().filter(Objects::nonNull).map(organizationMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	/**
	 * Get one organization by id.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public OrganizationDTO findOne(Long id) {
		log.debug("Request to get Organization : {}", id);
		Organization organization = organizationRepository.findById(id).orElse(null);
		return organizationMapper.toDto(organization);
	}

	/**
	 * Delete the organization by id.
	 *
	 * @param id
	 *            the id of the entity
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete Organization : {}", id);
		organizationRepository.deleteById(id);
	}

	@Override
	public List<OrganizationDTO> findRootOrTree(Map<String, String> params) {
		if (params.containsKey(UPPERID)) {
			return this.organizationRepository.findByUpperId(Long.parseLong(params.get(UPPERID))).stream()
					.map(organizationMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
		}
		return organizationRepository.findByUpperIsNull().stream().map(organizationMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	@Override
	public List<OrganizationDTO> queryTree(Map<String, String> params) {
		List<Organization> list = new ArrayList<>();
		if (params.containsKey("orgName")) {
			list = this.organizationRepository.findByOrgName(params.get("orgName"));
		}
		if (params.containsKey("orgCode")) {
			list = this.organizationRepository.findByOrgCode(params.get("orgCode"));
		}
		if (params.containsKey("orgDescription")) {
			list = this.organizationRepository.findByOrgDescription(params.get("orgDescription"));
		}
		Map<Long, Organization> allOrg = new HashMap<>();
		for (Organization organization : list) {
			allOrg.put(organization.getId(), organization);
			Organization upper = organization.getUpper();
			while (upper != null) {
				allOrg.put(upper.getId(), upper);
				upper = upper.getUpper();
			}
		}
		list.clear();
		for (Entry<Long, Organization> entry : allOrg.entrySet()) {
			list.add(entry.getValue());
		}
		return list.stream().map(organizationMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
	}

	@Override
	public List<OrganizationDTO> getOrgTree() {
		// List<Organization> all = new ArrayList<>();
		// TreeTool.getChildTree(this.organizationRepository, all, null,
		// Organization.class, UPPERID,"id");
		// 因为是同步方式加载，所以只需要一次全部查询出来，前端会自动加载为树形
		return organizationRepository.findAll().stream().map(organizationMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}
	
	@Override
	public void delete(Long id, Long upperId) {
		
		log.debug("删除id为{}的机构。",id);
		organizationRepository.deleteById(id);
		if(upperId != null) {
			Organization parent = organizationRepository.getOne(upperId) ;
			long childNum = organizationRepository.countByUpperId(upperId) ;
			log.debug("删除后上级节点还拥有{}个子节点",childNum) ;
			if(childNum == 0) {
				parent.setIsLeaf(true);
				organizationRepository.save(parent) ;
			}
			
		}
		
	}
	
	@Override
	public void changeOrganizationFlag(Long id) {
		
		Organization org = organizationRepository.getOne(id) ;
		org.setOrgFlag(org.getOrgFlag()==1?0:1);
		organizationRepository.save(org) ;
	}
}
