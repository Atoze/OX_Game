package jp.co.topgate.atoze.ox;

/**
 * プレイヤークラス
 */
interface Player {
    int next(ScreenBoard board);

    String getName();

    int getID();
}
