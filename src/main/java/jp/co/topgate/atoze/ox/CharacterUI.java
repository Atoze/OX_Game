package jp.co.topgate.atoze.ox;

import java.util.List;

/**
 * ターミナルにゲームの状況などのUIを出力するクラスです
 */
public class CharacterUI implements UI {
    public final static int O = 1;
    public final static int X = 2;

    private final static String LINE_FEED = System.getProperty("line.separator");


    public void showInsert(Player player, ScreenBoard board, int boardIndex) {
        StringBuilder sb = new StringBuilder();
        sb.append(changePlayerIdToCharacter(board, player.getID())).append("側");
        sb.append(player.getName()).append("が");
        sb.append(boardIndex).append("番に挿入しました").append(LINE_FEED);
        sb.append("-----------------------------------------------");

        System.out.println(sb.toString());
    }

    public void printStartTurn(Player player, ScreenBoard board) {
        StringBuilder sb = new StringBuilder();
        sb.append(changePlayerIdToCharacter(board, player.getID())).append("側 ");
        sb.append(player.getName()).append("のターンです").append(LINE_FEED);
        showBoard(board);
        System.out.println(sb.toString());
        showNotFilled(board);
    }

    public void printGameResult(Player winner, List<Player> player, ScreenBoard board, Result result) {
        StringBuilder sb = new StringBuilder();
        switch (result) {
            case WIN:
                showBoard(board);
                sb.append(changePlayerIdToCharacter(board, winner.getID())).append("側 ");
                sb.append(winner.getName()).append("の勝利\n");
                if (player.size() > 1) {
                    for (int i = 0; i < player.size(); i++) {
                        Player loser = player.get(i);
                        if (loser != winner) {
                            sb.append(changePlayerIdToCharacter(board, loser.getID())).append("側");
                            sb.append(loser.getName());
                        }
                    }
                }
                sb.append("は負けました…");
                break;
            case DRAW:
                showBoard(board);
                sb.append("引き分けです");
                break;
        }
        System.out.println(sb.toString());
    }

    private void showBoard(ScreenBoard board) {
        int row = board.getRow();
        int column = board.getColumn();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                sb.append("[").append(changePlayerIdToCharacter(board, board.getPlayerId(j + (i * row)))).append("]");
            }
            sb.append(LINE_FEED);
        }
        System.out.println(sb.toString());
    }

    private void showNotFilled(ScreenBoard board) {
        int row = board.getRow();
        int column = board.getColumn();
        int maxNumString = String.valueOf(board.getSize()).length();
        StringBuilder sb = new StringBuilder();

        System.out.println("現在埋まっていないマスです.");

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                int currentIndex = j + (i * row);
                sb.append("[");
                if (board.isFilled(currentIndex)) {
                    sb.append("  ");
                    for (int x = 0; x < maxNumString; x++) {
                        sb.append(" ");
                    }
                } else {
                    sb.append(" ").append(currentIndex).append(" ");
                    if (String.valueOf(currentIndex).length() < maxNumString) {
                        sb.append(" ");
                    }
                }
                sb.append("]");
            }
            sb.append(LINE_FEED);
        }
        System.out.println(sb.toString());
    }

    private static String changePlayerIdToCharacter(ScreenBoard board, int playerId) {
        switch (playerId) {
            case O:
                return " ○ ";
            case X:
                return " × ";
            default:
                if (playerId == board.getDefaultValue()) {
                    return "   ";
                }
                return "P" + Integer.toString(playerId) + " ";
        }
    }
}
