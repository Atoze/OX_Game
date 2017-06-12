package jp.co.topgate.atoze.ox.basic;

import jp.co.topgate.atoze.ox.ComputerPlayer;
import jp.co.topgate.atoze.ox.ScreenBoard;

/**
 * コンピューターが操作するプレイヤー
 */
public class NormalCPU extends ComputerPlayer {

    NormalCPU(int id) {
        super(id);
    }

    @Override
    public int next(ScreenBoard board) {
        int boardIndex;

        int shortSide = board.getColumn();
        int longSide = board.getRow();

        if (shortSide >= longSide) {
            shortSide ^= longSide;
            longSide ^= shortSide;
            shortSide ^= longSide;
        }
        int surplusSide = board.getLength() / (longSide * shortSide);
        while (true) {
            boardIndex = (int) (Math.random() * board.getLength());
            if (board.accept(boardIndex)) {
                break;
            }
        }
        return boardIndex;
    }
}
