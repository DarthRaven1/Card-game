//Code by Asher Lilly
//For CTE Software Development 1
//Instructor Kim Gross

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Game { // This is my game class
    private Deck deck;
    private ArrayList<Player> players;


    public Game(int numPlayers, int startingMoney) {
        this.deck = new Deck();
        this.deck.createDeck(); // Creating the deck
        this.deck.shuffle();


        this.players = new ArrayList<Player>();
        for (int i = 0; i < numPlayers; i++) {
            this.players.add(new Player(startingMoney));
        }
    }


public void deal(int numCards) { // Checking if the deck has enough cards, so it won't deal the same one to multiple players
    if (numCards < 1 || deck.cards.size() < numCards * players.size()) {
        System.out.println("Not enough cards to deal " + numCards + " to each player.");
        return; // Early exit if there aren't enough cards to deal as requested
    }
    for (Player player : this.players) {
        for (int i = 0; i < numCards; i++) {
            if (deck.cards.isEmpty()) {
                System.out.println("The deck has run out of cards."); // For when the deck is empty
                return; // Stop dealing if the deck is empty
            }
            player.draw(this.deck);
        }
    }
}


    public void play() {
        boolean gameRunning = true;
        while (gameRunning) {
            for (Player player : players) {
                player.draw(deck);
                ArrayList<Card> run = player.findRun();
                if (!run.isEmpty()) {
                    player.playRun(run);
                     System.out.println("Player played a run: " + run); // When the players play runs
                }


                // Allow player to play other combinations or cards


                // Player discards a card
                Card discardedCard = player.chooseCardToDiscard(); // This lets the players choose which card to discard
                player.discard(discardedCard);
                System.out.println("Player discards: " + discardedCard);


                // Check if the game should end
                if (player.hand.isEmpty() || deck.cards.size() == 0) {
                    gameRunning = false;
                    break;
                }
            }
        }


        calculateAndAnnounceWinner();
    }


    private void calculateAndAnnounceWinner() {
        Player winner = null;
        int highestScore = 0;


        for (Player player : players) {
            int score = player.calculateScore();
            System.out.println("Player score: " + score);
            if (score > highestScore) {
                highestScore = score;
                winner = player;
            }
        }


        if (winner != null && winner.hand.isEmpty()) { // Check if the winner went out first
            winner.doublePoints(); // Assuming you have a method to double the points
            System.out.println("Winner's points doubled!");
        }


        System.out.println("Winner is: " + winner);
    }
}


public ArrayList<Card> findRun() {
 Collections.sort(hand, new Comparator<Card>() {
     public int compare(Card c1, Card c2) {
         return c1.getValue().compareTo(c2.getValue());
     }
 });

 ArrayList<Card> run = new ArrayList<Card>();
 for (int i = 0; i < hand.size() - 1; i++) {
     if (hand.get(i).getSuit().equals(hand.get(i + 1).getSuit()) &&
         Integer.parseInt(hand.get(i + 1).getValue()) - Integer.parseInt(hand.get(i).getValue()) == 1) {
         if (run.isEmpty()) {
             run.add(hand.get(i));
         }
         run.add(hand.get(i + 1));
     } else if (!run.isEmpty()) {
         break;
     }
 }
 return run.size() >= 3 ? run : new ArrayList<Card>(); // Assuming a run needs at least 3 cards
}
public void playRun(ArrayList<Card> run) {
 if (!run.isEmpty()) {
     hand.removeAll(run);
     // You might want to add these cards to some "table" or "play area" object that shows which cards are in play
 }
}
public ArrayList<Card> findRun() {
 Collections.sort(hand, new Comparator<Card>() {
     public int compare(Card c1, Card c2) {
         if (!c1.isJoker() && !c2.isJoker()) {
             return c1.getValue().compareTo(c2.getValue());
         }
         return c1.isJoker() ? 1 : -1; // Push jokers to the end of the sorted list
     }
 });

 ArrayList<Card> run = new ArrayList<>();
 int jokerCount = 0;
 for (Card card : hand) {
     if (card.isJoker()) {
         jokerCount++;
     }
 }

 for (int i = 0; i < hand.size() - 1; i++) {
     if (hand.get(i).isJoker()) {
         continue; // Skip jokers in initial run detection
     }
     if (run.isEmpty() || run.get(run.size() - 1).getSuit().equals(hand.get(i).getSuit())) {
         while (i < hand.size() - 1 && (hand.get(i + 1).getValue() - hand.get(i).getValue() > 1) && jokerCount > 0) {
             // Use a joker to fill the gap
             run.add(new Card(hand.get(i).getSuit(), hand.get(i).getValue() + 1)); // Assuming Card constructor can take these arguments
             jokerCount--;

        }
    }
}
}