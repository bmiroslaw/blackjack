import org.example.Card;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CardTests {
    @Test
    public void testConstructor(){
        String rank = "Ace";
        int value = 11;

        Card card = new Card(rank, value);
        assertEquals(card.getRank(),rank);
        assertEquals(card.getValue(), value);
    }

    @Test
    public void testSetValue() {
        Card card = new Card("Ace", 11);
        card.setValue(1);
        assertEquals(1, card.getValue());
    }
}
