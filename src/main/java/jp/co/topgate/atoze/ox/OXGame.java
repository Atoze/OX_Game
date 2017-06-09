package jp.co.topgate.atoze.ox;

import java.util.List;

/**
 * Created by atoze on 2017/06/06.
 */
public class OXGame {
    //private Board board;
    private List<Player> players;
    private UI ui;
    private Rule rule;

    public void playSquareBoard(int gridNum, List<Player> players, UI ui) {
        start(new Rule(new SquaredBoard(gridNum)), players, ui);
    }

    /*
    OXGame(Board board, List<Player> players){
        this.board = board;
        this.players = players;
    }*/

    public void start(Rule rule, List<Player> players, UI ui) {
        this.ui = ui;
        this.players = players;
        this.rule = rule;

        game();
    }

    private void game() {
        Status status = Status.GAME_GO_ON;
        int playerNum = players.size();
        int currentTurn = 0;
        Player currentPlayer = players.get(0);

        while (status == Status.GAME_GO_ON) {
            for (int i = 0; i < playerNum; i++) {
                currentTurn++;
                currentPlayer = players.get(i);
                ui.showBoard(rule.getBoard());

                System.out.println();
                System.out.println();
                ui.showNotFilled(rule.getBoard());


                int gridIndex = currentPlayer.next(rule.getBoard(), rule);
                ui.insert(currentPlayer, gridIndex);
                rule.insert(currentPlayer.getID(), gridIndex);

                status = rule.checkStatus(currentPlayer.getID(), gridIndex, currentTurn);
                //status = Match.check(board, currentTurn);
                if (status != Status.GAME_GO_ON) {
                    break;
                }
            }
        }
        gameSet(status, currentPlayer);
        System.exit(0);
    }

    private void gameSet(Status status, Player winnerPlayer) {
        switch (status) {
            case WIN:
                ui.showBoard(rule.getBoard());
                System.out.println("Player" + winnerPlayer.getID() + " " + winnerPlayer.getName() + "の勝利");
                break;
            case DRAW:
                ui.showBoard(rule.getBoard());
                System.out.println("引き分けです");
                break;
        }
    }
}
