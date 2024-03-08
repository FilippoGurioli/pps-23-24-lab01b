package e2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CellTest {
    
    private Cell cell;

    @Test
    public void instance() {
        var startPosition = new Pair<Integer, Integer>(0,0);
        this.cell = new CellImpl(CellType.NORMAL, startPosition);
        assertFalse(this.cell.isDiscovered());
        assertEquals(startPosition, this.cell.getPosition());
    }

    @Test
    public void invalidInstance() {
        var invalidStartPosition = new Pair<Integer, Integer>(-1, -2);
        assertThrows(IllegalArgumentException.class, () -> this.cell = new CellImpl(CellType.NORMAL, invalidStartPosition));
    }

    @Test
    public void discoverMethod() {
        instantiateCell();
        this.cell.discover();
        assertTrue(this.cell.isDiscovered());
    }

    @Test
    public void typeAccessibleIfDiscovered() {
        this.cell = new CellImpl(CellType.BOMB, new Pair<>(0,0));
        assertEquals(CellType.BOMB, this.cell.discover());
    }

    @Test
    public void throwsExceptionAtTheSecondDiscover() {
        instantiateCell();
        this.cell.discover();
        assertThrows(IllegalStateException.class, () -> this.cell.discover());
    }

    @Test
    public void adjacency() {
        instantiateCell();
        var adjacentCell = new CellImpl(CellType.NORMAL, new Pair<>(1,0));
        assertTrue(this.cell.isAdjacentTo(adjacentCell));
    }

    @Test
    public void adjacencyFailure() {
        instantiateCell();
        var notAdjacentCell = new CellImpl(CellType.NORMAL, new Pair<>(5,5));
        assertFalse(this.cell.isAdjacentTo(notAdjacentCell));
    }

    private void instantiateCell() {
        this.cell = new CellImpl(CellType.BOMB, new Pair<>(0,0));
    }
}
