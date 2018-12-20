package com.thtf.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thtf.app.service.dto.ResourceDTO;

/**
 * Service Interface for managing Resource.
 */
public interface ResourceService {

    /**
     * Save a resource.
     *
     * @param resourceDTO the entity to save
     * @return the persisted entity
     */
    ResourceDTO save(ResourceDTO resourceDTO);

    /**
     *  Get all the resources.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    public Page<ResourceDTO> findAll(ResourceDTO resource,Pageable pageable);
    
    

    /**
     *  Get the "id" resource.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ResourceDTO findOne(Long id);

    /**
     *  Delete the "id" resource.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
    
    List<ResourceDTO> findRootOrLeaf(Map<String, String> params);
}
