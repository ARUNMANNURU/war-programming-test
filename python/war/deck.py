"""
Knexus Python Programming test

Author: JT
"""

from random import shuffle


class Deck(object):
    def __init__(self):
        """
        Creates an empty deck
        """
        self.draw_pile = []
        self.discard_pile = []

    def create_deck_with_cards(self, cards):
        """
        Constructs a deck with the given List of Card objects. Copies the Cards into draw pile list
        :param cards: to be copied
        :return: deck
        """
        self.draw_pile = cards
        self.discard_pile = []
        return self

    def copy_constructor(self, deck):
        """
        Copy Constructor. Creates a new deck with deep copies of the given deck's Draw and Discard pile lists
        :param deck: to be copied
        :return: newdeck
        """
        self.draw_pile = deck.draw_pile
        self.discard_pile = deck.discard_pile
        return self

    def draw(self):
        """
        Removes the first card in the draw pile list and returns it. If draw pile is empty, returns null
        :return: top card, or null
        """
        if len(self.draw_pile) == 0:
            return None
        return self.draw_pile.pop(0)

    def add_to_discard(self, card):
        """
        Adds card to the back of the discard
        :param card: discarded card
        """
        self.discard_pile.append(card)

    def shuffle(self):
        """
        Shuffles the deck
        :return:
        """
        self.draw_pile.extend(self.discard_pile)
        self.discard_pile = []
        shuffle(self.draw_pile)

    def get_draw_pile_size(self):
        """
        Return size of draw pile
        :return: int of size
        """
        return len(self.draw_pile)

    def get_discard_pile_size(self):
        """
        Returns size of discard pile
        :return: int of size
        """
        return len(self.discard_pile)

    def get_num_cards(self):
        """
        draw pile size plus discard size
        :return: that number
        """
        return self.get_draw_pile_size() + self.get_discard_pile_size()

    def __hash__(self):
        """
        Hashoverride
        :return: hashval
        """
        hashval = 7
        hashval = 59 * hashval + self.draw_pile.__hash__
        hashval = 59 * hashval + self.discard_pile.__hash__
        return hashval

    def __eq__(self, other):
        """
        Compares deck equality
        :param other: other deck to check
        :return: equal or not
        """
        if other is None:
            return False
        if type(self) != type(other):
            return False
        if self.draw_pile != other.draw_pile:
            return False
        if self.discard_pile != other.discard_pile:
            return False
        return True

    def __ne__(self, other):
        """
        Compares deck inequality
        :param other: other deck to check
        :return: unequal or not
        """
        return not self.__eq__(other)
