import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DeckTests {
    @Test
    public void testNumberOfCards(){
        // create a new deck of cards
        Deck deck = new Deck();

        int expectedNumberOfCards = 52;

        //should be 52
        int numberOfCards=deck.getCards().size();

        assertEquals(numberOfCards, expectedNumberOfCards);
    }

    @Test
    public void testDeal() {
        // create a new deck of cards
        Deck deck = new Deck();

        // since we are to remove one card
        int expectedSize = 52-1;

        deck.deal();
        int actualSize = deck.getCards().size();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void testCheckDeck() {
        // create a new deck of cards
        Deck deck = new Deck();
        // should be 52
        int initialSize = deck.getCards().size();

        // remove over half of the cards from the deck
        for (int i = 0; i < 27; i++) {
            deck.deal();
        }
        // since there are over 50% of cards missing the deck is reset
        deck.checkDeck();
        int finalSize = deck.getCards().size();

        assertEquals(initialSize, finalSize);
    }

    @Test
    public void testShuffle() {
        // create a new deck of cards
        Deck deck = new Deck();

        /*
        ArrayList<Card> initialCards = deck.getCards(); doesnt work since "initialCards" list is being created
        as a reference to the ArrayList object returned by deck.getCards(). When shuffle method is called on the
        deck object, it modifies the original ArrayList object that is stored in the cards field of the deck object.
        Since the initialCards list is a reference to this same object, the changes made to
        the object by the shuffle method will be reflected in the initialCards list as well.
         */
        // cards before shuffling in a new deck
        ArrayList<Card> initialCards = new ArrayList<>(deck.getCards());
        deck.shuffle();
        // cards after shuffling
        ArrayList<Card> shuffledCards = deck.getCards();

        assertNotSame(initialCards, shuffledCards);
    }

    @Test
    public void testDealEmptyDeck() {
        // create a new deck of cards
        Deck deck = new Deck();

        // remove all cards from the deck
        while (deck.getCards().size() > 0) {
            deck.deal();
        }
        // try removing a card from an empty deck
        Card card = deck.deal();
        assertNull(card);
    }

    @Test
    public void testCardValues() {
        // create a new deck of cards
        Deck deck = new Deck();

        String[] ranks = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
        int[] values = {11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};

        // get the list of cards from the deck
        ArrayList<Card> cards = deck.getCards();

        // loop through the list of cards and check their ranks and value
        for (int i = 0; i < ranks.length; i++) {
            Card card = cards.get(i*4); //i*4 since there are 4 cards each

            assertEquals(ranks[i], card.getRank());
            assertEquals(values[i], card.getValue());
        }
    }
}
