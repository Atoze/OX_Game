package jp.co.topgate.atoze.ox;

import java.util.List;

/**
 * 表示を
 */
public interface UI {
    void printInsert(Player player, ScreenBoard board, int boardIndex);

    int next();

    void printStartTurn(Player player, ScreenBoard board);

    void printGameResult(Player winner, List<Player> player, ScreenBoard board, Result result);
}
