package com.thtf.deconfliction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thtf.app.repository.BaseRepository;
import com.thtf.deconfliction.domain.RelevantPerson;

/**
 * Spring Data JPA repository for the RelevantPerson entity.
 */
@Repository
public interface RelevantPersonRepository extends BaseRepository<RelevantPerson> {
    @Query("select distinct de_relevant_person from RelevantPerson de_relevant_person left join fetch de_relevant_person.conflictCases left join fetch de_relevant_person.mediationInfos")
    List<RelevantPerson> findAllWithEagerRelationships();

    @Query("select de_relevant_person from RelevantPerson de_relevant_person left join fetch de_relevant_person.conflictCases left join fetch de_relevant_person.mediationInfos where de_relevant_person.id =:id")
    RelevantPerson findOneWithEagerRelationships(@Param("id") Long id);

    @Query(value = "select * from de_relevant_person r where r.id in (select rc.relevant_people_id from relevant_person_conflict_case rc where rc.conflict_cases_id = (:_cid))", nativeQuery = true)
	List<RelevantPerson> findByCid(@Param("_cid") Long cid);
    
    List<RelevantPerson> findByIdNumberAndAGroup(String id,String agroup);
    
}
