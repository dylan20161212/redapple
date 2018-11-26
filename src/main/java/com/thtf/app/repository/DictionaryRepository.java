package com.thtf.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.thtf.app.domain.Dictionary;


/**
 * Spring Data JPA repository for the Dictionary entity.
 */
@Repository
public interface DictionaryRepository extends BaseRepository<Dictionary> {

	List<Dictionary> findByUpperIsNull();
	
	List<Dictionary> findByUpper(Dictionary o);
	
	Optional<Dictionary> findById(Long id);
	
	List<Dictionary> findByUpperId(Long upperId);
	
	@Query("select d from Dictionary d where d.upper.id = (select id from Dictionary where dicKey=?1) order by d.dicOrder")
	List<Dictionary> findDics(String dicKey);
	
	List<Dictionary> findByIdAndCreatedBy(Long id, String userLogin);

}
