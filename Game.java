import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public interface Game {
    ArrayList<Integer> chooseLevel();
    void game();
    int calculateScore(Player player);
}
class twoPlayers implements Game {
    private final HumanPlayer humanPlayer = new HumanPlayer();
    Deck deck = new Deck();
    Board board = new Board();

    @Override
    public ArrayList<Integer> chooseLevel() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> level = new ArrayList<>();

        while (true) {
            try {
                System.out.println("\n1 - Novice\n2 - Regular\n3 - Expert");
                System.out.println("Enter the level of the bot: ");
                int selectedLevel = scanner.nextInt();

                if (selectedLevel < 1 || selectedLevel > 3) {
                    throw new IllegalArgumentException(" Please enter a number between 1 and 3.");
                }

                level.add(selectedLevel);
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        return level;
    }

    @Override
    public void game() {
        // Two player game algorithm.
        //Novice
        NoviceBot noviceBot = new NoviceBot();
        // Regular
        RegularBot regularBot = new RegularBot();
        // Expert
        ExpertBot expertBot = new ExpertBot();

        ArrayList<Integer> level = chooseLevel();
        ArrayList<Player> playerList = new ArrayList<>();

        if (level.get(0) == 1) playerList.add(noviceBot);
        if (level.get(0) == 2) playerList.add(regularBot);
        if (level.get(0) == 3) playerList.add(expertBot);

        String playerName = humanPlayer.getName();
        String botName = playerList.get(0).getName();

        Board.getBoard().addAll(deck.deal());
        Player.cardsPlayed.addAll(Board.getBoard());

        System.out.printf("%nCards that are on the board are:%n%s%n", Board.getBoard());

        for (int i = 0 ; i < 6 ;i++) {
            humanPlayer.getHand().addAll(deck.deal());
            playerList.get(0).getHand().addAll(deck.deal());

            for (int j = 0 ; j < 4 ; j++) {
                System.out.printf("\nYour cards are:%n%s%n", humanPlayer.getHand());
                humanPlayer.playCard();
                playerList.get(0).playCard();
            }
        }

        int playerScore = calculateScore(humanPlayer);
        System.out.printf("Your score is: %d%n", playerScore);

        int botScore = calculateScore(playerList.get(0));
        System.out.printf("%s's score is: %d\n", botName, botScore);

        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the score list file path as following format: fileName.txt");
            String fileName = scanner.nextLine();

            BufferedReader reader = new BufferedReader(new FileReader(fileName));
           // BufferedReader reader = new BufferedReader(new FileReader("scores.txt"));

            ArrayList<String> highScores = new ArrayList<>();

            String line;
            while ((line = reader.readLine()) != null) {
                highScores.add(line);
            }
            reader.close();

            highScores.add(playerName + ", " + playerScore);
            highScores.add(botName + ", " + botScore);

            highScores.sort((s1, s2) -> {
                int score1 = Integer.parseInt(s1.split(", ")[1]);
                int score2 = Integer.parseInt(s2.split(", ")[1]);
                return Integer.compare(score2, score1);
            });

            if (highScores.size() > 10) {
                highScores.subList(10, highScores.size()).clear();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
           // BufferedWriter writer = new BufferedWriter(new FileWriter("scores.txt"));
            for (String score : highScores) {
                writer.write(score + "\n");
            }
            writer.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public int calculateScore(Player player) {
        int score = 0;
        for (Card card : player.getCardsTaken()) {
            if (player.getMistiCards() == null)
                score += card.getPoint();
            else
                score += card.getPoint() + (player.getMistiCards().size() * 10);
        }
        return score;
    }
}


class threePlayers implements Game {
    private final HumanPlayer humanPlayer = new HumanPlayer();
    Deck deck = new Deck();
    Board board = new Board();

    @Override
    public ArrayList<Integer> chooseLevel() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> level = new ArrayList<>();

        while (true) {
            try {
                System.out.println("\n1 - Novice\n2 - Regular\n3 - Expert");
                
                 for (int i = 0; i < 2; i++) {
		            System.out.printf("Enter the level of the %s bot: ", i == 0 ? "first" : "second");
		            int selectedLevel = scanner.nextInt();
		            level.add(selectedLevel); 
		            if (selectedLevel < 1 || selectedLevel > 3) {
		            	throw new IllegalArgumentException(" Please enter a number between 1 and 3.");
		            }
		        }
                
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return level;
    }

    @Override
    public void game() {
        // three player game algorithm.
        //Novice
        NoviceBot noviceBot1 = new NoviceBot();
        NoviceBot noviceBot2 = new NoviceBot();
        // Regular
        RegularBot regularBot1 = new RegularBot();
        RegularBot regularBot2 = new RegularBot();
        // Expert
        ExpertBot expertBot1 = new ExpertBot();
        ExpertBot expertBot2 = new ExpertBot();

        ArrayList<Integer> level = chooseLevel();
        ArrayList<Player> playerList = new ArrayList<>();

        if (level.get(0) == 1) playerList.add(noviceBot1);
        if (level.get(1) == 1) playerList.add(noviceBot2);
        if (level.get(0) == 2) playerList.add(regularBot1);
        if (level.get(1) == 2) playerList.add(regularBot2);
        if (level.get(0) == 3) playerList.add(expertBot1);
        if (level.get(1) == 3) playerList.add(expertBot2);

        String playerName = humanPlayer.getName();
        String[] botNames = new String[playerList.size()];
        for (int i = 0 ; i < playerList.size() ; i++) {
            botNames[i] = playerList.get(i).getName();
        }

        Board.getBoard().addAll(deck.deal());
        Player.cardsPlayed.addAll(Board.getBoard());

        System.out.printf("%nCards that are on the board are:%n%s%n", Board.getBoard());

        for (int i = 0 ; i < 4 ;i++) {
            humanPlayer.getHand().addAll(deck.deal());
            playerList.get(0).getHand().addAll(deck.deal());
            playerList.get(1).getHand().addAll(deck.deal());

            for (int j = 0 ; j < 4 ; j++) {
                System.out.printf("\nYour cards are:%n%s%n", humanPlayer.getHand());

                humanPlayer.playCard();
                playerList.get(0).playCard();
                playerList.get(1).playCard();
            }
        }

        int playerScore = calculateScore(humanPlayer);
        System.out.printf("Your score is: %d%n", playerScore);

        for (int i = 0 ; i < playerList.size() ; i++) {
            int botScore = calculateScore(playerList.get(i));
            System.out.printf("%s's score is: %d\n", botNames[i], botScore);
        }

        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the score list file path as following format: fileName.txt");
            String fileName = scanner.nextLine();

            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            // BufferedReader reader = new BufferedReader(new FileReader("scores.txt"));

            ArrayList<String> highScores = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                highScores.add(line);
            }
            reader.close();

            highScores.add(playerName + ", " + playerScore);
            for (int i = 0; i < playerList.size(); i++) {
                int botScore = calculateScore(playerList.get(i));
                highScores.add(botNames[i] + ", " + botScore);
            }

            highScores.sort((s1, s2) -> {
                int score1 = Integer.parseInt(s1.split(", ")[1]);
                int score2 = Integer.parseInt(s2.split(", ")[1]);
                return Integer.compare(score2, score1);
            });

            if (highScores.size() > 10) {
                highScores.subList(10, highScores.size()).clear();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            for (String score : highScores) {
                writer.write(score + "\n");
            }
            writer.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public int calculateScore(Player player) {
        int score = 0;
        for (Card card : player.getCardsTaken()) {
            if (player.getMistiCards() == null)
                score += card.getPoint();
            else
                score += card.getPoint() + (player.getMistiCards().size() * 10);
        }
        return score;
    }
}

class fourPlayers implements Game {
    private final HumanPlayer humanPlayer = new HumanPlayer();
    Deck deck = new Deck();
    Board board = new Board();

    @Override
    public ArrayList<Integer> chooseLevel() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> level = new ArrayList<>();

 
        while (true) {
            try {
                System.out.println("\n1 - Novice\n2 - Regular\n3 - Expert");
                
                 for (int i = 0; i < 3; i++) {
		            System.out.printf("Enter the level of the %s bot: ", i == 0 ? "first" : (i == 1 ? "second" : "third"));
		            int selectedLevel = scanner.nextInt();
		            level.add(selectedLevel); 
		            if (selectedLevel < 1 || selectedLevel > 3) {
		            	throw new IllegalArgumentException(" Please enter a number between 1 and 3.");
		            }
		        }
                
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return level;
    }

    @Override
    public void game() {
        // four player game algorithm.
        //Novice
        NoviceBot noviceBot1 = new NoviceBot();
        NoviceBot noviceBot2 = new NoviceBot();
        NoviceBot noviceBot3 = new NoviceBot();
        // Regular
        RegularBot regularBot1 = new RegularBot();
        RegularBot regularBot2 = new RegularBot();
        RegularBot regularBot3 = new RegularBot();
        // Expert
        ExpertBot expertBot1 = new ExpertBot();
        ExpertBot expertBot2 = new ExpertBot();
        ExpertBot expertBot3 = new ExpertBot();

        ArrayList<Integer> level = chooseLevel();
        ArrayList<Player> playerList = new ArrayList<>();

        if (level.get(0) == 1) playerList.add(noviceBot1);
        if (level.get(1) == 1) playerList.add(noviceBot2);
        if (level.get(2) == 1) playerList.add(noviceBot3);
        if (level.get(0) == 2) playerList.add(regularBot1);
        if (level.get(1) == 2) playerList.add(regularBot2);
        if (level.get(2) == 2) playerList.add(regularBot3);
        if (level.get(0) == 3) playerList.add(expertBot1);
        if (level.get(1) == 3) playerList.add(expertBot2);
        if (level.get(2) == 3) playerList.add(expertBot3);

        String playerName = humanPlayer.getName();
        String[] botNames = new String[playerList.size()];
        for (int i = 0 ; i < playerList.size() ; i++) {
            botNames[i] = playerList.get(i).getName();
        }

        Board.getBoard().addAll(deck.deal());
        Player.cardsPlayed.addAll(Board.getBoard());

        System.out.printf("%nCards that are on the board are:%n%s%n", Board.getBoard());

        for (int i = 0 ; i < 3 ;i++) {
            humanPlayer.getHand().addAll(deck.deal());
            playerList.get(0).getHand().addAll(deck.deal());
            playerList.get(1).getHand().addAll(deck.deal());
            playerList.get(2).getHand().addAll(deck.deal());

            for (int j = 0 ; j < 4 ; j++) {
                System.out.printf("\nYour cards are:%n%s%n", humanPlayer.getHand());

                humanPlayer.playCard();
                playerList.get(0).playCard();
                playerList.get(1).playCard();
                playerList.get(2).playCard();
            }
        }

        int playerScore = calculateScore(humanPlayer);
        System.out.printf("Your score is: %d%n", playerScore);

        for (int i = 0 ; i < playerList.size() ; i++) {
            int botScore = calculateScore(playerList.get(i));
            System.out.printf("%s's score is: %d\n", botNames[i], botScore);
        }

        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the score list file path as following format: fileName.txt");
            String fileName = scanner.nextLine();

            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            // BufferedReader reader = new BufferedReader(new FileReader("scores.txt"));

            ArrayList<String> highScores = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                highScores.add(line);
            }
            reader.close();

            highScores.add(playerName + ", " + playerScore);
            for (int i = 0; i < playerList.size(); i++) {
                int botScore = calculateScore(playerList.get(i));
                highScores.add(botNames[i] + ", " + botScore);
            }

            highScores.sort((s1, s2) -> {
                int score1 = Integer.parseInt(s1.split(", ")[1]);
                int score2 = Integer.parseInt(s2.split(", ")[1]);
                return Integer.compare(score2, score1);
            });

            if (highScores.size() > 10) {
                highScores.subList(10, highScores.size()).clear();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            for (String score : highScores) {
                writer.write(score + "\n");
            }
            writer.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public int calculateScore(Player player) {
        int score = 0;
        for (Card card : player.getCardsTaken()) {
            if (player.getMistiCards() == null)
                score += card.getPoint();
            else
                score += card.getPoint() + (player.getMistiCards().size() * 10);
        }
        return score;
    }
}