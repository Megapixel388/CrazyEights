/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author obris
 */
public class CrazyEights extends Game {

    private Stack<Card> deck;
    private ArrayList<GroupOfCards> hands;
    private boolean isWinner;

    @Override
    public void play() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //This method calls other methods
        isWinner = false;
        setPlayers();
        createDeck();
//        GroupOfCards hand0 = new GroupOfCards(1);
//        GroupOfCards hand1 = new GroupOfCards(1);
//        hands.add(hand0);
//        hands.add(hand1);
//        hand0.addCard(deck.pop());
//        hand1.addCard(deck.pop());
        turn(0);
    }

    @Override
    public void declareWinner() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setPlayers() {
        Player player1 = new Player("Alice");
        addPlayer(player1);
        Player player2 = new Player("Bob");
        addPlayer(player2);
    }

    public void createDeck() {
        Card deckCard1 = new Card();
        deckCard1.setSuit(1);
        deckCard1.setValue(3);
        Card deckCard2 = new Card();
        deckCard2.setSuit(1);
        deckCard2.setValue(4);
        Card deckCard3 = new Card();
        deckCard3.setSuit(1);
        deckCard3.setValue(5);
        Card deckCard4 = new Card();
        deckCard4.setSuit(1);
        deckCard4.setValue(6);
        deck.push(deckCard1);
        deck.push(deckCard2);
        deck.push(deckCard3);
        deck.push(deckCard4);
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
