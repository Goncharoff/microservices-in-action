package com.gamification.client;

/**
 * allow to connect to multiplication service
 */
public interface MultiplicationResultAttemptClient {
    MultiplicationResultAttempt retrieveMultiplicationResultAttemptById(final Long multiplicationId);
}
