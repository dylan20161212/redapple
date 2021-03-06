package com.thtf.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thtf.app.domain.Organization;


/**
 * Spring Data JPA repository for the Organization entity.
 */
@Repository
public interface OrganizationRepository extends BaseRepository<Organization> {

	List<Organization> findByUpperIsNull();
	
	List<Organization> findByUpper(Organization o);
	
	List<Organization> findByUpperId(Long upperId);
	
	Optional<Organization> findById(Long id);

	List<Organization> findByOrgName(String string);

	List<Organization> findByOrgCode(String string);

	List<Organization> findByOrgDescription(String string);
	
	Long countByUpperId(Long upperId) ;
	
	@Query("SELECT o FROM Organization o WHERE o.orgIdentity LIKE CONCAT(:rootOrgIdentity,'%')")
	List<Organization> findByOrgIdentityLike(@Param("rootOrgIdentity") String rootOrgIdentity);
}
