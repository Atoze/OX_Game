package jp.co.topgate.atoze.ox;

/**
 * Created by atoze on 2017/06/07.
 */
public class CharacterUI implements UI{
    public void insert(Player player, int gridIndex) {
        System.out.printf("Player%d " + player.getName() + "が　%d番に挿入しました\n", player.getID(), gridIndex);
        System.out.println("-----------------------------------------------");
        System.out.println();
    }

    public void showBoard(Board board) {
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

    public void showNotFilled(Board board) {
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
                    //System.out.print(setCharacter(board.getPlayerId(j + (i * row))));
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
