package jp.co.topgate.atoze.ox;

/**
 * Created by atoze on 2017/06/07.
 */
public class CharacterUI implements UI {
    public void insert(Player player, int gridIndex) {
        System.out.printf("Player%d " + player.getName() + "が　%d番に挿入しました\n", player.getID(), gridIndex);
        System.out.println("-----------------------------------------------");
        System.out.println();
    }

    public void turnStart(Player player, ScreenBoard board) {
        System.out.printf("%sのターンです\n", player.getName());
        showBoard(board);
        System.out.println();
        System.out.println();
        showNotFilled(board);

    }

    public void gameSet(Player player, ScreenBoard board, Result result) {
        switch (result) {
            case WIN:
                showBoard(board);
                System.out.printf("Player%d %sの勝利\n", player.getID(), player.getName());
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
                System.out.print(setCharacter(board.getPlayerId(j + (i * row))));
                System.out.print("]");
            }
            System.out.print("\n");
        }
    }

    private void showNotFilled(ScreenBoard board) {
        int row = board.getRow();
        int column = board.getColumn();
        int maxNumString = String.valueOf(board.getLength()).length();

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

    private static String setCharacter(int playerId) {
        switch (playerId) {
            case -1:
                return "   ";
            case 1:
                return " ○ ";
            case 2:
                return " × ";
            default:
                return "P" + Integer.toString(playerId) + " ";
        }
    }
}
