package jp.co.topgate.atoze.ox;

/**
 * 空いてるところをランダムに指定してくるCPUです.
 */
public class EasyCPU implements Player {
    private final int id;

    EasyCPU(int id) {
        this.id = id;
    }

    @Override
    public int next(ScreenBoard board) {
        return (int) (Math.random() * board.getSize());
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getName() {
        return "コンピューター";
    }
}
