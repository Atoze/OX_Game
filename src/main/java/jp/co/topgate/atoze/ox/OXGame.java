package jp.co.topgate.atoze.ox;

import jp.co.topgate.atoze.ox.exception.BoardIndexOutOfBoundsException;
import jp.co.topgate.atoze.ox.exception.InvalidPlayerIdException;
import jp.co.topgate.atoze.ox.exception.PlayersOutOfBoundsException;

import java.util.List;

/**
 * ○×ゲームの処理
 */
public class OXGame {
    private static Board board;
    private List<Player> players;
    private UI ui;

    private int REQUIRED_ALIGNED_NUM;
    private static final int PLAYER_MAX_NUM = 2;
    private static final int PLAYER_MIN_NUM = 2;

    private final int MAX_TURN;

    OXGame(Board board, List<Player> players, int requiredAlignedNum, UI ui, int maxTurn) throws PlayersOutOfBoundsException, InvalidPlayerIdException {
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
                throw new InvalidPlayerIdException();
            }
        }
        this.REQUIRED_ALIGNED_NUM = requiredAlignedNum;
        this.MAX_TURN = maxTurn;
    }

    OXGame(Board board, List<Player> players, int requiredAlignedNum, UI ui) throws PlayersOutOfBoundsException, InvalidPlayerIdException {
        this(board, players, requiredAlignedNum, ui, board.getSize());
    }

    public void start() throws BoardIndexOutOfBoundsException, InvalidPlayerIdException {
        Result result = Result.CONTINUE;
        int playerNum = players.size();
        int currentTurn = 0;
        Board screenBoard = board.clone();

        while (result == Result.CONTINUE) {
            for (int i = 0; i < playerNum; i++) {
                currentTurn++;
                Player currentPlayer = players.get(i);
                ui.printStartTurn(currentPlayer, screenBoard);

                int boardIndex;
                while (true) {
                    boardIndex = currentPlayer.next(screenBoard, ui);
                    if (accept(boardIndex)) {
                        break;
                    }
                }
                board.insert(currentPlayer.getID(), boardIndex);
                screenBoard = board.clone();
                ui.printInsert(currentPlayer, screenBoard, boardIndex);

                result = checkStatus(screenBoard, currentPlayer, boardIndex, currentTurn);
                ui.printGameResult(currentPlayer, players, screenBoard, result);
                if (result != Result.CONTINUE) {
                    break;
                }
            }
        }
    }

    private Result checkStatus(Board board, Player player, int boardIndex, int currentTurn) {
        if (Match.isRowAligned(board, player.getID(), boardIndex, REQUIRED_ALIGNED_NUM) ||
                Match.isColumnAligned(board, player.getID(), boardIndex, REQUIRED_ALIGNED_NUM) ||
                Match.isDiagonalAligned(board, player.getID(), boardIndex, REQUIRED_ALIGNED_NUM)) {
            return Result.WIN;
        }
        if (currentTurn > board.getSize() - 1 || currentTurn > MAX_TURN) {
            return Result.DRAW;
        }
        return Result.CONTINUE;
    }

    private boolean accept(int selectedGridIndex) {
        if (selectedGridIndex < 0 || selectedGridIndex >= board.getSize()) {
            return false;
        }
        return !board.isFilled(selectedGridIndex);
    }
}
