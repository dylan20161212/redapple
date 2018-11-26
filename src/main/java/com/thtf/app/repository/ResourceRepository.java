package com.thtf.app.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.thtf.app.domain.Resource;


/**
 * Spring Data JPA repository for the Resource entity.
 */
@Repository
public interface ResourceRepository extends BaseRepository<Resource> {

	List<Resource> findByUpperIsNull();
	
	List<Resource> findByUpper(Resource o);
	
	List<Resource> findByUpperId(Long upperId);
	
	List<Resource> findByIdAndCreatedBy(Long upperId, String userLogin);
}
