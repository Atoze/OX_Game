package jp.co.topgate.atoze.ox;

/**
 * ゲームの状況を保管するクラス
 */
interface Board {
    boolean insert(int playerID, int dataIndex);

    void next();

    int getCurrentTurn();
}
