import java.io.*;
import java.util.*;

public interface Game {
    int howManyRounds();
    ArrayList<Integer> chooseLevel();
    void game();
    int calculateScore(Player player);
}

class twoPlayers implements Game {
    private final HumanPlayer humanPlayer = new HumanPlayer();
    Deck deck = new Deck();
    Board board = new Board();

    @Override
    public int howManyRounds() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n" + "How many rounds you want to play?");
        return scanner.nextInt();
    }

    @Override
    public ArrayList<Integer> chooseLevel() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> level = new ArrayList<>();

        System.out.println("\n1 - Novice\n2 - Regular\n3 - Expert");
        System.out.println("Enter the level of the bot: ");
        level.add(scanner.nextInt());

        return level;
    }

    @Override
    public void game() {
        ArrayList<Integer> level = chooseLevel();

        List<Player> bots = Arrays.asList(new NoviceBot(), new RegularBot(), new ExpertBot());
        Player bot = bots.get(level.get(0) - 1);

        String playerName = humanPlayer.getName();
        String botName = bot.getName();

        Board.getBoard().addAll(deck.deal());
        Player.cardsPlayed.addAll(Board.getBoard());

        System.out.printf("%nCards that are on the board are:%n%s%n", Board.getBoard());

        for (int i = 0 ; i < 6; i++) {
            humanPlayer.getHand().addAll(deck.deal());
            bot.getHand().addAll(deck.deal());

            for (int j = 0 ; j < 4 ; j++ ) {
                System.out.printf("\nYour cards are:%n%s%n", humanPlayer.getHand());
                humanPlayer.playCard();
                bot.playCard();
            }
        }

        int playerScore = calculateScore(humanPlayer);
        System.out.printf("Your score is: %d%n", playerScore);

        int botScore = calculateScore(bot);
        System.out.printf("%s's score is: %d\n", botName, botScore);

        try {
            BufferedReader reader = new BufferedReader(new FileReader("scores.txt"));
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

            BufferedWriter writer = new BufferedWriter(new FileWriter("scores.txt"));
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
    public int howManyRounds() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n" + "How many rounds you want to play?");
        return scanner.nextInt();
    }

    @Override
    public ArrayList<Integer> chooseLevel() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> level = new ArrayList<>();

        System.out.println("\n1 - Novice\n2 - Regular\n3 - Expert");

        for (int i = 0; i < 2; i++) {
            System.out.printf("Enter the level of the %s bot: ", i == 0 ? "first" : "second");
            level.add(scanner.nextInt());
        }
        return level;
    }

    @Override
    public void game() {
        ArrayList<Integer> level = chooseLevel();

        List<Player> bots = Arrays.asList(new NoviceBot(), new RegularBot(), new ExpertBot());
        List<Player> selectedBots = Arrays.asList(bots.get(level.get(0) - 1), bots.get(level.get(1) - 1));

        String playerName = humanPlayer.getName();
        String[] botNames = new String[selectedBots.size()];
        for (int i = 0 ; i < selectedBots.size() ; i++) {
            botNames[i] = selectedBots.get(i).getName();
        }

        Board.getBoard().addAll(deck.deal());
        Player.cardsPlayed.addAll(Board.getBoard());

        System.out.printf("%nCards that are on the board are:%n%s%n", Board.getBoard());

        for (int i = 0 ; i < 4; i++) {
            humanPlayer.getHand().addAll(deck.deal());
            for (Player bot : selectedBots) {
                bot.getHand().addAll(deck.deal());
            }

            for (int j = 0 ; j < 4 ; j++) {
                System.out.printf("\nYour cards are:%n%s%n", humanPlayer.getHand());
                humanPlayer.playCard();
                for (Player bot : selectedBots) {
                    bot.playCard();
                }
            }
        }

        int playerScore = calculateScore(humanPlayer);
        System.out.println("Your score is: " + playerScore);

        for (int i = 0 ; i < selectedBots.size() ; i++) {
            int botScore = calculateScore(selectedBots.get(i));
            System.out.printf("%s's score is: %d\n", botNames[i], botScore);
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader("scores.txt"));
            ArrayList<String> highScores = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                highScores.add(line);
            }
            reader.close();

            highScores.add(playerName + ", " + playerScore);
            for (int i = 0; i < selectedBots.size(); i++) {
                int botScore = calculateScore(selectedBots.get(i));
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

            BufferedWriter writer = new BufferedWriter(new FileWriter("scores.txt"));
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
    public int howManyRounds() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n" + "How many rounds you want to play?");
        return scanner.nextInt();
    }
    @Override
    public ArrayList<Integer> chooseLevel() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> level = new ArrayList<>();

        System.out.println("\n1 - Novice\n2 - Regular\n3 - Expert");

        for (int i = 0; i < 3 ; i++) {
            System.out.printf("Enter the level of the %s bot: ", i == 0 ? "first" : (i == 1 ? "second" : "third"));
            level.add(scanner.nextInt());
        }
        return level;
    }

    @Override
    public void game() {
        ArrayList<Integer> level = chooseLevel();

        List<Player> bots = Arrays.asList(new NoviceBot(), new RegularBot(), new ExpertBot());
        List<Player> selectedBots = Arrays.asList(bots.get(level.get(0) - 1), bots.get(level.get(1) - 1), bots.get(level.get(2) - 1));

        String playerName = humanPlayer.getName();
        String[] botNames = new String[selectedBots.size()];
        for (int i = 0 ; i < selectedBots.size() ; i++) {
            botNames[i] = selectedBots.get(i).getName();
        }

        Board.getBoard().addAll(deck.deal());
        Player.cardsPlayed.addAll(Board.getBoard());

        System.out.printf("%nCards that are on the board are:%n%s%n", Board.getBoard());

        for (int i = 0 ; i < 3; i++) {
            humanPlayer.getHand().addAll(deck.deal());
            for (Player bot : selectedBots) {
                bot.getHand().addAll(deck.deal());
            }

            for (int j = 0 ; j < 4 ; j++) {
                System.out.printf("\nYour cards are:%n%s%n", humanPlayer.getHand());
                humanPlayer.playCard();
                for (Player bot : selectedBots) {
                    bot.playCard();
                }
            }
        }

        int playerScore = calculateScore(humanPlayer);
        System.out.println("Your score is: " + playerScore);
        for (int i = 0 ; i < selectedBots.size() ; i++) {
            int botScore = calculateScore(selectedBots.get(i));
            System.out.printf("%s's score is: %d\n", botNames[i], botScore);
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader("scores.txt"));
            ArrayList<String> highScores = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                highScores.add(line);
            }
            reader.close();

            highScores.add(playerName + ", " + playerScore);
            for (int i = 0; i < selectedBots.size(); i++) {
                int botScore = calculateScore(selectedBots.get(i));
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

            BufferedWriter writer = new BufferedWriter(new FileWriter("scores.txt"));
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