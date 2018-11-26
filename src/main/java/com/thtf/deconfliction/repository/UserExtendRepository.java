package com.thtf.deconfliction.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.thtf.app.repository.BaseRepository;
import com.thtf.deconfliction.domain.UserExtend;


/**
 * Spring Data JPA repository for the UserExtend entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserExtendRepository extends BaseRepository<UserExtend> {
	List<UserExtend> findByLogin(String login);
	
	List<UserExtend> findByEmail(String email);
}
