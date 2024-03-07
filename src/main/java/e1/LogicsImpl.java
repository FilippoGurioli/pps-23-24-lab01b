package e1;

import java.util.*;

public class LogicsImpl implements Logics {
	
	private final Piece pawn;
	private final Piece knight;
	private final Random random = new Random();
	private final int size;

	public static final int DEFAULT_SIZE = 5;
	 
	public LogicsImpl(int size, Piece pawn, Piece knight) {
		this.size = size;
		if (this.size <= 0) {
			throw new IllegalArgumentException("Size must be positive");
		}
		this.pawn = pawn;
		this.knight = knight;
		if (this.pawn.getPosition().equals(this.knight.getPosition())) {
			throw new IllegalArgumentException("Cannot pass 2 pieces in the same place to constructor");
		}
	}

    public LogicsImpl(int size) {
		this.size = size;
		if (this.size <= 0) {
			throw new IllegalArgumentException("Size must be positive");
		}
		this.pawn = new PieceImpl();
		this.knight = new PieceImpl();
		var pawnPosition = randomEmptyPosition();
		var knightPosition = randomEmptyPosition();
		this.knight.GoTo(knightPosition.getX(), knightPosition.getY());
		this.pawn.GoTo(pawnPosition.getX(), pawnPosition.getY());
    }

	public LogicsImpl() {
		this(DEFAULT_SIZE);
	}

	private final Pair<Integer,Integer> randomEmptyPosition(){
    	Pair<Integer,Integer> pos = new Pair<>(this.random.nextInt(size),this.random.nextInt(size));
    	// the recursive call below prevents clash with an existing pawn
    	return this.pawn.getPosition().equals(pos) ? randomEmptyPosition() : pos;
    }
    
	@Override
	public boolean hit(int row, int col) {
		checkConsistency(row, col);
		// Below a compact way to express allowed moves for the knight
		int x = row-this.knight.getPosition().getX();
		int y = col-this.knight.getPosition().getY();
		if (x!=0 && y!=0 && Math.abs(x)+Math.abs(y)==3) {
			this.knight.Move(x, y);
			return this.pawn.getPosition().equals(this.knight.getPosition());
		}
		return false;
	}

	@Override
	public boolean hasKnight(int row, int col) {
		return this.knight.getPosition().equals(new Pair<>(row,col));
	}

	@Override
	public boolean hasPawn(int row, int col) {
		return this.pawn.getPosition().equals(new Pair<>(row,col));
	}

	public int getSize() {
		return size;
	}

	public Pair<Integer, Integer> getKnightPosition() {
		return this.knight.getPosition();
	}

	private void checkConsistency(int row, int col) {
		if (row<0 || col<0 || row >= this.size || col >= this.size) {
			throw new IndexOutOfBoundsException();
		}
	}
}
