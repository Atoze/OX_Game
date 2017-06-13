package jp.co.topgate.atoze.ox.basic;

import jp.co.topgate.atoze.ox.Player;
import jp.co.topgate.atoze.ox.ScreenBoard;
import jp.co.topgate.atoze.ox.UI;

/**
 * コンピューターが操作するプレイヤー
 */
public class NormalCPU implements Player {
    private final int id;

    NormalCPU(int id) {
        this.id = id;
    }

    @Override
    public int next(ScreenBoard board, UI ui) {
        int boardIndex;

        int shortSide = board.getColumn();
        int longSide = board.getRow();

        if (shortSide >= longSide) {
            int temp = longSide;
            longSide = shortSide;
            shortSide = temp;
            /*
            shortSide ^= longSide;
            longSide ^= shortSide;
            shortSide ^= longSide;*/
        }
        int surplusSide = board.getSize() / (longSide * shortSide);
        while (true) {
            boardIndex = (int) (Math.random() * board.getSize());
            break;
        }
        return boardIndex;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getName() {
        return "コンピューター";
    }
}
