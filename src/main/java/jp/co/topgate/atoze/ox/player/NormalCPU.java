package jp.co.topgate.atoze.ox.player;

import jp.co.topgate.atoze.ox.Board;
import jp.co.topgate.atoze.ox.OXGame;
import jp.co.topgate.atoze.ox.Player;

/**
 * 相手が並ぶのを邪魔してくるだけのコンピューター
 * 未実装
 */
public class NormalCPU implements Player {
    private final int id;

    private final static int DEFAULT_SCORE = -1;

    public NormalCPU(int id) {
        this.id = id;
    }

    @Override
    public int selectBoardIndex(OXGame game, int timeLeft) {

        Board board = game.getBoard();
        int boardIndex = board.getDefaultValue();
/*
        //あくまで勝利条件は変わらないことを前提とする
        int winNumber = game.REQUIRED_ALIGNED_NUM;

        for (int winNum = 0; winNum < winNumber; winNum++) {

            int score = 0;

            for (int id = 0; id < game.players.size(); id++) {
                for (int index = 0; index < board.getSize(); index++) {
                    if (board.isAligned(id, index, winNum)) {
                        if (game.accept(board, index)) {
                            if (score < winNum) {
                                score = winNum;
                                boardIndex = index;
                            }
                        }
                    }
                }
            }
/*
            //自分の勝利条件を探す
            for (int index = 0; index < board.getSize(); index++) {
                if (board.isAligned(this.id, index, winNum)) {
                    if (game.accept(board, index)) {
                        if (score < winNum) {
                            score = winNum;
                            boardIndex = index;
                        }
                    }

                }
            }
            */


        //if(game.isLose(this, boardIndex)){}


        /*
        if (boardIndex == board.getDefaultValue()) {
            boardIndex = (int) (Math.random() * board.getSize());
        }*/
        boardIndex = (int) (Math.random() * board.getSize());
        return boardIndex;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getName() {
        return "ちょっと強いコンピューター";
    }
}
