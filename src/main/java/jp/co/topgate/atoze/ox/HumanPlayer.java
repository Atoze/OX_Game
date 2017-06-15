package jp.co.topgate.atoze.ox;

/**
 * 外部から数字入力を受け付けることができるプレイヤーです.
 */
public class HumanPlayer implements Player {
    private String name = "プレイヤー";
    private final int id;

    HumanPlayer(int id) {
        this.id = id;
        this.name += "さん";
    }

    HumanPlayer(int id, String name) {
        this.id = id;
        this.name = name + "さん";
    }

    @Override
    public int selectBoardIndex(Board board, UI ui) {
        return ui.forSelectBoardIndex();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getID() {
        return id;
    }
}
