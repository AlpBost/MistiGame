import java.util.Scanner;
import java.util.ArrayList;

public class GamePlay3 {
	Scanner sc = new Scanner(System.in);		
	Deck deck =new Deck();
	Bots bot1 = new Bots();
	Bots bot2 = new Bots();
	Player player = new Player();
	
	protected ArrayList<Card> PlayerTakenCards;
	
	private int Bot1Level;
	private int Bot2Level;

	public int getBot1Level() {
		return Bot1Level;
	}
	public void setBot1Level(int bot1Level) {
		Bot1Level = bot1Level;
	}
	public int getBot2Level() {
		return Bot2Level;
	}
	public void setBot2Level(int bot2Level) {
		Bot2Level = bot2Level;
	}
	
	Deck Play3 = new Deck();
	
	
	public void GameStart() {
		for(int i=0;i<4;i++) {
			player.PlayersTurn();
			bot1.RegularBot();
			bot2.RegularBot();
		}
		
	}
}
