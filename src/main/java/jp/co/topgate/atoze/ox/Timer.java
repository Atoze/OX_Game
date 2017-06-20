package jp.co.topgate.atoze.ox;

/**
 * タイマーの役割を果たすクラス.
 * 0になるまで１秒毎にカウントダウンしますが、0以下の秒数は測りません.
 */
public class Timer extends Thread {
    private int count = 0;
    private boolean done = false;

    private final int sec;
    private final UI ui;
    private final boolean noCount;
    private final int timePrintInterval;

    /**
     * コンストラクタ
     *
     * @param sec               カウントダウンする時間. 0より下の数値を指定すると、カウントダウンを行いません.
     * @param timePrintInterval これ秒毎に残り秒数を表示する
     * @param ui                残り秒数を表示する際のインターフェース
     */
    public Timer(int sec, int timePrintInterval, UI ui) {
        this.sec = sec;
        if (sec < 0) {
            noCount = true;
            count = 1;
        } else {
            noCount = false;
            count = sec;
        }
        this.ui = ui;
        this.timePrintInterval = timePrintInterval;
    }

    public void run() {
        while (count > 0 && !done) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println(e);
            }
            if (!noCount) {
                count--;
                printTimeLeft(sec, count, timePrintInterval);
            }
        }
    }

    /**
     * timePrintInterval秒毎にtimeLeftを表示します.
     *
     * @param timeLeft          現在のタイマーの値
     * @param timePrintInterval 表示する間隔
     */
    void printTimeLeft(int maxTime, int timeLeft, int timePrintInterval) {
        if (0 == timePrintInterval || 0 == (maxTime - timeLeft - timePrintInterval) % (timePrintInterval)) {
            ui.printTimeLeft(timeLeft);
        }
    }

    public int getTime() {
        return count;
    }

    public void shutdown() {
        done = true;
    }
}
