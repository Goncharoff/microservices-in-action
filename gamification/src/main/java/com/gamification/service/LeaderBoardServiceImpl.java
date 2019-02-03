package com.gamification.service;

import com.gamification.domain.LeaderBoardRow;
import com.gamification.repository.ScoreCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaderBoardServiceImpl implements LeaderBoardService {
    private final ScoreCardRepository scoreCardRepository;

    @Autowired
    public LeaderBoardServiceImpl(ScoreCardRepository scoreCardRepository) {
        this.scoreCardRepository = scoreCardRepository;
    }

    @Override
    public List<LeaderBoardRow> getCurrentLeaderBoard() {
        return scoreCardRepository.findFirst10();
    }
}
