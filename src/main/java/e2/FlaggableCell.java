package e2;

public interface FlaggableCell extends Cell {

    boolean isFlagged();

    void setFlag(boolean flag);
}
