//Code by Asher Lilly
//For CTE Software Development 1
//Instructor Kim Gross

import java.util.Collections;

public class Deck {
    private ArrayList<Card> cards;

    public Deck() {
        this.cards = new ArrayList<Card>(); // Initialize the ArrayList
    }

    public void createDeck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        String[] wilds = {"Joker"};

        for(String suit : suits) {
            for(String value : values) {
                this.cards.add(new Card(suit, value));
            }
        }

        // Adding Jokers to the deck
        for(String wild : wilds) {
            this.cards.add(new Card(null, wild)); // Assuming the Joker doesn't need a suit
        }
    }

    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    public Card drawCard() {
        if (!this.cards.isEmpty()) {
            return this.cards.remove(0);
        } else {
            // Handle the case where there are no cards left to draw
            return null; // You could throw an exception here instead, but I chose not to.
        }
    }
}