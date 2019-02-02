package com.example.microservicesinactionbook.service;

import com.example.microservicesinactionbook.domain.Multiplication;
import com.example.microservicesinactionbook.domain.MultiplicationResultAttempt;

import java.util.List;

public interface MultiplicationService {
    /**
     * Creates a Multiplication object with two randomlygenerated factors
     * between 11 and 99.
     * *
     @return a Multiplication object with random factors
     */
    Multiplication createRandomMultiplication();

    /**
     *
     *
     * @return true if result matches the result of multiplication, false otherwise.
     */
    boolean checkAttempt(final MultiplicationResultAttempt resultAttempt);

    List<MultiplicationResultAttempt> getStatsForUser(String userAlias);
}
