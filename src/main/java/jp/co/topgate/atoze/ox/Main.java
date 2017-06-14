package jp.co.topgate.atoze.ox;

import jp.co.topgate.atoze.ox.basic.SquaredBoard;
import jp.co.topgate.atoze.ox.exception.BoardIndexOutOfBoundsException;
import jp.co.topgate.atoze.ox.exception.InvalidPlayerIdException;
import jp.co.topgate.atoze.ox.exception.PlayersOutOfBoundsException;

import java.util.ArrayList;
import java.util.List;

/**
 * 　実行クラス
 */
public class Main {
    public static void main(String[] args) {
        List<Player> players = new ArrayList<>();
        UI ui = new CharacterUI();
        players.add(new EasyCPU(1));
        players.add(new EasyCPU(2));
        //players.add(new HumanPlayer(2000000));
        try {
            playSquaredBoard(players, 3, ui);
        } catch (PlayersOutOfBoundsException | InvalidPlayerIdException | BoardIndexOutOfBoundsException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * n*nのボードでn個並んだら勝利条件で遊ぶ場合
     * //TODO:いずれOXGameFactoryみたいなのを作った方がよいかもしれない
     */
    private static void playSquaredBoard(List<Player> players, int gridSize, UI ui) throws PlayersOutOfBoundsException, InvalidPlayerIdException, BoardIndexOutOfBoundsException {
        Board board = new SquaredBoard(gridSize);
        OXGame game = new OXGame(board, players, gridSize, ui);
        game.start();
    }


}