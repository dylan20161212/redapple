package com.thtf.deconfliction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thtf.deconfliction.domain.DutySalaryInfo;


/**
 * Spring Data JPA repository for the DutySalaryInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DutySalaryInfoRepository extends JpaRepository<DutySalaryInfo, Long> {

}
