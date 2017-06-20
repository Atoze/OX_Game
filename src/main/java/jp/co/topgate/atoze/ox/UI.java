package jp.co.topgate.atoze.ox;

import java.util.List;

/**
 * インターフェース実装クラス
 */
public interface UI {
    int BLACK = 1;
    int WHITE = 2;

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
     *  @param defaultValue
     * @param timer
     */
    int selectBoardIndex(int defaultValue, Timer timer);

    /**
     * ターンの始めに表示される内容です.
     *
     * @param currentTurn
     * @param player  これから実行するプレイヤー
     * @param players 参加している全てのプレイヤーリスト
     * @param board   ボードクラス
     */
    void printStartTurn(int currentTurn, Player player, List<Player> players, Board board);

    /**
     * 1ターンが終了した際のゲームの状態を表示します.
     *
     * @param currentPlayer 現在のプレイヤー
     * @param player        参加している全てのプレイヤーリスト
     * @param board         ボードクラス
     * @param result        このターンの結果
     */
    void printGameResult(Player currentPlayer, List<Player> player, Board board, Result result);

    /**
     * 残り時間を出力します.
     *
     * @param timeLeft 残り時間
     */
    void printTimeLeft(int timeLeft);
}
