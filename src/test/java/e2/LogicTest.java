package e2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LogicTest {
    
    private Logics logics;

    @BeforeEach
    public void beforeEach() {
        int size = 5;
        this.logics = new LogicsImpl(size);
    }

    @Test
    public void getCellAtWithPair() {
        var position = new Pair<Integer, Integer>(1,1);
        assertEquals(this.logics.getCellAt(position.getX(), position.getY()), this.logics.getCellAt(position));
    }

    @Test
    public void expand() {
        Cell normalCell = this.logics.getAllCells().stream().filter(cell -> cell.getType().equals(CellType.NORMAL)).findFirst().get();
        this.logics.expand(normalCell);
        var expandedBombs = this.logics.getAllCells().stream().filter(cell -> cell.isDiscovered()).filter(cell -> cell.getType().equals(CellType.BOMB)).count();
        assertEquals(0, expandedBombs);
    }

    @Test
    public void gameOver() {
        Cell bomb = this.logics.getBombs().iterator().next();
        this.logics.expand(bomb);
        assertTrue(this.logics.isGameOver());
    }

    @Test
    public void isVictory() {
        var normalCells = this.logics.getAllCells().stream().filter(cell -> cell.getType().equals(CellType.NORMAL)).collect(Collectors.toList());
        for(var cell : normalCells) {
            cell.discover();
        }
        assertTrue(this.logics.isVictory());
    }
}
