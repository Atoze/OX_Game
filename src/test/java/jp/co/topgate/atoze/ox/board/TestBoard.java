package jp.co.topgate.atoze.ox.board;

import jp.co.topgate.atoze.ox.Board;
import jp.co.topgate.atoze.ox.exception.BoardIndexOutOfBoundsException;
import jp.co.topgate.atoze.ox.exception.InvalidPlayerIdException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by atoze on 2017/06/15.
 */
public class TestBoard extends Board {
    private final static int DEFAULT_VALUE = -1;

    private final int column;

    private final int row;

    private final int size;

    List<Integer> board;

    public TestBoard(int row, int column) {
        this.column = column;
        this.row = row;
        this.size = column * row;
        this.board = new ArrayList<>(Collections.nCopies(size, DEFAULT_VALUE));
    }

    @Override
    public boolean isFilled(int boardIndex) {
        return !(board.get(boardIndex) == DEFAULT_VALUE);
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getColumn() {
        return column;
    }

    @Override
    public int getMaxSideLength() {
        return Math.max(row, column);
    }

    @Override
    public int getMinSideLength() {
        return Math.min(row, column);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getPlayerId(int boardIndex) {
        return board.get(boardIndex);
    }

    @Override
    public int getDefaultValue() {
        return DEFAULT_VALUE;
    }

    @Override
    public void insert(int playerId, int boardIndex) throws BoardIndexOutOfBoundsException, InvalidPlayerIdException {
        if (playerId == DEFAULT_VALUE) {
            throw new InvalidPlayerIdException("許可されていないプレイヤーIDです");
        }
        if (playerId <= DEFAULT_VALUE || 0 > boardIndex || boardIndex >= size) {
            throw new BoardIndexOutOfBoundsException("ボードの範囲外です");
        }
        board.set(boardIndex, playerId);
    }

    @Override
    public boolean isAligned(int playerID, int boardIndex, int requiredAlignedNum) {
        return isDiagonalAligned(playerID, boardIndex, requiredAlignedNum) |
                isHorizontalAligned(playerID, boardIndex, requiredAlignedNum) |
                isVerticalAligned(playerID, boardIndex, requiredAlignedNum);
    }

    /**
     * 横列に指定したプレイヤー識別値が指定数分並んだか判定します
     *
     * @param playerID           挿入するプレイヤーの識別値
     * @param boardIndex         判定するボードの場所
     *                           ここから左、右にみていきます
     * @param requiredAlignedNum 指定数
     * @return 判定する場所から左右に指定数並んだかどうか
     */
    public boolean isHorizontalAligned(int playerID, int boardIndex, int requiredAlignedNum) {
        return requiredAlignedNum == countRowAligned(playerID, boardIndex) + 1;
    }

    /**
     * 縦列に指定したプレイヤー識別値が指定数分並んだか判定します
     *
     * @param playerID           挿入するプレイヤーの識別値
     * @param boardIndex         判定するボードの場所
     *                           ここから上、下にみていきます
     * @param requiredAlignedNum 指定数
     * @return 判定する場所から上下に指定数並んだかどうか
     */
    public boolean isVerticalAligned(int playerID, int boardIndex, int requiredAlignedNum) {
        return requiredAlignedNum == countColumnAligned(playerID, boardIndex) + 1;
    }

    /**
     * 斜めに指定したプレイヤー識別値が指定数分並んだか判定します
     *
     * @param playerID           挿入するプレイヤーの識別値
     * @param boardIndex         判定するボードの場所
     *                           ここから左上、右下、右上、左下にみていきます
     * @param requiredAlignedNum 指定数
     * @return 判定する場所から斜めに指定数並んだかどうか
     */
    public boolean isDiagonalAligned(int playerID, int boardIndex, int requiredAlignedNum) {
        return requiredAlignedNum == countLeftTiltAligned(playerID, boardIndex) + 1 || requiredAlignedNum == countRightTiltAligned(playerID, boardIndex) + 1;
    }

    /**
     * 横列に指定したプレイヤー識別値が指定した場からいくつ並んでいるか
     *
     * @param playerID   挿入するプレイヤーの識別値
     * @param boardIndex 判定するボードの場所
     *                   ここから左、右にみていきます
     * @return 並んだ数　なお、返す値に自分は含まれません
     */
    public int countRowAligned(int playerID, int boardIndex) {
        int lengthFromSide = boardIndex % row;

        int rightCount = 0;
        int leftCount = 0;

        for (int i = 1; lengthFromSide > 0; i++) {
            int nextCol = boardIndex - i;
            if (nextCol < 0 || !isFilled(nextCol) || !(playerID == getPlayerId(nextCol))) {
                break;
            }
            lengthFromSide = nextCol % row;
            leftCount++;
        }

        lengthFromSide = boardIndex % row;
        for (int i = 1; lengthFromSide < row - 1; i++) {
            int nextCol = boardIndex + i;
            if (nextCol >= size || !isFilled(nextCol) || !(playerID == getPlayerId(nextCol))) {
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
     * @param playerID   挿入するプレイヤーの識別値
     * @param boardIndex 判定するボードの場所
     *                   ここから上、下にみていきます
     * @return 並んだ数　なお、返す値に自分は含まれません
     */
    public int countColumnAligned(int playerID, int boardIndex) {
        int lengthFromTop = (boardIndex / row) % column;

        int downCount = 0;
        int upCount = 0;

        for (int i = 1; lengthFromTop > 0; i++) {
            int nextCol = boardIndex - (i * row);
            if (nextCol < 0 || !isFilled(nextCol) || !(playerID == getPlayerId(nextCol))) {
                break;
            }
            lengthFromTop = (nextCol / row) % column;
            upCount++;
        }

        lengthFromTop = (boardIndex / row) % column;
        for (int i = 1; lengthFromTop < column - 1; i++) {
            int nextCol = boardIndex + (i * row);
            if (nextCol >= size || !isFilled(nextCol) || !(playerID == getPlayerId(nextCol))) {
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
     * @param playerID   挿入するプレイヤーの識別値
     * @param boardIndex 判定するボードの場所
     *                   ここから左上、右下にみていきます
     * @return 並んだ数　なお、返す値に自分は含まれません
     */
    public int countLeftTiltAligned(int playerID, int boardIndex) {
        int lengthFromSide = boardIndex % row;
        int lengthFromTop = (boardIndex / row) % column;

        int upLeftCount = 0;
        int downRightCount = 0;

        //左上
        for (int i = 1; lengthFromTop > 0 && lengthFromSide > 0; i++) {
            int nextCol = boardIndex - (i * row) - i;
            if (nextCol < 0 || !isFilled(nextCol) || playerID != getPlayerId(nextCol)) {
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
            if (nextCol >= size || !isFilled(nextCol) || !(playerID == getPlayerId(nextCol))) {
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
     * @param playerID   挿入するプレイヤーの識別値
     * @param boardIndex 判定するボードの場所
     *                   ここから右上、左下にみていきます
     * @return 並んだ数　なお、返す値に自分は含まれません
     */
    public int countRightTiltAligned(int playerID, int boardIndex) {
        int lengthFromSide = boardIndex % row;
        int lengthFromTop = (boardIndex / row) % column;

        int upRightCount = 0;
        int downLeftCount = 0;

        //右上
        for (int i = 1; lengthFromTop > 0 && lengthFromSide < row - 1; i++) {
            int nextCol = boardIndex - (i * row) + i;
            if (nextCol < 0 || !isFilled(nextCol) || !(playerID == getPlayerId(nextCol))) {
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
            if (nextCol >= size || !isFilled(nextCol) || !(playerID == getPlayerId(nextCol))) {
                break;
            }
            lengthFromSide = nextCol % row;
            lengthFromTop = (nextCol / row) % column;
            downLeftCount++;
        }
        return upRightCount + downLeftCount;
    }
}
