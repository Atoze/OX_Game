package jp.co.topgate.atoze.ox.exception;

/**
 * プレイヤー人数が不適切であった場合に投げられる例外
 */

public class PlayersOutOfBoundsException extends Exception {
    public PlayersOutOfBoundsException(String msg) {
        super(msg);
    }
}