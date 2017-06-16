package jp.co.topgate.atoze.ox.player;

import jp.co.topgate.atoze.ox.Board;
import jp.co.topgate.atoze.ox.Player;

/**
 * 空いてるところをランダムに指定してくるCPUです.
 */
public class EasyCPU implements Player {
    private final int id;

    public EasyCPU(int id) {
        this.id = id;
    }

    @Override
    public int selectBoardIndex(Board board) {
        /*
        try {
            board.insert(1,4);
        } catch (BoardIndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (InvalidPlayerIdException e) {
            e.printStackTrace();
        }*/
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
