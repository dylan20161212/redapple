package com.thtf.app.repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thtf.app.domain.Organization;
import com.thtf.app.domain.User;

/**
 * Spring Data JPA repository for the User entity.
 */
@Repository
public interface UserRepository extends BaseRepository<User> {
	
	String USERS_BY_LOGIN_CACHE = "usersByLogin";

	String USERS_BY_EMAIL_CACHE = "usersByEmail";

	Optional<User> findOneByActivationKey(String activationKey);

	List<User> findAllByActivatedIsFalseAndCreatedDateBefore(Instant dateTime);

	Optional<User> findOneByResetKey(String resetKey);

	@Cacheable(cacheNames = USERS_BY_EMAIL_CACHE)
	Optional<User> findOneByEmailIgnoreCase(String email);

	@Cacheable(cacheNames = USERS_BY_LOGIN_CACHE)
	Optional<User> findOneByLoginIgnoreCase(String login);

	Optional<User> findOneByLogin(String login);

	@EntityGraph(attributePaths = "authorities")
	Optional<User> findOneWithAuthoritiesById(Long id);

	@EntityGraph(attributePaths = "authorities")
	@Cacheable(cacheNames = USERS_BY_LOGIN_CACHE)
	Optional<User> findOneWithAuthoritiesByLogin(String login);

	@EntityGraph(attributePaths = "authorities")
	@Cacheable(cacheNames = USERS_BY_EMAIL_CACHE)
	Optional<User> findOneWithAuthoritiesByEmail(String email);

	// @EntityGraph(value = "withRoles", type = EntityGraphType.FETCH)
	@EntityGraph(attributePaths = "roles")
	@Cacheable(cacheNames = USERS_BY_LOGIN_CACHE)
	Optional<User> findOneWithRolesByLogin(String login);

	@EntityGraph(attributePaths = { "roles" })
	Optional<User> getByLogin(String login);

	Page<User> findAllByLoginNot(Pageable pageable, String login);

	List<User> findByIdIn(List<Long> id);

	List<User> findByOrganizationId(Long organizationId);

	/**
	 * 更新用户组织机构
	 * 
	 * @param _orgId0
	 * @param _orgName0
	 * @param _ids0
	 * @return
	 */
	@Modifying
	@Query(value = "update jhi_user set organization_id = (:_orgId0),organization_name = (:_orgName0) where id in (:_ids0)", nativeQuery = true)
	int updateUserByidIn(@Param("_orgId0") Long orgId0, @Param("_orgName0") String orgName0,
			@Param("_ids0") List<Long> ids0);
	

	Optional<User> findUserByLoginAndOrganizationIn(String login,List<Organization> organization);

	List<User> findByLoginIn(List<String> logins);
	
	@Query("select uro.user.id from UserRoleOrganization uro  where uro.organization.id in (:_orgIds)  and uro.role.id in (:_roleIds) group by uro.user.id")
	List<Long> getUserIds(@Param("_orgIds") List<Long> orgIds,@Param("_roleIds") List<Long> roleIds);
	
	@Query("select uro.user.id from UserRoleOrganization uro  where uro.organization.id in (:_orgIds) group by uro.user.id")
	List<Long> getUserIds1(@Param("_orgIds") List<Long> orgIds);
	
	@Query("select uro.user.id from UserRoleOrganization uro  where uro.role.id in (:_roleIds) group by uro.user.id")
	List<Long> getUserIds2(@Param("_roleIds") List<Long> roleIds);
	
	Optional<User> findById(Long id);
	
	List<User> findByLogin(String login);
	
	List<User> findByEmail(String email);
	
//	@Query("select DISTINCT uro.user from UserRoleOrganization uro where (uro.organization.orgName like CONCAT('%',:orgParam,'%')  or uro.organization.orgCode like CONCAT('%',:orgParam,'%') ) and uro.role.roleName like CONCAT('%',:roleName,'%') ")
//	List<User> findUsers(@Param("orgParam") List<String> orgParam,@Param("roleName") List<String> roleName);
}
