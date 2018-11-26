package com.thtf.deconfliction.repository;

import org.springframework.stereotype.Repository;

import com.thtf.app.repository.BaseRepository;
import com.thtf.deconfliction.domain.OrgCaseNumber;

/**
 * Spring Data JPA repository for the FilingUserExtend entity.
 */
@Repository
public interface OrgCaseNumberRepository extends BaseRepository<OrgCaseNumber> {

}
