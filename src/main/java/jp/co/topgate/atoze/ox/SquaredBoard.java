package jp.co.topgate.atoze.ox;

/**
 * Created by atoze on 2017/06/06.
 */
public class SquaredBoard implements Board {
    private int currentTurn;
    private final int[] data;

    SquaredBoard(int gridNum) {
        this.data = new int[gridNum ^ 2];
    }


    public void next() {
        currentTurn++;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    @Override
    public boolean insert(int playerID, int dataIndex) {
        return true;
    }
}
