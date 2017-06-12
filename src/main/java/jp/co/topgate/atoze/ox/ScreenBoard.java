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

    public int getRow() {
        return board.getRow();
    }

    public int getColumn() {
        return board.getColumn();
    }

    public int getSize() {
        return board.getSize();
    }

    public int getPlayerId(int boardIndex) {
        return board.getPlayerId(boardIndex);
    }

    public int getDefaultValue() {
        return board.getDefaultValue();
    }
}
