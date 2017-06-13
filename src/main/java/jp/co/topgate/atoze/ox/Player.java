package jp.co.topgate.atoze.ox;

/**
 * プレイヤークラス
 */
public interface Player {
    int next(ScreenBoard board);

    String getName();

    int getID();
}
