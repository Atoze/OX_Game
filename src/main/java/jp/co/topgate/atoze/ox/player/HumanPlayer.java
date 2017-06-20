package jp.co.topgate.atoze.ox.player;

import jp.co.topgate.atoze.ox.OXGame;
import jp.co.topgate.atoze.ox.Player;
import jp.co.topgate.atoze.ox.Timer;
import jp.co.topgate.atoze.ox.UI;

/**
 * 外部から数字入力を受け付けることができるプレイヤーです.
 */
public class HumanPlayer implements Player {
    private final String name;
    private final int id;
    private final UI ui;

    public HumanPlayer(int id, UI ui) {
        this(id, "プレイヤー", ui);
    }

    public HumanPlayer(int id, String name, UI ui) {
        this.id = id;
        this.name = name + "さん";
        this.ui = ui;
    }

    @Override
    public int selectBoardIndex(OXGame game, Timer timer) {
        return ui.selectBoardIndex(game.getBoard().getDefaultValue(), timer);
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
