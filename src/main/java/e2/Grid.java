package e2;

import java.util.Iterator;
import java.util.Set;

public interface Grid {

	int getSize();

    FlaggableCell getCellAt(int x, int y);

	double getBombProbability();

    Iterator<FlaggableCell> getCellIterator();

    Set<FlaggableCell> getAllCells();

    Set<FlaggableCell> getAdjacentTo(int x, int y);

    Set<FlaggableCell> getBombs();

    int getNearBombCount(Cell cellNearToBomb);
}
