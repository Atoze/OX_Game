package jp.co.topgate.atoze.ox;

/**
 * Created by atoze on 2017/06/06.
 */
interface Player {
    int next(ScreenBoard board);

    String getName();

    int getID();
}
