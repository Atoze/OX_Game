package jp.co.topgate.atoze.ox;

import jp.co.topgate.atoze.ox.basic.SquaredBoard;
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
    public void 範囲内数字の確認テスト() {
        Player player = new EasyCPU(1);
        Board board = new SquaredBoard(3);
        int i = 0;
        while (i < 100) {
            assertThat(player.next(board, new CharacterUI()), is(both(greaterThan(-1)).and(lessThan(9))));
            i++;
        }
    }
}
