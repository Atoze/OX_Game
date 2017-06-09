package jp.co.topgate.atoze.ox;

/**
 * ゲームの状況を保管するクラス
 */
public interface Board {
    void insert(int playerId, int gridIndex);

    boolean isFilled(int gridIndex);

    int getRow();

    int getColumn();

    int getLength();

    int getPlayerId(int gridIndex);

    int[] getBoard();
}
