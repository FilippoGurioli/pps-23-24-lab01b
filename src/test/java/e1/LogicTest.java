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
        var pawn = new PieceImpl(1,2);
        var knight = new PieceImpl(3,3);
        this.logics = new LogicsImpl(size, pawn, knight);
        assertAll(
            () -> assertEquals(size, this.logics.getSize()),
            () -> assertTrue(this.logics.hasPawn(pawn.getPosition().getX(),pawn.getPosition().getY())),
            () -> assertTrue(this.logics.hasKnight(knight.getPosition().getX(), knight.getPosition().getY()))
        );
    }

    @Test
    public void testSamePositionForPiecesAreNotAllowed() {
        int size = 7;
        var piece = new PieceImpl(1,2);
        assertThrows(IllegalArgumentException.class,
            () -> this.logics = new LogicsImpl(size, piece, piece)
        );
    }

    @Test
    public void testAcceptedHits() {
        int size = 7;
        var pawn = new PieceImpl(1, 2);
        var knight = new PieceImpl(3, 3);
        var absValidMove = new Pair<Integer, Integer>(1,2);
        this.logics = new LogicsImpl(size, pawn, knight);
        for (int i = -1; i >= 1; i+=2) {
            for (int j = -1; j >= 1; j+=2) {
                assertTrue(this.checkMove(i * absValidMove.getX(), j * absValidMove.getY()));
                assertTrue(this.checkMove(i * absValidMove.getY(), j * absValidMove.getX()));
            }
        }
    }

    private boolean checkMove(int x, int y) {
        var previous = this.logics.getKnightPosition();
        this.logics.hit(x, y);
        boolean knightPresence = this.logics.hasKnight(x, y);
        this.logics.hit(previous.getX(), previous.getY());
        return knightPresence;
    }

    @Test
    public void testIllegalHit() {
        int size = 7;
        var pawn = new PieceImpl(1, 2);
        var knight = new PieceImpl(3, 3);
        var illegalMove = new Pair<Integer, Integer>(2 + knight.getPosition().getX(), 2 + knight.getPosition().getY());
        this.logics = new LogicsImpl(size, pawn, knight);
        this.logics.hit(illegalMove.getX(), illegalMove.getY());
        assertAll(
            () -> assertFalse(this.logics.hasKnight(illegalMove.getX(), illegalMove.getY())),
            () -> assertTrue(this.logics.hasKnight(knight.getPosition().getX(), knight.getPosition().getY())),
            () -> assertTrue(this.logics.hasPawn(pawn.getPosition().getX(), pawn.getPosition().getY()))
        );
    }

    @Test
    public void testHit() {
        int size = 7;
        var pawn = new PieceImpl(1, 2);
        var knight = new PieceImpl(3, 3);
        var validMove = new Pair<Integer, Integer>(knight.getPosition().getX() - 2, knight.getPosition().getY() - 1);
        this.logics = new LogicsImpl(size, pawn, knight);
        assertTrue(this.logics.hit(validMove.getX(), validMove.getY()));
    }
}
