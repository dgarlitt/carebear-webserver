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
        String logData = "GET /log HTTP/1.1\nPUT /these HTTP/1.1\n";
        session.addLogData("GET /log HTTP/1.1");
        session.addLogData("PUT /these HTTP/1.1");
        assertEquals(logData, session.getLogData());
    }

    @Test
    public void itReturnsAuthorized() throws Exception {
        session.setAuthorized();
        assertTrue(session.isAuthorized());

    }
}
