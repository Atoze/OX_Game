package jp.co.topgate.atoze.ox;

/**
 * 勝利判定を行うクラス
 */
public class MatchStatus {

    private final int REQUIRED_ALIGNED_NUM;
    private final int MAX_TURN;

    public MatchStatus(int requiredAlignedNum, int maxTurn) {
        this.REQUIRED_ALIGNED_NUM = requiredAlignedNum;
        this.MAX_TURN = maxTurn;
    }

    /**
     * ゲームの現状の判定を行います.
     *
     * 現在のルールは、以下の通りです.
     * 勝利判定:
     * 値を挿入したプレイヤーのIDが、ボード上でREQUIRED_ALIGNED_NUM分一列に並んだ場合
     *
     * 引き分け判定:
     * 決められたターン数あるいはボードの最大値に一致するターンまでに以内にいずれのプレイヤーも勝利判定をクリアできなかった場合
     *
     * 続行判定:
     * 上記二つどちらにも当てはまらない場合
     *
     * @param board ボードクラス
     * @param player 判定するプレイヤー
     * @param boardIndex 判定するボードの番号
     * @param currentTurn　現在のターン
     */
    public Result checkResult(Board board, Player player, int boardIndex, int currentTurn) {
        if (isRowAligned(board, player.getID(), boardIndex, REQUIRED_ALIGNED_NUM) ||
                isColumnAligned(board, player.getID(), boardIndex, REQUIRED_ALIGNED_NUM) ||
                isDiagonalAligned(board, player.getID(), boardIndex, REQUIRED_ALIGNED_NUM)) {
            return Result.WIN;
        }
        if (currentTurn >= board.getSize() || currentTurn > MAX_TURN) {
            return Result.DRAW;
        }
        return Result.CONTINUE;
    }

    /*
     * ここから下は一直線にいくつ並んだか判定するために使うメソッド群です
     * 現在は2次元しか対応できません
     */

    /**
     * 横列に指定したプレイヤー識別値が指定数分並んだか判定します
     *
     * @param board              表示用ボードクラス
     * @param playerID           挿入するプレイヤーの識別値
     * @param boardIndex         判定するボードの場所
     *                           ここから左、右にみていきます
     * @param requiredAlignedNum 指定数
     * @return 判定する場所から左右に指定数並んだかどうか
     */
    static boolean isRowAligned(Board board, int playerID, int boardIndex, int requiredAlignedNum) {
        return requiredAlignedNum == countRowAligned(board, playerID, boardIndex) + 1;
    }

    /**
     * 縦列に指定したプレイヤー識別値が指定数分並んだか判定します
     *
     * @param board              表示用ボードクラス
     * @param playerID           挿入するプレイヤーの識別値
     * @param boardIndex         判定するボードの場所
     *                           ここから上、下にみていきます
     * @param requiredAlignedNum 指定数
     * @return 判定する場所から上下に指定数並んだかどうか
     */
    static boolean isColumnAligned(Board board, int playerID, int boardIndex, int requiredAlignedNum) {
        return requiredAlignedNum == countColumnAligned(board, playerID, boardIndex) + 1;
    }

    /**
     * 斜めに指定したプレイヤー識別値が指定数分並んだか判定します
     *
     * @param board              表示用ボードクラス
     * @param playerID           挿入するプレイヤーの識別値
     * @param boardIndex         判定するボードの場所
     *                           ここから左上、右下、右上、左下にみていきます
     * @param requiredAlignedNum 指定数
     * @return 判定する場所から斜めに指定数並んだかどうか
     */
    static boolean isDiagonalAligned(Board board, int playerID, int boardIndex, int requiredAlignedNum) {
        return requiredAlignedNum == countLeftTiltAligned(board, playerID, boardIndex) + 1 || requiredAlignedNum == countRightTiltAligned(board, playerID, boardIndex) + 1;
    }

    /**
     * 横列に指定したプレイヤー識別値が指定した場からいくつ並んでいるか
     *
     * @param board      表示用ボードクラス
     * @param playerID   挿入するプレイヤーの識別値
     * @param boardIndex 判定するボードの場所
     *                   ここから左、右にみていきます
     * @return 並んだ数　なお、返す値に自分は含まれません
     */
    static int countRowAligned(Board board, int playerID, int boardIndex) {
        int row = board.getRow();
        int lengthFromSide = boardIndex % row;
        int length = board.getSize();

        int rightCount = 0;
        int leftCount = 0;

        for (int i = 1; lengthFromSide > 0; i++) {
            int nextCol = boardIndex - i;
            if (nextCol < 0 || !board.isFilled(nextCol) || !(playerID == board.getPlayerId(nextCol))) {
                break;
            }
            lengthFromSide = nextCol % row;
            leftCount++;
        }

        lengthFromSide = boardIndex % row;
        for (int i = 1; lengthFromSide < row - 1; i++) {
            int nextCol = boardIndex + i;
            if (nextCol >= length || !board.isFilled(nextCol) || !(playerID == board.getPlayerId(nextCol))) {
                break;
            }
            lengthFromSide = nextCol % row;
            leftCount++;
        }
        return rightCount + leftCount;
    }

    /**
     * 縦列に指定したプレイヤー識別値が指定した場からいくつ並んでいるか
     *
     * @param board      表示用ボードクラス
     * @param playerID   挿入するプレイヤーの識別値
     * @param boardIndex 判定するボードの場所
     *                   ここから上、下にみていきます
     * @return 並んだ数　なお、返す値に自分は含まれません
     */
    static int countColumnAligned(Board board, int playerID, int boardIndex) {
        int column = board.getColumn();
        int row = board.getRow();
        int length = board.getSize();
        int lengthFromTop = (boardIndex / row) % column;

        int downCount = 0;
        int upCount = 0;

        for (int i = 1; lengthFromTop > 0; i++) {
            int nextCol = boardIndex - (i * row);
            if (nextCol < 0 || !board.isFilled(nextCol) || !(playerID == board.getPlayerId(nextCol))) {
                break;
            }
            lengthFromTop = (nextCol / row) % column;
            upCount++;
        }

        lengthFromTop = (boardIndex / row) % column;
        for (int i = 1; lengthFromTop < column - 1; i++) {
            int nextCol = boardIndex + (i * row);
            if (nextCol >= length || !board.isFilled(nextCol) || !(playerID == board.getPlayerId(nextCol))) {
                break;
            }
            lengthFromTop = (nextCol / row) % column;
            downCount++;
        }
        return downCount + upCount;
    }


    /**
     * 左肩上がりに指定したプレイヤー識別値が指定した場からいくつ並んでいるか
     *
     * @param board      表示用ボードクラス
     * @param playerID   挿入するプレイヤーの識別値
     * @param boardIndex 判定するボードの場所
     *                   ここから左上、右下にみていきます
     * @return 並んだ数　なお、返す値に自分は含まれません
     */
    public static int countLeftTiltAligned(Board board, int playerID, int boardIndex) {
        int column = board.getColumn();
        int row = board.getRow();
        int length = board.getSize();

        int lengthFromSide = boardIndex % row;
        int lengthFromTop = (boardIndex / row) % column;

        int upLeftCount = 0;
        int downRightCount = 0;

        //左上
        for (int i = 1; lengthFromTop > 0 && lengthFromSide > 0; i++) {
            int nextCol = boardIndex - (i * row) - i;
            if (nextCol < 0 || !board.isFilled(nextCol) || playerID != board.getPlayerId(nextCol)) {
                break;
            }
            lengthFromSide = nextCol % row;
            lengthFromTop = (nextCol / row) % column;
            upLeftCount++;
        }

        lengthFromSide = boardIndex % row;
        lengthFromTop = (boardIndex / row) % column;
        //右下
        for (int i = 1; lengthFromTop < column - 1 && lengthFromSide < row - 1; i++) {
            int nextCol = boardIndex + (i * row) + i;
            if (nextCol >= length || !board.isFilled(nextCol) || !(playerID == board.getPlayerId(nextCol))) {
                break;
            }
            lengthFromSide = nextCol % row;
            lengthFromTop = (nextCol / row) % column;
            downRightCount++;
        }
        return upLeftCount + downRightCount;
    }

    /**
     * 右肩上がりに指定したプレイヤー識別値が指定した場からいくつ並んでいるか
     *
     * @param board      表示用ボードクラス
     * @param playerID   挿入するプレイヤーの識別値
     * @param boardIndex 判定するボードの場所
     *                   ここから右上、左下にみていきます
     * @return 並んだ数　なお、返す値に自分は含まれません
     */
    public static int countRightTiltAligned(Board board, int playerID, int boardIndex) {
        int column = board.getColumn();
        int row = board.getRow();
        int length = board.getSize();

        int lengthFromSide = boardIndex % row;
        int lengthFromTop = (boardIndex / row) % column;

        int upRightCount = 0;
        int downLeftCount = 0;

        //右上
        for (int i = 1; lengthFromTop > 0 && lengthFromSide < row - 1; i++) {
            int nextCol = boardIndex - (i * row) + i;
            if (nextCol < 0 || !board.isFilled(nextCol) || !(playerID == board.getPlayerId(nextCol))) {
                break;
            }
            lengthFromSide = nextCol % row;
            lengthFromTop = (nextCol / row) % column;
            upRightCount++;
        }

        lengthFromSide = boardIndex % row;
        lengthFromTop = (boardIndex / row) % column;

        //左下
        for (int i = 1; lengthFromTop < column - 1 && lengthFromSide > 0; i++) {
            int nextCol = boardIndex + (i * row) - i;
            if (nextCol >= length || !board.isFilled(nextCol) || !(playerID == board.getPlayerId(nextCol))) {
                break;
            }
            lengthFromSide = nextCol % row;
            lengthFromTop = (nextCol / row) % column;
            downLeftCount++;
        }
        return upRightCount + downLeftCount;
    }
}