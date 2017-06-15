package jp.co.topgate.atoze.ox;

import java.util.List;

/**
 * インターフェース実装クラス
 */
public interface UI {

    /**
     * 値が挿入された時に表示される内容です.
     *
     * @param player     挿入操作を行ったプレイヤー
     * @param board      ボードクラス
     * @param boardIndex 挿入した場所
     */
    void printInsert(Player player, Board board, int boardIndex);

    /**
     * 外部から挿入したい数を受け付ける際のインターフェースを決定します.
     */
    int forSelectBoardIndex();

    /**
     * ターンの始めに表示される内容です.
     *
     * @param currentPlayer これから実行するプレイヤー
     * @param players       参加している全てのプレイヤーリスト
     * @param board         ボードクラス
     */
    void printStartTurn(Player currentPlayer, List<Player> players, Board board);

    /**
     * 1ターンが終了した際のゲームの状態を表示します.
     *
     * @param currentPlayer 現在のプレイヤー
     * @param player        参加している全てのプレイヤーリスト
     * @param board         ボードクラス
     * @param result        このターンの結果
     */
    void printGameResult(Player currentPlayer, List<Player> player, Board board, Result result);
}
