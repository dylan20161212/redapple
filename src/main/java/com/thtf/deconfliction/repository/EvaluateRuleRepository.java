package com.thtf.deconfliction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thtf.app.repository.BaseRepository;
import com.thtf.deconfliction.domain.EvaluateRule;

/**
 * Spring Data JPA repository for the EvaluateRule entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EvaluateRuleRepository extends BaseRepository<EvaluateRule> {
    @Query("select distinct de_evaluate_rule from EvaluateRule de_evaluate_rule left join fetch de_evaluate_rule.items")
    List<EvaluateRule> findAllWithEagerRelationships();

    @Query("select de_evaluate_rule from EvaluateRule de_evaluate_rule left join fetch de_evaluate_rule.items where de_evaluate_rule.id =:id")
    EvaluateRule findOneWithEagerRelationships(@Param("id") Long id);

}
