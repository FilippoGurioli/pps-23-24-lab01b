package e2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CellTest {
    
    protected Cell cell;

    @BeforeEach
    public void beforeEach() {
        this.instantiateCell();
    }

    @Test
    public void instance() {
        assertFalse(this.cell.isDiscovered());
        assertEquals(new Pair<>(0,0), this.cell.getPosition());
    }

    @Test
    public void invalidInstance() {
        assertThrows(IllegalArgumentException.class, () -> this.illegalInstanceGenerator());
    }

    @Test
    public void getType() {
        assertEquals(CellType.BOMB, this.cell.getType());
    }

    @Test
    public void discoverMethod() {
        this.cell.discover();
        assertTrue(this.cell.isDiscovered());
    }

    @Test
    public void throwsExceptionAtTheSecondDiscover() {
        assertThrows(IllegalStateException.class, () -> {
            this.cell.discover();
            this.cell.discover();
        });
    }

    @Test
    public void adjacency() {
        var adjacentCell = new CellImpl(CellType.NORMAL, new Pair<>(1,0));
        assertTrue(this.cell.isAdjacentTo(adjacentCell));
    }

    @Test
    public void adjacencyFailure() {
        var notAdjacentCell = new CellImpl(CellType.NORMAL, new Pair<>(5,5));
        assertFalse(this.cell.isAdjacentTo(notAdjacentCell));
    }

    protected void instantiateCell() {
        this.cell = new CellImpl(CellType.BOMB, new Pair<>(0,0));
    }

    protected void illegalInstanceGenerator() {
        this.cell = new CellImpl(CellType.NORMAL, new Pair<>(-1, -2));
    }
}
