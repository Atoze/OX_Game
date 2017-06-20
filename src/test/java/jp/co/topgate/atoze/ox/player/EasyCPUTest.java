package jp.co.topgate.atoze.ox.player;

import jp.co.topgate.atoze.ox.*;
import jp.co.topgate.atoze.ox.board.SquaredBoard;
import jp.co.topgate.atoze.ox.board.TestBoard;
import jp.co.topgate.atoze.ox.exception.InvalidBoardSizeException;
import jp.co.topgate.atoze.ox.exception.InvalidPlayerIdException;
import jp.co.topgate.atoze.ox.exception.PlayersOutOfBoundsException;
import jp.co.topgate.atoze.ox.exception.RequiredNumberAlignedOutOfBoundsException;
import jp.co.topgate.atoze.ox.ui.CharacterUI;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by atoze on 2017/06/12.
 */
public class EasyCPUTest {
    OXGame game;
    Timer timer;

    @Before
    public void OXGameコンストラクタ作成() throws InvalidBoardSizeException, InvalidPlayerIdException, PlayersOutOfBoundsException, RequiredNumberAlignedOutOfBoundsException {
        List<Player> players = new ArrayList<>();
        players.add(new EasyCPU(1));
        players.add(new EasyCPU(2));
        Board board = new SquaredBoard(3);
        UI ui = new CharacterUI();
        game = new OXGame(board, players, 3, ui, 10);
        timer = new Timer(10, 1, ui);
    }

    @Test
    public void 範囲内数字の確認テストSquaredBoard() throws InvalidBoardSizeException {
        Player player = new EasyCPU(1);
        Board board = new SquaredBoard(3);

        int i = 0;
        while (i < 100) {
            assertThat(player.selectBoardIndex(game, timer), is(both(greaterThan(-1)).and(lessThan(9))));
            i++;
        }
    }

    @Test
    public void 範囲内数字の確認テスト() throws InvalidBoardSizeException {
        Player player = new EasyCPU(1);
        Board board = new TestBoard(3, 4);
        int i = 0;
        while (i < 100) {
            assertThat(player.selectBoardIndex(game, timer), is(both(greaterThan(-1)).and(lessThan(12))));
            i++;
        }
    }
}
