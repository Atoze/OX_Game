package jp.co.topgate.atoze.ox;

/**
 * Created by atoze on 2017/06/09.
 */
public interface UI {
    void insert(Player player, int gridIndex);

    void showBoard(Board board);

    void showNotFilled(Board board);
}
