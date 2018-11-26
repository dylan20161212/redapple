package com.thtf.deconfliction.repository;

import org.springframework.stereotype.Repository;

import com.thtf.app.repository.BaseRepository;
import com.thtf.deconfliction.domain.FullCalendar;

/**
 * Spring Data JPA repository for the FullCalendar entity.
 */
@Repository
public interface FullCalendarRepository extends BaseRepository<FullCalendar> {

}
