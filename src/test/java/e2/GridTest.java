package e2;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class GridTest {
    
    private Grid grid;

    @Test
    public void instance() {
        instantiate();
        assertNotNull(this.grid);
        assertEquals(GridImpl.DEFAULT_SIZE, this.grid.getSize());
    }

    @Test
    public void instancePassingAlsoBombProbability() {
        int size = 5;
        double bombProbability = 0.67;
        this.grid = new GridImpl(size, bombProbability);
        assertEquals(bombProbability, this.grid.getBombProbability());
    }

    @Test
    public void invalidInstance() {
        assertThrows(IllegalArgumentException.class, () -> this.grid = new GridImpl(-1));
    }

    @Test
    public void gridFilledWithCells() {
        instantiate();
        for (int i = 0; i < GridImpl.DEFAULT_SIZE; i++) {
            for (int j = 0; j < GridImpl.DEFAULT_SIZE; j++) {
                assertNotNull((this.grid).getCellAt(i,j));
            }
        }
    }

    @Test
    public void exceptionWhenAccessingCellOutOfRange() {
        instantiate();
        assertThrows(NoSuchElementException.class, () -> this.grid.getCellAt(-1, 0));
    }

    @Test
    public void getCellIterator() {
        instantiate();
        var iterator = this.grid.getCellIterator();
        Set<Cell> cells = new HashSet<>();
        while (iterator.hasNext()) {
            cells.add(iterator.next());
        }
        assertArrayEquals(cells.toArray(), this.grid.getAllCells().toArray());
    }

    @Test
    public void getAdjacentTo() {
        instantiate();
        var requestedPosition = new Pair<>(3,3);
        var adjacentSet = new HashSet<Pair<Integer, Integer>>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i != 0 || j != 0) {
                    adjacentSet.add(new Pair<>(requestedPosition.getX() + i, requestedPosition.getY() + j));
                }
            }
        }
        for (var position : adjacentSet) {
            assertTrue(this.grid.getAdjacentTo(requestedPosition.getX(), requestedPosition.getY())
                .stream()
                .map(cell -> cell.getPosition())
                .anyMatch(pos -> pos.equals(position))
            );
        }
    }

    private void instantiate() {
        this.grid = new GridImpl();
    }
}
