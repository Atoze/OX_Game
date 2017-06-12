package jp.co.topgate.atoze.ox;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * ターミナルから数字入力を受け付けるクラスです.
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
    public String getName() {
        return name;
    }

    @Override
    public int getID() {
        return id;
    }
}
