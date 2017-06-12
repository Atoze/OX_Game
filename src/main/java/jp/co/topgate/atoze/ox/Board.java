package jp.co.topgate.atoze.ox;

import jp.co.topgate.atoze.ox.exception.BoardIndexOutOfBoundsException;
import jp.co.topgate.atoze.ox.exception.PlayerIdException;

/**
 * ゲームの状況を保管するクラス
 */
public interface Board {
    void insert(int playerId, int boardIndex) throws BoardIndexOutOfBoundsException, PlayerIdException;

    boolean isFilled(int boardIndex);

    int getRow();

    int getColumn();

    int getLength();

    int getPlayerId(int boardIndex);

    int getDefaultValue();

    int[] getBoard();
}
