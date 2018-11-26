package com.thtf.app.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, Long> {

	//long customMethod();
	
	List<T> findAll(Map<String,Object> filters);
	
	List<T> findAllCriteria(Map<String,Object> filters);
	
	List<T> findAllNative(Map<String,Object> filters);
	
	long getTotalRows(Map<String,Object> filters);

	long getRows(Map<String,Object> filters);

}
