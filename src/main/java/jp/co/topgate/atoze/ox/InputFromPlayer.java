package jp.co.topgate.atoze.ox;

import java.io.BufferedInputStream;
import java.io.IOException;

/**
 * Created by atoze on 2017/06/19.
 */
public class InputFromPlayer {
    private static BufferedInputStream in = new BufferedInputStream(System.in);

    InputFromPlayer() {
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        //System.out.print(select());
    }

/*
    public static int select() throws IOException {
        Timer timer = new Timer(5);
        timer.start();
        int input = 0;
        StringBuilder sb = new StringBuilder();
        while (timer.getTime() > 0) {
            if (0 < (in.available())) {
                input = in.read();
                if (input == 10 || input == 0) {
                    break;
                }
                sb.append((char) input);
                timer.stop();
            }
        }
        if(sb.toString().isEmpty()){
            return (int) (Math.random() * 3);
        }
        return Integer.parseInt(sb.toString());
    }*/

}
