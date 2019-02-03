package com.gamification.client;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@JsonDeserialize(using = MultiplicationResultAttemptDeserializer.class)
public final class MultiplicationResultAttempt {
    private final String userAlias;

    private final int multiplicationFactorA;
    private final int multiplicationFactorB;
    private final int resultAttempt;

    private final boolean correct;

    public MultiplicationResultAttempt() {
        this.userAlias = null;
        this.multiplicationFactorA = -1;
        this.multiplicationFactorB = -1;
        this.resultAttempt = -1;
        this.correct = false;
    }
}
