/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package us.knexus.deck;

import java.util.Objects;

/**
 *
 * @author Justin
 */
public class Card {
    /**
     * An enumeration of possible card suits
     */
    public enum Suit { Club, Diamond, Heart, Spade }
    
    /**
     * The name of this card. (2-10, Jack, Queen, King, Ace)
     */
    private String name;
    
    /**
     * The suit of this card (Club, Diamond, Heart, Spade)
     */
    private Suit suit;
    
    /**
     * A numeric representation of the value of this card.
     * 2-10 for normal cards, Face cards: Jack=11, Queen=12, King=13, Ace=14
     */
    private int ordinal;
    
    /**
     * Card Constructor. Creates a Card with a given Name, Suit and Ordinal.
     * Protected.  Use CardFactory instead.
     * 
     * @param name - String representation of card name
     * @param suit - The Suit of this card
     * @param ordinal - A numerical value representing the value of this card
     */
    protected Card(String name, Suit suit, int ordinal) {
        this.name = name;
        this.suit = suit;
        this.ordinal = ordinal;
    }

    /**
     * Gets the name of this card
     * @return name - String name of card
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the suit of this card
     * @return suit - Suit enum value of this card
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Gets the numeric representation of this card
     * @return int - Integer value representation of this card
     */
    public int getOrdinal() {
        return ordinal;
    }
    
    /**
     * Pretty prints this card as 'NAME of SUITs' (5 of Clubs, Ace of Spaces, ...)
     * @return String - Pretty printed representation of this card
     */
    @Override
    public String toString() {
        return String.format("%s of %ss", name, suit.name());
    }

    /**
     * Generated hashCode method.
     * 
     * @return int - Hashed representation of a deck
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.name);
        hash = 31 * hash + Objects.hashCode(this.suit);
        hash = 31 * hash + this.ordinal;
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
        final Card other = (Card) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (this.suit != other.suit) {
            return false;
        }
        if (this.ordinal != other.ordinal) {
            return false;
        }
        return true;
    }
}
