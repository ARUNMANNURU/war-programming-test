"""
Knexus Python Programming test

Author: JT
"""

import card
import deck


class CardFactory(object):
    def __init__(self):
        pass  # Make it callable because static only is SO 2012.

    @staticmethod
    def create_full_deck():
        """
        * Creates a full deck with 52 cards.
        *  4 of each card 2-10 (one of each suit)
        *  4 of each Jack, Queen, King, and Ace (one of each suit)
        :return: A full, traditional, 52 card deck in ordinal and suit order
        """
        cards = []
        for ordinal in range(2, 15):
            for suit in ['Clubs', 'Diamonds', 'Hearts', 'Spades']:
                cards.append(CardFactory.create_card(suit, ordinal))
        new_deck = deck.Deck()
        return new_deck.create_deck_with_cards(cards)

    @staticmethod
    def create_card(suit, ordinal):
        """
        * Creates a card given a suite and ordinal value.  Creates the proper name
        * for the given ordinal.  Does not error check for invalid ordinals
        :param suit: Suit of card to create
        :param ordinal: 2-14 numerical value for this card
        :return: Generated card given suit and ordinal data
        """
        name = ordinal
        if ordinal == 11:
            name = "Jack"
        if ordinal == 12:
            name = "Queen"
        if ordinal == 13:
            name = "King"
        if ordinal == 14:
            name = "Ace"
        return card.Card(name, suit, ordinal)
