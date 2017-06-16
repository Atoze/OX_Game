package jp.co.topgate.atoze.ox.board;

import jp.co.topgate.atoze.ox.exception.BoardIndexOutOfBoundsException;
import jp.co.topgate.atoze.ox.exception.InvalidBoardSizeException;
import jp.co.topgate.atoze.ox.exception.InvalidPlayerIdException;
import jp.co.topgate.atoze.ox.exception.RequiredNumberAlignedOutOfBoundsException;
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

    @Test
    public void 横並び確認テスト() throws BoardIndexOutOfBoundsException, InvalidPlayerIdException, RequiredNumberAlignedOutOfBoundsException, InvalidBoardSizeException {
        SquaredBoard board = new SquaredBoard(3);
        board.insert(1, 0);
        assertThat(false, is(board.isHorizontalAligned(1, 1, 3)));
        board.insert(1, 2);
        assertThat(true, is(board.isHorizontalAligned(1, 1, 3)));

        board.insert(2, 4);
        board.insert(2, 3);
        assertThat(true, is(board.isHorizontalAligned(2, 5, 3)));

        board.insert(2, 7);
        board.insert(1, 8);
        assertThat(false, is(board.isHorizontalAligned(2, 6, 3)));
    }

    @Test
    public void 縦並び確認テスト() throws BoardIndexOutOfBoundsException, InvalidPlayerIdException, RequiredNumberAlignedOutOfBoundsException, InvalidBoardSizeException {
        SquaredBoard board = new SquaredBoard(3);
        board.insert(1, 0);
        assertThat(false, is(board.isVerticalAligned(1, 6, 3)));
        board.insert(1, 3);
        assertThat(true, is(board.isVerticalAligned(1, 6, 3)));

        board.insert(2, 1);
        board.insert(2, 4);
        assertThat(true, is(board.isVerticalAligned(2, 7, 3)));

        board.insert(2, 5);
        board.insert(1, 8);
        assertThat(false, is(board.isVerticalAligned(2, 2, 3)));
    }

    @Test
    public void 斜め並び確認テスト() throws BoardIndexOutOfBoundsException, InvalidPlayerIdException, InvalidBoardSizeException, RequiredNumberAlignedOutOfBoundsException {
        SquaredBoard board = new SquaredBoard(3);

        board.insert(1, 0);
        assertThat(false, is(board.isDiagonalAligned(1, 4, 3)));
        board.insert(1, 8);
        assertThat(true, is(board.isDiagonalAligned(1, 4, 3)));

        board.insert(2, 2);
        board.insert(2, 4);
        assertThat(true, is(board.isDiagonalAligned(2, 6, 3)));
    }
}