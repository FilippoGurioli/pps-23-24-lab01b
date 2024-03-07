package e1;

public interface Piece {

	Pair<Integer, Integer> getPosition();

    void GoTo(int x, int y);

    void Move(int x, int y);

}
