package jp.co.topgate.atoze.ox;

import java.util.List;

/**
 * ターミナルにゲームの状況などのUIを出力するクラスです
 */
public class CharacterUI implements UI {
    public final static int O = 1;
    public final static int X = 2;


    public void showInsert(Player player, ScreenBoard board, int boardIndex) {
        System.out.printf("%s側 %sが　%d番に挿入しました\n", setCharacter(board, player.getID()), player.getName(), boardIndex);
        System.out.println("-----------------------------------------------");
        System.out.println();
    }

    public void turnStart(Player player, ScreenBoard board) {
        System.out.printf("%s側　%sのターンです\n", setCharacter(board, player.getID()), player.getName());
        showBoard(board);
        System.out.println();
        System.out.println();
        showNotFilled(board);

    }

    public void gameSet(Player winner, List<Player> player, ScreenBoard board, Result result) {
        switch (result) {
            case WIN:
                showBoard(board);
                System.out.printf("%s側 %sの勝利\n", setCharacter(board, winner.getID()), winner.getName());
                if (player.size() > 1) {
                    for (int i = 0; i < player.size(); i++) {
                        Player loser = player.get(i);
                        if (loser != winner) {
                            System.out.printf("%s側 %s", setCharacter(board, loser.getID()), loser.getName());
                        }
                    }
                }
                System.out.println("は負けました…");

                break;
            case DRAW:
                showBoard(board);
                System.out.println("引き分けです");
                break;
        }
    }

    private void showBoard(ScreenBoard board) {
        int row = board.getRow();
        int column = board.getColumn();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print("[");
                System.out.print(setCharacter(board, board.getPlayerId(j + (i * row))));
                System.out.print("]");
            }
            System.out.print("\n");
        }
    }

    private void showNotFilled(ScreenBoard board) {
        int row = board.getRow();
        int column = board.getColumn();
        int maxNumString = String.valueOf(board.getSize()).length();

        System.out.println("現在埋まっていないマスです.");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                int currentIndex = j + (i * row);

                System.out.print("[");
                if (board.isFilled(currentIndex)) {
                    System.out.print(" ");
                    for (int x = 0; x < maxNumString; x++) {
                        System.out.print(" ");
                    }
                    System.out.print(" ");
                } else {
                    System.out.printf(" %d ", currentIndex);

                    if (String.valueOf(currentIndex).length() < maxNumString) {
                        System.out.print(" ");
                    }
                }
                System.out.print("]");
            }
            System.out.print("\n");
        }
    }

    private static String setCharacter(ScreenBoard board, int playerId) {
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
