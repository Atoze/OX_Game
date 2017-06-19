package jp.co.topgate.atoze.ox;

/**
 * Created by atoze on 2017/06/19.
 */
public class Timer extends Thread{
    private int count=0;
    private final UI ui;
    public Timer(int sec, UI ui) {
        count = sec;
        this.ui = ui;
    }

    public void run() {
        while (count > 0) {
            try {
                Thread.sleep(1000);
                System.out.print("残り時間" + count + "秒");
            }
            catch (InterruptedException ex) {
                System.err.println(ex);
            }
            count--;
        }
    }

    /**残り時間の取得*/
    public int getTime() {
        return count;
    }
}
