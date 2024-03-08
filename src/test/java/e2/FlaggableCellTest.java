package e2;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class FlaggableCellTest extends CellTest {
    
    @Override
    protected void instantiateCell() {
        super.cell = new FlaggableCellImpl(CellType.BOMB, new Pair<>(0,0));
    }

    @Override
    protected void illegalInstanceGenerator() {
        super.cell = new FlaggableCellImpl(CellType.NORMAL, new Pair<>(-1, -2));
    }

    @Test
    public void isNotFlaggedFromStart() {
        assertFalse(((FlaggableCell)super.cell).isFlagged());
    }

    @Test
    public void isFlaggedAfterFlagging() {
        var flaggableCell = (FlaggableCell) super.cell;
        flaggableCell.setFlag(true);
        assertTrue(flaggableCell.isFlagged());
    }

    @Test
    public void canFlagMultipleTime() {
        var flaggableCell = (FlaggableCell) super.cell;
        for (int i = 0; i < 10; i++) {
            flaggableCell.setFlag(false);
        }
        assertFalse(flaggableCell.isFlagged());
    }
}
