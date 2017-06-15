package jp.co.topgate.atoze.ox;

import jp.co.topgate.atoze.ox.basic.SquaredBoard;
import jp.co.topgate.atoze.ox.exception.InvalidBoardSizeException;
import org.junit.Test;

import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;

/**
 * Created by atoze on 2017/06/12.
 */
public class EasyCPUTest {
    @Test
    public void 範囲内数字の確認テストSquaredBoard() throws InvalidBoardSizeException {
        Player player = new EasyCPU(1);
        Board board = new SquaredBoard(3);
        int i = 0;
        while (i < 100) {
            assertThat(player.selectBoardIndex(board), is(both(greaterThan(-1)).and(lessThan(9))));
            i++;
        }
    }

    @Test
    public void 範囲内数字の確認テスト() throws InvalidBoardSizeException {
        Player player = new EasyCPU(1);
        Board board = new TestBoard(3,4);
        int i = 0;
        while (i < 100) {
            assertThat(player.selectBoardIndex(board), is(both(greaterThan(-1)).and(lessThan(12))));
            i++;
        }
    }
}
