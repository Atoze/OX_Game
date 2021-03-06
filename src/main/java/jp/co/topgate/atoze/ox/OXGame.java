package jp.co.topgate.atoze.ox;

import jp.co.topgate.atoze.ox.exception.BoardIndexOutOfBoundsException;
import jp.co.topgate.atoze.ox.exception.InvalidPlayerIdException;
import jp.co.topgate.atoze.ox.exception.PlayersOutOfBoundsException;
import jp.co.topgate.atoze.ox.exception.RequiredNumberAlignedOutOfBoundsException;

import java.util.ArrayList;
import java.util.List;

/**
 * ○×ゲームの処理
 */
public class OXGame {
    private final Board board;
    private final List<Player> players;
    private final UI ui;

    private static final int PLAYER_MAX_NUM = 2;
    private static final int PLAYER_MIN_NUM = 2;
    private static final int TIME_PRINT_INTERVAL = 5;

    private final int requiredAlignedNum;
    private final int maxTurn;

    private final int timeLimit;

    public OXGame(Board board, List<Player> players, int requiredAlignedNum, UI ui, int maxTurn, int timeLimit) throws PlayersOutOfBoundsException, InvalidPlayerIdException, RequiredNumberAlignedOutOfBoundsException {
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

        if (requiredAlignedNum <= 0) {
            throw new RequiredNumberAlignedOutOfBoundsException("一列に並べられる数ではありません");
        }
        if (board.getMinSideLength() < requiredAlignedNum) {
            throw new RequiredNumberAlignedOutOfBoundsException("勝利条件に用いられる一列に必要な数が、ボード上に斜めに並べる数を超えています");
        }
        if (board.getMaxSideLength() < requiredAlignedNum) {
            throw new RequiredNumberAlignedOutOfBoundsException("勝利条件に用いられる一列に必要な数が、ボード上に並べる数を超えています");
        }
        this.requiredAlignedNum = requiredAlignedNum;
        this.maxTurn = maxTurn;
        this.timeLimit = timeLimit;
    }

    public OXGame(Board board, List<Player> players, int requiredAlignedNum, UI ui, int timeLimit) throws PlayersOutOfBoundsException, InvalidPlayerIdException, RequiredNumberAlignedOutOfBoundsException {
        this(board, players, requiredAlignedNum, ui, board.getSize(), timeLimit);
    }

    void start() throws BoardIndexOutOfBoundsException, InvalidPlayerIdException {
        int currentTurn = 0;
        while (true) {
            Player currentPlayer = players.get(currentTurn % players.size());
            currentTurn++;
            ui.printStartTurn(currentTurn, currentPlayer, players, board);

            int boardIndex = board.getDefaultValue();
            if (currentTurn == 1) {
                boardIndex = getCenterIndex(board);
            } else {
                Timer timer = new Timer(timeLimit, TIME_PRINT_INTERVAL);
                timer.start();
                while (timer.getTime() > 0 && !accept(board, boardIndex, currentTurn)) {
                    boardIndex = currentPlayer.selectBoardIndex(this, timer);
                }
                timer.shutdown();
                if (boardIndex == board.getDefaultValue()) {
                    while (!accept(board, boardIndex, currentTurn)) {
                        boardIndex = (int) (Math.random() * board.getSize());
                    }
                }
            }
            board.insert(currentPlayer.getID(), boardIndex);
            ui.printInsert(currentPlayer, board, boardIndex);

            Result result = checkResult(currentPlayer, boardIndex, currentTurn);
            ui.printGameResult(currentPlayer, players, board, result);
            if (result != Result.CONTINUE) {
                break;
            }
        }
    }

    private int getCenterIndex(Board board) {
        board.getSize();
        int rowMid = board.getRowValueLength() / 2;
        int colMid = board.getColumnValueLength() / 2;
        return (board.getRowValueLength() * colMid) + rowMid;
    }

    public boolean accept(Board board, int selectedGridIndex, int currentTurn) {
        if (currentTurn == 2) {
            List<Integer> aroundCenter = new ArrayList<>();
            int center = getCenterIndex(board);
            aroundCenter.add(center - 1);
            aroundCenter.add(center + 1);
            aroundCenter.add(center - board.getRowValueLength());
            aroundCenter.add(center - board.getRowValueLength() + 1);
            aroundCenter.add(center - board.getRowValueLength() - 1);
            aroundCenter.add(center + board.getRowValueLength());
            aroundCenter.add(center + board.getRowValueLength() + 1);
            aroundCenter.add(center + board.getRowValueLength() - 1);
            if (!aroundCenter.contains(selectedGridIndex)) {
                return false;
            }
        }

        if (selectedGridIndex < 0 || selectedGridIndex >= board.getSize()) {
            return false;
        }
        return !board.isFilled(selectedGridIndex);
    }

    /**
     * ゲームの現状の判定を行います.
     * <p>
     * 現在のルールは、以下の通りです.
     * 勝利判定:
     * 値を挿入したプレイヤーのIDが、ボード上でREQUIRED_ALIGNED_NUM分一列に並んだ場合
     * <p>
     * 引き分け判定:
     * 決められたターン数あるいはボードの最大値に一致するターンまでに以内にいずれのプレイヤーも勝利判定をクリアできなかった場合
     * <p>
     * 続行判定:
     * 上記二つどちらにも当てはまらない場合
     *
     * @param player      判定するプレイヤー
     * @param boardIndex  判定するボードの番号
     * @param currentTurn 　現在のターン
     */
    private Result checkResult(Player player, int boardIndex, int currentTurn) {
        if (isWin(player, boardIndex)) {
            return Result.WIN;
        }
        if (isDraw(currentTurn)) {
            return Result.DRAW;
        }
        return Result.CONTINUE;
    }

    public boolean isWin(Player player, int boardIndex) {
        return board.isAligned(player.getID(), boardIndex, requiredAlignedNum);
    }

    public boolean isLose(Player player, int boardIndex) {
        return false;
    }

    public boolean isDraw(int currentTurn) {
        return (currentTurn >= board.getSize() || currentTurn >= maxTurn);
    }

    public Board getBoard() {
        return board;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
