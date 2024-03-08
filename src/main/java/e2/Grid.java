package e2;

import java.util.Iterator;
import java.util.Set;

public interface Grid {

	int getSize();

    Cell getCellAt(int x, int y);

	double getBombProbability();

    Iterator<Cell> getCellIterator();

    Set<Cell> getAllCells();

    Set<Cell> getAdjacentTo(int x, int y);

    Set<Cell> getBombs();

    int getNearBombCount(Cell cellNearToBomb);
}
