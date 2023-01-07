public class Card {
    private final String rank;
    private int value;

    //suit could be added but not needed according to requirements
    //constructor initializes the rank and value of the Card object
    public Card(String rank, int value) {
        this.rank = rank;
        this.value = value;
    }

    //returns the rank of the card
    public String getRank() {
        return rank;
    }

    //returns the Blackjack value of the card
    public int getValue() {
        return value;
    }

    //updates the value of the card (to deal with Aces)
    public void setValue(int value) {
        this.value = value;
    }
}
