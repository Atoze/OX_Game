package jp.co.topgate.atoze.ox;

/**
 * 外部から数字入力を受け付けることができるプレイヤーです.
 */
public class HumanPlayer implements Player {
    private final String name;
    private final int id;
    private final UI ui;

    HumanPlayer(int id, UI ui) {
        this(id, "プレイヤー", ui);
    }

    HumanPlayer(int id, String name, UI ui) {
        this.id = id;
        this.name = name + "さん";
        this.ui = ui;
    }

    @Override
    public int selectBoardIndex(Board board) {
        return ui.selectBoardIndex();
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
