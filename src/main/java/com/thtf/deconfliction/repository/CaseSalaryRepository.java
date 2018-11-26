package com.thtf.deconfliction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thtf.deconfliction.domain.CaseSalary;


/**
 * Spring Data JPA repository for the CaseSalary entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CaseSalaryRepository extends JpaRepository<CaseSalary, Long> {

}
