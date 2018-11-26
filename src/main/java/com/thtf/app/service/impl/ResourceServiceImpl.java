package com.thtf.app.service.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thtf.app.domain.Resource;
import com.thtf.app.repository.ResourceRepository;
import com.thtf.app.service.ResourceService;
import com.thtf.app.service.dto.ResourceDTO;
import com.thtf.app.service.mapper.ResourceMapper;
import com.thtf.app.web.rest.util.PaginationUtil;


/**
 * Service Implementation for managing Resource.
 */
@Service
@Transactional
public class ResourceServiceImpl implements ResourceService{

    private final Logger log = LoggerFactory.getLogger(ResourceServiceImpl.class);

    private final ResourceRepository resourceRepository;

    private final ResourceMapper resourceMapper;
    public ResourceServiceImpl(ResourceRepository resourceRepository, ResourceMapper resourceMapper) {
        this.resourceRepository = resourceRepository;
        this.resourceMapper = resourceMapper;
    }

    /**
     * Save a resource.
     *
     * @param resourceDTO the entity to save
     * @return the persisted entity
     */
    @Override
    @Transactional
    public ResourceDTO save(ResourceDTO resourceDTO) {
        log.debug("Request to save Resource : {}", resourceDTO);
        Resource resource = resourceMapper.toEntity(resourceDTO);
        resource = resourceRepository.save(resource);
        return resourceMapper.toDto(resource);
    }

    /**
     *  Get all the resources.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ResourceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Resources");
        Map<String, Object> filters = new HashMap<>();
        
        String filterscount = (String) filters.get("filterscount");
		if (filterscount == null || "".equals(filterscount)) {
			filterscount = "0";
		}

		filters.put("pagenum", "0");
		filters.put("pagesize", "65535");
//		filters.put("recordstartindex", "0");
//		filters.put("recordendindex", "65535");
		
		filters.put("filterscount", (Integer.parseInt(filterscount) + 1) + "");
		filters.put("filtervalue" + filterscount, "ROLE_DEVELOPER");
		filters.put("filtercondition" + filterscount, "NOT_EQUAL");
		filters.put("filterdatafield" + filterscount, "resSrc");
		filters.put("filteroperator" + filterscount, "1");
		
		filterscount = (Integer.parseInt(filterscount) + 1) + "";
		filters.put("filterscount", (Integer.parseInt(filterscount) + 1) + "");
		filters.put("filtervalue" + filterscount, "NULL");
		filters.put("filtercondition" + filterscount, "NULL");
		filters.put("filterdatafield" + filterscount, "resSrc");
		filters.put("filteroperator" + filterscount, "1");
		
		filters.put("sortdatafield", "resOrder");
		filters.put("sortorder", "desc");
        return new PageImpl<>(resourceMapper.toDto(resourceRepository.findAll(filters)),PaginationUtil.getDefaultPageable(),65535);
    }

    /**
     *  Get one resource by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ResourceDTO findOne(Long id) {
        log.debug("Request to get Resource : {}", id);
        Resource resource = resourceRepository.findById(id).orElse(null);
        return resourceMapper.toDto(resource);
    }

    /**
     *  Delete the  resource by id.
     *
     *  @param id the id of the entity
     */
    @Override
    @Transactional
    public void delete(Long id) {
        log.debug("Request to delete Resource : {}", id);
        resourceRepository.deleteById(id);
    }

	@Override
	@Transactional
	public List<ResourceDTO> findRootOrLeaf(Map<String, String> params) {
		if(params.containsKey("upperId")){
			return resourceRepository.findByUpperId(Long.parseLong(params.get("upperId"))).stream()
		            .map(resourceMapper::toDto)
		            .collect(Collectors.toCollection(LinkedList::new));
		}
		return resourceRepository.findByUpperIsNull().stream()
	            .map(resourceMapper::toDto)
	            .collect(Collectors.toCollection(LinkedList::new));
	}
}
