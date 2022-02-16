import junit.framework.TestCase;
import org.junit.Assert;

public class TriviaTest extends TestCase {

    public void testCreateRockQuestion() {
        Trivia trivia = new Trivia();
        Assert.assertEquals("Rock Question 3", trivia.createRockQuestion(3));
    }

    public void testPlayerAdd() {
        Trivia trivia = new Trivia();
        boolean result = trivia.add("");
        Assert.assertTrue(result);
        Assert.assertEquals(trivia.players.size(), 1);
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
        Assert.assertEquals(trivia.howManyPlayers(), 4);
    }

    public void testRoll() {
    }

    public void testWasCorrectlyAnswered() {
    }

    public void testWrongAnswer() {
    }
}