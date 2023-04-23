//import java.util.random.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Deck {
	  private ArrayList<Card> deck;
	  private ArrayList<Card> PlayerHand;
	  private ArrayList<Card> P2Hand;
	  private ArrayList<Card> P3Hand;
	  private ArrayList<Card> P4Hand;
	  private ArrayList<Card> TableCards;

	    public Deck() {
	        // how to create a deck using an arraylist
	        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
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
	    	    /*for (int i = 0; i < deck.size(); i++) 
	                System.out.println(deck.get(i));*/
	    }
	       
	       
	       
  //Deal method
   public void Deal() {
	  PlayerHand= new ArrayList<Card>();
	  P2Hand= new ArrayList<Card>();
	  P3Hand= new ArrayList<Card>();
	  P4Hand= new ArrayList<Card>();
	  TableCards= new ArrayList<Card>();

	  Scanner sc= new Scanner(System.in);
	  System.out.print("Number of players: ");
	  int players = sc.nextInt();
	
	  while (players<2 || players>4) { System.out.print("Enter again: ");players = sc.nextInt(); }
	  
	  if (players==2) {
		  for(int i=0;i<4;i++) {
			  PlayerHand.add(deck.get(i));  deck.remove(i); 
			  P2Hand.add(deck.get(i));  deck.remove(i);
		  }
 	  }
	  if (players==3) {
		  for(int i=0;i<4;i++) {
			  PlayerHand.add(deck.get(i));  deck.remove(i);
			  P2Hand.add(deck.get(i));  deck.remove(i);
			  P3Hand.add(deck.get(i));  deck.remove(i);
		  }
	  }
	  if (players==4) {
		  for(int i=0;i<4;i++) {
			  PlayerHand.add(deck.get(i));  deck.remove(i);
			  P2Hand.add(deck.get(i));  deck.remove(i);
			  P3Hand.add(deck.get(i));  deck.remove(i);
			  P4Hand.add(deck.get(i));  deck.remove(i);
		  }
	  }
	  
	  //Table Cards
	  for(int i=0;i<4;i++) { TableCards.add(deck.get(i)); deck.remove(i); }
	  System.out.println("Top Card: " + PlayerHand.get(3));
	
	  //Player Cards
	  System.out.println("Your Cards: " + PlayerHand);
   }
}
