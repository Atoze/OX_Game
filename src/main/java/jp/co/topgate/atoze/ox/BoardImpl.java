package jp.co.topgate.atoze.ox;

import jp.co.topgate.atoze.ox.exception.BoardIndexOutOfBoundsException;
import jp.co.topgate.atoze.ox.exception.InvalidPlayerIdException;

/**
 * ボードに操作許可を与えるクラス
 */
public abstract class BoardImpl implements Board{
    /**
     * ボードに指定したプレイヤーを対応するマスに書き込みます.
     *
     * @param playerId   プレイヤーを識別するIDです.
     * @param boardIndex ボードの番号です.
     * @throws BoardIndexOutOfBoundsException 挿入するIDがボードの範囲外
     * @throws InvalidPlayerIdException       プレイヤーIDが許可されていないID
     */
    public abstract void insert(int playerId, int boardIndex) throws BoardIndexOutOfBoundsException, InvalidPlayerIdException;
}
