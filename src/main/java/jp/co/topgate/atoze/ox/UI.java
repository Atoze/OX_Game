package jp.co.topgate.atoze.ox;

import java.util.List;

/**
 * 表示を
 */
public interface UI {
    void showInsert(Player player, ScreenBoard board, int boardIndex);

    void turnStart(Player player, ScreenBoard board);

    void gameSet(Player winner, List<Player> player, ScreenBoard board, Result result);
}
