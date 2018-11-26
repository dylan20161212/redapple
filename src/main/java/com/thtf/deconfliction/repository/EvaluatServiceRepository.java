package com.thtf.deconfliction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thtf.deconfliction.domain.EvaluatService;


/**
 * Spring Data JPA repository for the EvaluatService entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EvaluatServiceRepository extends JpaRepository<EvaluatService, Long> {

}
