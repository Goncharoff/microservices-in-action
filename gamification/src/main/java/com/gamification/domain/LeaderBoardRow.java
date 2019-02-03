package com.gamification.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class LeaderBoardRow {

    private final Long userId;
    private final Long totalScore;

    public LeaderBoardRow(){
        this(0L, 0L);
    }


}
