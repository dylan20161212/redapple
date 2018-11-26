package com.thtf.deconfliction.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.thtf.app.repository.BaseRepository;
import com.thtf.deconfliction.domain.ConflictCase;

/**
 * Spring Data JPA repository for the ConflictCase entity.
 */
@Repository
public interface ConflictCaseRepository extends BaseRepository<ConflictCase> {
	Page<ConflictCase> findByCStatus(String cStatus, Pageable pageable);
	
	Page<ConflictCase> findByMediateOrgNameIsNotNull(Pageable pageable);
	
	Page<ConflictCase> findByIdIn(Pageable pageable,List<Long> ids);
}
