package e1;

import java.util.*;

public class LogicsImpl implements Logics {
	
	private final Pair<Integer,Integer> pawn;
	private Pair<Integer,Integer> knight;
	private final Random random = new Random();
	private final int size;

	public static final int DEFAULT_SIZE = 5;
	 
    public LogicsImpl(int size){
		this.size = size;
        this.pawn = this.randomEmptyPosition();
        this.knight = this.randomEmptyPosition();	
		checkSizePositivity();
    }

	public LogicsImpl() {
		this(DEFAULT_SIZE);
	}

	public LogicsImpl(int size, Pair<Integer,Integer> pawn, Pair<Integer,Integer> knight) {
		this.size = size;
		this.pawn = pawn;
		this.knight = knight;
		checkSizePositivity();
		checkConsistency(pawn.getX(), pawn.getY());
		checkConsistency(knight.getX(), knight.getY());
		if (pawn.getX() == knight.getX() && pawn.getY() == knight.getY()) {
			throw new IllegalArgumentException("knight and pawn cannot be placed in the same cell");
		}
	}

	private final Pair<Integer,Integer> randomEmptyPosition(){
    	Pair<Integer,Integer> pos = new Pair<>(this.random.nextInt(size),this.random.nextInt(size));
    	// the recursive call below prevents clash with an existing pawn
    	return this.pawn!=null && this.pawn.equals(pos) ? randomEmptyPosition() : pos;
    }
    
	@Override
	public boolean hit(int row, int col) {
		checkConsistency(row, col);
		// Below a compact way to express allowed moves for the knight
		int x = row-this.knight.getX();
		int y = col-this.knight.getY();
		if (x!=0 && y!=0 && Math.abs(x)+Math.abs(y)==3) {
			this.knight = new Pair<>(row,col);
			return this.pawn.equals(this.knight);
		}
		return false;
	}

	@Override
	public boolean hasKnight(int row, int col) {
		return this.knight.equals(new Pair<>(row,col));
	}

	@Override
	public boolean hasPawn(int row, int col) {
		return this.pawn.equals(new Pair<>(row,col));
	}

	public int getSize() {
		return size;
	}

	public Pair<Integer, Integer> getKnight() {
		return this.knight;
	}

	private void checkConsistency(int row, int col) {
		if (row<0 || col<0 || row >= this.size || col >= this.size) {
			throw new IndexOutOfBoundsException();
		}
	}

	private void checkSizePositivity() {
		if (this.size <= 0) {
			throw new IllegalArgumentException("Size must be positive");
		}
	}
}
