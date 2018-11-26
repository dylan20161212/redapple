package com.thtf.deconfliction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thtf.deconfliction.domain.CaseMediatorSalary;


/**
 * Spring Data JPA repository for the CaseMediatorSalary entity.
 */

@Repository
public interface CaseMediatorSalaryRepository extends JpaRepository<CaseMediatorSalary, Long> {

}
