package jp.co.topgate.atoze.ox;

import jp.co.topgate.atoze.ox.basic.SquaredBoard;
import jp.co.topgate.atoze.ox.exception.BoardIndexOutOfBoundsException;
import jp.co.topgate.atoze.ox.exception.PlayerIdException;
import jp.co.topgate.atoze.ox.exception.PlayersOutOfBoundsException;

import java.util.ArrayList;
import java.util.List;

/**
 * 　実行クラス
 */
public class Main {
    public static void main(String[] args) {
        List<Player> players = new ArrayList<>();
        CharacterUI ui = new CharacterUI();
        players.add(new EasyCPU(ui.X));
        //players.add(new EasyCPU(ui.O));
        players.add(new HumanPlayer(ui.O));
        try {
            playSquaredBoard(players, 3, ui);
        } catch (PlayersOutOfBoundsException | PlayerIdException | BoardIndexOutOfBoundsException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void playSquaredBoard(List<Player> players, int gridNum, UI ui) throws PlayersOutOfBoundsException, PlayerIdException, BoardIndexOutOfBoundsException {
        Board board = new SquaredBoard(gridNum);
        OXGame game = new OXGame();
        game.start(board, players, gridNum, ui);
    }


}