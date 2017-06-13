package jp.co.topgate.atoze.ox;

import java.util.List;

/**
 * 表示を
 */
public interface UI {
    void showInsert(Player player, ScreenBoard board, int boardIndex);

    void printStartTurn(Player player, ScreenBoard board);

    void printGameResult(Player winner, List<Player> player, ScreenBoard board, Result result);
}
