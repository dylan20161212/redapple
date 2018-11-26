package com.thtf.app.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thtf.app.domain.Dictionary;
import com.thtf.app.repository.DictionaryRepository;
import com.thtf.app.service.DictionaryService;
import com.thtf.app.service.dto.DictionaryDTO;
import com.thtf.app.service.mapper.DictionaryMapper;
import com.thtf.app.service.util.TreeTool;

/**
 * Service Implementation for managing Dictionary.
 */
@Service
@Transactional
public class DictionaryServiceImpl implements DictionaryService{

    private final Logger log = LoggerFactory.getLogger(DictionaryServiceImpl.class);

    private final DictionaryRepository dictionaryRepository;

    private final DictionaryMapper dictionaryMapper;

    public DictionaryServiceImpl(DictionaryRepository dictionaryRepository, DictionaryMapper dictionaryMapper) {
        this.dictionaryRepository = dictionaryRepository;
        this.dictionaryMapper = dictionaryMapper;
    }

    /**
     * Save a dictionary.
     *
     * @param dictionaryDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DictionaryDTO save(DictionaryDTO dictionaryDTO) {
        log.debug("Request to save Dictionary : {}", dictionaryDTO);
        Dictionary dictionary = dictionaryMapper.toEntity(dictionaryDTO);
        dictionary = dictionaryRepository.save(dictionary);
        return dictionaryMapper.toDto(dictionary);
    }

    /**
     *  Get all the dictionaries.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<DictionaryDTO> findAll() {
        log.debug("Request to get all Dictionaries");
        return dictionaryRepository.findAll().stream()
            .map(dictionaryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one dictionary by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public DictionaryDTO findOne(Long id) {
        log.debug("Request to get Dictionary : {}", id);
        Dictionary dictionary = dictionaryRepository.findById(id).orElseGet(null);
        return dictionaryMapper.toDto(dictionary);
    }
 
    /**
     *  Delete the  dictionary by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Dictionary : {}", id);
        dictionaryRepository.deleteById(id);
    }

    /**
     *  Get all the dictionaries.
     *
     *  @return the list of entities
     */
    @Override
	public List<DictionaryDTO> findRootOrLeaf(Map<String, String> params) {
		if(params.containsKey("upperId")){
			return dictionaryRepository.findByUpperId(Long.parseLong(params.get("upperId"))).stream()
		            .map(dictionaryMapper::toDto)
		            .collect(Collectors.toCollection(LinkedList::new));
		}
		return dictionaryRepository.findByUpperIsNull().stream()
	            .map(dictionaryMapper::toDto)
	            .collect(Collectors.toCollection(LinkedList::new));
	}

	@Override
	public List<DictionaryDTO> findDicProperties(String dicKey) {
		return this.dictionaryRepository.findDics(dicKey).stream()
	        .map(dictionaryMapper::toDto)
	        .collect(Collectors.toCollection(LinkedList::new));
		
	}
	
	public List<DictionaryDTO> findDicsById(Long id,Boolean containedMe){
		List<Dictionary> all = new ArrayList<Dictionary>();
		boolean isContainedMe = Optional.ofNullable(containedMe).orElse(false);
		if(isContainedMe){
			TreeTool.getChildTreeContainedMe(this.dictionaryRepository, all, id, Dictionary.class, "upperId", "id");
		}else{
			TreeTool.getChildTree(this.dictionaryRepository, all, id, Dictionary.class, "upperId", "id");
		}
		
//		return DtoTransferTool.toDto(all, DictionaryDTO.class, "upper");
		return all.stream().map(dictionaryMapper::toDto).collect(Collectors.toCollection(LinkedList::new));

		
	}
	
	@Override
	public DictionaryDTO findDicsById(Long id, String key, Boolean containedMe){
		List<Dictionary> all = new ArrayList<>();
		if(containedMe != null && containedMe){
			all.add(dictionaryRepository.findById(id).orElse(null));
		}else{
			all = dictionaryRepository.findByUpperId(id);
		}
		for(Dictionary d : all){
			if(key.equals(d.getDicKey())){
				return dictionaryMapper.toDto(d);
			}
			//all.addAll(dictionaryRepository.findByUpperId(d.getId()));
		}
		
		return null;
	}
}
