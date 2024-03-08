package e2;

public class LogicsImpl extends GridImpl implements Logics {

    private boolean isGameOver = false;

    public LogicsImpl(int size) {
        super(size);
    }

	@Override
	public FlaggableCell getCellAt(Pair<Integer, Integer> position) {
        return super.getCellAt(position.getX(), position.getY());
	}

    @Override
    public void expand(Cell cell) {
        var expandedCell = this.getCellAt(cell.getPosition());
        if (expandedCell.isDiscovered()) {
            return;
        }
        var adjacents = super.getAdjacentTo(expandedCell.getPosition().getX(), expandedCell.getPosition().getY());
        expandedCell.discover();
        if (expandedCell.getType().equals(CellType.BOMB)) {
            this.isGameOver = true;
            return;
        }
        if (adjacents.stream().anyMatch(c -> c.getType().equals(CellType.BOMB))) {
            return;
        } else {
            for (var adjacent : adjacents) {
                this.expand(adjacent);
            }
        }
    }

    @Override
    public boolean isGameOver() {
        return this.isGameOver;
    }

	@Override
	public boolean isVictory() {
        return super.getAllCells()
            .stream()
            .filter(cell -> cell.getType().equals(CellType.NORMAL))
            .allMatch(cell -> cell.isDiscovered()) &&
            super.getBombs().stream().allMatch(cell -> !cell.isDiscovered());
	}
}
