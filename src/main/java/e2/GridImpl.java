package e2;

import java.util.HashSet;
import java.util.Iterator;

import java.util.Random;
import java.util.Set;

public class GridImpl implements Grid {

    private final int size;
    private final Set<FlaggableCell> grid = new HashSet<>();
    private final double bombProbability;

    public static final double DEFAULT_BOMB_PROBABILITY = 0.90;
    public static final int DEFAULT_SIZE = 5;

    public GridImpl(int size, double bombProbability) {
        if (size <= 0) {
            throw new IllegalArgumentException("Cannot pass a negative value for size");
        }
        this.bombProbability = bombProbability;
        this.size = size;
        populateList();
    }

    private void populateList() {
        var random = new Random();
        for (int i = 0; i < this.getSize(); i++) {
            for (int j = 0; j < this.getSize(); j++) {
                if (random.nextDouble() > this.bombProbability) {
                    this.grid.add(new FlaggableCellImpl(CellType.BOMB, new Pair<>(i,j)));
                } else {
                    this.grid.add(new FlaggableCellImpl(CellType.NORMAL, new Pair<>(i,j)));
                }
            }
        }
    }

    public GridImpl(int size) {
        this(size, DEFAULT_BOMB_PROBABILITY);
    }

    public GridImpl() {
        this(DEFAULT_SIZE, DEFAULT_BOMB_PROBABILITY);
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public FlaggableCell getCellAt(int x, int y) {
        return this.grid.stream()
            .filter(cell -> cell.getPosition().equals(new Pair<>(x, y)))
            .findFirst()
            .get();
    }

    @Override
    public double getBombProbability() {
        return this.bombProbability;
    }

	@Override
	public Iterator<FlaggableCell> getCellIterator() {
        return this.grid.iterator();
	}

	@Override
	public Set<FlaggableCell> getAllCells() {
        return new HashSet<FlaggableCell>(this.grid);
	}

	@Override
	public Set<FlaggableCell> getAdjacentTo(int x, int y) {
        var adjacentSet = new HashSet<FlaggableCell>();
        this.grid
            .stream()
            .filter(cell -> cell.isAdjacentTo(this.getCellAt(x, y)))
            .forEach(cell -> adjacentSet.add(cell));
        return adjacentSet;
	}

	@Override
	public Set<FlaggableCell> getBombs() {
        var bombs = new HashSet<FlaggableCell>();
        this.grid
            .stream()
            .filter(cell -> cell.getType().equals(CellType.BOMB))
            .forEach(cell -> bombs.add(cell));
        return bombs;
    }

	@Override
	public int getNearBombCount(Cell cell) {
        if (!this.grid.contains(cell)) {
            throw new IllegalArgumentException("The passed cell is not in this grid");
        }
        if (cell.getType().equals(CellType.BOMB)) {
            return -1;
        } else {
            return (int)this.getAdjacentTo(cell.getPosition().getX(), cell.getPosition().getY())
                .stream()
                .filter(c -> c.getType() == CellType.BOMB)
                .count();
        }
	}
}
