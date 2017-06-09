package jp.co.topgate.atoze.ox;

/**
 * Created by atoze on 2017/06/09.
 */
public class ScreenBoard {
    private Board board;

    ScreenBoard(Board board) {
        this.board = board;
    }

    public boolean isFilled(int gridIndex) {
        return board.isFilled(gridIndex);
    }

    public boolean accept(int selectedGridIndex) {
        if (selectedGridIndex < 0 || selectedGridIndex >= getLength()) {
            return false;
        }
        return !board.isFilled(selectedGridIndex);
    }

    public int getRow() {
        return board.getRow();
    }

    public int getColumn() {
        return board.getColumn();
    }

    public int getLength() {
        return board.getLength();
    }

    public int getPlayerId(int gridIndex) {
        return board.getPlayerId(gridIndex);
    }
}
