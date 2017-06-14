package jp.co.topgate.atoze.ox;

import java.util.List;

/**
 * 表示を
 */
public interface UI {
    void printInsert(Player player, Board board, int boardIndex);

    int next();

    void printStartTurn(Player player, Board board);

    void printGameResult(Player winner, List<Player> player, Board board, Result result);
}
