package com.example.microservicesinactionbook.controller;

import com.example.microservicesinactionbook.domain.MultiplicationResultAttempt;
import com.example.microservicesinactionbook.service.MultiplicationService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/results")
final public class MultiplicationResultController {
    private final MultiplicationService multiplicationService;

    @Autowired
    public MultiplicationResultController(final MultiplicationService multiplicationService) {
        this.multiplicationService = multiplicationService;
    }

    @RequiredArgsConstructor
    @NoArgsConstructor(force = true)
    @Getter
    public static final class ResultResponse {
        private final boolean correct;
    }

    @PostMapping
    ResponseEntity<MultiplicationResultAttempt> postResult(@RequestBody MultiplicationResultAttempt attempt) {
        boolean isCorrect = multiplicationService.checkAttempt(attempt);
        MultiplicationResultAttempt multiplicationResultAttempt = new MultiplicationResultAttempt(
                attempt.getUser(),
                attempt.getMultiplication(),
                attempt.getResultAttempt(),
                isCorrect
        );

        return ResponseEntity.ok(multiplicationResultAttempt);
    }

    @GetMapping
    ResponseEntity<List<MultiplicationResultAttempt>> getStatistics(@RequestParam("alias") String alias){
        return ResponseEntity.ok(multiplicationService.getStatsForUser(alias));
    }


}
