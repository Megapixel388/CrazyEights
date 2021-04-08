/**
 * SYST 17796 Project Winter 2019 Base code.
 * Students can modify and extend to implement their game.
 * Add your name as a modifier and the date!
 * This is a change
 */
package ca.sheridancollege.project;

/**
 * A class to be used as the base Card class for the project. Must be general
 * enough to be instantiated for any Card game. Students wishing to add to the
 * code should remember to add themselves as a modifier.
 *
 * @author dancye, 2018
 */
public class Card {

    //default modifier for child classes
    private String suit; //clubs, spades, diamonds, hearts
    private int value;//1-13
    //private String valueString;

    public static final String[] SUITS = {"Hearts", "Diamonds", "Spades", "Clubs"};

    /**
     * Students should implement this method for their specific children classes
     *
     * @return a String representation of a card. Could be an UNO card, a
     * regular playing card etc.
     */
    public Card() {
    }

    public Card(String suit, int value) {
        this.suit = suit;
        this.value = value;
        //this.valueString = valueString;
    }

    public void setSuit(int suitNum) {
        suit = SUITS[suitNum];
    }

    public String getSuit() {
        return suit;
    }

    public void setValue(int value) {
        //Turn the int into a string based on the suit
        //Update to take face cards into account later
        //valueString = Integer.toString(value);
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
//    @Override
//    public String toString();
//
//}
