public class Main {
    public static void main(String[] args) throws InterruptedException {
        String playerName = "Player";
        Blackjack game = new Blackjack(playerName);
        game.play();
    }
}