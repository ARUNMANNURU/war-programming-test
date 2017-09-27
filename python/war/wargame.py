"""
Knexus Python Programming test

Author: JT
"""
import cardfactory as cardfact
import card as card
import deck as deck


def play_round(deck1, deck2):
    pass  # YOUR CODE GOES HERE

def play_game(player1, player2):
    """
    This function when called will play an entire game of War.
    playRound will get called until either play is completely out of cards.
    
    :param player1: Deck of cards representing player 1
    :param player2: Deck of cards representing player 2
    """
    
    while player1.get_num_cards() > 0 and player2.get_num_cards() > 0:
        play_round(player1, player2)

if __name__== '__main__':
    # Creates the card factory
    factory = cardfact.CardFactory()

    # Makes a full deck for p1, empty for p2, shuffles
    player1 = factory.create_full_deck()
    player2 = deck.Deck()
    player1.shuffle()

    # Deals half of player 1's shuffled deck to player 2
    decksize = int(player1.get_draw_pile_size() / 2)
    for i in range(decksize):
        player2.add_to_discard(player1.draw())

    # Shuffle the decks
    player1.shuffle()
    player2.shuffle()

    # play a single round of War
    play_round(player1, player2)

    # play an entire game of War
    #play_game(player1, player2)