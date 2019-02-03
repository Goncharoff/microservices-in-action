package com.gamification.service;

import com.gamification.domain.LeaderBoardRow;
import com.gamification.repository.ScoreCardRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
public class LeaderBoardServiceImplTest {
    private LeaderBoardService leaderBoardService;

    @Mock
    private ScoreCardRepository scoreCardRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        leaderBoardService = new LeaderBoardServiceImpl(scoreCardRepository);
    }

    @Test
    public void retriveLeaderBoardTest() {
        //given
        Long userId = 1L;
        LeaderBoardRow leaderBoardRow = new LeaderBoardRow(userId, 300L);
        List<LeaderBoardRow> expectedLeaderBoard = Collections.singletonList(leaderBoardRow);

        given(scoreCardRepository.findFirst10()).willReturn(expectedLeaderBoard);

        //when
        List<LeaderBoardRow> leaderBoard  = leaderBoardService.getCurrentLeaderBoard();

        //then
        assertThat(leaderBoard).isEqualTo(expectedLeaderBoard);
    }
}
