package com.thtf.app.service;

import java.util.List;
import java.util.Map;

import com.thtf.app.service.dto.OrganizationDTO;

/**
 * Service Interface for managing Organization.
 */
public interface OrganizationService {

    /**
     * Save a organization.
     *
     * @param organizationDTO the entity to save
     * @return the persisted entity
     */
    OrganizationDTO save(OrganizationDTO organizationDTO);

    /**
     *  Get all the organizations.
     *
     *  @return the list of entities
     */
    List<OrganizationDTO> findAll();

    /**
     *  Get all the organizations.
     *
     *  @return the list of entities
     */
    List<OrganizationDTO> findOwn();
    
    /**
     *  Get the "id" organization.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    OrganizationDTO findOne(Long id);

    /**
     *  Delete the "id" organization.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
    
    /**
     * 删除机构，并更新上级节点isLeaf属性。
     * @param id 节点id
     * @param upperId 上级节点id
     */
    void delete(Long id , Long upperId) ;

    /**
     *  Delete the "id" organization.
     *
     *  @param id the id of the entity
     */
    List<OrganizationDTO> findRootOrTree(Map<String, String> params);
    
    /**
     * seach matched main key tree 
     * @param params
     * @return
     */
	List<OrganizationDTO> queryTree(Map<String, String> params);
	
	
	List<OrganizationDTO> getOrgTree();
	
	/**
	 * A更新机构状态
	 * @param id
	 */
	void changeOrganizationFlag(Long id) ;
}
