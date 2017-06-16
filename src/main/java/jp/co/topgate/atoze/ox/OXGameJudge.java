package jp.co.topgate.atoze.ox;

import jp.co.topgate.atoze.ox.exception.RequiredNumberAlignedOutOfBoundsException;

/**
 * 勝利判定条件を担うクラス
 */
public class OXGameJudge {

    final int REQUIRED_ALIGNED_NUM;
    final int MAX_TURN;
    final int BOARD_MAX_TURN;

    public OXGameJudge(Board board, int requiredAlignedNum, int maxTurn) throws RequiredNumberAlignedOutOfBoundsException {
        if (requiredAlignedNum <= 0) {
            throw new RequiredNumberAlignedOutOfBoundsException("一列に並べられる数ではありません");
        }
        if (board.getMinSideLength() < requiredAlignedNum) {
            throw new RequiredNumberAlignedOutOfBoundsException("勝利条件に用いられる一列に必要な数が、ボード上に斜めに並べる数を超えています");
        }
        if (board.getMaxSideLength() < requiredAlignedNum) {
            throw new RequiredNumberAlignedOutOfBoundsException("勝利条件に用いられる一列に必要な数が、ボード上に並べる数を超えています");
        }
        this.REQUIRED_ALIGNED_NUM = requiredAlignedNum;
        this.MAX_TURN = maxTurn;
        this.BOARD_MAX_TURN = board.getSize();
    }

    public boolean isWin(Board board, Player player, int boardIndex) {
        return board.isAligned(player.getID(), boardIndex, REQUIRED_ALIGNED_NUM);
    }

    public boolean isLose() {
        return false;
    }

    public boolean isDraw(int currentTurn) {
        return (currentTurn >= BOARD_MAX_TURN || currentTurn >= MAX_TURN);
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
     * @param board       ボードクラス
     * @param player      判定するプレイヤー
     * @param boardIndex  判定するボードの番号
     * @param currentTurn 　現在のターン
     */
    /*
    public Result checkResult(Board board, Player player, int boardIndex, int currentTurn) {
        if (board.isAligned(player.getID(), boardIndex, REQUIRED_ALIGNED_NUM)) {
            return Result.WIN;
        }
        if (currentTurn >= board.getSize() || currentTurn >= MAX_TURN) {
            return Result.DRAW;
        }
        return Result.CONTINUE;
    }*/
    public boolean accept(Board board, int selectedGridIndex) {
        if (selectedGridIndex < 0 || selectedGridIndex >= board.getSize()) {
            return false;
        }
        return !board.isFilled(selectedGridIndex);
    }
}