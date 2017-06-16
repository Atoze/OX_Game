package jp.co.topgate.atoze.ox;

/**
 * プレイヤークラス
 */
public interface Player {

    /**
     * 挿入する値を決めて、その値を返します
     *
     * @param board ボードクラス
     */
    int selectBoardIndex(Board board);

    /**
     * プレイヤーの名前を取得します.
     */
    String getName();

    /**
     * プレイヤーの識別IDを取得します.
     * 同IDを持つプレイヤーは、同じ陣営であると判断します.
     */
    int getID();
}
