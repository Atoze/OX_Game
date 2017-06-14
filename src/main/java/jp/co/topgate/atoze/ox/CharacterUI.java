package jp.co.topgate.atoze.ox;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * ターミナルにゲームの状況などのUIを出力するクラスです
 */
public class CharacterUI implements UI {
    private final static String LINE_FEED = System.getProperty("line.separator");

    @Override
    public void printInsert(Player player, Board board, int boardIndex) {
        StringBuilder sb = new StringBuilder();
        sb.append(playerIdToString(player.getID())).append("側");
        sb.append(player.getName()).append("が");
        sb.append(boardIndex).append("番に挿入しました").append(LINE_FEED);
        sb.append("-----------------------------------------------").append(LINE_FEED);

        System.out.println(sb.toString());
    }

    @Override
    public int next() {
        int boardIndex;
        System.out.println("埋まってないマスの中から数字を選択して入力してエンターを押してください");
        while (true) {
            Scanner sc = new Scanner(System.in);
            try {
                boardIndex = sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("埋まってないマスの中から数字を選択して入力してエンターを押してください");
            }
        }
        return boardIndex;
    }

    @Override
    public void printStartTurn(Player player, Board board) {
        StringBuilder sb = new StringBuilder();
        sb.append(playerIdToString(player.getID())).append("側 ");
        sb.append(player.getName()).append("のターンです").append(LINE_FEED);
        sb.append(emptyGridIndicatorToString(board));

        System.out.println(sb.toString());

    }

    @Override
    public void printGameResult(Player winner, List<Player> player, Board board, Result result) {
        StringBuilder sb = new StringBuilder();
        switch (result) {
            case CONTINUE:
                sb.append("現在のボードの状況です.").append(LINE_FEED);
                sb.append(boardToString(board));

                System.out.println(sb.toString());
                return;
            case WIN:
                sb.append("勝負あり！").append(LINE_FEED).append("最終的なボードの状況です.").append(LINE_FEED);
                sb.append(boardToString(board)).append(LINE_FEED);

                sb.append(playerIdToString(winner.getID())).append("側 ");
                sb.append(winner.getName()).append("の勝利\n");
                if (player.size() > 1) {
                    for (int i = 0; i < player.size(); i++) {
                        Player loser = player.get(i);
                        if (loser != winner) {
                            sb.append(playerIdToString(loser.getID())).append("側 ");
                            sb.append(loser.getName());
                        }
                    }
                }
                sb.append("は負けました…");
                System.out.println(sb.toString());
                System.exit(0);
                break;
            case DRAW:
                sb.append("引き分けです.最終的なボードの状況です.").append(LINE_FEED);
                sb.append(boardToString(board));
                System.out.println(sb.toString());
                System.exit(0);
                break;
            default:
                //TODO: ここにたどり着くのはエラー
                return;
        }

    }

    private String boardToString(Board board) {
        int row = board.getRow();
        int column = board.getColumn();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                sb.append("[ ");
                if (board.getPlayerId(j + (i * row)) == board.getDefaultValue()) {
                    sb.append("   ");
                } else {
                    sb.append(playerIdToString(board.getPlayerId(j + (i * row))));
                }
                sb.append(" ]");
            }
            sb.append(LINE_FEED);
        }

        return sb.toString();
    }

    private String emptyGridIndicatorToString(Board board) {
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
                    for (int x = 0; x <= maxNumString; x++) {
                        sb.append(" ");
                    }
                } else {
                    sb.append(" ").append(currentIndex);
                    for (int x = String.valueOf(currentIndex).length(); x < maxNumString; x++) {
                        sb.append(" ");
                    }
                }
                sb.append(" ]");
            }
            sb.append(LINE_FEED);
        }
        return sb.toString();
    }

    private static String playerIdToString(int playerId) {
        switch (playerId) {
            case 1:
                return " ○ ";
            case 2:
                return " × ";
            default:
                return " P" + Integer.toString(playerId) + " ";
        }
    }
}
