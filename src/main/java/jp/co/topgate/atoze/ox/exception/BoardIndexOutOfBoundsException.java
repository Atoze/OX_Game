package jp.co.topgate.atoze.ox.exception;

/**
 * ボードの範囲を超えた場合に投げられる例外
 */

public class BoardIndexOutOfBoundsException extends Exception {
    public BoardIndexOutOfBoundsException(String msg) {
        super(msg);
    }
}