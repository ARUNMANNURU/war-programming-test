/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package us.knexus.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Justin
 */
public class Deck {
    /**
     * The list of cards representing the 'Draw Pile'. Or cards which can be drawn.
     */
    private List<Card> drawPile;
    
    /**
     * The list of cards representing the 'Discard Pile'. Or cards to be shuffled in.
     */
    private List<Card> discardPile;
    
    /**
     * Default Constructor.
     * Creates an empty deck
     */
    public Deck() {
        drawPile = new ArrayList<Card>();
        discardPile = new ArrayList<Card>();
    }
    
    /**
     * Constructs a deck with the given List of Card objects. Copies the Cards
     * into the drawPile List
     * 
     * @param cards 
     */
    public Deck(List<Card> cards) {
        drawPile = new ArrayList<Card>(cards);
        discardPile = new ArrayList<Card>();
    }
    
    /**
     * Copy Constructor. Creates a new deck with deep copies of the given
     * deck's Draw and Discard pile lists
     * 
     * @param other 
     */
    public Deck(Deck other) {
        drawPile = new ArrayList<Card>(other.drawPile);
        discardPile = new ArrayList<Card>(other.discardPile);
    }
    
    /**
     * Removes the first card in the draw pile list and returns it. If
     * draw pile is empty, returns null
     * 
     * @return Card - First card in Draw Pile, null if empty
     */
    public Card draw() {
        if( drawPile.isEmpty() )
            return null;
        return drawPile.remove(0);
    }
    
    /**
     * Adds the given Card to the bottom of the discard pile (back of the
     * discardPile list)
     * 
     * @param card Card - To add to the discard pile
     */
    public void addToDiscard(Card card) {
        discardPile.add(card);
    }
    
    /**
     * Moves all cards from the discard pile to the draw pile and randomizes
     * the draw pile's order.
     */
    public void shuffle() {
        drawPile.addAll(discardPile);
        discardPile.clear();
        
        Collections.shuffle(drawPile);
    }
    
    /**
     * Returns the number of cards currently in the Draw Pile
     * 
     * @return int - Number of cards in drawPile list
     */
    public int getDrawPileSize() {
        return drawPile.size();
    }
    
    /**
     * Returns the number of cards currently in the Discard Pile
     * 
     * @return int - Number of cards in discardPile list
     */
    public int getDiscardPileSize() {
        return discardPile.size();
    }
    
    /**
     * Returns the number of cards currently in the Deck (draw and discard piles)
     * 
     * @return int - Number of cards in any pile of the deck
     */
    public int getNumCards() {
        return drawPile.size() + discardPile.size();
    }

    /**
     * Generated hashCode method.
     * 
     * @return int - Hashed representation of a deck
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.drawPile);
        hash = 59 * hash + Objects.hashCode(this.discardPile);
        return hash;
    }

    /**
     * Generated equals method.
     * 
     * @param obj - Object to compare to this
     * @return boolean - Whether or not the given Object is equivalent to this Deck
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Deck other = (Deck) obj;
        if (!Objects.equals(this.drawPile, other.drawPile)) {
            return false;
        }
        if (!Objects.equals(this.discardPile, other.discardPile)) {
            return false;
        }
        return true;
    }
}
