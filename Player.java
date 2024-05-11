//Code by Asher Lilly
//For CTE Software Development 1
//Instructor Kim Gross

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Player { // This is my player class
    private ArrayList<Card> hand;
    private int score;
     private int money; // The money you start with
 
     public Player(int money) {
         this.hand = new ArrayList<Card>();
         this.money = money;
     }
 
     public void draw(Deck deck) {
         this.hand.add(deck.drawCard()); // Drawing cards into your hand
     }
 
     public void discard(int index) {
         this.hand.remove(index); // Discarding cards from your hand
     }
 
    public ArrayList<Card> findRun() { // Finding runs and putting them together
        Collections.sort(hand, new Comparator<Card>() {
            public int compare(Card c1, Card c2) {
                return Integer.parseInt(c1.getValue()) - Integer.parseInt(c2.getValue());
            }
        });

        ArrayList<Card> run = new ArrayList<>();
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
}
 
 public int calculateScore() { // Adding the scores together
     int score = 0;
     for (Card card : hand) {
         String value = card.getValue();
         if (value.equals("Jack") || value.equals("Queen") || value.equals("King")) {
             score += 10;
         } else if (value.equals("Ace")) {
             score += 20;
         } else if (value.equals("Joker")) {
             score += 100; // Jokers are now worth 100 points
         } else {
             score += Integer.parseInt(value);
             }
         }
     return score;
     }
     public void doublePoints() {
    this.score *= 2; // For when the winner is the player that went out first
    }
