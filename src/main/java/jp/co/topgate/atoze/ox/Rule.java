package jp.co.topgate.atoze.ox;

/**
 * プレイヤーがマスに置く際に、制約をかけるクラスです.
 */
public class Rule {
    private final Board board;
    private final int REQUIRED_ALIGNED_NUM;

    Rule(Board board) {
        this.board = board;
        REQUIRED_ALIGNED_NUM = Math.max(board.getColumn(), board.getRow());
    }

    Rule(Board board, int requiredAlignedNum) {
        this.board = board;
        REQUIRED_ALIGNED_NUM = requiredAlignedNum;
    }


    public boolean accept(int selectedGridIndex) {
        if (selectedGridIndex < 0 || selectedGridIndex >= board.getLength()) {
            return false;
        }
        return !board.isFilled(selectedGridIndex);
    }

    public void insert(int playerId, int gridIndex) {
        board.insert(playerId, gridIndex);
    }

    public Board getBoard() {
        return board;
    }

    public Status checkStatus(int playerID, int gridIndex, int currentTurn) {
        if (Match.isRowAligned(board, playerID, gridIndex, REQUIRED_ALIGNED_NUM) ||
                Match.isColumnAligned(board, playerID, gridIndex, REQUIRED_ALIGNED_NUM) ||
                Match.isDiagonalAligned(board, playerID, gridIndex, REQUIRED_ALIGNED_NUM)) {
            return Status.WIN;
        }
        if (currentTurn > board.getLength() - 1) {
            return Status.DRAW;
        }
        return Status.GAME_GO_ON;
    }
}
