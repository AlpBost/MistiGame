import java.util.ArrayList;

public class Board {
    private static ArrayList<Card> board;

    public Board() {
        board = new ArrayList<>();
    }

    public static ArrayList<Card> getBoard() {
        return board;
    }

}
