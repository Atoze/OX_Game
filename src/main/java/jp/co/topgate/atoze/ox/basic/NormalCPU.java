package jp.co.topgate.atoze.ox.basic;

import jp.co.topgate.atoze.ox.ComputerPlayer;
import jp.co.topgate.atoze.ox.ScreenBoard;

/**
 * Created by atoze on 2017/06/08.
 */
public class NormalCPU extends ComputerPlayer {

    NormalCPU(int id) {
        super(id);
    }

    @Override
    public int next(ScreenBoard board) {
        int gridIndex;

        int shortSide = board.getColumn();
        int longSide = board.getRow();

        if (shortSide >= longSide) {
            shortSide ^= longSide;
            longSide ^= shortSide;
            shortSide ^= longSide;
        }
        int surplusSide = board.getLength() / (longSide * shortSide);
        while (true) {
            gridIndex = (int) (Math.random() * board.getLength());
            if (board.accept(gridIndex)) {
                break;
            }
        }
        return gridIndex;
    }
}
