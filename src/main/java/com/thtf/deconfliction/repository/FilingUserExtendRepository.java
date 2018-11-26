package com.thtf.deconfliction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thtf.deconfliction.domain.FilingUserExtend;

/**
 * Spring Data JPA repository for the FilingUserExtend entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FilingUserExtendRepository extends JpaRepository<FilingUserExtend, Long> {
    @Query("select distinct filing_user_extend from FilingUserExtend filing_user_extend left join fetch filing_user_extend.filingCases left join fetch filing_user_extend.filingProcesses")
    List<FilingUserExtend> findAllWithEagerRelationships();

    @Query("select filing_user_extend from FilingUserExtend filing_user_extend left join fetch filing_user_extend.filingCases left join fetch filing_user_extend.filingProcesses where filing_user_extend.id =:id")
    FilingUserExtend findOneWithEagerRelationships(@Param("id") Long id);

}
