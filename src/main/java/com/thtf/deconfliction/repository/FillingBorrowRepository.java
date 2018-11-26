package com.thtf.deconfliction.repository;

import org.springframework.stereotype.Repository;

import com.thtf.app.repository.BaseRepository;
import com.thtf.deconfliction.domain.FillingBorrow;


/**
 * Spring Data JPA repository for the FillingBorrow entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FillingBorrowRepository extends BaseRepository<FillingBorrow> {

}
