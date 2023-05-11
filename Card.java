public class Card {
    private final String suit;
    private final String face;
    private final int point;

    public Card(String suit, String face, int point) {
        this.suit = suit;
        this.face = face;
        this.point = point;
    }

    public String getFace() {
        return face;
    }

    public int getPoint() {
        return point;
    }

    @Override
    public String toString() {
        return face + suit;
    }
}