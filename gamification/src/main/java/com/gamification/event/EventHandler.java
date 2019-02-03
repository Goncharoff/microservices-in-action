package com.gamification.event;

import com.gamification.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
class EventHandler {

    private final GameService gameService;

    EventHandler(final GameService gameService) {
        this.gameService = gameService;
    }

    @RabbitListener(queues = "${multiplication.queue}")
    void handleMultiplicationSolved(final MultiplicationSolvedEvent event) {
        log.info("Recived multiplication solved event: {}", event.getMultiplicationResultAttemptId());

        try {
            gameService.newAttemptForUser(event.getUserId(),
                    event.getMultiplicationResultAttemptId(),
                    event.isCorrect());
        } catch (final Exception ex) {
            log.error("Error trying to process MultiplicationSolvedEvent", ex);

            //Avoid event to be re-queued and reprocessed.
            throw new AmqpRejectAndDontRequeueException(ex);
        }
    }
}
