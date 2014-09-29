import com.carebears.Request;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;

public class RequestTest {
    private StringReader sr;
    private BufferedReader getReader(String input) {
        sr = null;
        sr = new StringReader(input);
        return new BufferedReader(sr);
    }

    @Test
    public void ParsesRawRequestCorrectly() throws Exception {
        Request request = new Request(getReader("GET /fake HTTP/1.1"));
        assertEquals("GET", request.getMethod());
        assertEquals("/fake", request.getPath());
        assertEquals("HTTP/1.1", request.getVersion());
    }

    @Test
    public void ParsesHeaderCorrectly() throws Exception {
        Request request = new Request(getReader("GET /fake HTTP/1.1\nhost:localhost"));
        assertEquals("localhost", request.getHeader("host"));
    }

    @Test
    public void SkipsHeaderParsing() throws Exception {
        Request request = new Request(getReader("GET /fake HTTP/1.1\nthis is wrong"));
        assertEquals(null, request.getHeader("host"));
    }

    @Test
    public void ParsesRawRequestWithParameters() throws Exception {
        Request request = new Request(getReader("GET /fake?var=123 HTTP/1.1"));
        assertEquals("GET", request.getMethod());
        assertEquals("/fake", request.getPath());
        assertEquals("HTTP/1.1", request.getVersion());
        assertEquals("{var=123}", request.getParameters().toString());
    }

    @Test
    public void getParam() throws Exception {
        Request request = new Request(getReader("GET /fake?var=123 HTTP/1.1"));
        assertEquals("123", request.getParam("var"));
    }

}
