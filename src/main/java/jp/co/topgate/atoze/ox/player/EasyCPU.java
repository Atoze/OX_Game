package jp.co.topgate.atoze.ox.player;

import jp.co.topgate.atoze.ox.Board;
import jp.co.topgate.atoze.ox.OXGame;
import jp.co.topgate.atoze.ox.Player;
import jp.co.topgate.atoze.ox.Timer;

/**
 * 空いてるところをランダムに指定してくるCPUです.
 */
public class EasyCPU implements Player {
    private final int id;

    public EasyCPU(int id) {
        this.id = id;
    }

    @Override
    public int selectBoardIndex(OXGame game, Timer timer) {
        Board board = game.getBoard();
        return (int) (Math.random() * board.getSize());
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
