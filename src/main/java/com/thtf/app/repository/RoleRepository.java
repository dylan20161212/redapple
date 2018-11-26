package com.thtf.app.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thtf.app.domain.Role;

/**
 * Spring Data JPA repository for the Role entity.
 */
@Repository
public interface RoleRepository extends BaseRepository<Role> {
    @Query("select distinct t_role from Role t_role left join fetch t_role.resources")
    List<Role> findAllWithEagerRelationships();

    @Query("select t_role from Role t_role left join fetch t_role.resources where t_role.id =:id")
    Role findOneWithEagerRelationships(@Param("id") Long id);
    
    @Query(value = "select * from t_role where id =:id and created_by =:created_by", nativeQuery = true)
    List<Role> findOneByIdAndCreatedby(@Param("id") Long id, @Param("created_by") String createdBy);
    
    Set<Role> findByRoleName(String roleName);
}
