import java.util.Scanner;

public class Start {
	private static int round;
    public int getRound() {return round;}
	public void setRound(int round) {this.round = round;}


	public static void startGame() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Mişti!");
        System.out.println("\n" + "To see the rules, press 0.");
        System.out.println("To start the game, press 1.");

        int x = scanner.nextInt();

        switch (x) {
            case 0:
                System.out.println("Mişti is played with a 52-card deck. The dealer shuffles and deals 4 cards to each player and 4 cards to the board.");
                System.out.println("Players take turns throwing cards onto the table. A card with the same value as the top card wins the pile, except for Jacks which win all cards.");
                System.out.println("If there is only one card left on the table, playing the same card wins a Mişti worth 10 points.");
                System.out.println("The game continues until all cards are played. The player with the most cards gets 3 extra points.");
                System.out.println("Cards have different point values: Ten of diamonds is worth 3 points, Two of clubs is worth 2 points, and all other cards are worth 1 point.");
                System.out.println("Good luck!");

            case 1:
                System.out.println("\n1 - Two Players\n2 - Three Players\n3 - Four Players");
                System.out.println("\nHow many players do you want to play with?");
                int players = scanner.nextInt();
               
                
                System.out.println("\nHow many rounds do you want to play ?");
                round = scanner.nextInt();
                
                
                
                switch (players) {
                
                    case 1:
                    	for(int i = 0; i<round;i++) {
                    		twoPlayers newGame = new twoPlayers();
                    		newGame.game();
                    	}
                        break;

                    case 2:
                    	for(int i = 0; i<round;i++) {
	                        threePlayers newGame1 = new threePlayers();
	                        newGame1.game();
                    	}
	                    break;
	                    
                    case 3:
                    	for(int i = 0; i<round;i++) {
	                        fourPlayers newGame2 = new fourPlayers();
	                        newGame2.game();
                    	}
                        break;
                }
        }
        
    }
}
