package jp.co.topgate.atoze.ox;

/**
 * 指定した場所から何個並んでいるかを見るクラスです.
 */
class Match {
    static boolean isRowAligned(ScreenBoard board, int playerID, int gridIndex, int requiredAlignedNum) {
        return requiredAlignedNum == countRowAligned(board, playerID, gridIndex) + 1;
    }

    static boolean isColumnAligned(ScreenBoard board, int playerID, int gridIndex, int requiredAlignedNum) {
        return requiredAlignedNum == countColumnAligned(board, playerID, gridIndex) + 1;
    }

    static boolean isDiagonalAligned(ScreenBoard board, int playerID, int gridIndex, int requiredAlignedNum) {
        return requiredAlignedNum == countLeftTiltAligned(board, playerID, gridIndex) + 1 || requiredAlignedNum == countRightTiltAligned(board, playerID, gridIndex) + 1;
    }


    //自分を含まない数であることの注意
    static int countRowAligned(ScreenBoard board, int playerID, int gridIndex) {
        int row = board.getRow();
        int length = board.getLength();
        int lengthFromSide = gridIndex % row;

        int rightCount = 0;
        int leftCount = 0;
        if (lengthFromSide > 0) {
            for (int i = 1; i < row; i++) {
                int nextCol = gridIndex - i;
                if (nextCol < 0 || !board.isFilled(nextCol) || !(playerID == board.getPlayerId(nextCol))) {
                    break;
                }
                leftCount++;
            }
        }

        if (lengthFromSide < row - 1) {
            for (int i = 1; i < row - lengthFromSide; i++) {
                int nextCol = gridIndex + i;
                if (nextCol >= length || !board.isFilled(nextCol) || !(playerID == board.getPlayerId(nextCol))) {
                    break;
                }
                rightCount++;
            }
        }
        return rightCount + leftCount;
    }

    //自分を含まない数であることの注意
    static int countColumnAligned(ScreenBoard board, int playerID, int gridIndex) {
        int column = board.getColumn();
        int row = board.getRow();
        int length = board.getLength();
        int lengthFromTop = (gridIndex / row) % column;

        int downCount = 0;
        int upCount = 0;

        if (lengthFromTop > 0) {
            for (int i = 1; i < column; i++) {
                int nextCol = gridIndex - (i * row);
                if (nextCol < 0 || !board.isFilled(nextCol) || !(playerID == board.getPlayerId(nextCol))) {
                    break;
                }
                upCount++;
            }
        }

        if (lengthFromTop < column - 1) {
            for (int i = 1; i < column - lengthFromTop; i++) {
                int nextCol = gridIndex + (i * row);
                if (nextCol >= length || !board.isFilled(nextCol) || !(playerID == board.getPlayerId(nextCol))) {
                    break;
                }
                downCount++;
            }
        }
        return downCount + upCount;
    }


    public static int countLeftTiltAligned(ScreenBoard board, int playerID, int gridIndex) {
        int column = board.getColumn();
        int row = board.getRow();
        int length = board.getLength();

        int lengthFromSide = gridIndex % row;
        int lengthFromTop = (gridIndex / row) % column;

        int maxMatchNum = Math.min(column, row);

        int upLeftCount = 0;
        int downRightCount = 0;

        //左上へ向かう
        if (lengthFromTop > 0 && lengthFromSide > 0) {
            for (int i = 1; i < maxMatchNum; i++) {
                int nextCol = gridIndex - (i * row) - i;
                if (nextCol < 0 || !board.isFilled(nextCol) || !(playerID == board.getPlayerId(nextCol))) {
                    break;
                }
                upLeftCount++;
            }
        }

        //右下
        if (lengthFromTop < column - 1 && lengthFromSide < row - 1) {
            for (int i = 1; i < maxMatchNum; i++) {
                int nextCol = gridIndex + (i * row) + i;
                if (nextCol >= length || !board.isFilled(nextCol) || !(playerID == board.getPlayerId(nextCol))) {
                    break;
                }
                downRightCount++;
            }
        }
        return upLeftCount + downRightCount;
    }


    public static int countRightTiltAligned(ScreenBoard board, int playerID, int gridIndex) {
        int column = board.getColumn();
        int row = board.getRow();
        int length = board.getLength();
        int maxMatchNum = Math.min(column, row);

        int lengthFromSide = gridIndex % row;
        int lengthFromTop = (gridIndex / row) % column;

        int upRightCount = 0;
        int downLeftCount = 0;

        //右上
        if (lengthFromTop > 0 && lengthFromSide < row - 1) {
            for (int i = 1; i < maxMatchNum; i++) {
                int nextCol = gridIndex - (i * row) + i;
                if (nextCol < 0 || !board.isFilled(nextCol) || !(playerID == board.getPlayerId(nextCol))) {
                    break;
                }
                upRightCount++;
            }
        }
        //左下
        if (lengthFromTop < column - 1 && lengthFromSide > 0) {
            for (int i = 1; i < maxMatchNum; i++) {
                int nextCol = gridIndex + (i * row) - i;
                if (nextCol >= length || !board.isFilled(nextCol) || !(playerID == board.getPlayerId(nextCol))) {
                    break;
                }
                downLeftCount++;
            }
        }
        return upRightCount + downLeftCount;
    }

}
