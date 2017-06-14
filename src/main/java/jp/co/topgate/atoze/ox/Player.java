package jp.co.topgate.atoze.ox;

/**
 * プレイヤークラス
 */
public interface Player {

    /**
     * 挿入する値を決めて、その値を返します
     * @param board ボードクラス
     * @param ui 外部から入力を受け付ける際のインターフェース
     */
    int selectBoardIndex(Board board, UI ui);

    /**
     * プレイヤーの名前を取得します.
     */
    String getName();

    /**
     * プレイヤーの識別IDを取得します.
     * 同IDを持つプレイヤーは、同じプレイヤーだと判断します.
     */
    int getID();
}
