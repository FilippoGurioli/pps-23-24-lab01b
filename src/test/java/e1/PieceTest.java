package e1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class PieceTest {
    
    private Piece piece;

    private void startAt(int x, int y) {
        this.piece = new PieceImpl(x, y);
    }

    @Test
    public void instance() {
        this.startAt(0, 0);
        assertNotNull(this.piece);
        assertEquals(new Pair<Integer, Integer>(0,0), this.piece.getPosition());
    }

    @Test
    public void instanceWithRowParameters() {
        var startPosition = new Pair<Integer, Integer>(1,4);
        this.startAt(startPosition.getX(), startPosition.getY());
        assertEquals(startPosition, this.piece.getPosition());
    }

    @Test
    public void instanceWithPairAsParameter() {
        var startPosition = new Pair<Integer, Integer>(1, 4);
        Piece piece = new PieceImpl(startPosition);
        assertEquals(startPosition, piece.getPosition());
    }

    @Test
    public void absoluteMove() {
        this.startAt(0, 0);
        var absMove = new Pair<Integer, Integer>(1, 4);
        this.piece.GoTo(absMove.getX(), absMove.getY());
        assertEquals(absMove, this.piece.getPosition());
    }

    @Test
    public void relativeMove() {
        var startPosition = new Pair<Integer, Integer> (3, 3);
        var relativeMove = new Pair<Integer, Integer>(1, 4);
        this.startAt(startPosition.getX(), startPosition.getY());
        this.piece.Move(relativeMove.getX(), relativeMove.getY());
        assertEquals(new Pair<Integer, Integer>(relativeMove.getX() + startPosition.getX(), relativeMove.getY() + startPosition.getY()), this.piece.getPosition());
    }
}
