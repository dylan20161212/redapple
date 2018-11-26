package com.thtf.deconfliction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thtf.deconfliction.domain.FilingRelevantPerson;

/**
 * Spring Data JPA repository for the FilingRelevantPerson entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FilingRelevantPersonRepository extends JpaRepository<FilingRelevantPerson, Long> {
    @Query("select distinct filing_relevant_person from FilingRelevantPerson filing_relevant_person left join fetch filing_relevant_person.filingCases left join fetch filing_relevant_person.filingProcesses")
    List<FilingRelevantPerson> findAllWithEagerRelationships();

    @Query("select filing_relevant_person from FilingRelevantPerson filing_relevant_person left join fetch filing_relevant_person.filingCases left join fetch filing_relevant_person.filingProcesses where filing_relevant_person.id =:id")
    FilingRelevantPerson findOneWithEagerRelationships(@Param("id") Long id);

    @Query(value = "select * from filing_relevant_person r where r.id in (select rc.filing_relevant_people_id from filing_relevant_person_filing_case rc where rc.filing_cases_id = :_cid)", nativeQuery = true)
    List<FilingRelevantPerson> findByCid(@Param("_cid") Long cid);

}
