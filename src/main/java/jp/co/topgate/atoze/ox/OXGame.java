package jp.co.topgate.atoze.ox;

import jp.co.topgate.atoze.ox.basic.SquaredBoard;

import java.util.List;

/**
 * Created by atoze on 2017/06/06.
 */
public class OXGame {
    private Board board;
    private List<Player> players;
    private UI ui;

    private int REQUIRED_ALIGNED_NUM;

    public void playSquareBoard(int gridNum, List<Player> players, UI ui) {
        Board board = new SquaredBoard(gridNum);
        start(board, players, gridNum, ui);
    }

    /*
    OXGame(Board board, List<Player> players){
        this.board = board;
        this.players = players;
    }*/

    public void start(Board board, List<Player> players, int requiredAlignedNum, UI ui) {
        this.board = board;
        this.ui = ui;
        this.players = players;
        this.REQUIRED_ALIGNED_NUM = requiredAlignedNum;
        game();
    }

    private void game() {
        Result result = Result.GAME_GO_ON;
        int playerNum = players.size();
        int currentTurn = 0;
        Player currentPlayer = players.get(0);
        ScreenBoard screenBoard = new ScreenBoard(board);

        while (result == Result.GAME_GO_ON) {
            for (int i = 0; i < playerNum; i++) {
                currentTurn++;
                currentPlayer = players.get(i);

                ui.turnStart(currentPlayer, screenBoard);
                int gridIndex = currentPlayer.next(screenBoard);
                ui.insert(currentPlayer, gridIndex);
                board.insert(currentPlayer.getID(), gridIndex);

                result = checkStatus(screenBoard, currentPlayer, gridIndex, currentTurn);
                if (result != Result.GAME_GO_ON) {
                    break;
                }
            }
        }
        ui.gameSet(currentPlayer, screenBoard, result);
        System.exit(0);
    }


    public Result checkStatus(ScreenBoard board, Player player, int gridIndex, int currentTurn) {
        if (Match.isRowAligned(board, player.getID(), gridIndex, REQUIRED_ALIGNED_NUM) ||
                Match.isColumnAligned(board, player.getID(), gridIndex, REQUIRED_ALIGNED_NUM) ||
                Match.isDiagonalAligned(board, player.getID(), gridIndex, REQUIRED_ALIGNED_NUM)) {
            return Result.WIN;
        }
        if (currentTurn > board.getLength() - 1) {
            return Result.DRAW;
        }
        return Result.GAME_GO_ON;
    }
}
