package cz.wz.marysidy.island.model;

public class MoveIntent {
    private final int dx;
    private final int dy;

    public MoveIntent(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }
}
