package com.thtf.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thtf.app.domain.Authority;
import com.thtf.app.domain.User;
import com.thtf.app.domain.UserRoleOrganization;


/**
 * Spring Data JPA repository for the UserRoleOrganization entity.
 */
@Repository
public interface UserRoleOrganizationRepository  extends BaseRepository<UserRoleOrganization> {
	List<UserRoleOrganization> findByUserId(Long id);
	
	List<UserRoleOrganization> findByUserIdOrderByIdAsc(Long id);
	
	List<UserRoleOrganization> findByUserIdAndOrganizationIdOrderByIdAsc(Long userId, Long organizationId);
	
	List<UserRoleOrganization> findByUserIdAndRoleIdAndOrganizationId(Long userId,Long roleId,Long organizationId);
	
	List<UserRoleOrganization> findByUserIdAndRoleIdAndOrganizationIdIsNull(Long userId,Long roleId);
	
	@Query("select uro.user from UserRoleOrganization uro where uro.role.roleName = :roleName")
	List<User> findUsers(@Param("roleName")String roleName);
    
	UserRoleOrganization findByRoleId(Long selOrgRoleId);
	
	void deleteByUserIdAndOrganizationId(long userId, long organizationId);

	Optional<List<UserRoleOrganization>> findByOrganizationIdAndUserLogin(Long selorgid, String login);

}
