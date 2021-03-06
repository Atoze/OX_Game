package jp.co.topgate.atoze.ox.ui;

import jp.co.topgate.atoze.ox.*;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.List;

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
    public int selectBoardIndex(int defaultValue, Timer timer) {
        System.out.println("数字を入力してエンターを押してください");
        return select(defaultValue, timer);
    }

    private int select(int defaultValue, Timer timer) {
        BufferedInputStream in = new BufferedInputStream(System.in);
        int input = 0;
        int boardIndex;

        StringBuilder sb = new StringBuilder();
        while (timer.getTime() > 0) {
            if(timer.printTimeLeft()){
                printTimeLeft(timer.getTime());
            }
            try {
                if (0 < (in.available())) {
                    input = in.read();
                    if (input == 10 || input == 0) {
                        break;
                    }
                    sb.append((char) input);
                }
            } catch (IOException e) {
                return defaultValue;
            }
        }
        try {
            boardIndex = Integer.parseInt(sb.toString());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
        return boardIndex;
    }

    @Override
    public void printStartTurn(int currentTurn, Player player, List<Player> players, Board board) {
        StringBuilder sb = new StringBuilder();
        sb.append(playerIdToString(player.getID())).append("側 ");
        sb.append(player.getName()).append("のターンです.").append(LINE_FEED);
        switch (currentTurn) {
            case 1:
                sb.append("最初のターンは、コマは自動的に真ん中に置かれます.");
                break;
            case 2:
                sb.append("このターンでは、最初のコマから隣接した場所にしか置けません.");
            default:
                sb.append("置く場所の数字を指定してください.");
        }
        sb.append(LINE_FEED);
        sb.append(emptyGridIndicatorToString(board));
        System.out.println(sb.toString());
    }

    @Override
    public void printGameResult(Player currentPlayer, List<Player> player, Board board, Result result) {
        StringBuilder sb = new StringBuilder();
        switch (result) {
            case CONTINUE:
                break;
            case WIN:
                sb.append("勝負あり！").append(LINE_FEED).append("最終的なボードの状況です.").append(LINE_FEED);
                sb.append(boardToString(board)).append(LINE_FEED);

                sb.append(playerIdToString(currentPlayer.getID())).append("側 ");
                sb.append(currentPlayer.getName()).append("の勝利\n");
                if (player.size() > 1) {
                    for (int i = 0; i < player.size(); i++) {
                        Player loser = player.get(i);
                        if (loser != currentPlayer) {
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

    @Override
    public void printTimeLeft(int timeLeft) {
        System.out.println("残り時間" + timeLeft + "秒");
    }

    private String boardToString(Board board) {
        int maxNumString = String.valueOf(board.getSize() - 1).length();
        if (maxNumString < 3) {
            maxNumString = 3;
        }
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < board.getColumnValueLength(); i++) {
            sb.append(LINE_FEED);
            sb.append(gridRow(board, i, maxNumString, "·"));
            sb.append(LINE_FEED);
            for (int j = 0; j < board.getRowValueLength(); j++) {
                if (i < board.getColumnValueLength() - 1) {
                    sb.append(charWithSpace("|", maxNumString));
                    sb.append(" ");
                }
            }
        }
        return sb.toString();
    }

    private String emptyGridIndicatorToString(Board board) {
        int maxNumString = String.valueOf(board.getSize() - 1).length();
        if (maxNumString < 3) {
            maxNumString = 3;
        }
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < board.getColumnValueLength(); i++) {
            sb.append(LINE_FEED);
            sb.append(gridRow(board, i, maxNumString, null));
            sb.append(LINE_FEED);
            for (int j = 0; j < board.getRowValueLength(); j++) {
                if (i < board.getColumnValueLength() - 1) {
                    sb.append(charWithSpace("|", maxNumString));
                    sb.append(" ");
                }
            }
        }
        return sb.toString();
    }

    /**
     * CUI表示用に、文字が挿入され整えられた行を返します.
     *
     * @param board         ボードクラス
     * @param currentRow    この行が何行目か
     * @param maxNumString  挿入したいスペースの数
     * @param emptyGridChar 埋まっていない場所に入れるStringです.
     *                      nullを指定すると、そこに対応するBoardIndexを表示します
     * @return 行データを返します
     */
    private String gridRow(Board board, int currentRow, int maxNumString, String emptyGridChar) {
        int row = board.getRowValueLength();
        StringBuilder sb = new StringBuilder();

        for (int currentCol = 0; currentCol < row; currentCol++) {
            int currentIndex = currentCol + (currentRow * row);
            if (currentCol != 0) {
                sb.append("-");
            }
            if (board.isFilled(currentIndex)) {
                String filledChar = playerIdToString(board.getPlayerId(currentIndex));
                sb.append(charWithSpace(filledChar, maxNumString));
            } else {
                if (emptyGridChar != null) {
                    sb.append(charWithSpace(emptyGridChar, maxNumString));
                } else {
                    sb.append(charWithSpace(String.valueOf(currentIndex), maxNumString));
                }
            }
        }
        return sb.toString();
    }

    /**
     * 文字列と最大文字列の長さを揃えるためのメソッド.
     * 文字列(string)が足りないスペースを文字列が真ん中に来るように入れて返す
     * もしスペースが前後に均等に分けて挿入できない場合は、前に数合わせで入る.
     *
     * @param string       文字列(半角でないと崩れます)
     * @param maxNumString 挿入したいスペースの数
     * @return スペースが挿入されたstring
     */
    private String charWithSpace(String string, int maxNumString) {
        StringBuilder sb = new StringBuilder();
        int spaceLength = maxNumString - string.length();
        if (spaceLength % 2 != 0) {
            sb.append(" ");
        }
        for (int frontSpace = 0; frontSpace < spaceLength / 2; frontSpace++) {
            sb.append(" ");
        }
        sb.append(string);
        for (int backSpace = 0; backSpace < spaceLength / 2; backSpace++) {
            sb.append(" ");
        }
        return sb.toString();
    }

    /**
     * プレイヤーIDをそれぞれ対応したボード状のマスとしての形に変換して返します.
     *
     * @param playerId プレイヤーID
     * @return 表示ボード用マス
     */
    private static String playerIdToString(int playerId) {
        switch (playerId) {
            case BLACK:
                return "●";
            case WHITE:
                return "○";
            default:
                return "P" + Integer.toString(playerId);
        }
    }
}
