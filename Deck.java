//import java.util.random.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Deck {
	  
	  static private ArrayList<Card> deck= new ArrayList<Card>();
	  static private ArrayList<Card> PlayerHand= new ArrayList<Card>();;
	  static private ArrayList<Card> NoviceHand= new ArrayList<Card>();;
	  static private ArrayList<Card> RegularHand= new ArrayList<Card>();;
	  static private ArrayList<Card> ExpertHand= new ArrayList<Card>();;
	  static private ArrayList<Card> TableCards= new ArrayList<Card>();;
	  Scanner sc= new Scanner(System.in);
	  
	  //--------------------------------------------SETTERS----------------------------------------
	  public void setDeck(ArrayList<Card> deck) {this.deck=deck;}
	  public void setPlayerHand(ArrayList<Card> PlayerHand) {this.PlayerHand=PlayerHand;}
	  public void setP2Hand(ArrayList<Card> NoviceHand) {this.NoviceHand=NoviceHand;}
	  public void setP3Hand(ArrayList<Card> RegularHand) {this.RegularHand=RegularHand;}
	  public void setP4Hand(ArrayList<Card> expertHand) {this.ExpertHand=expertHand;}
	  public void setTableCard(ArrayList<Card> TableCards) {this.TableCards=TableCards;}
	  
	  //--------------------------------------------GETTERS----------------------------------------
	  
	  public ArrayList<Card> getDeck() {return deck;}
	  public ArrayList<Card> getPlayerHand() {return PlayerHand;}
	  public ArrayList<Card> getNoviceHand() {return NoviceHand;}
	  public ArrayList<Card> getRegularHand() {return RegularHand;}
	  public ArrayList<Card> getExpertHand() {return ExpertHand;}
	  public ArrayList<Card> getTableCards() {return TableCards;}
	  
	  //-----------------------------------------CREATING A DECK------------------------------------
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
	    
	   
	    //---------------------------------------------CUT-----------------------------------------

	       public void Cut() {

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
	    	    
	    }
	       
	     //Deal method
	       public void Deal() {
	    	  System.out.print("Number of players: ");
	    	  int players = sc.nextInt();
	    	
	    	  while (players<2 || players>4) { System.out.print("Enter again: ");players = sc.nextInt(); }
	    	  if (players==2) {
	    		  for(int i=0;i<4;i++) {
	    			  getPlayerHand().add(deck.get(i));  deck.remove(i); 
	    			  NoviceHand.add(deck.get(i));  deck.remove(i);
	    		  }
	     	  }
	    	  if (players==3) {
	    		  for(int i=0;i<4;i++) {
	    			  PlayerHand.add(deck.get(i));  deck.remove(i);
	    			  NoviceHand.add(deck.get(i));  deck.remove(i);
	    			  RegularHand.add(deck.get(i));  deck.remove(i);
	    			  ExpertHand.add(deck.get(i));  deck.remove(i);

	    		  }
	    	  }
	    	  if (players==4) {
	    		  for(int i=0;i<4;i++) {
	    			  getPlayerHand().add(deck.get(i));  deck.remove(i);
	    			  getNoviceHand().add(deck.get(i));  deck.remove(i);
	    			  RegularHand.add(deck.get(i));  deck.remove(i);
	    			  ExpertHand.add(deck.get(i));  deck.remove(i);
	    		  }
	    	  }
	       }
       public void Display() {
    	   //Table Cards
	    	  for(int i=0;i<4;i++) { TableCards.add(deck.get(i)); deck.remove(i); }
	    	  System.out.println("Top Card: " + TableCards.get(3));
	    	
	    	  //Player Cards
	    	  System.out.println("Your Cards: " + PlayerHand);

       }
	
}