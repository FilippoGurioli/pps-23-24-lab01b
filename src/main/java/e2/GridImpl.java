package e2;

import java.util.HashSet;
import java.util.Iterator;

import java.util.Random;
import java.util.Set;

public class GridImpl implements Grid {

    private final int size;
    private final Set<Cell> grid = new HashSet<>();
    private final double bombProbability;

    public static final double DEFAULT_BOMB_PROBABILITY = 0.70;
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
                    this.grid.add(new CellImpl(CellType.BOMB, new Pair<>(i,j)));
                } else {
                    this.grid.add(new CellImpl(CellType.NORMAL, new Pair<>(i,j)));
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
    public Cell getCellAt(int x, int y) {
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
	public Iterator<Cell> getCellIterator() {
        return this.grid.iterator();
	}

	@Override
	public Set<Cell> getAllCells() {
        return new HashSet<Cell>(this.grid);
	}

	@Override
	public Set<Cell> getAdjacentTo(int x, int y) {
        var adjacentSet = new HashSet<Cell>();
        this.grid
            .stream()
            .filter(cell -> cell.isAdjacentTo(this.getCellAt(x, y)))
            .forEach(cell -> adjacentSet.add(cell));
        return adjacentSet;
	}
}
