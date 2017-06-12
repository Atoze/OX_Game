package jp.co.topgate.atoze.ox.exception;

/**
 * Created by atoze on 2017/06/12.
 */

public class PlayerIdException extends Exception {
    public PlayerIdException() {
        super("許可されていないプレイヤーIDです");
    }

    public PlayerIdException(String msg) {
        super(msg);
    }
}