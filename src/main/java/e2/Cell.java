package e2;

public interface Cell {

    boolean isDiscovered();

    Pair<Integer,Integer> getPosition();

    void discover();

    boolean isAdjacentTo(Cell adjacentCell);

	CellType getType();
}
