import java.util.ArrayList;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Player {
	Deck deck = new Deck();
	Random random =new Random();
	Scanner scanner = new Scanner(System.in);
	protected ArrayList<Card> NoviceTakenCards = new ArrayList<Card>();
	protected ArrayList<Card> RegularTakenCards = new ArrayList<Card>();
	protected ArrayList<Card> ExpertTakenCards = new ArrayList<Card>();
	protected ArrayList<Card> PlayerTakenCards= new ArrayList<Card>();
	private ArrayList<Integer> PlayedCards=new ArrayList<Integer>();
	


	public void player() {

		//System.out.println("Enter your name: ");
		//String playerName = scanner.nextLine();

		System.out.println("Which card you want to play?");
		int index = scanner.nextInt();

		// exception handling for index input
        /* do {
             try {
                if (index > deck.getPlayerHand().size() - 1)
                    throw new IndexOutOfBoundsException();
                else
                    break;
            } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                System.out.println("Enter a new index.");
            }
        } while (true); */

		Card playerCard = deck.getPlayerHand().get(index);

		switch (deck.getTableCards().size()) {
			case 0:
				deck.getTableCards().add(playerCard);
				deck.getPlayerHand().remove(playerCard);
				System.out.println("Cards that are on the table are: ");
				System.out.println(String.valueOf(deck.getTableCards()));
				break;

			case 1:
				if (playerCard.getValue().equals(deck.getTableCards().get(deck.getTableCards().size() - 1).getValue())) {
					deck.getTableCards().add(playerCard);
					deck.getPlayerHand().remove(playerCard);
					System.out.println("Mişti!");
					deck.getTableCards().clear();

				} else if (playerCard.getValue() == "J") {
					deck.getTableCards().add(playerCard);
					deck.getPlayerHand().remove(playerCard);
					System.out.println("You take the cards!");
					deck.getTableCards().clear();

				} else {
					deck.getTableCards().add(playerCard);
					deck.getPlayerHand().remove(playerCard);
					System.out.println("Cards that are on the table are : ");
					System.out.println(String.valueOf(deck.getTableCards()));

				}
				break;

			default:
				if (playerCard.getValue().equals(deck.getTableCards().get(deck.getTableCards().size() - 1))
						|| playerCard.getValue() == "J") {
					//deck.getTableCards().add(playerCard);
					deck.getPlayerHand().remove(playerCard);
					System.out.println("You take the cards!");
					deck.getTableCards().clear();

				} else {
					deck.getTableCards().add(playerCard);
					deck.getPlayerHand().remove(playerCard);
					System.out.println("Cards that are on the table are : ");
					System.out.println(String.valueOf(deck.getTableCards()));

				}

				System.out.println("Game continues!");
				System.out.println(deck.getTableCards());

				
		}
	}


	public void NoviceBot() {
		//System.out.println("Enter the name of the bot: ");
		//String botName = scanner.nextLine();

		int randomIndex = random.nextInt(deck.getNoviceHand().size() - 1);
		Card botCard = deck.getNoviceHand().get(randomIndex);

		switch (deck.getTableCards().size()) {
			case 0:
				System.out.println("Bot played: " + botCard);
				deck.getTableCards().add(botCard);
				deck.getNoviceHand().remove(botCard);
				System.out.println("Cards that are on the table are : ");
				System.out.println(String.valueOf(deck.getTableCards()));

				break;

			case 1:
				System.out.println("Bot played: " + botCard);
				if (botCard.getValue().equals(deck.getTableCards().get(deck.getTableCards().size() - 1).getValue())) {
					deck.getTableCards().add(botCard);
					deck.getNoviceHand().remove(botCard);
					System.out.println("Mişti!");
					deck.getTableCards().clear();

				} else if (botCard.getValue() == "J") {
					deck.getTableCards().add(botCard);
					deck.getNoviceHand().remove(botCard);
					System.out.println("Bot takes the cards!");
					deck.getTableCards().clear();

				} else {
					deck.getTableCards().add(botCard);
					deck.getNoviceHand().remove(botCard);
					System.out.println("Cards that are on the table are: ");
					System.out.println(String.valueOf(deck.getTableCards()));
				}
				break;

			default:
				System.out.println("Bot played:" + botCard);
				if (botCard.getValue().equals(deck.getTableCards().get(deck.getTableCards().size() - 1).getValue())
						|| botCard.getValue() == "J") {
					deck.getTableCards().add(botCard);
					deck.getNoviceHand().remove(botCard);
					System.out.println("Bot takes the cards!");
					deck.getTableCards().clear();

				} else {
					deck.getTableCards().add(botCard);
					deck.getNoviceHand().remove(botCard);
					System.out.println("Cards that are on the table are: ");
					System.out.println(String.valueOf(deck.getTableCards()));

				}

				
		}
	}

	public void RegularBot() {
		//System.out.println("Enter the name of the bot: ");
		//String botName = scanner.nextLine();

		switch(deck.getTableCards().size()){
			case 0:
				int randomIndex = random.nextInt(deck.getRegularHand().size());
				Card botCard = deck.getRegularHand().get(randomIndex);
				System.out.println("Bot played: " + botCard);
				deck.getTableCards().add(botCard);
				deck.getRegularHand().remove(botCard);
				System.out.println("Cards that are on the table are: ");
				System.out.println(String.valueOf(deck.getTableCards()));
				break;

			case 1:
				for (int i = 0 ; i < deck.getRegularHand().size() ; i++) {
					if (deck.getRegularHand().get(i).getValue() == deck.getTableCards().get(deck.getTableCards().size() - 1).getValue()) {
						deck.getTableCards().add(deck.getRegularHand().get(i));
						deck.getRegularHand().remove(deck.getRegularHand().get(i));
						System.out.println("Mişti!");
						deck.getTableCards().clear();
						break;

					} else if (deck.getRegularHand().get(i).getValue() == "J") {
						deck.getTableCards().add(deck.getRegularHand().get(i));
						deck.getRegularHand().remove(deck.getRegularHand().get(i));
						System.out.println("Bot takes the cards!");
						deck.getTableCards().clear();
						break;

					} else {
						deck.getTableCards().add(deck.getRegularHand().get(i));
						deck.getRegularHand().remove(deck.getRegularHand().get(i));
						System.out.println("Cards that are on the table are: ");
						System.out.println(String.valueOf(deck.getTableCards()));

						break;
					}	
				}

			default:
				for (int i = 0 ; i < deck.getRegularHand().size() ; i++) {
					if (deck.getRegularHand().get(i).getValue() == deck.getTableCards().get(deck.getTableCards().size() - 1).getValue()
							|| deck.getRegularHand().get(i).getValue() == "J") {
						deck.getTableCards().add(deck.getRegularHand().get(i));
						deck.getRegularHand().remove(deck.getRegularHand().get(i));
						System.out.println("Bot takes the cards!");
						deck.getTableCards().clear();
						break;
					} else {
						deck.getTableCards().add(deck.getRegularHand().get(i));
						deck.getRegularHand().remove(deck.getRegularHand().get(i));
						System.out.println("Cards that are on the table are: ");
						System.out.println(String.valueOf(deck.getTableCards()));

						break;
					}
				}
				
		}
	}

	public void ExpertBot() {
	    
	   
		int[] Cards = new int[13];
		
		for (int i = 0; i < PlayedCards.size(); i++) {
		    int Value = PlayedCards.get(i);
		    Cards[Value - 1]++;
		}
		
		int MostRepeatedValue= 0;
		for (int i = 0; i < Cards.length; i++) {
		    if (Cards[i] > MostRepeatedValue) {
		    	MostRepeatedValue = Cards[i];
		    	MostRepeatedValue = i + 1;
		    }
		}

		/*System.out.println("Cards:");
		for (int i = 0; i < Cards.length; i++) {
		    System.out.println((i + 1) + ": " + Cards[i]);
		}*/
		System.out.println("Value of the most repeated number: " + MostRepeatedValue);
		
		
		
		switch(deck.getTableCards().size()){
		case 0:
			for (int i = 0 ; i < deck.getExpertHand().size() ; i++) {
				if(deck.getExpertHand().get(i).getValue()==String.valueOf(MostRepeatedValue)) {
					deck.getTableCards().add(deck.getExpertHand().get(i));
					deck.getExpertHand().remove(deck.getExpertHand().get(i));
					System.out.println("Cards that are on the table are: ");
					System.out.println(String.valueOf(deck.getTableCards()));
					break;

				}
			}
		
		case 1:
			for (int i = 0 ; i < deck.getExpertHand().size() ; i++) {
				if (deck.getExpertHand().get(i).getValue() == deck.getTableCards().get(deck.getTableCards().size() - 1).getValue()) {
					deck.getTableCards().add(deck.getExpertHand().get(i));
					deck.getExpertHand().remove(deck.getExpertHand().get(i));
					System.out.println("Mişti!");
					deck.getTableCards().clear();
					break;

				} else if (deck.getExpertHand().get(i).getValue() == "J") {
					deck.getTableCards().add(deck.getExpertHand().get(i));
					deck.getExpertHand().remove(deck.getExpertHand().get(i));
					System.out.println("Bot takes the cards!");

					deck.getTableCards().clear();
					break;

				} else {
					
					
						if(deck.getExpertHand().get(i).getValue()==String.valueOf(MostRepeatedValue)) {
							deck.getTableCards().add(deck.getExpertHand().get(i));
							deck.getExpertHand().remove(deck.getExpertHand().get(i));
							System.out.println("Cards that are on the table are: ");
							System.out.println(String.valueOf(deck.getTableCards()));
							break;
						}
				}
			}
					
			        
				

		default:
			
	       
			for (int i = 0 ; i < deck.getExpertHand().size() ; i++) {
				if(deck.getTableCards().size()>0) {
					if (deck.getExpertHand().get(i).getValue().equals(deck.getTableCards().get(deck.getTableCards().size() - 1).getValue()) || deck.getExpertHand().get(i).getValue().equals("J")) {
					deck.getTableCards().add(deck.getExpertHand().get(i));
					deck.getExpertHand().remove(deck.getExpertHand().get(i));
					System.out.println("Bot takes the cards!");

					deck.getTableCards().clear();
					break;
				}
				
				} else {
					if(deck.getExpertHand().get(i).getValue()==String.valueOf(MostRepeatedValue)) {
					deck.getTableCards().add(deck.getExpertHand().get(i));
					deck.getExpertHand().remove(deck.getExpertHand().get(i));
					System.out.println("Cards that are on the table are: ");
					System.out.println(String.valueOf(deck.getTableCards()));
					}

					break;
				}
			}
			
		}
	}
	
}
    



