package jp.co.topgate.atoze.ox;

import jp.co.topgate.atoze.ox.basic.SquaredBoard;
import jp.co.topgate.atoze.ox.exception.BoardIndexOutOfBoundsException;
import jp.co.topgate.atoze.ox.exception.PlayerIdException;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by atoze on 2017/06/07.
 */
public class SquaredBoardTest {
    @Test
    public void 行列指定挿入テスト() throws BoardIndexOutOfBoundsException, PlayerIdException {
        SquaredBoard board = new SquaredBoard(3);
        board.insert(1, 1, 1);
        assertThat(board.isFilled(0), is(true));

        board.insert(1, 1, 2);
        assertThat(board.isFilled(3), is(true));

        board.insert(1, 3, 1);
        assertThat(board.isFilled(2), is(true));
    }

    @Test
    public void 勝利条件テスト() throws BoardIndexOutOfBoundsException, PlayerIdException {
        Board board = new SquaredBoard(3);
        board.insert(1, 0);
        board.insert(1, 1);
        board.insert(1, 2);

        //assertThat(board.matched(), is(true));
    }

    /*
    @Test
    public void プレイヤー対戦決着つかずテスト() {
        Board board = new SquaredBoard(3);
        board.showInsert(1, 0);
        board.showInsert(2, 1);
        board.showInsert(1, 2);
        board.showInsert(2, 3);
        board.showInsert(1, 4);
        board.showInsert(1, 5);
        board.showInsert(2, 6);
        board.showInsert(1, 7);
        board.showInsert(2, 8);

        //assertThat(board.matched(), is(false));
    }


    @Test
    public void 三マス縦列勝利テスト() {
        Board board = new SquaredBoard(3);
        board.showInsert(1, 0);
        board.showInsert(1, 3);
        board.showInsert(1, 6);
        assertThat(board.matched(), is(true));

        board = new SquaredBoard(3);
        board.showInsert(1, 1);
        board.showInsert(1, 4);
        board.showInsert(1, 7);
        assertThat(board.matched(), is(true));

        board = new SquaredBoard(3);
        board.showInsert(1, 2);
        board.showInsert(1, 5);
        board.showInsert(1, 8);
        assertThat(board.matched(), is(true));
    }

    @Test
    public void 三マス横列勝利テスト() {
        Board board = new SquaredBoard(3);
        board.showInsert(1, 0);
        board.showInsert(1, 1);
        board.showInsert(1, 2);
        assertThat(board.matched(), is(true));

        board = new SquaredBoard(3);
        board.showInsert(1, 3);
        board.showInsert(1, 4);
        board.showInsert(1, 5);
        assertThat(board.matched(), is(true));

        board = new SquaredBoard(3);
        board.showInsert(1, 6);
        board.showInsert(1, 7);
        board.showInsert(1, 8);
        assertThat(board.matched(), is(true));
    }

    @Test
    public void 三マス斜列勝利テスト() {
        SquaredBoard board = new SquaredBoard(3);
        board.showInsert(1, 0);
        board.showInsert(1, 4);
        board.showInsert(1, 8);
        assertThat(board.matched(), is(true));

        board = new SquaredBoard(3);
        board.showInsert(1, 2);
        board.showInsert(1, 4);
        board.showInsert(1, 6);
        assertThat(board.matched(), is(true));
    }


    @Test
    public void 四マス縦列勝利テスト() {
        Board board = new SquaredBoard(4);
        board.showInsert(1, 0);
        board.showInsert(1, 4);
        board.showInsert(1, 8);
        board.showInsert(1, 12);
        assertThat(board.matched(), is(true));

        board = new SquaredBoard(4);
        board.showInsert(1, 1);
        board.showInsert(1, 5);
        board.showInsert(1, 9);
        board.showInsert(1, 13);
        assertThat(board.matched(), is(true));

        board = new SquaredBoard(4);
        board.showInsert(1, 2);
        board.showInsert(1, 6);
        board.showInsert(1, 10);
        board.showInsert(1, 14);
        assertThat(board.matched(), is(true));

        board = new SquaredBoard(4);
        board.showInsert(1, 3);
        board.showInsert(1, 7);
        board.showInsert(1, 11);
        board.showInsert(1, 15);
        assertThat(board.matched(), is(true));
    }

    @Test
    public void 四マス横列勝利テスト() {
        Board board = new SquaredBoard(4);
        board.showInsert(1, 0);
        board.showInsert(1, 1);
        board.showInsert(1, 2);
        board.showInsert(1, 3);
        assertThat(board.matched(), is(true));

        board = new SquaredBoard(4);
        board.showInsert(1, 4);
        board.showInsert(1, 5);
        board.showInsert(1, 6);
        board.showInsert(1, 7);
        assertThat(board.matched(), is(true));

        board = new SquaredBoard(4);
        board.showInsert(1, 8);
        board.showInsert(1, 9);
        board.showInsert(1, 10);
        board.showInsert(1, 11);
        assertThat(board.matched(), is(true));

        board = new SquaredBoard(4);
        board.showInsert(1, 12);
        board.showInsert(1, 13);
        board.showInsert(1, 14);
        board.showInsert(1, 15);
        assertThat(board.matched(), is(true));
    }

    @Test
    public void 四マス斜列勝利テスト() {
        Board board = new SquaredBoard(4);
        board.showInsert(1, 0);
        board.showInsert(1, 5);
        board.showInsert(1, 10);
        board.showInsert(1, 15);
        assertThat(board.matched(), is(true));

        board = new SquaredBoard(4);
        board.showInsert(1, 3);
        board.showInsert(1, 6);
        board.showInsert(1, 9);
        board.showInsert(1, 12);
        assertThat(board.matched(), is(true));
    }*/
}
