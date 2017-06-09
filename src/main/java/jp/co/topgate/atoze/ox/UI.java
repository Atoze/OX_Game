package jp.co.topgate.atoze.ox;

/**
 * Created by atoze on 2017/06/09.
 */
public interface UI {
    void insert(Player player, int gridIndex);

    void turnStart(Player player, ScreenBoard board);

    void gameSet(Player player, ScreenBoard board, Result result);
}
