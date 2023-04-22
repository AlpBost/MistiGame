import java.util.random.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
    private ArrayList<Card> deck;

    public Deck() {
        // how to create a deck using an arraylist
        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10",
                "J", "Q", "K", "A"};
        String[] suits = {"maca", "sinek", "kupa", "karo"}; /*"♠", "♣", "♥", "♦"*/

        // Creating an empty arraylist of Card objects.
        deck = new ArrayList<Card>();

        // Populate the arraylist with Card objects.
        for (int count = 0; count < 52; count++)
            deck.add(new Card(values[count % 13], suits[count / 13]));
        
        

        // Shuffling the deck with the Java Collection shuffle method
        Collections.shuffle(deck);
       
    }

        // cut method
       public void Cut() {
        	System.out.println("------------------------");

        	Random p=new Random();
        	int cut=p.nextInt(deck.size());
        	
        	//create an empty arraylists to seperate the deck
    		ArrayList<Card> top= new ArrayList<Card>();
    		ArrayList<Card> bottom= new ArrayList<Card>();
    		
    		//Filling the arraylists
    		for (int i = 0; i < cut; i++) {
    			top.add(deck.get(i));
    		}
    		for (int i = cut; i < deck.size(); i++) {
    			bottom.add(deck.get(i));
    		}
    		
    	    deck.clear();
    		deck.addAll(bottom);
    	    deck.addAll(top);
    	    
    	    
    		
    	
        // a loop to test the code
        for (int i = 0; i < deck.size(); i++) 
            System.out.println(deck.get(i));
    }

}