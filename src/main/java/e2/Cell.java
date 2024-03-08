package e2;

public interface Cell {

    boolean isDiscovered();

    Pair<Integer,Integer> getPosition();

    CellType discover();

    boolean isAdjacentTo(Cell adjacentCell);
}
