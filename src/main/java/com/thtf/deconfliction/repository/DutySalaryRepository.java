package com.thtf.deconfliction.repository;

import org.springframework.stereotype.Repository;

import com.thtf.app.repository.BaseRepository;
import com.thtf.deconfliction.domain.DutySalary;


/**
 * Spring Data JPA repository for the DutySalary entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DutySalaryRepository extends BaseRepository<DutySalary> {

}
