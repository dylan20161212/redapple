package com.thtf.deconfliction.repository;

import org.springframework.stereotype.Repository;

import com.thtf.app.repository.BaseRepository;
import com.thtf.deconfliction.domain.VUserProcessedCasesNumber;

/**
 * Spring Data JPA repository for the FilingUserExtend entity.
 */
@Repository
public interface VUserProcessedCasesNumberRepository extends BaseRepository<VUserProcessedCasesNumber> {

}
