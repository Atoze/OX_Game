package jp.co.topgate.atoze.ox.player;

import jp.co.topgate.atoze.ox.Board;
import jp.co.topgate.atoze.ox.OXGame;
import jp.co.topgate.atoze.ox.Player;

/**
 * 相手が並ぶのを邪魔してくるだけのコンピューター
 * 未実装
 */
public class NormalCPU implements Player {
    private final int id;

    public NormalCPU(int id) {
        this.id = id;
    }

    @Override
    public int selectBoardIndex(OXGame game) {
        Board board = game.getBoard();
        int boardIndex;

        int winNumber;

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
