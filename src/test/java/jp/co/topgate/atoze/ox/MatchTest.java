package jp.co.topgate.atoze.ox;

import jp.co.topgate.atoze.ox.basic.SquaredBoard;
import jp.co.topgate.atoze.ox.exception.BoardIndexOutOfBoundsException;
import jp.co.topgate.atoze.ox.exception.PlayerIdException;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by atoze on 2017/06/08.
 */
public class MatchTest {
    @Test
    public void Match3on横並び() throws BoardIndexOutOfBoundsException, PlayerIdException {
        Match match = new Match();
        Board board = new SquaredBoard(3);
        board.insert(1, 0);
        board.insert(1, 2);
        assertThat(true, is(match.isRowAligned(new ScreenBoard(board), 1, 1, 3)));

        board.insert(2, 3);
        board.insert(2, 4);
        assertThat(true, is(match.isRowAligned(new ScreenBoard(board), 2, 5, 3)));

        board.insert(2, 7);
        board.insert(1, 8);
        assertThat(false, is(match.isRowAligned(new ScreenBoard(board), 2, 6, 3)));

    }
}
