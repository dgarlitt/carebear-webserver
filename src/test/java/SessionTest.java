import com.carebears.Session;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SessionTest {
    private Session session;

    @Before
    public void setup() {
        session = new Session();
    }

    @Test
    public void itReturnsSetData() throws Exception {
        String sessData = "This is my session data";
        session.setData(sessData);
        assertEquals(sessData, session.getData());
    }

    @Test
    public void itReturnsLogData() throws Exception {
        String logData = "HTTP/1.1 GET /log\nHTTP/1.1 PUT /these\n";
        session.addLogData("HTTP/1.1 GET /log");
        session.addLogData("HTTP/1.1 PUT /these");
        assertEquals(logData, session.getLogData());
    }

    @Test
    public void itReturnsAuthorized() throws Exception {
        session.setAuthorized();
        assertTrue(session.isAuthorized());

    }
}
