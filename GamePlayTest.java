import java.util.Scanner;
import java.util.ArrayList;

public class GamePlayTest {
	Scanner sc = new Scanner(System.in);
	Deck deck =new Deck();
	Player bot1 = new Player();
	Player bot2 = new Player();
	Player player = new Player();

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
			player.player();
			bot1.RegularBot();
			bot2.RegularBot();
		}
	}
}
