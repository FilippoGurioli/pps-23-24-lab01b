package e1;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class LogicTest {

    private LogicsImpl logics;

    @Test
    public void testEmptyConstructor() {
        this.logics = new LogicsImpl();
        assertEquals(LogicsImpl.DEFAULT_SIZE, logics.getSize());
    }

    @Test
    public void testOneParameterConstructor() {
        int size = 8;
        this.logics = new LogicsImpl(size);
        assertEquals(size, logics.getSize());
    }

    @Test
    public void testFullConstructor() {
        int size = 7;
        var pawnPosition = new Pair<Integer, Integer>(1,2);
        var knightPosition = new Pair<Integer, Integer>(3,3);
        this.logics = new LogicsImpl(size, pawnPosition, knightPosition);
        assertAll(
            () -> assertEquals(size, this.logics.getSize()),
            () -> assertTrue(this.logics.hasPawn(pawnPosition.getX(),pawnPosition.getY())),
            () -> assertTrue(this.logics.hasKnight(knightPosition.getX(), knightPosition.getY()))
        );
    }

    @Test
    public void testOutOfBoundWhenPiecesAreOutOfBoardBounds() {
        int size = 7;
        var pawnPosition = new Pair<Integer, Integer>(-1,-2);
        var knightPosition = new Pair<Integer, Integer>(-3,-3);
        assertThrows(IndexOutOfBoundsException.class,
            () -> this.logics = new LogicsImpl(size, pawnPosition, knightPosition)
        );
    }

    @Test
    public void testSamePositionForPiecesAreNotAllowed() {
        int size = 7;
        var position = new Pair<Integer, Integer>(1,2);
        assertThrows(IllegalArgumentException.class,
            () -> this.logics = new LogicsImpl(size, position, position)
        );
    }

    @Test
    public void testAcceptedHits() {
        int size = 7;
        var pawnPosition = new Pair<Integer, Integer>(1, 2);
        var knightPosition = new Pair<Integer, Integer>(3, 3);
        var absValidMove = new Pair<Integer, Integer>(1,2);
        this.logics = new LogicsImpl(size, pawnPosition, knightPosition);
        for (int i = -1; i >= 1; i+=2) {
            for (int j = -1; j >= 1; j+=2) {
                this.checkMove(i * absValidMove.getX(), j * absValidMove.getY());
                this.checkMove(i * absValidMove.getY(), j * absValidMove.getX());
            }
        }
    }

    @Test //TODO: attualmente questa check non fa la assert
    private void checkMove(int x, int y) {
        var previous = this.logics.getKnight();
        this.logics.hit(x, y);
        assertTrue(this.logics.hasKnight(x, y));
        this.logics.hit(previous.getX(), previous.getY());
    }

    @Test
    public void testIllegalHit() {
        int size = 7;
        var pawnPosition = new Pair<Integer, Integer>(1, 2);
        var knightPosition = new Pair<Integer, Integer>(3, 3);
        var illegalMove = new Pair<Integer, Integer>(2 + knightPosition.getX(), 2 + knightPosition.getY());
        this.logics = new LogicsImpl(size, pawnPosition, knightPosition);
        this.logics.hit(illegalMove.getX(), illegalMove.getY());
        assertAll(
            () -> assertFalse(this.logics.hasKnight(illegalMove.getX(), illegalMove.getY())),
            () -> assertTrue(this.logics.hasKnight(knightPosition.getX(), knightPosition.getY())),
            () -> assertTrue(this.logics.hasPawn(pawnPosition.getX(), pawnPosition.getY()))
        );
    }

    @Test
    public void testHit() {
        int size = 7;
        var pawnPosition = new Pair<Integer, Integer>(1, 2);
        var knightPosition = new Pair<Integer, Integer>(3, 3);
        var validMove = new Pair<Integer, Integer>(knightPosition.getX() - 2, knightPosition.getY() - 1);
        this.logics = new LogicsImpl(size, pawnPosition, knightPosition);
        assertTrue(this.logics.hit(validMove.getX(), validMove.getY()));
    }
}
