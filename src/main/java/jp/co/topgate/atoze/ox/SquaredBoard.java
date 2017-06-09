package jp.co.topgate.atoze.ox;

/**
 * Created by atoze on 2017/06/06.
 */
public class SquaredBoard implements Board {
    private final static int DEFAULT_VALUE = -1;
    private final int sideLength;
    private final int length;
    //private final String[] data;
    private final int[] board;

    SquaredBoard(int sideLength) {
        this.sideLength = sideLength;
        this.length = (int) Math.pow(sideLength, 2);
        this.board = new int[this.length];

        for (int i = 0; i < this.board.length; i++) {
            board[i] = DEFAULT_VALUE;
        }
    }

    //TODO:returnより範囲外であることの例外を投げた方がよさげ
    public void insert(int playerId, int column, int row) {
        if (playerId <= DEFAULT_VALUE || column <= 0 || row <= 0 || column > sideLength || row > sideLength) {
            return;
        }

        int gridIndex = (column - 1) + ((row - 1) * sideLength);
        insert(playerId, gridIndex);
    }

    @Override
    public void insert(int playerId, int gridIndex) {
        if (playerId <= DEFAULT_VALUE || 0 > gridIndex || gridIndex > (length)) {
            return;
        }
        board[gridIndex] = playerId;
    }

    @Override
    public boolean isFilled(int gridIndex) {
        return !(board[gridIndex] == DEFAULT_VALUE);
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
    public int getPlayerId(int gridIndex) {
        return board[gridIndex];
    }

    @Override
    public int[] getBoard() {
        return board;
    }
}
