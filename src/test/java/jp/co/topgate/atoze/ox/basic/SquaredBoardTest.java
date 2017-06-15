package jp.co.topgate.atoze.ox.basic;

import jp.co.topgate.atoze.ox.exception.BoardIndexOutOfBoundsException;
import jp.co.topgate.atoze.ox.exception.InvalidBoardSizeException;
import jp.co.topgate.atoze.ox.exception.InvalidPlayerIdException;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by atoze on 2017/06/07.
 */
public class SquaredBoardTest {
    @Test
    public void 挿入テスト() throws BoardIndexOutOfBoundsException, InvalidPlayerIdException, InvalidBoardSizeException {
        SquaredBoard board = new SquaredBoard(3);
        for (int i = 0; i < (3 * 3); i++) {
            board.insert(1, i);
            assertThat(board.isFilled(i), is(true));
        }
    }

    @Test(expected = BoardIndexOutOfBoundsException.class)
    public void 上限超えた範囲外テスト() throws BoardIndexOutOfBoundsException, InvalidPlayerIdException, InvalidBoardSizeException {
        SquaredBoard board = new SquaredBoard(3);
        board.insert(1, 9);
    }

    @Test(expected = BoardIndexOutOfBoundsException.class)
    public void 下限超えた範囲外テスト() throws BoardIndexOutOfBoundsException, InvalidPlayerIdException, InvalidBoardSizeException {
        SquaredBoard board = new SquaredBoard(3);
        board.insert(1, -1);
    }

    @Test
    public void 行列指定挿入テスト() throws BoardIndexOutOfBoundsException, InvalidPlayerIdException, InvalidBoardSizeException {
        SquaredBoard board = new SquaredBoard(3);
        assertThat(board.isFilled(0), is(false));
        board.insert(1, 1, 1);
        assertThat(board.isFilled(0), is(true));

        board.insert(1, 1, 2);
        assertThat(board.isFilled(3), is(true));

        board.insert(1, 3, 1);
        assertThat(board.isFilled(2), is(true));
    }
}
