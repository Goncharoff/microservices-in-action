package com.gamification.event;

import com.gamification.domain.GameStats;
import com.gamification.service.GameService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class EventHandlerTest {

    private EventHandler eventHandler;

    @Mock
    private GameService gameService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        eventHandler = new EventHandler(gameService);
    }

    @Test
    public void multiplicationAttemptReceivedTest() {
        // given
        Long userId = 1L;
        Long attemptId = 8L;
        boolean correct = true;
        GameStats gameStatsExpected = new GameStats();
        MultiplicationSolvedEvent event = new MultiplicationSolvedEvent(attemptId, userId, correct);
        given(gameService.newAttemptForUser(userId, attemptId, correct)).willReturn(gameStatsExpected);

        // when
        eventHandler.handleMultiplicationSolved(event);

        // then
        verify(gameService).newAttemptForUser(userId, attemptId, correct);
    }

}
