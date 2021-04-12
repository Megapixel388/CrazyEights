/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.Scanner;

/**
 *
 * @author obris
 */
public class CrazyEights extends Game {

    CrazyEights(String givenName) {
        super(givenName);
    }

    private boolean isWinner;
    private GroupOfCards deck;

    @Override
    public void play() {
        isWinner = false;   //This will update when a player wins
        setPlayers();       //Adds players to the game
        //GroupOfCards deck = new GroupOfCards();       //Creates the deck
        deck = createDeck();
        deal();         //Deal cards to all players
        //Start turns until a winner is decided
        int currentPlayer = 0;
        Card topCard = deck.popCard();
        while (!isWinner) {
            turn(currentPlayer, topCard);  //Player takes their turn

            if (currentPlayer == getNumPlayers()) { //If at last player, go to first player
                currentPlayer = 0;
            } else {                                //Otherwise go to the next player
                currentPlayer++;
            }
        }
    }

    @Override
    public void declareWinner() {
    }

    public void setPlayers() {
        Scanner input = new Scanner(System.in);
        int playerNum = 0;         //Keeps track of how many players there are
        String playerName = "";
        System.out.println("Type 'End' to stop adding players");

        while (!(playerName.equalsIgnoreCase("end")) && playerNum < 6) { //Adds  players until the user types end or maximum is reached
            if (playerNum == 5) {                       //Displays a different message when they reach max players
                System.out.println("Enter player " + (playerNum + 1) + "(last player) name");
            } else {
                System.out.println("Enter player " + (playerNum + 1) + " name");
            }
            playerName = input.nextLine();              //Assign input to player name
            Player newPlayer = new Player(playerName);  //Assign player name to a  new player
            addPlayer(newPlayer);                       //Adds a player to the players arraylist in game
            playerNum++;                               // Increase player num
        }
    }

    public GroupOfCards createDeck() {
        for (int s = 0; s < 4; s++) {       //s is suit
            for (int v = 1; v < 13; v++) {  //v is value
                Card newCard = new Card(s, v);  //Create new cards in order
                deck.addCard(newCard);  //Add new cards to the deck
            }
        }
        deck.shuffle();                 //Shuffle the deck
        return deck;                    //Return full functional deck of cards
    }

    public void deal() {
        for (int i = 0; i < 8; i++) {   //Deal up to 8 cards
            for (int j = 0; j < getNumPlayers(); j++) { //Cycle through each player until you reach the end
                drawCard(j);         //Do the draw card method for the current player, from the deck
                System.out.println(getPlayer(j).getHand().getCard(i)); //Display to make sure it worked.
            }
        }
    }

    public Card turn(int playerNum, Card topCard) {
        System.out.println("Top card:\n" + deck.getCard(deck.getSize()));
        if (checkCards(playerNum, topCard)) {
            playCard();
        } else {
            drawCard(playerNum);
        }
        return topCard;
    }

    public boolean checkCards(int playerNum, Card topCard) {
        GroupOfCards hand = getPlayer(playerNum).getHand();
        boolean match = false;
        //int cardNum = 0;
        for (int i = 0; i < hand.getSize(); i++) {
            if ((hand.getCard(i).getValue() == topCard.getValue())
                    || (hand.getCard(i).getSuit() == topCard.getSuit())
                    || (hand.getCard(i).getValue() == 7)) {
                match = true;
            }
        }
        return match;
    }

    public void drawCard(int playerNum) {
        //Card newCard = deck.pop();

//        setPlayer(playerNum, //Set this player
//                getPlayer(playerNum).//to themselves but...
//                        setHand(//set their hand to...
//                                (getPlayer(playerNum). //the current player's
//                                        getHand(). //hand but
//                                        addCard(deck.pop())))); //Add a card to it off the top of the deck
        Player tempPlayer = getPlayer(playerNum);
        GroupOfCards tempHand = tempPlayer.getHand();
        tempHand.addCard(deck.popCard());
        tempPlayer.setHand(tempHand);
        setPlayer(playerNum, tempPlayer);    //This works better, but still sucks
    }

    public void playCard() {

    }

}
