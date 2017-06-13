package jp.co.topgate.atoze.ox;

import jp.co.topgate.atoze.ox.exception.BoardIndexOutOfBoundsException;
import jp.co.topgate.atoze.ox.exception.InvalidPlayerIdException;

/**
 * ゲームの状況を保管するクラス
 */
public interface Board {

    /**
     * ボードに指定したプレイヤーを対応するマスに書き込みます.
     * @param playerId プレイヤーを識別するIDです.
     * @param boardIndex ボードの番号です.
     * @throws BoardIndexOutOfBoundsException
     * @throws InvalidPlayerIdException
     */
    void insert(int playerId, int boardIndex) throws BoardIndexOutOfBoundsException, InvalidPlayerIdException;

    boolean isFilled(int boardIndex);

    int getRow();

    int getColumn();

    int getSize();

    int getPlayerId(int boardIndex);

    int getDefaultValue();

    int[] getBoard();
}
