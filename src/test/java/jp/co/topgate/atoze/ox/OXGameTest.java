package jp.co.topgate.atoze.ox;

import jp.co.topgate.atoze.ox.board.TestBoard;
import jp.co.topgate.atoze.ox.exception.*;
import jp.co.topgate.atoze.ox.player.EasyCPU;
import jp.co.topgate.atoze.ox.ui.CharacterUI;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by atoze on 2017/06/06.
 */
public class OXGameTest {
    @Test(expected = InvalidPlayerIdException.class)
    public void プレイヤーIDが正しくないテスト() throws BoardIndexOutOfBoundsException, InvalidPlayerIdException, PlayersOutOfBoundsException, InvalidBoardSizeException, RequiredNumberAlignedOutOfBoundsException {
        List<Player> players = new ArrayList<>();
        players.add(new EasyCPU(-1));
        players.add(new EasyCPU(2));
        OXGame game = new OXGame(new TestBoard(3, 3), players, 3, new CharacterUI());
        game.start();
    }

    @Test(expected = PlayersOutOfBoundsException.class)
    public void プレイヤー人数が足りてないテスト() throws BoardIndexOutOfBoundsException, InvalidPlayerIdException, PlayersOutOfBoundsException, InvalidBoardSizeException, RequiredNumberAlignedOutOfBoundsException {
        List<Player> players = new ArrayList<>();
        players.add(new EasyCPU(1));
        OXGame game = new OXGame(new TestBoard(3, 3), players, 3, new CharacterUI());
        game.start();
    }

    @Test(expected = RequiredNumberAlignedOutOfBoundsException.class)
    public void 達成できない勝利条件エラー() throws RequiredNumberAlignedOutOfBoundsException, InvalidPlayerIdException, PlayersOutOfBoundsException {
        //Board board = new TestBoard(3, 3);
        List<Player> players = new ArrayList<>();
        players.add(new EasyCPU(1));
        players.add(new EasyCPU(2));
        new OXGame(new TestBoard(3, 3), players, 5, new CharacterUI());
    }
}
