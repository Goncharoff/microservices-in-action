package com.gamification.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public final class GameStats {

    private final Long userId;
    private final int score;
    private final List<Badge> badges;

    public GameStats() {
        this(0L, 0, new ArrayList<>());
    }

    /**
     * Factory to build empty instance.
     * @param userId - id of user
     * @return {@link GameStats} object with zero score and no badges.
     */

    public static GameStats emptyStats(final Long userId){
        return new GameStats(userId, 0, Collections.emptyList());
    }

    /**
     * @return unmodifiable view of badge cards list
     */
    public List<Badge> getBadges(){
        return Collections.unmodifiableList(badges);
    }
}
