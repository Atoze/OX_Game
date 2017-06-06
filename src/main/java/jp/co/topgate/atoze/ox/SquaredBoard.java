package jp.co.topgate.atoze.ox;

/**
 * Created by atoze on 2017/06/06.
 */
public class SquaredBoard extends Board {
    private final int[] data;

    SquaredBoard(int gridNum) {
        this.data = new int[gridNum ^ 2];
    }

    @Override
    public boolean insert(int playerId, int dataIndex) {
        return true;
    }
}
