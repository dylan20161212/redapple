package com.thtf.deconfliction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thtf.deconfliction.domain.GroupMember;


/**
 * Spring Data JPA repository for the GroupMember entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember, Long> {

}
