package com.thtf.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thtf.app.domain.Authority;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
