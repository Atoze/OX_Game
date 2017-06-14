package jp.co.topgate.atoze.ox;

/**
 * ターミナルから数字入力を受け付けるクラスです.
 */
public class HumanPlayer implements Player {
    private String name = "プレイヤー";
    private final int id;

    HumanPlayer(int id) {
        this.id = id;
        this.name = name + id + "さん";
    }

    HumanPlayer(int id, String name) {
        this.id = id;
        this.name = name + "さん";
    }

    @Override
    public int next(Board board, UI ui) {
        return ui.next();
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
