package com.thtf.app.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.thtf.app.service.DictionaryService;
import com.thtf.app.service.dto.DictionaryDTO;
import com.thtf.app.web.rest.errors.BadRequestAlertException;
import com.thtf.app.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Dictionary.
 */
@RestController
@RequestMapping("/api")
public class DictionaryResource {

    private final Logger log = LoggerFactory.getLogger(DictionaryResource.class);

    private static final String ENTITY_NAME = "dictionary";

    private final DictionaryService dictionaryService;

    public DictionaryResource(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    /**
     * POST  /dictionaries : Create a new dictionary.
     *
     * @param dictionaryDTO the dictionaryDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new dictionaryDTO, or with status 400 (Bad Request) if the dictionary has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/dictionaries")
    @Timed
    public ResponseEntity<DictionaryDTO> createDictionary(@Valid @RequestBody DictionaryDTO dictionaryDTO) throws URISyntaxException {
        log.debug("REST request to save Dictionary : {}", dictionaryDTO);
        if (dictionaryDTO.getId() != null) {
            throw new BadRequestAlertException("A new dictionary cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DictionaryDTO result = dictionaryService.save(dictionaryDTO);
        return ResponseEntity.created(new URI("/api/dictionaries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /dictionaries : Updates an existing dictionary.
     *
     * @param dictionaryDTO the dictionaryDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated dictionaryDTO,
     * or with status 400 (Bad Request) if the dictionaryDTO is not valid,
     * or with status 500 (Internal Server Error) if the dictionaryDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/dictionaries")
    @Timed
    public ResponseEntity<DictionaryDTO> updateDictionary(@Valid @RequestBody DictionaryDTO dictionaryDTO) throws URISyntaxException {
        log.debug("REST request to update Dictionary : {}", dictionaryDTO);
        if (dictionaryDTO.getId() == null) {
            return createDictionary(dictionaryDTO);
        }
        DictionaryDTO result = dictionaryService.save(dictionaryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, dictionaryDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /dictionaries : get all the dictionaries.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of dictionaries in body
     */
    @GetMapping("/dictionaries")
    @Timed
    public List<DictionaryDTO> getAllDictionaries() {
        log.debug("REST request to get all Dictionaries");
        return dictionaryService.findAll();
        }

    /**
     * GET  /dictionaries : get all the dictionaries.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of dictionaries in body
     */
    @GetMapping("/dictionariesx")
    @Timed
    public List<DictionaryDTO> getAllDictionariesx(@RequestParam Map<String, String> params) {
        log.debug("REST request to get a page of Dictionaries");
        return dictionaryService.findRootOrLeaf(params);
    }
    
    /**
     * GET  /dictionaries/:id : get the "id" dictionary.
     *
     * @param id the id of the dictionaryDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the dictionaryDTO, or with status 404 (Not Found)
     */
    @GetMapping("/dictionaries/{id}")
    @Timed
    public ResponseEntity<DictionaryDTO> getDictionary(@PathVariable Long id) {
        log.debug("REST request to get Dictionary : {}", id);
        DictionaryDTO dictionaryDTO = dictionaryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(dictionaryDTO));
    }

    /**
     * DELETE  /dictionaries/:id : delete the "id" dictionary.
     *
     * @param id the id of the dictionaryDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/dictionaries/{id}")
    @Timed
    public ResponseEntity<Void> deleteDictionary(@PathVariable Long id) {
        log.debug("REST request to delete Dictionary : {}", id);
        dictionaryService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    
    @GetMapping("/dictionaries/property/{value}")
    @Timed
    public List<DictionaryDTO> getPropertiesMap(@PathVariable String value) {
//    	List<DictionaryDTO> dics =  dictionaryService.findDicProperties(value);
//    	List<Map<String,String>> list = new ArrayList<Map<String,String>>();
    	
//    	Long keyId = null;
//    	for (DictionaryDTO dictionaryDTO : dics) {
//    		if(dictionaryDTO.getDicKey().equals(value)){
//    			keyId = dictionaryDTO.getId();
//    			break;
//    		}
//		}
//    	for (DictionaryDTO dictionaryDTO : dics) {
//    		if(dictionaryDTO.getUpperId() != null && dictionaryDTO.getUpperId() == keyId){
//    			Map<String,String> result = new HashMap<String,String>();
//    			result.put("value",dictionaryDTO.getDicKey());
//    			result.put("label",dictionaryDTO.getDicValue());
//    			list.add(result);
//    		}
//		}
    	
//	    for (DictionaryDTO dictionaryDTO : dics) {
//				Map<String,String> result = new HashMap<String,String>();
//				result.put("value",dictionaryDTO.getDicKey());
//				result.put("label",dictionaryDTO.getDicValue());
//				list.add(result);
//		}
    	
    	return dictionaryService.findDicProperties(value);
    }
    
    
    
    @GetMapping("/dictionaries/property/values")
    @Timed
    public Map<String,List<DictionaryDTO>>  getPropertiesMaps(@RequestParam String keys) {
//    	List<DictionaryDTO> dics =  dictionaryService.findDicProperties(value);
//    	List<Map<String,String>> list = new ArrayList<Map<String,String>>();
    	
//    	Long keyId = null;
//    	for (DictionaryDTO dictionaryDTO : dics) {
//    		if(dictionaryDTO.getDicKey().equals(value)){
//    			keyId = dictionaryDTO.getId();
//    			break;
//    		}
//		}
//    	for (DictionaryDTO dictionaryDTO : dics) {
//    		if(dictionaryDTO.getUpperId() != null && dictionaryDTO.getUpperId() == keyId){
//    			Map<String,String> result = new HashMap<String,String>();
//    			result.put("value",dictionaryDTO.getDicKey());
//    			result.put("label",dictionaryDTO.getDicValue());
//    			list.add(result);
//    		}
//		}
    	
//	    for (DictionaryDTO dictionaryDTO : dics) {
//				Map<String,String> result = new HashMap<String,String>();
//				result.put("value",dictionaryDTO.getDicKey());
//				result.put("label",dictionaryDTO.getDicValue());
//				list.add(result);
//		}
        String[] vs= keys.split("\\,");
        Map<String,List<DictionaryDTO>> map = new HashMap<String,List<DictionaryDTO>>();
        for(String value:vs){
        	List<DictionaryDTO> ds = dictionaryService.findDicProperties(value);
        	map.put(value,ds);
        }
    	return map;
    }
    
    
    /**
     * 根据id 获取树
     * @param id
     * @param containedMe 是否包含当前节点
     * @return
     */
    @GetMapping("/dictionaries/id/{id}")
    @Timed
    public List<DictionaryDTO> getDicsById(@PathVariable Long id,Boolean containedMe) {
    	
    	return dictionaryService.findDicsById(id,containedMe);
    }
    
    
    /**
     * 根据id 获取树
     * @param id
     * @param containedMe 是否包含当前节点
     * @return
     */
    @GetMapping("/dictionaries/property/ids")
    @Timed
    public Map<String,List<DictionaryDTO>> getDicsByIds(@RequestParam String ids,Boolean containedMe) {
    	
    	 String[] vs= ids.split("\\,");
         Map<String,List<DictionaryDTO>> map = new HashMap<String,List<DictionaryDTO>>();
         for(String value:vs){
         	List<DictionaryDTO> ds = dictionaryService.findDicsById(Long.parseLong(value),containedMe);
         	map.put(value,ds);
         }
     	return map;
    	
    }
}
