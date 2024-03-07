package e1;

public class PieceImpl implements Piece {

    private Pair<Integer, Integer> position;

    public PieceImpl(int x, int y) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("x and y must be positive or 0");
        }
        this.position = new Pair<Integer,Integer>(x, y);
    }

    public PieceImpl() {
        this(0,0);
	}

	public PieceImpl(Pair<Integer, Integer> startPosition) {
        this(startPosition.getX(), startPosition.getY());
    }

    @Override
    public Pair<Integer, Integer> getPosition() {
        return this.position;
    }

    @Override
    public void GoTo(int x, int y) {
        this.position = new Pair<Integer, Integer>(x, y);
    }

    @Override
    public void Move(int x, int y) {
        this.position = new Pair<Integer, Integer>(this.position.getX() + x, this.position.getY() + y);
    }

}
