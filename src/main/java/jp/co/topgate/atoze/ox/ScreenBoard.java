package jp.co.topgate.atoze.ox;

/**
 * Boardクラスを表示するためのクラス　書き換えできないように
 */
public class ScreenBoard {
    private Board board;

    ScreenBoard(Board board) {
        this.board = board;
    }

    public boolean isFilled(int boardIndex) {
        return board.isFilled(boardIndex);
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
        return board.getSize();
    }

    public int getPlayerId(int boardIndex) {
        return board.getPlayerId(boardIndex);
    }

    public int getDefaultValue() {
        return board.getDefaultValue();
    }
}
