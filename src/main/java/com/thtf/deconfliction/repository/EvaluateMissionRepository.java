package com.thtf.deconfliction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thtf.deconfliction.domain.EvaluateMission;


/**
 * Spring Data JPA repository for the EvaluateMission entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EvaluateMissionRepository extends JpaRepository<EvaluateMission, Long> {

}
