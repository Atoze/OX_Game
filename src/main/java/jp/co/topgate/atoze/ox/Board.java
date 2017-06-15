package jp.co.topgate.atoze.ox;

/**
 * ゲームの状況を保管するクラス
 */
public interface Board {
    /**
     * 指定した番号がすでに埋まっているかどうか確認します.
     *
     * @param boardIndex 確認したいボードの番号
     * @return 埋まってたらtrue、埋まっていなかったらfalseを返す
     */
    boolean isFilled(int boardIndex);

    /**
     * ボードの行数を返します.
     *
     * @return 行数
     */
    int getRow();

    /**
     * ボードの列数を返します.
     *
     * @return 列数
     */
    int getColumn();

    /**
     * このボードが持つ最長辺を返します.
     *
     * @return 最長辺
     */
    int getMaxSideLength();

    /**
     * このボードが持つ最短辺を返します.
     *
     * @return 最短辺
     */
    int getMinSideLength();

    /**
     * このボード番号の最大数を返します.
     *
     * @return ボード番号最大数
     */
    int getSize();

    /**
     * このボード番号の最大数を返します.
     *
     * @return ボード番号最大数
     */
    int getPlayerId(int boardIndex);

    /**
     * このボードの初期値を返します.
     * 基本的にこの値と一致するプレイヤーIDはInvalidPlayerIdExceptionを投げることを想定してます.
     *
     * @return ボードの初期値
     */
    int getDefaultValue();
}
