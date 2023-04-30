import java.util.Scanner;

public class Game {
    Scanner scanner = new Scanner(System.in);
    Deck deck =new Deck();
    Player bot1 = new Player();
    Player bot2 = new Player();
    Player bot3 = new Player();
    Player player = new Player();

    private int Bot1Level;
    private int Bot2Level;
    private int Bot3Level;

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

    public int getBot3Level() {
        return Bot3Level;
    }

    public void setBot3Level(int bot3Level) {
        Bot3Level = bot3Level;
    }


    public void game2Players() {
        for (int i = 0; i < 6 ; i++) {
            player.player();
            bot1.NoviceBot();
        }
    }

    public void game3Players() {
        for(int i=0;i<4;i++) {
            player.player();
            bot1.RegularBot();
            bot2.RegularBot();
        }

    }

}
