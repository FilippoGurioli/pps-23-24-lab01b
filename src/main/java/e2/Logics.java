package e2;

public interface Logics extends Grid {
    FlaggableCell getCellAt(Pair<Integer, Integer> position);

	void expand(Cell cell);

    boolean isGameOver();

    boolean isVictory();
}
