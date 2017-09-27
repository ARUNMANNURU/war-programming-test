/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package us.knexus.deck;

import java.util.ArrayList;
import java.util.List;
import us.knexus.deck.Card.Suit;

/**
 *
 * @author Justin
 */
public class CardFactory {
    /**
     * Creates a full deck with 52 cards.
     *  4 of each card 2-10 (one of each suit)
     *  4 of each Jack, Queen, King, and Ace (one of each suit)
     * @return Deck - A full, traditional, 52 card deck in ordinal and suit order
     */
    public Deck createFullDeck() {
        List<Card> cards = new ArrayList<Card>();
        for(int ordinal=2; ordinal<15; ++ordinal) {
            for(Suit suit : Suit.values()) {
                cards.add( this.createCard(suit, ordinal) );
            }
        }
        
        return new Deck(cards);
    }
    
    /**
     * Creates a card given a suite and ordinal value.  Creates the proper name
     * for the given ordinal.  Does not error check for invalid ordinals
     * 
     * @param suit - Suit of card to create
     * @param ordinal - 2-14 numerical value for this card
     * @return Card - Generated card given suit and ordinal data
     */
    public Card createCard(Suit suit, int ordinal) {
        String name;
        switch(ordinal) {
            case 11:
                name = "Jack";
                break;
            case 12:
                name = "Queen";
                break;
            case 13:
                name = "King";
                break;
            case 14:
                name = "Ace";
                break;
            default:
                name = String.valueOf(ordinal);
        }
        
        return new Card(name, suit, ordinal);
    } 
}
