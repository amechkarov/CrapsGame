package com.gangames.crapsgame.service;

import com.gangames.crapsgame.models.Game;
import com.gangames.crapsgame.utils.randomprovider.RandomProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.gangames.crapsgame.helpers.TestHelpers.createGame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CrapsGameServiceTests {
    @Mock
    private RandomProvider randomProvider;

    @InjectMocks
    private GameServiceImpl gameService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void play_outcomeShouldReturnZero_When_DiceRollTotal_Is_2() {
        // Arrange
        Game game = createGame();


        when(randomProvider.roll()).thenReturn(2);

        // Act
        Game result = gameService.play(game);

        // Assert
        assertEquals(0.0, result.getOutcome().get("sumOfWins"));
    }

    @Test
    public void play_outcomeShouldReturnZero_When_DiceRollTotal_Is_3() {
        // Arrange
        Game game = createGame();


        when(randomProvider.roll()).thenReturn(3);

        // Act
        Game result = gameService.play(game);

        // Assert
        assertEquals(0.0, result.getOutcome().get("sumOfWins"));
    }

    @Test
    public void play_outcomeShouldReturnZero_When_DiceRollTotal_Is_12() {
        // Arrange
        Game game = createGame();


        when(randomProvider.roll()).thenReturn(12);

        // Act
        Game result = gameService.play(game);

        // Assert
        assertEquals(0.0, result.getOutcome().get("sumOfWins"));
    }

    @Test
    public void play_outcomeShouldReturnWin_EqualToStake_When_DiceRollTotal_Is_7() {
        // Arrange
        Game game = createGame();


        when(randomProvider.roll()).thenReturn(7);

        // Act
        Game result = gameService.play(game);

        // Assert
        assertEquals(game.getStake(), result.getOutcome().get("sumOfWins"));
    }

    @Test
    public void play_outcomeShouldReturnWin_EqualToStake_When_DiceRollTotal_Is_11() {
        // Arrange
        Game game = createGame();

        when(randomProvider.roll()).thenReturn(11);

        // Act
        Game result = gameService.play(game);

        // Assert
        assertEquals(game.getStake(), result.getOutcome().get("sumOfWins"));
    }

    @Test
    public void play_outcomeShouldReturn_TotalWin_SumOfStakes_And_Rtp_WhenMultipleRounds() {
        // Arrange
        double stake = 100.00;
        int rounds = 5;
        Game game = createGame(stake,rounds);

        when(randomProvider.roll()).thenReturn(11)
                .thenReturn(2)
                .thenReturn(2)
                .thenReturn(2)
                .thenReturn(11);

        // Act
        Game result = gameService.play(game);
        double totalStakes = stake*rounds;
        double totalWin = 200.0;
        double rtp = totalWin / totalStakes;

        // Assert
        assertEquals(200.0, result.getOutcome().get("sumOfWins"));
        assertEquals(totalStakes, result.getOutcome().get("sumOfStakes"));
        assertEquals(rtp, result.getOutcome().get("returnToPlayer"));

    }

    @Test
    public void play_outcomeShouldReturnZero_When_PlayerPointIsNotHit() {
        // Arrange
        Game game = createGame();


        when(randomProvider.roll()).thenReturn(4,7);

        // Act
        Game result = gameService.play(game);

        // Assert
        assertEquals(0.0, result.getOutcome().get("sumOfWins"));
    }

    @Test
    public void play_outcomeShouldReturnWin_EqualToStake_PlayerPointIstHit() {
        // Arrange
        Game game = createGame();


        when(randomProvider.roll()).thenReturn(4,4);

        // Act
        Game result = gameService.play(game);

        // Assert
        assertEquals(game.getStake(), result.getOutcome().get("sumOfWins"));
    }

}
