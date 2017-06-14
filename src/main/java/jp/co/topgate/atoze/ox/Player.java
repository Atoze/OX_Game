package jp.co.topgate.atoze.ox;

/**
 * プレイヤークラス
 */
public interface Player {
    int next(Board board, UI ui);

    String getName();

    int getID();
}
