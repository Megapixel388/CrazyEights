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
    private int suit; //clubs, spades, diamonds, hearts
    private int value;//1-13
    //private String valueString;

    public static final String[] SUITS = {"Hearts", "Diamonds", "Spades", "Clubs", "Craziness"};

    /**
     * Students should implement this method for their specific children classes
     *
     * @return a String representation of a card. Could be an UNO card, a
     * regular playing card etc.
     */
    public Card() {
    }

    public Card(int suit, int value) {
        this.suit = suit;
        this.value = value;
        //this.valueString = valueString;
    }

    public void setSuit(int suit) {
        this.suit = suit;
    }

    public int getSuit() {
        return suit;
    }

    public String getSuitName() {
        return SUITS[suit];
    }

    public void setValue(int value) {
        //Turn the int into a string based on the suit
        //Update to take face cards into account later
        //valueString = Integer.toString(value);
        this.value = value;
        if (value == 7) {
            this.suit = 4; //Usually only goes up to 3, so eights get a special suit to themselves 
        }
    }

    public int getValue() {
        return value;
    }

    public String getValueName() {
        String valueName;
        switch (value) {
            case 10:
                valueName = "Jack";
                break;
            case 11:
                valueName = "Queen";
                break;
            case 12:
                valueName = "King";
                break;
            default:
                valueName = Integer.toString(value);
        }
        return valueName;
    }

    @Override
    public String toString() {
        return (getValueName() + " of " + getSuitName());
    }

}
