package jp.co.topgate.atoze.ox;

import jp.co.topgate.atoze.ox.basic.SquaredBoard;
import jp.co.topgate.atoze.ox.exception.BoardIndexOutOfBoundsException;
import jp.co.topgate.atoze.ox.exception.InvalidBoardSizeException;
import jp.co.topgate.atoze.ox.exception.InvalidPlayerIdException;
import jp.co.topgate.atoze.ox.exception.RequiredNumberAlignedOutOfBoundsException;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by atoze on 2017/06/08.
 */
public class MatchStatusTest {
    @Test
    public void コンティニューテスト() throws RequiredNumberAlignedOutOfBoundsException {
        BoardImpl board = new TestBoard(3, 3);
        MatchStatus match = new MatchStatus(board, 3, board.getSize());
        assertThat(Result.CONTINUE, is(match.checkResult(board, new EasyCPU(1),0,0)));
    }

    @Test
    public void ターン経過引き分けテスト() throws RequiredNumberAlignedOutOfBoundsException, BoardIndexOutOfBoundsException, InvalidPlayerIdException {
        BoardImpl board = new TestBoard(3, 3);
        MatchStatus match = new MatchStatus(board, 3, board.getSize());
        board.insert(1,0);
        board.insert(2,1);
        board.insert(3,2);
        board.insert(4,3);
        board.insert(5,4);
        board.insert(6,5);
        board.insert(7,6);
        board.insert(8,7);
        board.insert(9,8);
        assertThat(Result.DRAW, is(match.checkResult(board, new EasyCPU(1),0,10)));
    }

    @Test
    public void 勝利したテスト() throws RequiredNumberAlignedOutOfBoundsException, BoardIndexOutOfBoundsException, InvalidPlayerIdException {
        BoardImpl board = new TestBoard(3, 3);
        MatchStatus match = new MatchStatus(board, 3, board.getSize());
        Player player = new EasyCPU(1);

        board.insert(player.getID(),0);
        board.insert(player.getID(),1);
        assertThat(Result.WIN, is(match.checkResult(board, player,2,3)));
    }

    @Test
    public void 勝利がターンギリギリテスト() throws RequiredNumberAlignedOutOfBoundsException, BoardIndexOutOfBoundsException, InvalidPlayerIdException {
        BoardImpl board = new TestBoard(3, 3);
        MatchStatus match = new MatchStatus(board, 3, board.getSize());
        Player player = new EasyCPU(1);

        board.insert(player.getID(),0);
        board.insert(player.getID(),1);
        assertThat(Result.WIN, is(match.checkResult(board, player,2,9)));
    }

    //優先度的にはDRAW > WIN
    @Test
    public void 勝利しているけどターンオーバーテスト() throws RequiredNumberAlignedOutOfBoundsException, BoardIndexOutOfBoundsException, InvalidPlayerIdException {
        BoardImpl board = new TestBoard(3, 3);
        MatchStatus match = new MatchStatus(board, 3, board.getSize());
        Player player = new EasyCPU(1);

        board.insert(player.getID(),0);
        board.insert(player.getID(),1);
        assertThat(Result.DRAW, is(match.checkResult(board, player,2,10)));
    }

    @Test(expected = RequiredNumberAlignedOutOfBoundsException.class)
    public void 達成できない勝利条件エラー() throws RequiredNumberAlignedOutOfBoundsException {
        BoardImpl board = new TestBoard(3, 3);
        new MatchStatus(board, 5, board.getSize());
    }

    @Test
    public void Match3on3横並び確認テスト() throws BoardIndexOutOfBoundsException, InvalidPlayerIdException, RequiredNumberAlignedOutOfBoundsException, InvalidBoardSizeException {
        BoardImpl board = new TestBoard(3, 3);
        MatchStatus match = new MatchStatus(board, 3, board.getSize());
        board.insert(1, 0);
        assertThat(false, is(match.isHorizontalAligned(board, 1, 1, 3)));
        board.insert(1, 2);
        assertThat(true, is(match.isHorizontalAligned(board, 1, 1, 3)));

        board.insert(2, 4);
        board.insert(2, 3);
        assertThat(true, is(match.isHorizontalAligned(board, 2, 5, 3)));

        board.insert(2, 7);
        board.insert(1, 8);
        assertThat(false, is(match.isHorizontalAligned(board, 2, 6, 3)));
    }

    @Test
    public void Match3on3縦並び確認テスト() throws BoardIndexOutOfBoundsException, InvalidPlayerIdException, RequiredNumberAlignedOutOfBoundsException, InvalidBoardSizeException {
        BoardImpl board = new TestBoard(3, 3);
        MatchStatus match = new MatchStatus(board, 3, board.getSize());
        board.insert(1, 0);
        assertThat(false, is(match.isVerticalAligned(board, 1, 6, 3)));
        board.insert(1, 3);
        assertThat(true, is(match.isVerticalAligned(board, 1, 6, 3)));

        board.insert(2, 1);
        board.insert(2, 4);
        assertThat(true, is(match.isVerticalAligned(board, 2, 7, 3)));

        board.insert(2, 5);
        board.insert(1, 8);
        assertThat(false, is(match.isVerticalAligned(board, 2, 2, 3)));
    }

    @Test
    public void Match3on3斜め並び確認テスト() throws BoardIndexOutOfBoundsException, InvalidPlayerIdException, InvalidBoardSizeException, RequiredNumberAlignedOutOfBoundsException {
        BoardImpl board = new TestBoard(3, 3);
        MatchStatus match = new MatchStatus(board, 3, board.getSize());

        board.insert(1, 0);
        assertThat(false, is(match.isDiagonalAligned(board, 1, 4, 3)));
        board.insert(1, 8);
        assertThat(true, is(match.isDiagonalAligned(board, 1, 4, 3)));

        board.insert(2, 2);
        board.insert(2, 4);
        assertThat(true, is(match.isDiagonalAligned(board, 2, 6, 3)));
    }


    @Test
    public void Match5on4横並び確認テスト() throws BoardIndexOutOfBoundsException, InvalidPlayerIdException, RequiredNumberAlignedOutOfBoundsException, InvalidBoardSizeException {
        BoardImpl board = new TestBoard(5, 4);
        int requireNum = 4;
        MatchStatus match = new MatchStatus(board, requireNum, board.getSize());
        assertThat(false, is(match.isHorizontalAligned(board, 1, 0, requireNum)));
        board.insert(1, 1);
        board.insert(1, 2);
        board.insert(1, 3);
        assertThat(true, is(match.isHorizontalAligned(board, 1, 0, requireNum)));

        board.insert(2, 5);
        board.insert(2, 7);
        board.insert(2, 8);
        assertThat(true, is(match.isHorizontalAligned(board, 2, 6, requireNum)));

        board.insert(2, 11);
        board.insert(2, 13);
        board.insert(2, 14);
        assertThat(true, is(match.isHorizontalAligned(board, 2, 12, requireNum)));

        board.insert(1, 16);
        board.insert(2, 17);
        board.insert(1, 18);
        assertThat(false, is(match.isHorizontalAligned(board, 1, 19, requireNum)));

        board.insert(1, 17);
        assertThat(true, is(match.isHorizontalAligned(board, 1, 19, requireNum)));
    }

    @Test
    public void Match5on4縦並び確認テスト() throws BoardIndexOutOfBoundsException, InvalidPlayerIdException, RequiredNumberAlignedOutOfBoundsException, InvalidBoardSizeException {
        BoardImpl board = new SquaredBoard(3);
        MatchStatus match = new MatchStatus(board, 3, board.getSize());
        board.insert(1, 0);
        assertThat(false, is(match.isVerticalAligned(board, 1, 6, 3)));
        board.insert(1, 3);
        assertThat(true, is(match.isVerticalAligned(board, 1, 6, 3)));

        board.insert(2, 1);
        board.insert(2, 4);
        assertThat(true, is(match.isVerticalAligned(board, 2, 7, 3)));

        board.insert(2, 5);
        board.insert(1, 8);
        assertThat(false, is(match.isVerticalAligned(board, 2, 2, 3)));
    }

    @Test
    public void Match5on4斜め並び確認テスト() throws BoardIndexOutOfBoundsException, InvalidPlayerIdException, InvalidBoardSizeException, RequiredNumberAlignedOutOfBoundsException {
        BoardImpl board = new SquaredBoard(3);
        MatchStatus match = new MatchStatus(board, 3, board.getSize());

        board.insert(1, 0);
        assertThat(false, is(match.isDiagonalAligned(board, 1, 4, 3)));
        board.insert(1, 8);
        assertThat(true, is(match.isDiagonalAligned(board, 1, 4, 3)));

        board.insert(2, 2);
        board.insert(2, 4);
        assertThat(true, is(match.isDiagonalAligned(board, 2, 6, 3)));
    }
}
