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

    private Stack<Card> deck;
    //Deck is a stack instead of GroupOfCards, 
    //because the pop method is good for drawing cards
    //There is only one deck so it can be declared here

    //private ArrayList<GroupOfCards> hands;
    private boolean isWinner;
    private Scanner input;

    @Override
    public void play() {
        isWinner = false;   //This will update when a player wins
        setPlayers();       //Adds players to the game
        createDeck();       //Creates the deck
        deal();
        turn(0);
    }

    @Override
    public void declareWinner() {
    }

    public void setPlayers() {
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

    public void createDeck() {
        //Create every card in the deck and add it to the stack
        for (int s = 0; s < 4; s++) {       //s is suit
            for (int v = 1; v < 14; v++) {  //v is value
                Card newCard = new Card(s, v);
                deck.add(newCard);
            }
        }
        Collections.shuffle(deck);
    }

    public void deal() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < getNumPlayers(); j++) {
                setPlayer(j, //Set this player
                        getPlayer(j).//to themselves but...
                                setHand(//set their hand to...
                                        (getPlayer(j). //the current player's
                                        getHand(). //hand but
                                                addCard(deck.pop())))); //Add a card to it off the top of the deck
                System.out.println(getPlayer(j).getHand().getCard(i));
            }
        }
    }

    public void turn(int playerNumber) {
        drawCard();
        playCard();
    }

    public void drawCard() {

    }

    public void playCard() {

    }

}
