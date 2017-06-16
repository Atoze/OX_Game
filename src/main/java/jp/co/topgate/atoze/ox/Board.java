package jp.co.topgate.atoze.ox;

import jp.co.topgate.atoze.ox.exception.BoardIndexOutOfBoundsException;
import jp.co.topgate.atoze.ox.exception.InvalidPlayerIdException;

/**
 * ゲームの状況を保管するクラス
 */
public abstract class Board {
    /**
     * 指定した番号がすでに埋まっているかどうか確認します.
     *
     * @param boardIndex 確認したいボードの番号
     * @return 埋まってたらtrue、埋まっていなかったらfalseを返す
     */
    public abstract boolean isFilled(int boardIndex);

    /**
     * ボードの行数を返します.
     *
     * @return 行数
     */
    public abstract int getRow();

    /**
     * ボードの列数を返します.
     *
     * @return 列数
     */
    public abstract int getColumn();

    /**
     * このボードが持つ最長辺を返します.
     *
     * @return 最長辺
     */
    public abstract int getMaxSideLength();

    /**
     * このボードが持つ最短辺を返します.
     *
     * @return 最短辺
     */
    public abstract int getMinSideLength();

    /**
     * このボード番号の最大数を返します.
     *
     * @return ボード番号最大数
     */
    public abstract int getSize();

    /**
     * このボード番号の最大数を返します.
     *
     * @return ボード番号最大数
     */
    public abstract int getPlayerId(int boardIndex);

    /**
     * このボードの初期値を返します.
     * 基本的にこの値と一致するプレイヤーIDはInvalidPlayerIdExceptionを投げることを想定してます.
     *
     * @return ボードの初期値
     */
    public abstract int getDefaultValue();

    /**
     * ボード上で、指定したプレイヤーIDがボードの位置から何かしらの形で並んだかを判定します
     *
     * @return 並んだか否か
     */
    public abstract boolean isAligned(int playerID, int boardIndex, int requiredAlignedNum);

    /**
     * ボードに指定したプレイヤーを対応するマスに書き込みます.
     *
     * @param playerId   プレイヤーを識別するIDです.
     * @param boardIndex ボードの番号です.
     * @throws BoardIndexOutOfBoundsException 挿入するIDがボードの範囲外
     * @throws InvalidPlayerIdException       プレイヤーIDが許可されていないID
     */
    protected abstract void insert(int playerId, int boardIndex) throws BoardIndexOutOfBoundsException, InvalidPlayerIdException;
}
