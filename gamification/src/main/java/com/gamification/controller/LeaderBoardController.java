package com.gamification.controller;

import com.gamification.domain.LeaderBoardRow;
import com.gamification.service.LeaderBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/leaders")
class LeaderBoardController {

    private final LeaderBoardService leaderBoardService;

    @Autowired
    public LeaderBoardController(final LeaderBoardService leaderBoardService) {
        this.leaderBoardService = leaderBoardService;
    }

    @GetMapping
    public List<LeaderBoardRow> getLeaderBoardRaw(){
        return leaderBoardService.getCurrentLeaderBoard();
    }
}
