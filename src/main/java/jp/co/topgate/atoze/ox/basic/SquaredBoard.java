package jp.co.topgate.atoze.ox.basic;

import jp.co.topgate.atoze.ox.BoardImpl;
import jp.co.topgate.atoze.ox.exception.BoardIndexOutOfBoundsException;
import jp.co.topgate.atoze.ox.exception.InvalidBoardSizeException;
import jp.co.topgate.atoze.ox.exception.InvalidPlayerIdException;

/**
 * 正方形のボードでプレイする際に使用するBoard
 */
public class SquaredBoard extends BoardImpl {

    /**
     * ボードの初期値.
     * この値に一致するプレイヤーIDは、InvalidPlayerIdExceptionを返されます.
     */
    private final static int DEFAULT_VALUE = -1;

    /**
     * ボードの一辺の長さです.
     */
    private final int sideLength;

    /**
     * ボードが保持する配列の長さです.
     */
    private final int size;

    /**
     * int型の配列にプレイヤー識別値を保管します.
     * 初期値は、DEFAULT_VALUEで指定された値です.
     */
    private int[] board;

    /**
     * このボードが何次元状のものであるか
     */
    private static final int DIMENSION = 2;

    public SquaredBoard(int sideLength) throws InvalidBoardSizeException {
        if (sideLength <= 0|| sideLength>100) {
            throw new InvalidBoardSizeException();
        }

        this.sideLength = sideLength;
        this.size = (int) Math.pow(sideLength, 2);
        this.board = new int[this.size];

        for (int i = 0; i < this.board.length; i++) {
            board[i] = DEFAULT_VALUE;
        }
    }

    public final void insert(int playerId, int column, int row) throws BoardIndexOutOfBoundsException, InvalidPlayerIdException {
        if (column <= 0 || row <= 0 || column > sideLength || row > sideLength) {
            throw new BoardIndexOutOfBoundsException("ボードの範囲外です");
        }

        int boardIndex = (column - 1) + ((row - 1) * sideLength);
        insert(playerId, boardIndex);
    }

    @Override
    public final void insert(int playerId, int boardIndex) throws BoardIndexOutOfBoundsException, InvalidPlayerIdException {
        if (playerId == DEFAULT_VALUE) {
            throw new InvalidPlayerIdException("許可されていないプレイヤーIDです");
        }
        if (playerId <= DEFAULT_VALUE || 0 > boardIndex || boardIndex >= (size)) {
            throw new BoardIndexOutOfBoundsException("ボードの範囲外です");
        }
        board[boardIndex] = playerId;
    }

    @Override
    public boolean isFilled(int boardIndex) {
        return !(board[boardIndex] == DEFAULT_VALUE);
    }

    @Override
    public int getRow() {
        return sideLength;
    }

    @Override
    public int getColumn() {
        return sideLength;
    }

    @Override
    public int getMaxSideLength() {
        return sideLength;
    }

    @Override
    public int getMinSideLength() {
        return sideLength;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getPlayerId(int boardIndex) {
        return board[boardIndex];
    }

    @Override
    public int getDefaultValue() {
        return DEFAULT_VALUE;
    }
}
