/**
 * SYST 17796 Project Winter 2019 Base code.
 * Students can modify and extend to implement their game.
 * Add your name as a modifier and the date!
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A concrete class that represents any grouping of cards for a Game. HINT, you
 * might want to subclass this more than once. The group of cards has a maximum
 * size attribute which is flexible for reuse.
 *
 * @author dancye
 */
public class GroupOfCards {

    //The group of cards, stored in an ArrayList
    private ArrayList<Card> cards;
    private int size;//the size of the grouping

    public GroupOfCards() {

    }

    public GroupOfCards(int givenSize) {
        size = givenSize;
    }

    /**
     * A method that will get the group of cards as an ArrayList
     *
     * @return the group of cards.
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Card getCard(int cardNum) {
        return cards.get(cardNum);
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    public Card popCard() {
        return cards.remove(cards.size() - 1);
    }

    /**
     * @return the size of the group of cards
     */
    public int getSize() {
        return cards.size();
    }

    /**
     * @param givenSize the max size for the group of cards
     */
    public void setSize(int givenSize) {
        size = givenSize;
    }

}//end class
