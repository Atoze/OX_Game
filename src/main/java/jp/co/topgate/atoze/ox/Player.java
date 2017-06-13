package jp.co.topgate.atoze.ox;

/**
 * プレイヤークラス
 */
public interface Player {
    int next(ScreenBoard board, UI ui);

    String getName();

    int getID();
}
