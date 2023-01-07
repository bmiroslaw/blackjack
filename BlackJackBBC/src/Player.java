import java.util.ArrayList;
import java.util.Collection;

public class Player {
    private ArrayList<Card> hand;
    private int score;
    private final String name;

    //constructor initialises the name, hand and the score of the player
    public Player(String name) {
        this.name = name;
        hand = new ArrayList<Card>();
        score = 0;
    }

    //adds a card to the player's hand and update the score
    public void newCard(Card card) {
        hand.add(card);
        score += card.getValue();
    }

    //checks if the player is busted
    public boolean isBusted() {
        return this.getScore() > 21;
    }

    //prints the hand and score of the player
    public void printHand(){
        System.out.print(name + "'s hand is: ");
        for (Card card : hand) {
            System.out.print(card.getRank()+" ");
        }
        System.out.print("equal to "+score+"\n");
    }

    //returns the player's name
    public String getName() {
        return name;
    }

    //returns the player's score
    public int getScore() {
        return score;
    }

    //returns the player's hand
    public ArrayList<Card> getHand() {
        return hand;
    }
}
