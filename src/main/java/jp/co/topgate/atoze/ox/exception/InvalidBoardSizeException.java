package jp.co.topgate.atoze.ox.exception;

/**
 * Created by atoze on 2017/06/15.
 */
public class InvalidBoardSizeException extends Exception {
    public InvalidBoardSizeException() {
        super("ボードの大きさが不適切です");
    }

    public InvalidBoardSizeException(String msg) {
        super(msg);
    }
}
