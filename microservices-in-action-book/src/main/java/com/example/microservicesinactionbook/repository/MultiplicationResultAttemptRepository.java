package com.example.microservicesinactionbook.repository;

import com.example.microservicesinactionbook.domain.MultiplicationResultAttempt;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MultiplicationResultAttemptRepository  extends CrudRepository<MultiplicationResultAttempt, Long> {
    /**
     * @return lattest 5 attemtps for given user
     */
    List<MultiplicationResultAttempt> findTop5ByUserAliasOrderByIdDesc(String userAlias);

}
