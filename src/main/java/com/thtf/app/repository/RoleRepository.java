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
    @Query("select distinct sys_role from Role sys_role left join fetch sys_role.resources")
    List<Role> findAllWithEagerRelationships();

    @Query("select sys_role from Role sys_role left join fetch sys_role.resources where sys_role.id =:id")
    Role findOneWithEagerRelationships(@Param("id") Long id);
    
    @Query(value = "select * from sys_role where id =:id and created_by =:created_by", nativeQuery = true)
    List<Role> findOneByIdAndCreatedby(@Param("id") Long id, @Param("created_by") String createdBy);
    
    Set<Role> findByRoleName(String roleName);
}
