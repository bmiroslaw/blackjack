package org.example;

import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.Thread.sleep;

public class Blackjack {
    private final Deck deck;
    private Player player1;
    private Player dealer;
    private boolean endGame;

    //constructor method to initialise the game system
    public Blackjack(String playerName) {
        this.deck = new Deck();
        this.player1 = new Player(playerName);
        this.dealer = new Player("Dealer");
        this.endGame =false;
    }

    //keeps the game active until the user decides to stop it
    public void play() throws InterruptedException {
        System.out.println("Starting a new game of Blackjack!");
        deck.shuffle();
        while(!endGame){
            playRound();
            keepPlaying();
        }
    }

    //plays one round of BlackJack
    private void playRound() throws InterruptedException {
        //deals starting hands
        dealStartingHand(player1);
        dealStartingHand(dealer);
        //prints hands
        player1.printHand();
        dealer.printHand();

        // check if anyone has blackjack
        if (isBlackjack(player1)) {
            System.out.println(player1.getName() + " has Blackjack and wins!");
            return; //player has blackjack so end round
        }
        if (isBlackjack(dealer)) {
            System.out.println(dealer.getName() + " has Blackjack and wins!");
            return; //dealer has blackjack so end round
        }

        // player's turn
        if(playersTurn(player1)) //if player is busted end the round
            return;
        // dealer's turn
        if(dealersTurn(dealer)) //if dealer is busted end the round
            return;

        // compare hands and determine the winner
        endRound();
    }



    //gives the players initial cards
    private void initialCard(Card card, Player player){
        if(isAce(card)){
            //gives option to assign the value of Ace to be 11 or 1
            if(!player.getName().equals(dealer.getName()))
                card.setValue(playerSelectValueAce());
            else
                card.setValue(dealerSelectValueAce(player));
        }
        //adds the card to the player
        player.newCard(card);
    }

    //gives players 2 cards
    private void dealStartingHand(Player player){
        initialCard(deck.deal(), player);
        initialCard(deck.deal(), player);
    }

    //returns true if player has a blackjack
    private boolean isBlackjack(Player player) {
        return player.getScore() == 21;
    }

    //allows the player to play their turn
    private boolean playersTurn(Player player){
        boolean busted = false;
        while (!busted) {
            //player decides to hit or stand
            System.out.println("1. Hit");
            System.out.println("2. Stand");

            int playersChoice=getChoiceOfTwo();
            if(playersChoice==1){
                busted=addNewCard(player); //new card is added to the player and busted is updated
            } else{
                break; //if 2 the loop is ended
            }
        }
        System.out.println(busted);
        return busted; //returns true if player got busted
    }

    //allows the dealer to play their turn
    private boolean dealersTurn(Player dealer) throws InterruptedException {
        boolean busted = false;
        //deals cards to the dealer until their had is equal 17 or more
        while (dealer.getScore() < 17) {
            sleep(500); //Makes the gameplay look smoother/more realistic
            busted=addNewCard(dealer);
            }
        return busted; //returns true if the dealer got busted
    }

    //adds cards to the player or dealer and returns true if busted
    private boolean addNewCard(Player selectedPlayer){
        Card newCard = deck.deal();

        //gives option to assign the value of Ace to be 11 or 1
        if(isAce(newCard)){
            if(!selectedPlayer.getName().equals(dealer.getName()))
                newCard.setValue(playerSelectValueAce());
            else //this has to be divided since the dealer has a different approach to dealing with Aces
                newCard.setValue(dealerSelectValueAce(selectedPlayer));
        }
        System.out.println(selectedPlayer.getName() + " draws " + newCard.getRank() + " " + newCard.getValue());

        //adds the card to player
        selectedPlayer.newCard(newCard);
        System.out.println(selectedPlayer.getName() + "'s hand: " + selectedPlayer.getScore());

        //checks if player is busted
        if (selectedPlayer.isBusted()) {
            System.out.println(selectedPlayer.getName() + " is busted and loses!");
            return true;
        }
        return false; //since the selectedPlayer is not busted
    }

    //returns true if the card is an Ace
    private boolean isAce(Card card){
        return card.getValue() == 11;
    }

    //gets the choice 1 or 2 from the user
    private int getChoiceOfTwo(){
        while(true){
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            if (choice == 1) {
                return 1;
            }else if (choice == 2) {
                return 2;
            }else {
                System.out.println("Select 1 or 2!");
            }
        }
    }

    //dealers approach to selecting value for Aces (11 if it doesn't result in the dealer being busted)
    private int dealerSelectValueAce(Player selectedPlayer){
        if(selectedPlayer.getScore()+11>21){
            return 1;
        }
        return 11;
    }

    //players approach to selecting value for Aces
    private int playerSelectValueAce(){
        System.out.println("You drew an ACE, decide the value of the card");
        System.out.println("1. 11");
        System.out.println("2. 1");

        //player selects if 11 or 1
        if(getChoiceOfTwo()==1){
            return 11;
        }
        return 1;
    }

    //gives the result of the round if neither the player nor dealer ar busted
    private void endRound(){
        if (player1.getScore() > dealer.getScore()) {
            System.out.println(player1.getName() + " wins with a hand value of " + player1.getScore());
        } else if (player1.getScore() < dealer.getScore()) {
            System.out.println(dealer.getName() + " wins with a hand value of " + dealer.getScore());
        } else {
            System.out.println("It's a tie!");
        }
    }

    //asks the player if he wishes to continue the game
    private void keepPlaying(){
        System.out.println("Another round?");
        System.out.print("1. Yes");
        System.out.print(" 2. No \n");

        //if yes then start another round
        if(getChoiceOfTwo()==1){
            //starting another round
            System.out.println("\nStarting another round!");
            player1=new Player(player1.getName());
            dealer=new Player(dealer.getName());
            deck.checkDeck(); //if the deck lost 50% of the cards then reshuffle
        } else{
            //ending game
            System.out.println("Ending the game!");
            endGame = true;
        }
    }

    //returns the player
    public Player getPlayer1() {
        return player1;
    }

    //TODO These methods are created for testing only and require choices arraylist to predetermine user's choices
    //plays one round of BlackJack
    public int playRoundTestTwo(ArrayList<Card> cards, ArrayList<Integer> choices, boolean aces) {
        //deals starting hands
        for (Card card: cards) {
            player1.newCard(card);
        }

        player1.printHand();

        if(!aces){//to remove aces
            for (int i = 0; i < 4; i++)
                deck.deal();
        }

        // player's turn
        playersTurnTest(player1, choices);
        player1.printHand();

        return player1.getScore();
    }

    //allows the player to play their turn
    protected boolean playersTurnTest(Player player, ArrayList<Integer> choices){
        boolean busted = false;
        while (!busted) {
            //player decides to hit or stand
            System.out.println("1. Hit");
            System.out.println("2. Stand");

            int playersChoice=choices.remove(0);
            if(playersChoice==1){
                busted=addNewCardTest(player, choices); //new card is added to the player and busted is updated
            } else{
                break; //if 2 the loop is ended
            }
        }
        return busted; //returns true if player got busted
    }

    //adds cards to the player or dealer and returns true if busted
    protected boolean addNewCardTest(Player selectedPlayer, ArrayList<Integer> choices){
        Card newCard = deck.deal();

        //gives option to assign the value of Ace to be 11 or 1
        if(isAce(newCard)){
            if(!selectedPlayer.getName().equals(dealer.getName()))
                newCard.setValue(playerSelectValueAceTest(choices));
            else //this has to be divided since the dealer has a different approach to dealing with Aces
                newCard.setValue(dealerSelectValueAce(selectedPlayer));
        } else {
            System.out.println(selectedPlayer.getName() + " draws " + newCard.getRank() + " " + newCard.getValue());
        }
        //adds the card to player
        selectedPlayer.newCard(newCard);
        System.out.println(selectedPlayer.getName() + "'s hand: " + selectedPlayer.getScore());

        //checks if player is busted
        if (selectedPlayer.isBusted()) {
            System.out.println(selectedPlayer.getName() + " is busted and loses!");
            return true;
        }
        return false; //since the selectedPlayer is not busted
    }
    //players approach to selecting value for Aces
    protected int playerSelectValueAceTest(ArrayList<Integer> choices){
        System.out.println("You drew an ACE, decide the value of the card");
        System.out.println("1. 11");
        System.out.println("2. 1");

        //player selects if 11 or 1
        if(choices.remove(0)==1){
            return 11;
        }
        return 1;
    }
}
