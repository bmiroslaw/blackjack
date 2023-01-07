import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> cards;

    //constructor initialises the Cards arraylist and adds all the cards to it
    public Deck() {
        cards = new ArrayList<>();
        String[] ranks = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
        int[] values = {11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};

        //add all deck of Card objects to the cards arraylist
        for (int i = 0; i < ranks.length; i++) {
            for(int j=0; j<4; j++){
                Card card = new Card(ranks[i], values[i]);
                cards.add(card);
            }
        }
    }

    //returns the arraylist of cards
    public ArrayList<Card> getCards() {
        return cards;
    }

    //checks how many cards are remaining in the deck and reshuffles it if less than 50%
    public void checkDeck(){
        if(cards.size()<26){
            //get a new deck of cards
            cards = new Deck().getCards();
            //shuffle it
            Collections.shuffle(cards);
        }
    }

    //Shuffle the deck
    public void shuffle() {
        Collections.shuffle(cards);
    }

    //Deal a card from the top of the deck
    public Card deal() {
        if(cards.isEmpty())
            return null;
        return cards.remove(0);
    }
}
