package jp.co.topgate.atoze.ox;

/**
 * Created by atoze on 2017/06/12.
 */
public class Match {
    static boolean isRowAligned(ScreenBoard board, int playerID, int boardIndex, int requiredAlignedNum) {
        return requiredAlignedNum == countRowAligned(board, playerID, boardIndex) + 1;
    }

    static boolean isColumnAligned(ScreenBoard board, int playerID, int boardIndex, int requiredAlignedNum) {
        return requiredAlignedNum == countColumnAligned(board, playerID, boardIndex) + 1;
    }

    static boolean isDiagonalAligned(ScreenBoard board, int playerID, int boardIndex, int requiredAlignedNum) {
        return requiredAlignedNum == countLeftTiltAligned(board, playerID, boardIndex) + 1 || requiredAlignedNum == countRightTiltAligned(board, playerID, boardIndex) + 1;
    }

    //自分を含まない数であることの注意
    static int countRowAligned(ScreenBoard board, int playerID, int boardIndex) {
        int row = board.getRow();
        int lengthFromSide = boardIndex % row;
        int length = board.getLength();

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

    //自分を含まない数であることの注意
    static int countColumnAligned(ScreenBoard board, int playerID, int boardIndex) {
        int column = board.getColumn();
        int row = board.getRow();
        int length = board.getLength();
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


    public static int countLeftTiltAligned(ScreenBoard board, int playerID, int boardIndex) {
        int column = board.getColumn();
        int row = board.getRow();
        int length = board.getLength();

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


    public static int countRightTiltAligned(ScreenBoard board, int playerID, int boardIndex) {
        int column = board.getColumn();
        int row = board.getRow();
        int length = board.getLength();

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