import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Deck {
	private final ArrayList<Card> deck;

	public Deck() {
		Scanner scanner = new Scanner(System.in);
		deck = new ArrayList<>();

		// Create cards with each possible suit and face.
		String[] suits = {"♠", "♣", "♥", "♦"};
		String[] faces = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
		int point = 1; // default point

		for (String suit : suits) {
			for (String face : faces) {
				Card card = new Card(suit, face, point);
				deck.add(card);
			}
		}

		// Assign points to the cards from the pointFile.
		try {
			System.out.println("Enter the path of the point file as following format: fileName.txt");
			BufferedReader reader = new BufferedReader(new FileReader(scanner.nextLine()));

			String line;
			while ((line = reader.readLine()) != null) {
				// Split the line into an array of Strings
				String[] parts = line.split(" ");

				String face = parts[0];
				point = Integer.parseInt(parts[1]);

				Card card = new Card("", face, point);
				for (int i = 0 ; i < deck.size() ; i++) {
					Card tempCard = deck.get(i);
					if(tempCard.getFace().equals(face))
						deck.set(i, card);
				}
			}
			reader.close();
		} catch (IOException ioException) {
			System.err.println("Error reading from file: " + ioException.getMessage());
		}

		Collections.shuffle(deck);

		// Cut the deck
		Random random = new Random();
		int x = random.nextInt(deck.size());

		ArrayList<Card> top = new ArrayList<>();
		ArrayList<Card> bottom = new ArrayList<>();

		for (int i = 0; i < x; i++)
			top.add(deck.get(i));

		for (int i = x; i < deck.size(); i++)
			bottom.add(deck.get(i));

		deck.clear();
		deck.addAll(bottom);
		deck.addAll(top);

		//System.out.println(deck);
	}

	public ArrayList<Card> deal() {
		// Draw 4 cards from the top of the deck
		ArrayList<Card> tempHand = new ArrayList<>();
		for (int i = 0 ; i < 4 ; i++) {
			tempHand.add(deck.get(deck.size() - 1));
			deck.remove(deck.size() - 1);
		}
		return tempHand;
	}
}
