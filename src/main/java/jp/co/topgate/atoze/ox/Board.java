package jp.co.topgate.atoze.ox;

import jp.co.topgate.atoze.ox.exception.BoardIndexOutOfBoundsException;
import jp.co.topgate.atoze.ox.exception.InvalidPlayerIdException;

/**
 * ゲームの状況を保管するクラス
 */
public interface Board {

    int DEFAULT = -1;

    /**
     * ボードに指定したプレイヤーを対応するマスに書き込みます.
     *
     * @param playerId   プレイヤーを識別するID
     * @param boardIndex ボードの番号
     * @throws BoardIndexOutOfBoundsException 挿入するIDがボードの範囲外
     * @throws InvalidPlayerIdException       プレイヤーIDが許可されていないIDである
     */
    void insert(int playerId, int boardIndex) throws BoardIndexOutOfBoundsException, InvalidPlayerIdException;

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
     * このボード番号の最大数を返します.
     *
     * @return ボード番号最大数
     */
    int getDefaultValue();

    /**
     * このボード番号の最大数を返します.
     *
     * @return ボード番号最大数
     */
    int[] getBoardValue();

    /**
     * このボード番号の最大数を返します.
     *
     * @return ボード番号最大数
     */
    Board clone();
}
