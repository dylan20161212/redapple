package com.thtf.deconfliction.repository;

import org.springframework.stereotype.Repository;

import com.thtf.app.repository.BaseRepository;
import com.thtf.deconfliction.domain.UserExtendSalary;


/**
 * Spring Data JPA repository for the UserExtendSalary entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserExtendSalaryRepository extends BaseRepository<UserExtendSalary> {

}
