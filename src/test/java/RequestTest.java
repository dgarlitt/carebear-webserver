import com.carebears.Request;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RequestTest {

    @Test
    public void ParsesRawRequestCorrectly() throws Exception {
        Request request = new Request("GET /fake HTTP/1.0");
        assertEquals("GET", request.getMethod());
        assertEquals("/fake", request.getPath());
        assertEquals("HTTP/1.0", request.getVersion());
    }

}
