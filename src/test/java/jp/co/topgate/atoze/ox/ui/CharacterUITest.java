package jp.co.topgate.atoze.ox.ui;

import jp.co.topgate.atoze.ox.Timer;
import jp.co.topgate.atoze.ox.UI;
import jp.co.topgate.atoze.ox.exception.InvalidBoardSizeException;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by atoze on 2017/06/15.
 */
public class CharacterUITest {
    Timer timer = new Timer(1, 0);
    private final static String LINE_FEED = System.getProperty("line.separator");

    @Test
    public void CUIにおける正常入力確認テスト() throws InvalidBoardSizeException, IOException {
        UI ui = new CharacterUI();
        //挿入
        InputStream input = new ByteArrayInputStream(("1" + LINE_FEED).getBytes());
        System.setIn(input);
        assertThat(ui.selectBoardIndex(-1, timer), is(1));

        //挿入
        input = new ByteArrayInputStream(("1000000" + LINE_FEED).getBytes());
        System.setIn(input);
        assertThat(ui.selectBoardIndex(-1, timer), is(1000000));
    }

    @Test
    public void CUIにおける正常入力マイナステスト() throws InvalidBoardSizeException, IOException {
        UI ui = new CharacterUI();
        //挿入
        InputStream input = new ByteArrayInputStream(("-999" + LINE_FEED).getBytes());
        System.setIn(input);
        assertThat(ui.selectBoardIndex(-1, timer), is(-999));
    }

    @Test
    public void 入力時間切れテスト() throws InvalidBoardSizeException, IOException {
        UI ui = new CharacterUI();
        Timer timer = new Timer(1, 0);
        timer.start();
        //挿入
        InputStream input = new ByteArrayInputStream(("").getBytes());
        System.setIn(input);
        assertThat(ui.selectBoardIndex(-1, timer), is(-1));
    }

    @Test
    public void 文字列入力されたテスト() throws InvalidBoardSizeException, IOException {
        UI ui = new CharacterUI();
        Timer timer = new Timer(1, 0);
        timer.start();
        //挿入
        InputStream input = new ByteArrayInputStream(("hoge" + LINE_FEED).getBytes());
        System.setIn(input);
        assertThat(ui.selectBoardIndex(-1, timer), is(-1));
    }
}
