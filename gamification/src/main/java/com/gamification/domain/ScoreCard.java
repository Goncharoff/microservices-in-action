package com.gamification.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
public final class ScoreCard {
    //default score if not assigned
    public static final int DEFAULT_SCORE = 10;

    @Id
    @GeneratedValue
    @Column(name = "CARD_ID")
    private final Long cardId;

    @Column(name = "USER_ID")
    private final Long userId;

    @Column(name = "ATTEMPT_ID")
    private final Long attemptId;

    @Column(name = "SCORE_TS")
    private final long scoreTimestamp;

    @Column(name = "SCORE")
    private final int score;

    public ScoreCard() {
        this(null, null, null, 0, 0);
    }

    public ScoreCard(final Long userId, final Long attemptId, final long scoreTimestamp, final int score) {
        this.cardId = null;
        this.userId = userId;
        this.attemptId = attemptId;
        this.scoreTimestamp = new Date().getTime();
        this.score = DEFAULT_SCORE;
    }

    public ScoreCard(final Long userId, final Long attemptId) {
        this(null, userId, attemptId, System.currentTimeMillis(), DEFAULT_SCORE);
    }
}
