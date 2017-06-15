package jp.co.topgate.atoze.ox;

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
    @Test
    public void CUIにおける正常入力確認テスト() throws InvalidBoardSizeException, IOException {
        UI ui = new CharacterUI();
        //挿入
        InputStream input = new ByteArrayInputStream("1".getBytes());
        System.setIn(input);
        assertThat(ui.selectBoardIndex(), is(1));

        //挿入
        input = new ByteArrayInputStream("1000000".getBytes());
        System.setIn(input);
        assertThat(ui.selectBoardIndex(), is(1000000));
    }

    @Test
    public void CUIにおける正常入力マイナステスト() throws InvalidBoardSizeException, IOException {
        UI ui = new CharacterUI();
        //挿入
        InputStream input = new ByteArrayInputStream("-999".getBytes());
        System.setIn(input);
        assertThat(ui.selectBoardIndex(), is(-999));
    }
}
