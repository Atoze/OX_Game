package jp.co.topgate.atoze.ox;

/**
 * タイマーの役割を果たすクラス.
 * 0になるまで１秒毎にカウントダウンしますが、0以下の秒数は測りません.
 */
public class Timer extends Thread {
    private int count = 0;
    private boolean done = false;

    private final int sec;
    private final boolean noCount;
    private final int timePrintInterval;

    private boolean isPastOneSec = false;

    /**
     * コンストラクタ
     *
     * @param sec               カウントダウンする時間. 0より下の数値を指定すると、カウントダウンを行いません.
     * @param timePrintInterval これ秒毎に残り秒数を表示する
     */
    public Timer(int sec, int timePrintInterval) {
        this.sec = sec;
        if (sec < 0) {
            noCount = true;
            count = 1;
        } else {
            noCount = false;
            count = sec;
        }
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
                if (!isPastOneSec) {
                    isPastOneSec = true;
                }
            }
        }
    }

    /**
     * timePrintIntervalで指定した時間が経過したら、trueで返します.
     */
    public boolean printTimeLeft() {
        if ((0 == timePrintInterval || 0 == (sec - count - timePrintInterval) % (timePrintInterval)) && isPastOneSec) {
            isPastOneSec = false;
            /*
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                return false;
            }*/
            return true;
        }
        return false;
    }

    public int getTime() {
        return count;
    }

    public void shutdown() {
        done = true;
    }
}
