package edu.umkc.se.messager;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Local unit test
 *
 * 1. Test that sendText() is receiving a string and returning true
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class messager_UnitTest {
    @Test
    public void sendTextValidator_CorrectMessage_DisplaysText(){
        assertThat(message_backend.sendMessage("8165093340","hello world"), is(true));
    }
}
