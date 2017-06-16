package jp.co.topgate.atoze.ox;

import jp.co.topgate.atoze.ox.board.TestBoard;
import jp.co.topgate.atoze.ox.exception.BoardIndexOutOfBoundsException;
import jp.co.topgate.atoze.ox.exception.InvalidPlayerIdException;
import jp.co.topgate.atoze.ox.exception.RequiredNumberAlignedOutOfBoundsException;
import jp.co.topgate.atoze.ox.player.EasyCPU;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by atoze on 2017/06/08.
 */
public class OXGameJudgeTest {

    @Test
    public void コンティニューテスト() throws RequiredNumberAlignedOutOfBoundsException {
        Board board = new TestBoard(3, 3);
        OXGameJudge judge = new OXGameJudge(board, 3, board.getSize());
        assertThat(true, is((!judge.isWin(board, new EasyCPU(1), 0)) && !judge.isDraw(0)));
    }

    @Test
    public void ターン経過引き分けテスト() throws RequiredNumberAlignedOutOfBoundsException, BoardIndexOutOfBoundsException, InvalidPlayerIdException {
        Board board = new TestBoard(3, 3);
        OXGameJudge judge = new OXGameJudge(board, 3, board.getSize());
        board.insert(1, 0);
        board.insert(2, 1);
        board.insert(3, 2);
        board.insert(4, 3);
        board.insert(5, 4);
        board.insert(6, 5);
        board.insert(7, 6);
        board.insert(8, 7);
        board.insert(9, 8);
        assertThat(true, is(judge.isDraw(10)));
    }

    @Test
    public void 勝利したテスト() throws RequiredNumberAlignedOutOfBoundsException, BoardIndexOutOfBoundsException, InvalidPlayerIdException {
        Board board = new TestBoard(3, 3);
        OXGameJudge judge = new OXGameJudge(board, 3, board.getSize());
        Player player = new EasyCPU(1);

        board.insert(player.getID(), 0);
        board.insert(player.getID(), 1);
        assertThat(true, is(judge.isWin(board, player, 2)));
    }

    /*
    @Test
    public void 勝利がターンギリギリテスト() throws RequiredNumberAlignedOutOfBoundsException, BoardIndexOutOfBoundsException, InvalidPlayerIdException {
        Board board = new TestBoard(3, 3);
        OXGameJudge judge = new OXGameJudge(board, 3, board.getSize());
        Player player = new EasyCPU(1);

        board.insert(player.getID(), 0);
        board.insert(player.getID(), 1);
        assertThat(true, is(judge.isWin(board, player, 2)));
    }
    */


    @Test(expected = RequiredNumberAlignedOutOfBoundsException.class)
    public void 達成できない勝利条件エラー() throws RequiredNumberAlignedOutOfBoundsException {
        Board board = new TestBoard(3, 3);
        new OXGameJudge(board, 5, board.getSize());
    }
}
