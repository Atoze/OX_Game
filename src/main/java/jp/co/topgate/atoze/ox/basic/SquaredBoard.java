package jp.co.topgate.atoze.ox.basic;

import jp.co.topgate.atoze.ox.Board;
import jp.co.topgate.atoze.ox.exception.BoardIndexOutOfBoundsException;
import jp.co.topgate.atoze.ox.exception.PlayerIdException;

/**
 * Created by atoze on 2017/06/06.
 */
public class SquaredBoard implements Board {
    private final static int DEFAULT_VALUE = -1;
    private final int sideLength;
    private final int length;
    private final int[] board;

    public SquaredBoard(int sideLength) {
        this.sideLength = sideLength;
        this.length = (int) Math.pow(sideLength, 2);
        this.board = new int[this.length];

        for (int i = 0; i < this.board.length; i++) {
            board[i] = DEFAULT_VALUE;
        }
    }

    public final void insert(int playerId, int column, int row) throws BoardIndexOutOfBoundsException, PlayerIdException {
        if (column <= 0 || row <= 0 || column > sideLength || row > sideLength) {
            throw new BoardIndexOutOfBoundsException("ボードの範囲外です");
        }

        int boardIndex = (column - 1) + ((row - 1) * sideLength);
        insert(playerId, boardIndex);
    }

    @Override
    public final void insert(int playerId, int boardIndex) throws BoardIndexOutOfBoundsException, PlayerIdException {
        if (playerId == DEFAULT_VALUE){
            throw new PlayerIdException("許可されていないプレイヤーIDです");
        }
        if (playerId <= DEFAULT_VALUE || 0 > boardIndex || boardIndex > (length)) {
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
    public int getLength() {
        return length;
    }

    @Override
    public int getPlayerId(int boardIndex) {
        return board[boardIndex];
    }

    @Override
    public int getDefaultValue() {
        return DEFAULT_VALUE;
    }

    @Override
    public int[] getBoard() {
        return board;
    }
}
