package jp.co.topgate.atoze.ox;

import jp.co.topgate.atoze.ox.exception.BoardIndexOutOfBoundsException;
import jp.co.topgate.atoze.ox.exception.InvalidPlayerIdException;
import jp.co.topgate.atoze.ox.exception.PlayersOutOfBoundsException;
import jp.co.topgate.atoze.ox.exception.RequiredNumberAlignedOutOfBoundsException;

import java.util.List;

/**
 * ○×ゲームの処理
 */
public class OXGame {
    private final BoardImpl board;
    private final List<Player> players;
    private final UI ui;

    private static final int PLAYER_MAX_NUM = 2;
    private static final int PLAYER_MIN_NUM = 2;

    private final MatchStatus match;

    OXGame(BoardImpl board, List<Player> players, int requiredAlignedNum, UI ui, int maxTurn) throws PlayersOutOfBoundsException, InvalidPlayerIdException, RequiredNumberAlignedOutOfBoundsException {
        this.board = board;

        this.ui = ui;
        this.players = players;

        if (players.size() > PLAYER_MAX_NUM) {
            throw new PlayersOutOfBoundsException("プレイヤー人数が多すぎます");
        }
        if (players.size() < PLAYER_MIN_NUM) {
            throw new PlayersOutOfBoundsException("必要なプレイヤー人数が足りていません");
        }
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getID() == board.getDefaultValue()) {
                throw new InvalidPlayerIdException();
            }
        }
        match = new MatchStatus(board, requiredAlignedNum, maxTurn);
    }

    OXGame(BoardImpl board, List<Player> players, int requiredAlignedNum, UI ui) throws PlayersOutOfBoundsException, InvalidPlayerIdException, RequiredNumberAlignedOutOfBoundsException {
        this(board, players, requiredAlignedNum, ui, board.getSize());
    }

    public void start() throws BoardIndexOutOfBoundsException, InvalidPlayerIdException {
        Result result = Result.CONTINUE;
        int playerNum = players.size();
        int currentTurn = 0;

        while (result == Result.CONTINUE) {
            for (int i = 0; i < playerNum; i++) {
                currentTurn++;
                Player currentPlayer = players.get(i);
                ui.printStartTurn(currentPlayer, players, board);

                int boardIndex;
                while (true) {
                    //TODO:もし設置に制限があったり、勝利条件をCPU側が参照したい際は、Ruleクラスにまとめるといいかもしれない
                    //TODO:勝利条件はn個一列に並ぶ、なのでその変数nの値さえ知れば後は共通した勝利条件なので把握したも同じである
                    boardIndex = currentPlayer.selectBoardIndex(board, ui);
                    if (accept(boardIndex)) {
                        break;
                    }
                }
                this.board.insert(currentPlayer.getID(), boardIndex);
                ui.printInsert(currentPlayer, board, boardIndex);

                result = match.checkResult(board, currentPlayer, boardIndex, currentTurn);
                ui.printGameResult(currentPlayer, players, board, result);
                if (result != Result.CONTINUE) {
                    break;
                }
            }
        }
    }

    private boolean accept(int selectedGridIndex) {
        if (selectedGridIndex < 0 || selectedGridIndex >= board.getSize()) {
            return false;
        }
        return !board.isFilled(selectedGridIndex);
    }
}
