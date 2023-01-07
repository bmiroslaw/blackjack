import org.example.Card;
import org.example.Player;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTests {

    @Test
    public void testNewCard() {
        // create a new Player object named "Player1"
        Player player = new Player("Player1");

        // create a new Card object with rank "Ace" and value 11
        Card card = new Card("Ace", 11);

        // add the Card object to the player's hand
        player.newCard(card);

        assertEquals(1, player.getHand().size());
        assertEquals(11, player.getScore());
    }

    @Test
    public void testIsBusted() {
        // create a new Player object named "Player1"
        Player player = new Player("Player1");

        // create two new Card objects with ranks "Ace" and "Ten" and
        // values 11 and 10, respectively
        Card card1 = new Card("Ace", 11);
        Card card2 = new Card("Ten", 10);

        // add the Card objects to the player's hand
        player.newCard(card1);
        player.newCard(card2);

        // check that the player is not busted (score is less than 22)
        assertFalse(player.isBusted());
        // add a new Card object with rank "Two" and value 2 to the player's hand
        player.newCard(new Card("Two", 2));

        // check that the player is now busted (score is greater than 21)
        assertTrue(player.isBusted());
    }

    @Test
    public void testGetName() {
        Player player = new Player("Player1");
        assertEquals("Player1", player.getName());
    }

    @Test
    public void testGetScore() {
        // create a new Player object named "Player1"
        Player player = new Player("Player1");

        // create two new Card objects with ranks "Ace" and "Ten" and
        // values 11 and 10, respectively
        Card card1 = new Card("Ace", 11);
        Card card2 = new Card("Ten", 10);

        // add the Card objects to the player's hand
        player.newCard(card1);
        player.newCard(card2);

        // check that the player's score is 21
        assertEquals(card1.getValue()+card2.getValue(), player.getScore());
    }

    @Test
    public void testGetHand() {
        // create a new Player object named "Player1"
        Player player = new Player("Player1");
        // create two new Card objects with ranks "Ace" and "Ten" and
        // values 11 and 10, respectively
        Card card1 = new Card("Ace", 11);
        Card card2 = new Card("Ten", 10);

        // add the Card objects to the player's hand
        player.newCard(card1);
        player.newCard(card2);

        // create an ArrayList of Card objects that represents the expected hand
        ArrayList<Card> expectedHand = new ArrayList<>();
        expectedHand.add(card1);
        expectedHand.add(card2);

        // check that the player's hand matches the expected hand
        assertEquals(expectedHand, player.getHand());
    }
}
