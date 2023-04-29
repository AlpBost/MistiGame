import java.util.ArrayList;
import java.util.Random;

public class Bots {
	Deck deck = new Deck();
	Random rd =new Random();
	protected ArrayList<Card> Bot1TakenCards = new ArrayList<Card>();
	protected ArrayList<Card> Bot2TakenCards= new ArrayList<Card>();
	int h=0;
	public void NoviceBot() {}
	
	public void RegularBot() {
		for (int i=0;i<deck.getRegularHand().size();i++) {
			if (h == 0) {
				if(deck.getTableCards().get(deck.getTableCards().size()-1).getValue().equals(deck.getRegularHand().get(i).getValue())) {
					System.out.println("Bot 1 took all cards");
					Bot1TakenCards.addAll(deck.getTableCards());
					deck.getTableCards().clear();
					deck.getRegularHand().remove(i);
					h++;
				}
			}
		}
	
			
			 if (h==0) {
				int played = rd.nextInt(deck.getRegularHand().size());
				deck.getTableCards().add(deck.getRegularHand().get(played));
				System.out.println("Bot 1 played: " + deck.getRegularHand().get(played));
				deck.getRegularHand().remove(played);
				h++;
			 }
	}
	
	
	public void ExpertBot() {}
	
}
