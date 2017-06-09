package jp.co.topgate.atoze.ox;

/**
 * Created by atoze on 2017/06/06.
 */
public abstract class ComputerPlayer implements Player {
    private final int id;

    ComputerPlayer(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return "コンピューター";
    }
}
