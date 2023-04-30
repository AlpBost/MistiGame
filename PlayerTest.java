import java.util.Scanner;

public class PlayerTest {
		Deck deck = new Deck();
		Scanner sc = new Scanner (System.in);
		public void PlayersTurn() {
			System.out.println("Choose card number to play");
		int rplayed = sc.nextInt();
		if(deck.getTableCards().get(deck.getTableCards().size()-1).equals(deck.getPlayerHand().get(rplayed-1)))	{
			System.out.println("You took all cards!");
			deck.getPlayerHand().remove(rplayed-1);
		}
		deck.getTableCards().add(deck.getPlayerHand().get(rplayed-1));
		System.out.println("Top Card: " + deck.getPlayerHand().get(rplayed-1));
		}
		
}
