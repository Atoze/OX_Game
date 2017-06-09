package jp.co.topgate.atoze.ox;

/**
 * 空いてるところをランダムに指定してくるCPUです.
 */
public class EasyCPU extends ComputerPlayer {

    EasyCPU(int id) {
        super(id);
    }

    @Override
    public int next(Board board, Rule rule) {
        int gridIndex;
        while (true) {
            gridIndex = (int) (Math.random() * board.getLength());
            if (rule.accept(gridIndex)) {
                break;
            }
        }
        return gridIndex;
    }
}
