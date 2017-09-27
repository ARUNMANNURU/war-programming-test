/*
 *----------------------------------------------------------------
 * @author: Justin
 * Project Name: Programming Test
 *----------------------------------------------------------------
 * Description:(Class overview)
 *  A programming test designed to use a Deck API to build a functioning
 * version of the card game War.
 *  
 *----------------------------------------------------------------
 * Copyright Notice: Knexus Research Corporation, ${date?date?string("yyyy")}
 * All rights Reserved

 *----------------------------------------------------------------
 * Disclaimer:
 * THIS SOFTWARE IS PROVIDED BY KNEXUS RESEARCH CORP., "AS IS" 
 * AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, WARRANTIES OF INFRINGEMENT AND THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL KNEXUS RESEARCH CORP.
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE DISTRIBUTION OR USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */
package us.knexus.war;

import us.knexus.deck.Card;
import us.knexus.deck.CardFactory;
import us.knexus.deck.Deck;

/**
 * A simple version of the card game WarGame
 
 * @author Justin
 */
public class WarGame {

    public static final int NUMBER_CARDS_DECK = 52;
    private static final int NUMBER_CARDS_TIEBREAKER = 3;
    
    public static void playRound(Deck deck1, Deck deck2) {

        //create an array of Card to store the cards in the pile
        //these cards will be added to each array.

        Card[] pile = new Card[NUMBER_CARDS_DECK];

        Deck deckOne = new Deck();
        Deck deckTwo = new Deck();

        Card playerOneCard = deckOne.draw();
        Card playerTwoCard = deckTwo.draw();

        //remove the first card from playerOneCard
        //This method shifts everything over, so that the value at index 0 is a
        //different card.

        removeTopCard(deckOne);
        addCardToBottom(pile, playerOneCard);
        removeTopCard(deckTwo);
        addCardToBottom(pile, playerTwoCard);

        int comparison = compareCards(playerOneCard, playerTwoCard);

        printRoundResults(playerOneCard, playerTwoCard);

        while (comparison == 0) {
            //deal out 3 more cards because you do that in war normally, then evaluate again on the 4th.
            //note that we need to make sure the piles are not empty.

            for (int j = 0; j < NUMBER_CARDS_TIEBREAKER; j++) {
                //We must assure that both decks have cards before removing from the top
                //This sort of check will be easier to do when we add better Object oriented design
                //as it can be "contained" or "encapsulated" into another class-- the same way
                //that we kept all the logic of suits being 0-3 inside the Card class. This allowed us
                //to prove that no card could ever have suit < 0 or > 3. Similarly, with a type we
                //define called CardPile , we will be able to assure various other things
                //In this case, if a deck has nothing in it, we'll return. This means we'll leave the method
                //and in the main method, the central while loop will be left.
                if (!hasCards(deckOne) || !hasCards(deckTwo)) {
                    return;
                }

                addCardToBottom(pile, deck1[0]);
                addCardToBottom(pile, deck2[0]);
                removeTopCard(deck1);
                removeTopCard(deck2);
            }

            if (!hasCards(deck1) || !hasCards(deck2)) {
                return;
            }

            //now compare the top cards again:
            playerOneCard = deck1[0];
            playerTwoCard = deck2[0];

            //remove the first card from playerOneCard
            removeTopCard(deck1);
            addCardToBottom(pile, playerOneCard);
            removeTopCard(deck2);
            addCardToBottom(pile, playerTwoCard);

            comparison = compareCards(playerOneCard, playerTwoCard);
            printRoundResults(playerOneCard, playerTwoCard);
        }

        //now add all cards in the pile to the winner of that round's hand
        if (comparison > 0) {
            while (hasCards(pile)) {
                addCardToBottom(deck1, pile[0]);
                removeTopCard(pile);
            }
        }
        else if (comparison < 0) {
            while (hasCards(pile)) {
                addCardToBottom(deck2, pile[0]);
                removeTopCard(pile);
            }
        }

    }

    private static boolean hasCards(Card[] deck) {
        for (int i=0; i < deck.length; i++) {
            if (deck[i] != null) {
                return true;
            }
        }

        return false;
    }


    //This method removes a card from an array by taking the top card off and shifting
    //every other value in the array.
    private static void removeTopCard(Card[] deck)
    {
        //note we only go up to length -1 to avoid an array out of bounds error
        //Because deck is a reference type, we can modify it inside this method
        for (int i=0; i < deck.length - 1; i++) {
            deck[i] = deck[i + 1];
        }
    }

    //This method prints the output of a round given two Cards
    private static void printRoundResults(Card playerOneCard, Card playerTwoCard) {
        System.out.println("Player one plays " + playerOneCard.getName());
        System.out.println("Player two plays " + playerTwoCard.getName());

        int comparison = compareCards(playerOneCard, playerTwoCard);

        if (comparison == 0) {
            System.out.println("WAR!");
        }
        else if (comparison > 0) {
            System.out.println("Player one wins that round!");
        }
        else {
            System.out.println("Player two wins that round!");
        }
    }

    //This method takes as input 2 Card. It assumes neither Card is null
    //If the value of card1 == the value of card2, it returns 0;
    //If the value of card1 > the value of card2, it returns 1;
    //If the value of card1 < the value of card2, it returns -1;
    //Note that by "value" we mean the value of the card in the game of War,
    //meaning aces are > king
    private static int compareCards(Card card1, Card card2) {
        int playerOneNumber = card1.getOrdinal();
        int playerTwoNumber = card2.getOrdinal();

        if (playerOneNumber == playerTwoNumber) {
            return 0;
        }

        //check for aces. Note that at this point in the code we know for sure
        //that playerOneNumber != playerTwoNumber. Otherwise, we'd have returned
        //at the previous statement. This means if playerOneNumber == 1, then he won.
        if (playerOneNumber == 1) {
            return 1;
        }

        if (playerTwoNumber == 1) {
            return 1;
        }

        if (playerTwoNumber > playerOneNumber) {
            return -1;
        }
        else {
            return 1;
        }
    }

    //This method takes as inptu a Card[] and a Card.
    //It adds the Card to the first non-null spot of Card[]
    private static void addCardToBottom(Card[] deck, Card newCard)
    {
        for (int i = 0; i < deck.length; i++) {
            if (deck[i] == null) {
                deck[i] = newCard;
                return; //leave the method immediately!
            }
        }
    }


    /**
     * This function when called will play an entire game of War.
     * playRound will get called until either play is completely out of cards.
     * 
     * @param player1 Deck of cards representing player 1
     * @param player2 Deck of cards representing player 2
     */
    public static void playGame(Deck player1, Deck player2) {
        while( player1.getNumCards() > 0 && player2.getNumCards() > 0 )
            playRound(player1, player2);
        
        System.out.println("Player1: " + player1.getNumCards());
        System.out.println("Player2: " + player2.getNumCards());
        if( player1.getNumCards() > 0 )
            System.out.println("Player 1 Wins!");
        else if( player2.getNumCards() > 0 )
            System.out.println("Player 2 Wins!");
    }
    
    public static void main(String[] args) {
        // Get a new CardFactory instance
        CardFactory factory = new CardFactory();
        
        // Get a full deck, set as player 1
        Deck player1 = factory.createFullDeck();        
        // Make a new empty deck for player 2
        Deck player2 = new Deck();
        // Shuffle the player 1 deck
        player1.shuffle();

        // deal half of player 1's shuffled deck to player 2
        int deckSize = player1.getDrawPileSize() / 2;
        for(int i=0; i<deckSize; ++i)
            player2.addToDiscard(player1.draw());

        // shuffle both decks
        player1.shuffle();
        player2.shuffle();

        // play a single round of War
        playRound(player1, player2);
        
        // play an entire game of War
        playGame(player1, player2);
    }
}
