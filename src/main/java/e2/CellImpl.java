package e2;

public class CellImpl implements Cell {

    private boolean isDiscovered;
    private final Pair<Integer, Integer> position;
    private final CellType type;

	public CellImpl(CellType type, Pair<Integer, Integer> position) {
		this.isDiscovered = false;
        this.type = type;
        if (position.getX() < 0 || position.getY() < 0) {
            throw new IllegalArgumentException("Cannot pass negative positions.");
        }
        this.position = position;
	}

	@Override
	public boolean isDiscovered() {
        return this.isDiscovered;
	}

	@Override
	public Pair<Integer, Integer> getPosition() {
        return this.position;
	}

    @Override
    public void discover() {
        if (!this.isDiscovered()) {
            this.isDiscovered = true;
        } else {
            throw new IllegalStateException("Cannot discover an already discovered cell");
        }
    }

    @Override
    public boolean isAdjacentTo(Cell adjacentCell) {
        var distance = new Pair<>(
            Math.abs(this.position.getX() - adjacentCell.getPosition().getX()),
            Math.abs(this.position.getY() - adjacentCell.getPosition().getY())
        );
        return distance.getX() <= 1 && distance.getY() <= 1 && !adjacentCell.getPosition().equals(this.getPosition());
    }

    @Override
    public CellType getType() {
        return this.type;
    }
}
