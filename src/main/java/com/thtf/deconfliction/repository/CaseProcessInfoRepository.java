package com.thtf.deconfliction.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thtf.app.repository.BaseRepository;
import com.thtf.deconfliction.domain.CaseProcessInfo;


/**
 * Spring Data JPA repository for the CaseProcessInfo entity.
 */
@Repository
public interface CaseProcessInfoRepository extends BaseRepository<CaseProcessInfo> {
    @Query("select case_process_info from CaseProcessInfo case_process_info where case_process_info.conflictCase.id = :cid")
    Set<CaseProcessInfo> findCaseProcessInfoByCid(@Param("cid") Long cid);
}
