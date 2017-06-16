package jp.co.topgate.atoze.ox;

import jp.co.topgate.atoze.ox.exception.BoardIndexOutOfBoundsException;
import jp.co.topgate.atoze.ox.exception.InvalidPlayerIdException;
import jp.co.topgate.atoze.ox.exception.RequiredNumberAlignedOutOfBoundsException;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by atoze on 2017/06/08.
 */
public class OXGameJudgeTest {
    @Test
    public void コンティニューテスト() throws RequiredNumberAlignedOutOfBoundsException {
        BoardImpl board = new TestBoard(3, 3);
        OXGameJudge judge = new OXGameJudge(board, 3, board.getSize());
        assertThat(Result.CONTINUE, is(judge.checkResult(board, new EasyCPU(1),0,0)));
    }

    @Test
    public void ターン経過引き分けテスト() throws RequiredNumberAlignedOutOfBoundsException, BoardIndexOutOfBoundsException, InvalidPlayerIdException {
        BoardImpl board = new TestBoard(3, 3);
        OXGameJudge judge = new OXGameJudge(board, 3, board.getSize());
        board.insert(1,0);
        board.insert(2,1);
        board.insert(3,2);
        board.insert(4,3);
        board.insert(5,4);
        board.insert(6,5);
        board.insert(7,6);
        board.insert(8,7);
        board.insert(9,8);
        assertThat(Result.DRAW, is(judge.checkResult(board, new EasyCPU(1),0,10)));
    }

    @Test
    public void 勝利したテスト() throws RequiredNumberAlignedOutOfBoundsException, BoardIndexOutOfBoundsException, InvalidPlayerIdException {
        BoardImpl board = new TestBoard(3, 3);
        OXGameJudge judge = new OXGameJudge(board, 3, board.getSize());
        Player player = new EasyCPU(1);

        board.insert(player.getID(),0);
        board.insert(player.getID(),1);
        assertThat(Result.WIN, is(judge.checkResult(board, player,2,3)));
    }

    @Test
    public void 勝利がターンギリギリテスト() throws RequiredNumberAlignedOutOfBoundsException, BoardIndexOutOfBoundsException, InvalidPlayerIdException {
        BoardImpl board = new TestBoard(3, 3);
        OXGameJudge judge = new OXGameJudge(board, 3, board.getSize());
        Player player = new EasyCPU(1);

        board.insert(player.getID(),0);
        board.insert(player.getID(),1);
        assertThat(Result.WIN, is(judge.checkResult(board, player,2, board.getSize())));
    }

    @Test(expected = RequiredNumberAlignedOutOfBoundsException.class)
    public void 達成できない勝利条件エラー() throws RequiredNumberAlignedOutOfBoundsException {
        BoardImpl board = new TestBoard(3, 3);
        new OXGameJudge(board, 5, board.getSize());
    }
}
