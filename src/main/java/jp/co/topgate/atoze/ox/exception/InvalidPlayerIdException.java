package jp.co.topgate.atoze.ox.exception;

/**
 * 許可されていないプレイヤーIDが存在する時に投げられる例外
 */

public class InvalidPlayerIdException extends Exception {
    public InvalidPlayerIdException() {
        super("許可されていないプレイヤーIDです");
    }

    public InvalidPlayerIdException(String msg) {
        super(msg);
    }
}