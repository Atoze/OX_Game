package jp.co.topgate.atoze.ox;

import jp.co.topgate.atoze.ox.basic.SquaredBoard;
import jp.co.topgate.atoze.ox.exception.BoardIndexOutOfBoundsException;
import jp.co.topgate.atoze.ox.exception.InvalidPlayerIdException;
import jp.co.topgate.atoze.ox.exception.PlayersOutOfBoundsException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by atoze on 2017/06/06.
 */
public class OXGameTest {
    @Test(expected = InvalidPlayerIdException.class)
    public void プレイヤーIDが正しくないテスト() throws BoardIndexOutOfBoundsException, InvalidPlayerIdException, PlayersOutOfBoundsException {
        List<Player> players = new ArrayList<>();
        players.add(new EasyCPU(-1));
        players.add(new EasyCPU(2));
        OXGame game = new OXGame(new SquaredBoard(3), players, 3, new CharacterUI());
        game.start();
    }

    @Test(expected = PlayersOutOfBoundsException.class)
    public void プレイヤー人数が足りてないテスト() throws BoardIndexOutOfBoundsException, InvalidPlayerIdException, PlayersOutOfBoundsException {
        List<Player> players = new ArrayList<>();
        players.add(new EasyCPU(1));
        OXGame game = new OXGame(new SquaredBoard(3), players, 3, new CharacterUI());
        game.start();
    }
}
