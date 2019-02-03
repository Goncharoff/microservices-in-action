package com.gamification.service;

import com.gamification.domain.GameStats;

public interface GameService {
    GameStats newAttemptForUser(Long userId, Long attemptId, boolean correct);

    GameStats retriveStatsForUser(Long userId);
}


