package jp.co.topgate.atoze.ox;

import jp.co.topgate.atoze.ox.basic.SquaredBoard;
import jp.co.topgate.atoze.ox.exception.BoardIndexOutOfBoundsException;
import jp.co.topgate.atoze.ox.exception.InvalidPlayerIdException;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by atoze on 2017/06/08.
 */
public class MatchStatusTest {
    @Test
    public void Match3on横並び確認テスト() throws BoardIndexOutOfBoundsException, InvalidPlayerIdException {
        Board board = new SquaredBoard(3);
        MatchStatus match = new MatchStatus(3, board.getSize());
        board.insert(1, 0);
        assertThat(false, is(match.isRowAligned(board, 1, 1, 3)));
        board.insert(1, 2);
        assertThat(true, is(match.isRowAligned(board, 1, 1, 3)));

        board.insert(2, 4);
        board.insert(2, 3);
        assertThat(true, is(match.isRowAligned(board, 2, 5, 3)));

        board.insert(2, 7);
        board.insert(1, 8);
        assertThat(false, is(match.isRowAligned(board, 2, 6, 3)));
    }

    @Test
    public void Match3on縦並び確認テスト() throws BoardIndexOutOfBoundsException, InvalidPlayerIdException {
        Board board = new SquaredBoard(3);
        MatchStatus match = new MatchStatus(3, board.getSize());
        board.insert(1, 0);
        assertThat(false, is(match.isColumnAligned(board, 1, 6, 3)));
        board.insert(1, 3);
        assertThat(true, is(match.isColumnAligned(board, 1, 6, 3)));

        board.insert(2, 1);
        board.insert(2, 4);
        assertThat(true, is(match.isColumnAligned(board, 2, 7, 3)));

        board.insert(2, 5);
        board.insert(1, 8);
        assertThat(false, is(match.isColumnAligned(board, 2, 2, 3)));
    }

    @Test
    public void Match3on3斜め並び確認テスト() throws BoardIndexOutOfBoundsException, InvalidPlayerIdException {
        Board board = new SquaredBoard(3);
        MatchStatus match = new MatchStatus(3, board.getSize());

        board.insert(1, 0);
        assertThat(false, is(match.isDiagonalAligned(board, 1, 4, 3)));
        board.insert(1, 8);
        assertThat(true, is(match.isDiagonalAligned(board, 1, 4, 3)));

        board.insert(2, 2);
        board.insert(2, 4);
        assertThat(true, is(match.isDiagonalAligned(board, 2, 6, 3)));
    }
}
