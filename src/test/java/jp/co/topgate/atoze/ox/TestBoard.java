package jp.co.topgate.atoze.ox;

import jp.co.topgate.atoze.ox.exception.BoardIndexOutOfBoundsException;
import jp.co.topgate.atoze.ox.exception.InvalidPlayerIdException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by atoze on 2017/06/15.
 */
public class TestBoard extends BoardImpl {
    private final static int DEFAULT_VALUE = -1;

    private final int column;

    private final int row;

    private final int size;

    List<Integer> board;

    TestBoard(int row, int column) {
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
}
