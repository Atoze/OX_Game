package jp.co.topgate.atoze.ox;

/**
 * ゲームの状況を保管するクラス
 */
abstract class Board {
    private int currentTurn;

    abstract boolean insert(int playerId, int dataIndex);

    public void count() {
        currentTurn++;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

}
