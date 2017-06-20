package jp.co.topgate.atoze.ox;

import jp.co.topgate.atoze.ox.board.SquaredBoard;
import jp.co.topgate.atoze.ox.exception.*;
import jp.co.topgate.atoze.ox.player.EasyCPU;
import jp.co.topgate.atoze.ox.player.HumanPlayer;
import jp.co.topgate.atoze.ox.ui.CharacterUI;

import java.util.ArrayList;
import java.util.List;

/**
 * 　実行クラス
 */
public class Main {
    public static void main(String[] args) {
        List<Player> players = new ArrayList<>();
        UI ui = new CharacterUI();
        players.add(new EasyCPU(UI.BLACK));
        players.add(new HumanPlayer(UI.WHITE, ui));
        try {
            playGomoku(players, 11, ui, 20);
        } catch (PlayersOutOfBoundsException | InvalidPlayerIdException | InvalidBoardSizeException | BoardIndexOutOfBoundsException | RequiredNumberAlignedOutOfBoundsException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * n*nのボードで5個並んだら勝利条件で遊ぶ場合
     * //TODO:いずれOXGameFactoryみたいなのを作った方がよいかもしれない
     */
    private static void playGomoku(List<Player> players, int gridSize, UI ui, int timeLimit) throws PlayersOutOfBoundsException, InvalidPlayerIdException, BoardIndexOutOfBoundsException, InvalidBoardSizeException, RequiredNumberAlignedOutOfBoundsException {
        Board board = new SquaredBoard(gridSize);
        OXGame game = new OXGame(board, players, 5, ui, timeLimit);
        game.start();
    }
}