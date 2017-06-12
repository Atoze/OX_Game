package jp.co.topgate.atoze.ox;

import jp.co.topgate.atoze.ox.exception.BoardIndexOutOfBoundsException;
import jp.co.topgate.atoze.ox.exception.PlayerIdException;
import jp.co.topgate.atoze.ox.exception.PlayersOutOfBoundsException;

import java.util.List;

/**
 * ○×ゲームの処理
 */
public class OXGame {
    private Board board;
    private List<Player> players;
    private UI ui;

    private int REQUIRED_ALIGNED_NUM;
    private static final int PLAYER_MAX_NUM = 2;
    private static final int PLAYER_MIN_NUM = 2;

    private int MAX_TURN;

    public void start(Board board, List<Player> players, int requiredAlignedNum, UI ui) throws PlayersOutOfBoundsException, BoardIndexOutOfBoundsException, PlayerIdException {
        this.board = board;
        this.ui = ui;
        this.players = players;
        if (players.size() > PLAYER_MAX_NUM) {
            throw new PlayersOutOfBoundsException("プレイヤー人数が多すぎます");
        }
        if (players.size() < PLAYER_MIN_NUM) {
            throw new PlayersOutOfBoundsException("必要なプレイヤー人数が足りていません");
        }
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getID() == board.getDefaultValue()) {
                throw new PlayerIdException();
            }
        }
        this.REQUIRED_ALIGNED_NUM = requiredAlignedNum;
        this.MAX_TURN = board.getSize();
        game();
    }

    private void game() throws BoardIndexOutOfBoundsException, PlayerIdException {
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
                int boardIndex = currentPlayer.next(screenBoard);
                ui.showInsert(currentPlayer, screenBoard, boardIndex);
                board.insert(currentPlayer.getID(), boardIndex);

                result = checkStatus(screenBoard, currentPlayer, boardIndex, currentTurn);
                if (result != Result.GAME_GO_ON) {
                    break;
                }
            }
        }
        ui.gameSet(currentPlayer, players, screenBoard, result);
        System.exit(0);
    }

    public Result checkStatus(ScreenBoard board, Player player, int boardIndex, int currentTurn) {
        if (Match.isRowAligned(board, player.getID(), boardIndex, REQUIRED_ALIGNED_NUM) ||
                Match.isColumnAligned(board, player.getID(), boardIndex, REQUIRED_ALIGNED_NUM) ||
                Match.isDiagonalAligned(board, player.getID(), boardIndex, REQUIRED_ALIGNED_NUM)) {
            return Result.WIN;
        }
        if (currentTurn > board.getLength() - 1 || currentTurn > MAX_TURN) {
            return Result.DRAW;
        }
        return Result.GAME_GO_ON;
    }
}
