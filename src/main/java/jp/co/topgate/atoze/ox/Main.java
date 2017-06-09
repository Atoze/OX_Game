package jp.co.topgate.atoze.ox;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * メインメソッド
 */
public class Main {
    Scanner scanner;


    public static void main(String[] args) {
        List<Player> players = new ArrayList<>();
        players.add(new EasyCPU(1));
        players.add(new EasyCPU(2));
        OXGame game = new OXGame();
        game.playSquareBoard(5, players, new CharacterUI());
    }
}