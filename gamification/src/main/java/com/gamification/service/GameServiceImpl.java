package com.gamification.service;

import com.gamification.client.MultiplicationResultAttempt;
import com.gamification.client.MultiplicationResultAttemptClient;
import com.gamification.domain.Badge;
import com.gamification.domain.BadgeCard;
import com.gamification.domain.GameStats;
import com.gamification.domain.ScoreCard;
import com.gamification.repository.BadgeCardRepository;
import com.gamification.repository.ScoreCardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GameServiceImpl implements GameService {
    public static int LUCKY_NUMBER = 42;

    private  ScoreCardRepository scoreCardRepository;
    private  BadgeCardRepository badgeCardRepository;
    private  MultiplicationResultAttemptClient attemptClient;

    public GameServiceImpl(ScoreCardRepository scoreCardRepository, BadgeCardRepository badgeCardRepository, MultiplicationResultAttemptClient attemptClient) {
        this.scoreCardRepository = scoreCardRepository;
        this.badgeCardRepository = badgeCardRepository;
        this.attemptClient = attemptClient;
    }


    @Override
    public GameStats newAttemptForUser(final Long userId, final Long attemptId, final boolean correct) {
        if (correct) {
            ScoreCard scoreCard = new ScoreCard(userId, attemptId);
            scoreCardRepository.save(scoreCard);
            log.info("User with id {} scored {} points for attempt id {}",
                    userId, scoreCard.getScore(), attemptId);

            List<BadgeCard> badgeCards = processForBadgest(userId, attemptId);
            return new GameStats(userId, scoreCard.getScore(), badgeCards.stream()
                    .map(BadgeCard::getBadge)
                    .collect(Collectors.toList()));
        }

        return GameStats.emptyStats(userId);
    }

    @Override
    public GameStats retriveStatsForUser(Long userId) {
        int score = scoreCardRepository.getTotalScoreForUser(userId);
        List<BadgeCard> badgeCards = badgeCardRepository.findByUserIdOrderByBadgeTimestampDesc(userId);

        return new GameStats(userId, score, badgeCards.stream()
                .map(BadgeCard::getBadge)
                .collect(Collectors.toList()));
    }

    /**
     * Check total score an the different score card obtained
     * to give new badges in case their conditions are met.
     */
    private List<BadgeCard> processForBadgest(final Long userId,
                                              final Long attemptId) {
        List<BadgeCard> badgeCards = new ArrayList<>();

        int totalScore = scoreCardRepository.getTotalScoreForUser(userId);
        log.info("New score for user {} is {}", userId, totalScore);

        List<ScoreCard> scoreCardsList = scoreCardRepository.findByUserIdOrderByScoreTimestampDesc(userId);
        List<BadgeCard> badgeCardList = badgeCardRepository.findByUserIdOrderByBadgeTimestampDesc(userId);

        //badges depending on score
        checkAndGiveBadgeBasedOnScore(badgeCardList, Badge.BRONZE_MULTIPLICATOR, totalScore, 100, userId)
                .ifPresent(badgeCards::add);
        checkAndGiveBadgeBasedOnScore(badgeCardList, Badge.SILVER_MULTIPLICATOR, totalScore, 500, userId)
                .ifPresent(badgeCards::add);
        checkAndGiveBadgeBasedOnScore(badgeCardList, Badge.GOLD_MULTIPLICATOR, totalScore, 999, userId)
                .ifPresent(badgeCards::add);

        //first play
        if (scoreCardsList.size() == 1 && !containsBadge(badgeCardList, Badge.FIRST_WON)) {
            BadgeCard firstBadgeWin = giveBadgeToUser(Badge.FIRST_WON, userId);
            badgeCards.add(firstBadgeWin);
        }

        //lucky number
        MultiplicationResultAttempt attempt = attemptClient.retrieveMultiplicationResultAttemptById(attemptId);

        if (!containsBadge(badgeCardList, Badge.LUCKY_NUMBER) && (LUCKY_NUMBER == attempt.getMultiplicationFactorA()
                || LUCKY_NUMBER == attempt.getMultiplicationFactorB())) {
            BadgeCard luckyNumberBadge = giveBadgeToUser(Badge.LUCKY_NUMBER, userId);
            badgeCards.add(luckyNumberBadge);
        }
        return badgeCards;
    }


    /**
     * Checking current score against threshold to gain badges.
     * If condition are met, assign badge to user
     */
    private Optional<BadgeCard> checkAndGiveBadgeBasedOnScore(final List<BadgeCard> badgeCards,
                                                              final Badge badge,
                                                              final int score,
                                                              final int scoreThreshold,
                                                              final Long userId) {

        if (score >= scoreThreshold && !containsBadge(badgeCards, badge)) {
            return Optional.of(giveBadgeToUser(badge, userId));
        }

        return Optional.empty();
    }

    /**
     * Check if list of badges contains badge
     */
    private boolean containsBadge(final List<BadgeCard> badgeCards, final Badge badge) {
        return badgeCards.stream().anyMatch(badgeCard -> badgeCard.getBadge().equals(badge));
    }

    private BadgeCard giveBadgeToUser(final Badge badge, final Long userId) {
        BadgeCard badgeCard = new BadgeCard(userId, badge);
        badgeCardRepository.save(badgeCard);
        log.info("User with id {} won a new badge: {}", userId, badge);
        return badgeCard;
    }
}
