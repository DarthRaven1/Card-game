//Code by Asher Lilly
//For CTE Software Development 1
//Instructor Kim Gross

public class Card { //this is my card class
    private String suit;
    private String value;

    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public String getValue() {
        return value;
    }
}