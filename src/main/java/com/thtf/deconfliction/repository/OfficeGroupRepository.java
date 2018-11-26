package com.thtf.deconfliction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thtf.deconfliction.domain.OfficeGroup;


/**
 * Spring Data JPA repository for the OfficeGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OfficeGroupRepository extends JpaRepository<OfficeGroup, Long> {

}
