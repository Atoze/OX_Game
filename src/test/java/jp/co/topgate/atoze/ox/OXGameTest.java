package jp.co.topgate.atoze.ox;

import jp.co.topgate.atoze.ox.basic.SquaredBoard;
import jp.co.topgate.atoze.ox.exception.BoardIndexOutOfBoundsException;
import jp.co.topgate.atoze.ox.exception.PlayerIdException;
import jp.co.topgate.atoze.ox.exception.PlayersOutOfBoundsException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by atoze on 2017/06/06.
 */
public class OXGameTest {
    @Test
    public void 正常実行テスト() throws BoardIndexOutOfBoundsException, PlayerIdException, PlayersOutOfBoundsException {
        OXGame game = new OXGame();
        List<Player> players = new ArrayList<>();
        players.add(new EasyCPU(1));
        players.add(new EasyCPU(2));
        game.start(new SquaredBoard(3), players, 3, new CharacterUI());
    }

    @Test(expected = PlayerIdException.class)
    public void プレイヤーIDが正しくないテスト() throws BoardIndexOutOfBoundsException, PlayerIdException, PlayersOutOfBoundsException {
        OXGame game = new OXGame();
        List<Player> players = new ArrayList<>();
        players.add(new EasyCPU(-1));
        players.add(new EasyCPU(2));
        game.start(new SquaredBoard(3), players, 3, new CharacterUI());
    }

    @Test(expected = PlayersOutOfBoundsException.class)
    public void プレイヤー人数が足りてないテスト() throws BoardIndexOutOfBoundsException, PlayerIdException, PlayersOutOfBoundsException {
        OXGame game = new OXGame();
        List<Player> players = new ArrayList<>();
        players.add(new EasyCPU(1));
        game.start(new SquaredBoard(3), players, 3, new CharacterUI());
    }
}
