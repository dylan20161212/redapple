package com.thtf.deconfliction.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thtf.app.repository.BaseRepository;
import com.thtf.deconfliction.domain.FillingCaseProcessInfo;


/**
 * Spring Data JPA repository for the FillingCaseProcessInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FillingCaseProcessInfoRepository extends BaseRepository<FillingCaseProcessInfo> {
	 @Query("select fcase_process_info from FillingCaseProcessInfo fcase_process_info where fcase_process_info.filingCaseId = :cid")
	 Set<FillingCaseProcessInfo> findFCaseProcessInfoByFilingCaseId(@Param("cid") Long cid);
}
