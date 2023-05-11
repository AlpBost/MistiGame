import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
	private final ArrayList<Card> deck;

	public Deck() {
		deck = new ArrayList<>();

		// Read from the file to populate the deck with Card objects
		try {
			BufferedReader reader = new BufferedReader(new FileReader("pointFile.txt"));

			String line;
			while ((line = reader.readLine()) != null) {
				// Split the line into an array of Strings
				String[] parts = line.split(" ");

				// Extract the suit, face, and point from the parts
				String suit = parts[0].substring(0, 1);
				String face = parts[0].substring(1);
				int point = Integer.parseInt(parts[1]);

				Card card = new Card(suit, face, point);
				deck.add(card);
			}
			reader.close();
		} catch (IOException e) {
			System.err.println("Error reading from file: " + e.getMessage());
		}

		Collections.shuffle(deck); // Shuffle the deck

		// Cut the deck
		Random random = new Random(); // a Random object to generate a random place to cut
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
