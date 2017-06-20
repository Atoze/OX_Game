package jp.co.topgate.atoze.ox;

import jp.co.topgate.atoze.ox.board.TestBoard;
import jp.co.topgate.atoze.ox.exception.*;
import jp.co.topgate.atoze.ox.player.EasyCPU;
import jp.co.topgate.atoze.ox.ui.CharacterUI;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by atoze on 2017/06/06.
 */
public class OXGameTest {
    @Test(expected = InvalidPlayerIdException.class)
    public void プレイヤーIDが正しくないテスト() throws BoardIndexOutOfBoundsException, InvalidPlayerIdException, PlayersOutOfBoundsException, InvalidBoardSizeException, RequiredNumberAlignedOutOfBoundsException {
        List<Player> players = new ArrayList<>();
        players.add(new EasyCPU(-1));
        players.add(new EasyCPU(2));
        OXGame game = new OXGame(new TestBoard(3, 3), players, 3, new CharacterUI(), 10);
        game.start();
    }

    @Test(expected = PlayersOutOfBoundsException.class)
    public void プレイヤー人数が足りてないテスト() throws BoardIndexOutOfBoundsException, InvalidPlayerIdException, PlayersOutOfBoundsException, InvalidBoardSizeException, RequiredNumberAlignedOutOfBoundsException {
        List<Player> players = new ArrayList<>();
        players.add(new EasyCPU(1));
        OXGame game = new OXGame(new TestBoard(3, 3), players, 3, new CharacterUI(), 10);
        game.start();
    }

    @Test(expected = RequiredNumberAlignedOutOfBoundsException.class)
    public void 達成できない勝利条件エラー() throws RequiredNumberAlignedOutOfBoundsException, InvalidPlayerIdException, PlayersOutOfBoundsException {
        //Board board = new TestBoard(3, 3);
        List<Player> players = new ArrayList<>();
        players.add(new EasyCPU(1));
        players.add(new EasyCPU(2));
        new OXGame(new TestBoard(3, 3), players, 5, new CharacterUI(), 10);
    }

    OXGame game;

    @Before
    public void OXGameコンストラクタ作成() throws InvalidBoardSizeException, InvalidPlayerIdException, PlayersOutOfBoundsException, RequiredNumberAlignedOutOfBoundsException {
        List<Player> players = new ArrayList<>();
        players.add(new EasyCPU(1));
        players.add(new EasyCPU(2));
        Board board = new TestBoard(3, 3);
        UI ui = new CharacterUI();
        game = new OXGame(board, players, 3, ui, 10);
    }

    @Test
    public void コンティニューテスト() throws RequiredNumberAlignedOutOfBoundsException {
        Player player = game.getPlayers().get(0);
        assertThat(true, is((!game.isWin(player, 0)) && !game.isDraw(0)));
    }

    @Test
    public void ターン経過引き分けテスト() throws RequiredNumberAlignedOutOfBoundsException, BoardIndexOutOfBoundsException, InvalidPlayerIdException {
        assertThat(true, is(game.isDraw(10)));
    }

    @Test
    public void 勝利したテスト() throws RequiredNumberAlignedOutOfBoundsException, BoardIndexOutOfBoundsException, InvalidPlayerIdException {
        Board board = game.getBoard();
        Player player = game.getPlayers().get(0);

        board.insert(player.getID(), 0);
        board.insert(player.getID(), 1);
        assertThat(true, is(game.isWin(player, 2)));
    }
}
