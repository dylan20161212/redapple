package com.thtf.deconfliction.repository;

import org.springframework.stereotype.Repository;

import com.thtf.app.repository.BaseRepository;
import com.thtf.deconfliction.domain.FillingConflictCase;


/**
 * Spring Data JPA repository for the FillingConflictCase entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FillingConflictCaseRepository extends BaseRepository<FillingConflictCase> {

}
