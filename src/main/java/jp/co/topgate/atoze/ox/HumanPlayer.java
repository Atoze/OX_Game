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
    public int next(Board board, Rule rule) {
        int gridIndex;
        while (true) {
            System.out.println(name + "の番です  埋まってないマスの中から数字を選択して入力してエンターを押してください");
            int input;
            while (true) {
                Scanner sc = new Scanner(System.in);
                try {
                    int i = sc.nextInt();
                    if (rule.accept(i)) {
                        gridIndex = i;
                        break;
                    } else {
                        System.out.printf("埋まってないマスの中から数字を選択して入力してエンターを押してください");
                    }
                } catch (InputMismatchException e) {
                    System.out.printf("埋まってないマスの中から数字を選択して入力してエンターを押してください");
                }
            }
            return gridIndex;
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
