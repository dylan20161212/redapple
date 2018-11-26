package com.thtf.deconfliction.repository;

import org.springframework.stereotype.Repository;

import com.thtf.app.repository.BaseRepository;
import com.thtf.deconfliction.domain.EvaluateRuleItem;


/**
 * Spring Data JPA repository for the EvaluateRuleItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EvaluateRuleItemRepository extends BaseRepository<EvaluateRuleItem> {

}
