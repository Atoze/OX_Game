package jp.co.topgate.atoze.ox;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by atoze on 2017/06/06.
 */
public class HumanPlayer implements Player {
    private String name = "プレイヤー";
    private final int id;

    HumanPlayer(int id) {
        this.id = id;
        this.name = name + id + "さん";
    }

    HumanPlayer(int id, String name) {
        this.id = id;
        this.name = name + "さん";
    }

    @Override
    public int next(ScreenBoard board) {
        int boardIndex;
        while (true) {
            System.out.println("埋まってないマスの中から数字を選択して入力してエンターを押してください");
            int input;
            while (true) {
                Scanner sc = new Scanner(System.in);
                try {
                    int i = sc.nextInt();
                    if (board.accept(i)) {
                        boardIndex = i;
                        break;
                    } else {
                        System.out.println("埋まってないマスの中から数字を選択して入力してエンターを押してください");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("埋まってないマスの中から数字を選択して入力してエンターを押してください");
                }
            }
            return boardIndex;
        }
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
