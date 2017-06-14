package jp.co.topgate.atoze.ox;

/**
 * 一直線にいくつ並んだか判定するクラス
 * 現在は2次元しか対応できません
 */
public class Match {
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