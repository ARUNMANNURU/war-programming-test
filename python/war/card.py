"""
Knexus Python Programming test

Author: JT
"""


class Card(object):
    def __init__(self, name, suit, ordinal):
        """
        Card object
        :param name: The name of this card. (2-10, Jack, Queen, King, Ace)
        :param suit: The suit of this card (Club, Diamond, Heart, Spade)
        :param ordinal: A numeric representation of the value of this card.
                        2-10 for normal cards, Face cards: Jack=11, Queen=12, King=13, Ace=14
        """
        self.name = name
        self.suit = suit
        self.ordinal = ordinal

    def get_name(self):
        """
        String name of card
        :return: String name of card
        """
        return self.name

    def get_suit(self):
        """
        Suit enum value of this card
        :return: Suit enum value of this card
        """
        return self.suit

    def get_ordinal(self):
        """
        Integer value representation of this card
        :return: Integer value representation of this card
        """
        return self.ordinal

    def __str__(self):
        """
        Pretty prints this card as 'NAME of SUITs' (5 of Clubs, Ace of Spaces, ...)
        :return: Pretty printed representation of this card
        """
        return "%s of %s" %(self.name, self.suit)

    def __hash__(self):
        """
        Generated hashCode method.
        :return: Hashed representation of a deck
        """
        cardhash = 5
        cardhash = 31 * cardhash + self.name.__hash__
        cardhash = 31 * cardhash + self.suit.__hash__
        cardhash = 31 * cardhash + self.ordinal
        return cardhash

    def __eq__(self, other):
        """
        Generated equals method.
        :param other: Object to compare to this
        :return: True if Equal, False else
        """
        if other is None:
            return False
        if type(self) != type(other):
            return False
        if self.name != other.name:
            return False
        if self.suit != other.suit:
            return False
        if self.ordinal != other.ordinal:
            return False
        return True

    def __ne__(self, other):
        """
        Negation of equals method
        :param other: Object to compare to this
        :return: True if not equal, false else
        """
        return not self.__eq__(other)
