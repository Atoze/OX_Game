package jp.co.topgate.atoze.ox;

import jp.co.topgate.atoze.ox.basic.SquaredBoard;
import org.junit.Test;

/**
 * Created by atoze on 2017/06/08.
 */
public class MatchTest {
    @Test
    public void Matchのテスト() {
        Match match = new Match();
        Board board = new SquaredBoard(4);
        board.insert(1,0);
        board.insert(1,1);
        board.insert(1,2);
        board.insert(1,3);

        //
        board.insert(1,4);
        board.insert(1,8);
        board.insert(1,12);

        board.insert(1,1);
        //board.insert(1,5);
        board.insert(1,10);
        //board.insert(1,15);

        //match.isAligned(board, board.getColumn());
        //System.out.print(match.countRowAligned(board,1,2));
        //System.out.print(match.countLeftTiltAligned(board,1,5));

    }
}
