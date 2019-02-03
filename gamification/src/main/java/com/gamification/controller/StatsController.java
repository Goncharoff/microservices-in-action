package com.gamification.controller;

import com.gamification.domain.GameStats;
import com.gamification.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
class StatsController {

    private final GameService gameService;

    @Autowired
    public StatsController(final GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public GameStats getStatsForUser(@RequestParam("userId") final Long userId){
        return gameService.retriveStatsForUser(userId);
    }
}
