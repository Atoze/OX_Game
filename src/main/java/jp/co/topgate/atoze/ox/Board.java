package jp.co.topgate.atoze.ox;

/**
 * ゲームの状況を保管するクラス
 */
interface Board {
    abstract void insert(int playerId, int gridIndex);

    abstract boolean isFilled(int gridIndex);

    abstract int getRow();

    abstract int getColumn();

    abstract int getLength();

    abstract int getPlayerId(int gridIndex);

    abstract int[] getBoard();
}
