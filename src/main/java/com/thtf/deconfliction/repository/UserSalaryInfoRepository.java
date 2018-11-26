package com.thtf.deconfliction.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.thtf.app.repository.BaseRepository;
import com.thtf.deconfliction.domain.UserSalaryInfo;


/**
 * Spring Data JPA repository for the UserSalaryInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserSalaryInfoRepository extends BaseRepository<UserSalaryInfo> {
	List<UserSalaryInfo> findByUserExtendSalaryId(Long userExtendSalaryId);
}
