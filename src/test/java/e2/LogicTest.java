package e2;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    public void getCellAtPosition() {
        var cellPosition = new Pair<Integer, Integer>(3,3);
        Cell cell = this.logics.getCellAt(cellPosition);
        assertAll(
            () -> assertNotNull(cell),
            () -> assertEquals(cellPosition, cell.getPosition())
        );
    }

    @Test
    public void getCellAtPositionWithIntegers() {
        int x = 3;
        int y = 3;
        Cell cell = this.logics.getCellAt(x,y);
        assertAll(
            () -> assertNotNull(cell),
            () -> assertEquals(new Pair<>(x, y), cell.getPosition())
        );
    }
}
