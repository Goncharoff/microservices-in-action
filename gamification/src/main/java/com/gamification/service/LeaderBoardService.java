package com.gamification.service;

import com.gamification.domain.LeaderBoardRow;

import java.util.List;

public interface LeaderBoardService {
    List<LeaderBoardRow> getCurrentLeaderBoard();
}
