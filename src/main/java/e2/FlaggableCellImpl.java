package e2;

public class FlaggableCellImpl extends CellImpl implements FlaggableCell {

    private boolean flag = false;

    public FlaggableCellImpl(CellType type, Pair<Integer, Integer> position) {
        super(type, position);
    }

	@Override
	public boolean isFlagged() {
        return this.flag;
	}

    @Override
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
