package jp.co.topgate.atoze.ox.exception;

/**
 * ボードに一列に並べられない数を指定した場合に投げられる例外
 */

public class RequiredNumberAlignedOutOfBoundsException extends Exception {
    public RequiredNumberAlignedOutOfBoundsException(String msg) {
        super(msg);
    }
}