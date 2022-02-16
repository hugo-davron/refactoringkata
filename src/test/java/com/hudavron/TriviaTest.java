package com.hudavron;

import junit.framework.TestCase;
import org.junit.Assert;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TriviaTest extends TestCase {

    public void testCreateRockQuestion() {
        Trivia trivia = new Trivia();
        Assert.assertEquals("Rock Question 3", trivia.createRockQuestion(3));
    }

    public void testPlayerAdd() {
        Trivia trivia = new Trivia();
        boolean result = trivia.add("");
        Assert.assertTrue(result);
        Assert.assertEquals(1, trivia.players.size());
    }

    public void testIsntPlayable() {
        Trivia trivia = new Trivia();
        trivia.add("Hugo");
        Assert.assertFalse(trivia.isPlayable());
    }

    public void testIsPlayable() {
        Trivia trivia = new Trivia();
        trivia.add("Hugo");
        trivia.add("Marie");
        Assert.assertTrue(trivia.isPlayable());
    }

    public void testHowManyPlayers() {
        Trivia trivia = new Trivia();
        trivia.add("Hugo");
        trivia.add("Marie");
        trivia.add("Dylan");
        trivia.add("Alex");
        Assert.assertEquals(4, trivia.howManyPlayers());
    }

    public void testRoll() {
            /*Trivia trivia = new Trivia();
            trivia*/

    }

    public void testWasCorrectlyAnsweredGettingOutOfPenaltyBox0Coins() {
        Trivia trivia = new Trivia();
        trivia.add("Hugo");
        trivia.add("Marie");
        trivia.wrongAnswer();
        trivia.currentPlayer = 0;
        trivia.roll(1);
        Assert.assertTrue(trivia.wasCorrectlyAnswered());
    }

    public void testWasCorrectlyAnsweredGettingOutOfPenaltyBox6Coins() {
        Trivia trivia = new Trivia();
        trivia.add("Hugo");
        trivia.add("Marie");
        trivia.wrongAnswer();
        trivia.currentPlayer = 0;
        trivia.purses[0] = 5;
        trivia.roll(1);
        Assert.assertFalse(trivia.wasCorrectlyAnswered());
    }

    public void testWasCorrectlyAnsweredGettingOutOfPenaltyBox0CoinsRollEven() {
        Trivia trivia = new Trivia();
        trivia.add("Hugo");
        trivia.add("Marie");
        trivia.wrongAnswer();
        trivia.currentPlayer = 0;
        trivia.roll(2);
        Assert.assertTrue(trivia.wasCorrectlyAnswered());
    }

    public void testWasCorrectlyAnswered6CoinsNotInPenaltyBox() {
        Trivia trivia = new Trivia();
        trivia.add("Hugo");
        trivia.add("Marie");
        trivia.currentPlayer = 0;
        trivia.purses[0] = 5;
        trivia.roll(2);
        Assert.assertFalse(trivia.wasCorrectlyAnswered());
    }

    public void testWasCorrectlyAnswered0CoinsNotInPenaltyBox() {
        Trivia trivia = new Trivia();
        trivia.add("Hugo");
        trivia.add("Marie");
        trivia.currentPlayer = 0;
        trivia.roll(2);
        Assert.assertTrue(trivia.wasCorrectlyAnswered());
    }


    public void testWrongAnswer() {
        Trivia trivia = new Trivia();
        trivia.add("Hugo");
        trivia.add("Marie");
        Assert.assertTrue(trivia.wrongAnswer());
    }
}