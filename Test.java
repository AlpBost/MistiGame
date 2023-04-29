import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Deck deck = new Deck();
		GamePlay3 play = new GamePlay3();
		Bots aj = new Bots();
		deck.Cut();
		deck.Deal();
		
		System.out.println("Write first bot level number: ");
		System.out.println("1-Novice ");
		System.out.println("2-Regular ");
		System.out.println("3-Expert");
		int a = sc.nextInt();
		play.setBot1Level(a);
		
		System.out.println("Write first bot level number: ");
		System.out.println("1-Novice ");
		System.out.println("2-Regular ");
		System.out.println("3-Expert");
		int b = sc.nextInt();
		play.setBot2Level(b);
		System.out.println("Deck is cutted");
		System.out.println("Deck is dealed");
		
		deck.Display();
		play.GameStart();
	}
}
