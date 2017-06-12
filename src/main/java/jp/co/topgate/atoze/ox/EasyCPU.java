package jp.co.topgate.atoze.ox;

/**
 * 空いてるところをランダムに指定してくるCPUです.
 */
public class EasyCPU extends ComputerPlayer {

    EasyCPU(int id) {
        super(id);
    }

    @Override
    public int next(ScreenBoard board) {
        int boardIndex;
        while (true) {
            boardIndex = (int) (Math.random() * board.getLength());
            if (board.accept(boardIndex)) {
                break;
            }
        }
        return boardIndex;
    }
}
