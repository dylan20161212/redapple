package com.thtf.deconfliction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thtf.app.repository.BaseRepository;
import com.thtf.deconfliction.domain.EvaluateMediator;

/**
 * Spring Data JPA repository for the EvaluateMediator entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EvaluateMediatorRepository extends BaseRepository <EvaluateMediator> {
    @Query("select distinct de_evaluate_mediator from EvaluateMediator de_evaluate_mediator left join fetch de_evaluate_mediator.rules")
    List<EvaluateMediator> findAllWithEagerRelationships();

    @Query("select de_evaluate_mediator from EvaluateMediator de_evaluate_mediator left join fetch de_evaluate_mediator.rules where de_evaluate_mediator.id =:id")
    EvaluateMediator findOneWithEagerRelationships(@Param("id") Long id);

}
