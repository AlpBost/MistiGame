import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public interface Player {
    ArrayList<Card> cardsPlayed = new ArrayList<>();
    String getName();
    Card chooseCard();
    void playCard();
    ArrayList<Card> getHand();
    ArrayList<Card> getCardsTaken();
    ArrayList<Card> getMistiCards();
}

class HumanPlayer implements Player {
    private String name;
    private ArrayList<Card> hand;
    private ArrayList<Card> cardsTaken;
    private ArrayList<Card> mistiCards;

    public HumanPlayer() {
        this.hand = new ArrayList<>();
        this.cardsTaken = new ArrayList<>();
        this.mistiCards = new ArrayList<>();
    }

    @Override
    public String getName() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nEnter your name: ");
        name = scanner.nextLine();
        return name;
    }

    @Override
    public Card chooseCard() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nWhich card you want to play?");
        while (true) {
            try {
                int index = scanner.nextInt();
                return hand.get(index-1);
            } catch (InputMismatchException e) {
                System.out.println(" Please enter a valid number.");
                scanner.next(); 
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid index. Please select a card within the range of your hand.");
            }
        }
    }

    @Override
    public void playCard() {
        Card playerCard = chooseCard();
        ArrayList<Card> board = Board.getBoard();

        System.out.printf("%nYou played %s%n", playerCard);
        cardsPlayed.add(playerCard);
        hand.remove(playerCard);

        switch (board.size()) {
            case 0:
                board.add(playerCard);
                System.out.printf("%nCards that are on the board are:%n%s%n", board);
                break;
            case 1:
                if (playerCard.getFace().equals(board.get(0).getFace())) {
                    System.out.println("Mişti!");
                    board.add(playerCard);
                    mistiCards.addAll(board);
                    board.clear();
                } else if (playerCard.getFace().equals("J")) {
                    System.out.println("You take the cards!");
                    board.add(playerCard);
                    cardsTaken.addAll(board);
                    board.clear();
                } else {
                    board.add(playerCard);
                    System.out.printf("%nCards that are on the board are:%n%s%n", board);
                }
                break;
            default:
                if (playerCard.getFace().equals(board.get(board.size() - 1).getFace())
                        || playerCard.getFace().equals("J")) {
                    System.out.println("You take all the cards!");
                    board.add(playerCard);
                    cardsTaken.addAll(board);
                    board.clear();
                } else {
                    board.add(playerCard);
                    System.out.printf("%nCards that are on the board are:%n%s%n", board);
                }
                break;
        }
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public ArrayList<Card> getCardsTaken() {
        return cardsTaken;
    }

    @Override
    public ArrayList<Card> getMistiCards() {
        return mistiCards;
    }
}

class NoviceBot implements Player {
    private String name;
    private ArrayList<Card> hand;
    private ArrayList<Card> cardsTaken;
    private ArrayList<Card> mistiCards;

    public NoviceBot() {
        this.hand = new ArrayList<>();
        this.cardsTaken = new ArrayList<>();
        this.mistiCards = new ArrayList<>();
    }

    @Override
    public String getName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n" + "Enter the name of the bot: ");
        name = scanner.nextLine();
        return name + "(novice)";
    }

    @Override
    public Card chooseCard() {
        Random random = new Random();
        // Novice bot plays a random card from its hand.
        int randomIndex = random.nextInt(hand.size());
        return hand.get(randomIndex);
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public ArrayList<Card> getCardsTaken() {
        return cardsTaken;
    }

    @Override
    public ArrayList<Card> getMistiCards() {
        return mistiCards;
    }

    @Override
    public void playCard() {
        Card botCard = chooseCard();
        ArrayList<Card> board = Board.getBoard();

        System.out.printf("%n%s played %s%n", name, botCard);
        cardsPlayed.add(botCard);
        hand.remove(botCard);

        switch (board.size()) {
            case 0:
                board.add(botCard);
                System.out.printf("%nCards that are on the board are:%n%s%n", board);
                break;
            case 1:
                if (botCard.getFace().equals(board.get(0).getFace())) {
                    System.out.println("Mişti!");
                    board.add(botCard);
                    cardsTaken.addAll(board);
                    board.clear();
                } else if (botCard.getFace().equals("J")) {
                    System.out.printf("%n%s takes the cards!%n", name);
                    board.add(botCard);
                    cardsTaken.addAll(board);
                    board.clear();
                } else {
                    board.add(botCard);
                    System.out.printf("%nCards that are on the board are:%n%s%n", board);
                }
                break;
            default:
                if (botCard.getFace().equals(board.get(board.size() - 1).getFace())
                        || botCard.getFace().equals("J")) {
                    System.out.printf("%n%s takes all the cards!%n", name);
                    board.add(botCard);
                    cardsTaken.addAll(board);
                    board.clear();
                } else {
                    board.add(botCard);
                    System.out.printf("%nCards that are on the board are:%n%s%n", board);
                }
                break;
        }
    }
}

class RegularBot implements Player {
    private String name;
    private ArrayList<Card> hand;
    private ArrayList<Card> cardsTaken;
    private ArrayList<Card> mistiCards;

    public RegularBot() {
        this.hand = new ArrayList<>();
        this.cardsTaken = new ArrayList<>();
        this.mistiCards = new ArrayList<>();
    }
    @Override
    public String getName() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n" + "Enter the name of the bot: ");
        name = scanner.nextLine();
        return name + "(regular)";
    }

    @Override
    public Card chooseCard() {
        Random random = new Random();
        int randomIndex = random.nextInt(hand.size());

        Card botCard = null;
        for (Card card : hand) {
            if (Board.getBoard().isEmpty()) {
                botCard = hand.get(randomIndex);
                break;
            } else {
                if (card.getFace().equals(Board.getBoard().get(Board.getBoard().size() - 1).getFace())
                        || card.getFace().equals("J")) {
                    botCard = card;
                    break;
                } else {
                    botCard = hand.get(randomIndex);
                }
            }
        }
        return botCard;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }
    public ArrayList<Card> getCardsTaken() {
        return cardsTaken;
    }

    @Override
    public ArrayList<Card> getMistiCards() {
        return mistiCards;
    }

    @Override
    public void playCard() {
        Card botCard = chooseCard();
        ArrayList<Card> board = Board.getBoard();

        System.out.printf("%n%s played %s%n", name, botCard);
        cardsPlayed.add(botCard);
        hand.remove(botCard);

        switch (board.size()) {
            case 0:
                board.add(botCard);
                System.out.printf("%nCards that are on the board are:%n%s%n", board);
                break;
            case 1:
                if (botCard.getFace().equals(board.get(0).getFace())) {
                    System.out.println("Mişti!");
                    board.add(botCard);
                    cardsTaken.addAll(board);
                    board.clear();
                } else if (botCard.getFace().equals("J")) {
                    System.out.printf("%n%s takes the cards!%n", name);
                    board.add(botCard);
                    cardsTaken.addAll(board);
                    board.clear();
                } else {
                    board.add(botCard);
                    System.out.printf("%nCards that are on the board are:%n%s%n", board);
                }
                break;
            default:
                if (botCard.getFace().equals(board.get(board.size() - 1).getFace())
                        || botCard.getFace().equals("J")) {
                    System.out.printf("%n%s takes all the cards!%n", name);
                    board.add(botCard);
                    cardsTaken.addAll(board);
                    board.clear();
                } else {
                    board.add(botCard);
                    System.out.printf("%nCards that are on the board are:%n%s%n", board);
                }
                break;
        }
    }
}

class ExpertBot implements Player {
    private String name;
    private ArrayList<Card> hand;
    private ArrayList<Card> cardsTaken;
    private ArrayList<Card> mistiCards;

    public ExpertBot() {
        this.hand = new ArrayList<>();
        this.cardsTaken = new ArrayList<>();
        this.mistiCards = new ArrayList<>();
    }

    @Override
    public String getName() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n" + "Enter the name of the bot: ");
        name = scanner.nextLine();

        return name + "(expert)";
    }

    @Override
    public Card chooseCard() {
        Random random = new Random();
        int randomIndex = random.nextInt(hand.size());

        // Find the index of the most repeated face value.
        String[] faces = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        int[] Cards = new int[13];
        for (Card card : cardsPlayed) {
            int index = Arrays.asList(faces).indexOf(card.getFace());
            if (index != -1) {
                Cards[index]++;
            }
        }
        int mostRepeatedValue = 0;
        for (int i = 0; i < Cards.length; i++) {
            if (Cards[i] > Cards[mostRepeatedValue]) {
                mostRepeatedValue = i;
            }
        }

        Card botCard = null;
        for (Card card : hand) {
            if (Board.getBoard().isEmpty()) {
                if (card.getFace().equals(faces[mostRepeatedValue])) {
                    botCard = card;
                    break;
                }
            } else {
                if (card.getFace().equals(Board.getBoard().get(Board.getBoard().size() - 1).getFace())
                        || card.getFace().equals("J") || card.getFace().equals(faces[mostRepeatedValue])) {
                    botCard = card;
                    break;
                }
            }
        }

        if (botCard == null) {
            botCard = hand.get(randomIndex);
        }

        return botCard;
    }

    @Override
    public void playCard() {
        Card botCard = chooseCard();
        ArrayList<Card> board = Board.getBoard();

        System.out.printf("%n%s played %s%n", name, botCard);
        cardsPlayed.add(botCard);
        hand.remove(botCard);

        switch (board.size()) {
            case 0:
                board.add(botCard);
                System.out.printf("%nCards that are on the board are:%n%s%n", board);
                break;
            case 1:
                if (botCard.getFace().equals(board.get(0).getFace())) {
                    System.out.println("Mişti!");
                    board.add(botCard);
                    mistiCards.addAll(board);
                    board.clear();
                } else if (botCard.getFace().equals("J")) {
                    System.out.printf("%n%s takes the cards!%n", name);
                    board.add(botCard);
                    cardsTaken.addAll(board);
                    board.clear();
                } else {
                    board.add(botCard);
                    System.out.printf("%nCards that are on the board are:%n%s%n", board);
                }
                break;
            default:
                if (botCard.getFace().equals(board.get(board.size() - 1).getFace()) || botCard.getFace().equals("J")) {
                    System.out.printf("%n%s takes all the cards!%n", name);
                    board.add(botCard);
                    cardsTaken.addAll(board);
                    board.clear();
                } else {
                    board.add(botCard);
                    System.out.printf("%nCards that are on the board are:%n%s%n", board);
                }
                break;
        }
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public ArrayList<Card> getCardsTaken() {
        return cardsTaken;
    }

    @Override
    public ArrayList<Card> getMistiCards() {
        return mistiCards;
    }
}