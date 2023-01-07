import org.example.Blackjack;
import org.example.Card;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


public class BlackjackTests {
    /*
    Given I play a game of blackjack
    When I am dealt my opening hand
    Then I have two cards
     */
    @Test
    public void scenarioOne(){
        //creating a new game
        String playerName = "Player";
        Blackjack game = new Blackjack(playerName);
        //cards to be added to the hand
        Card card1 = new Card("King", 10);
        Card card2 = new Card("Two", 2);

        ArrayList<Card> cards = new ArrayList<>(Arrays.asList(card1,card2));
        //predefined user's choices
        ArrayList<Integer> choices = new ArrayList<>(Arrays.asList(2));

        game.playRoundTestTwo(cards, choices,false);
        assertEquals(2, game.getPlayer1().getHand().size());
    }
    /*
    Given I have a valid hand of cards
    When I choose to ‘hit’
    Then I receive another card
    And my score is updated

    Given I have a valid hand of cards
    When I choose to ‘stand’
    Then I receive no further cards
    And my score is evaluated

    Given my score is updated or evaluated
    When it is 21 or less
    Then I have a valid hand


     */

    @Test
    public void scenarioTwo(){
        //creating a new game
        String playerName = "Player";
        Blackjack game = new Blackjack(playerName);
        //cards to be added to the hand
        Card card1 = new Card("King", 10);
        Card card2 = new Card("Two", 2);

        ArrayList<Card> cards = new ArrayList<>(Arrays.asList(card1,card2));
        //predefined user's choices
        ArrayList<Integer> choices = new ArrayList<>(Arrays.asList(1,2));

        // Call the method under test
        int score = game.playRoundTestTwo(cards, choices,false);

        assertNotEquals(card2.getValue() + card1.getValue(), score);
        assertFalse(game.getPlayer1().isBusted()); //isnt busted
    }

    /*
    Given my score is updated
    When it is 22 or more
    Then I am ‘bust’ and do not have a valid hand

    */
    @Test
    public void scenarioThree(){
        //creating a new game
        String playerName = "Player";
        Blackjack game = new Blackjack(playerName);
        //cards to be added to the hand
        Card card1 = new Card("King", 10);
        Card card2 = new Card("King", 10);
        Card card3 = new Card("Two", 2);

        ArrayList<Card> cards = new ArrayList<>(Arrays.asList(card1,card2,card3));
        //predefined user's choices
        ArrayList<Integer> choices = new ArrayList<>(Arrays.asList(2));

        // Call the method under test
        game.playRoundTestTwo(cards, choices,false);

        assertTrue(game.getPlayer1().isBusted()); //is busted
    }
    /*
    Given I have a king and an ace
    When my score is evaluated
    Then my score is 21

    */
    @Test
    public void scenarioFour(){
        //creating a new game
        String playerName = "Player";
        Blackjack game = new Blackjack(playerName);
        //cards to be added to the hand
        Card card1 = new Card("King", 10);
        Card card2 = new Card("Ace", 11);

        ArrayList<Card> cards = new ArrayList<>(Arrays.asList(card1,card2));
        //predefined user's choices
        ArrayList<Integer> choices = new ArrayList<>(Arrays.asList(2));

        // Call the method under test
        game.playRoundTestTwo(cards, choices,false);

        assertEquals(21,game.getPlayer1().getScore());
    }
    /*
    Given I have a king, a queen, and an ace
    When my score is evaluated
    Then my score is 21
*/

    @Test
    public void scenarioFive(){
        //creating a new game
        String playerName = "Player";
        Blackjack game = new Blackjack(playerName);
        //cards to be added to the hand
        Card card1 = new Card("King", 10);
        Card card2 = new Card("Queen", 10);

        ArrayList<Card> cards = new ArrayList<>(Arrays.asList(card1,card2));
        //predefined user's choices
        ArrayList<Integer> choices = new ArrayList<>(Arrays.asList(1,2,2));

        // Call the method under test
        game.playRoundTestTwo(cards, choices,true);

        assertEquals(21,game.getPlayer1().getScore());
    }

    /*
    Given that I have a nine, an ace, and another ace
    When my score is evaluated
    Then my score is 21
     */

    @Test
    public void scenarioSix(){
        //creating a new game
        String playerName = "Player";
        Blackjack game = new Blackjack(playerName);
        //cards to be added to the hand
        Card card1 = new Card("Nine", 9);
        Card card2 = new Card("Ace", 11);

        ArrayList<Card> cards = new ArrayList<>(Arrays.asList(card1,card2));
        //predefined user's choices
        ArrayList<Integer> choices = new ArrayList<>(Arrays.asList(1,2,2));

        // Call the method under test
        game.playRoundTestTwo(cards, choices,true);

        assertEquals(21,game.getPlayer1().getScore());
    }
}