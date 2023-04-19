import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> deck;

    public Deck() {
        // how to create a deck using an arraylist
        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10",
                "J", "Q", "K", "A"};
        String[] suits = {"♠", "♣", "♥", "♦"};

        // Create an empty arraylist of Card objects.
        deck = new ArrayList<Card>();

        // Populate the arraylist with Card objects.
        for (int count = 0; count < 52; count++)
            deck.add(new Card(values[count % 13], suits[count / 13]));

        // Shuffle the deck with the Java Collection shuffle method
        Collections.shuffle(deck);

        // cut method

        // a loop to test the code
        for (int i = 0; i < deck.size(); i++)
            System.out.println(deck.get(i));
    }

}