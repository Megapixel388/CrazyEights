/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sheridancollege.project;

import java.util.Scanner;

/**
 *
 * @author obris
 */
public class CrazyEights extends Game {

    private boolean isWinner;
    private GroupOfCards deck;

    CrazyEights(String givenName) {
        super(givenName);
    }

    @Override
    public void play() throws Exception {
        isWinner = false;   //This will update when a player wins
        setPlayers();       //Adds players to the game
        deck = createDeck();
        deal();         //Deal cards to all players
        //Start turns until a winner is decided
        int currentPlayer = 0;
        Card topCard = deck.popCard();              //Initialize the game by flipping over the first card in the deck
        while (!isWinner) {
            turn(currentPlayer, topCard);            //Player takes their turn

            if (currentPlayer == getNumPlayers()) { //If at last player, go to first player
                currentPlayer = 0;
            } else {                                //Otherwise go to the next player
                currentPlayer++;
            }
        }
        declareWinner();                            //When getWinner is true, finally declare the winner
    }

    @Override
    public void declareWinner() {
        for (int i = 0; i < getNumPlayers(); i++) {                              //Cycle through the players
            if (getPlayer(i).getHand().getSize() < 1) {                         //If the player has fewer than 1 card
                System.out.println(getPlayer(i).getPlayerID() + " wins!!!");    //Declare them as the winner
            }
        }
    }

    public void setPlayers() {
        Scanner input = new Scanner(System.in);
        int playerNum = 0;                                              //Keeps track of how many players there are
        String playerName = "";
        System.out.println("Type 'End' to stop adding players");

        while (!(playerName.equalsIgnoreCase("end")) && playerNum < 6) { //Adds  players until the user types end or maximum is reached
            if (playerNum == 5) {                                       //Displays a different message when they reach max players
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
        for (int s = 0; s < 4; s++) {                  //s is suit
            for (int v = 1; v < 13; v++) {             //v is value
                Card newCard = new Card(s, v);         //Create new cards in order
                deck.addCard(newCard);                 //Add new cards to the deck
            }
        }
        deck.shuffle();                                 //Shuffle the deck
        return deck;                                    //Return full functional deck of cards
    }

    public void deal() {
        for (int i = 0; i < 8; i++) {                                   //Deal up to 8 cards
            for (int j = 0; j < getNumPlayers(); j++) {                 //Cycle through each player until you reach the end
                drawCard(j);                                            //Do the draw card method for the current player, from the deck
                System.out.println(getPlayer(j).getHand().getCard(i));  //Display to make sure it worked.
            }
        }
    }

    public Card turn(int playerNum, Card topCard) throws Exception {
        System.out.println("Top card:\n" + deck.getCard(deck.getSize()));   //Display the top card
        GroupOfCards playables = checkCards(playerNum, topCard);            //Check which cards are playable
        if (playables.getSize() > 0) {                                      //If the size of the hand of playable cards is more than 0
            topCard = playCard(playerNum, playables);                                  //Allow the user to play a card, and set it to the top of the pile
        } else {                                                            //Or else
            drawCard(playerNum);                                            //Make them draw a card
        }
        if (getPlayer(playerNum).getHand().getSize() == 0) {                //If the player has no cards lef
            isWinner = true;                                                //They win!
        }
        return topCard;                                                     //Return the new top card
    }

    public GroupOfCards checkCards(int playerNum, Card topCard) {
        GroupOfCards hand = getPlayer(playerNum).getHand();                 //Make a hand so you don't have to type getPlayer(playerNum).getHand(); every time
        GroupOfCards playable = new GroupOfCards();                         // This will hold which cards in the player's hand are playable
        for (int i = 0; i < hand.getSize(); i++) {                          //Loop through the player's hand
            if ((hand.getCard(i).getValue() == topCard.getValue()) //If the card matches the value...
                    || (hand.getCard(i).getSuit() == topCard.getSuit()) //suit...
                    || (hand.getCard(i).getValue() == 7)) {                 // or is an 8
                playable.addCard(hand.getCard(i));                          //Add the playable card to the list
            }
        }
        return playable;
    }

    public void drawCard(int playerNum) {
        //Card newCard = deck.pop();

//        setPlayer(playerNum, //Set this player
//                getPlayer(playerNum).//to themselves but...
//                        setHand(//set their hand to...
//                                (getPlayer(playerNum). //the current player's
//                                        getHand(). //hand but
//                                        addCard(deck.pop())))); //Add a card to it off the top of the deck
//
        Player tempPlayer = getPlayer(playerNum);       //Make new temporary player; a copy of whoever's drawing
        GroupOfCards tempHand = tempPlayer.getHand();   //Make a copy of their hand too
        tempHand.addCard(deck.popCard());               //Add a card to that hand
        tempPlayer.setHand(tempHand);                   //Set the temp player's hand to that
        setPlayer(playerNum, tempPlayer);               //Set the original player to the temp's state
        //This works better than the mess up there, but still sucks
    }

    public Card playCard(int playerNum, GroupOfCards cards) throws Exception {
        boolean validInput = false;                                             //This turns true if the card played is valid
        Card playedCard = new Card();                                           //This stores the card that is eventually returned

        while (!validInput) {                                                   //While the input is not yet valid
            Scanner input = new Scanner(System.in);
            try {
                System.out.println("Pick a card from your hand (type the number)");
                for (int i = 0; i < cards.getSize(); i++) {                     //Loops through the list of playable cards
                    System.out.println((i + 1) + ". "
                            + cards.getCard(i).toString());                     //Displays them to the user
                }
                //
                int playedCardNum = input.nextInt() - 1;                        //Take the input of the user
                playedCard = cards.getCard(playedCardNum);                      //Assign it to the played card
                removeCard(playerNum, playedCard);                              //Remove card from their hand
                //
                if (playedCard.getValue() == 7) {                               //If it's an 8!
                    System.out.println("Crazy Eight! Enter suit number:\n "     //Prompt to pick a suit
                            + "1. Hearts\n2. Diamonds\n3. Spades\n4. Clubs");
                    playedCard.setSuit(input.nextInt());                        //Set the suit of the card to the new choice
                }
                //
                System.out.println(playedCard.toString() + " was played");
                validInput = true;                                              //Set the input as valid, ending the loop
            } catch (Exception e) {                                             //If that card does not exist, report error and start the loop again
                System.out.println("Not a valid number!");
            }
        }
        return playedCard;                                                      //Return the played card
    }

    public void removeCard(int playerNum, Card card) {
        Player tempPlayer = getPlayer(playerNum);       //Make new temporary player; a copy of whoever's drawing
        GroupOfCards tempHand = tempPlayer.getHand();   //Make a copy of their hand too
        tempHand.removeCard(card);                      //Remove card from that hand
        tempPlayer.setHand(tempHand);                   //Set the temp player's hand to that
        setPlayer(playerNum, tempPlayer);               //Set the original player to the temp's state
    }
}
