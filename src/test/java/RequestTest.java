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
        Request request = new Request(getReader("GET /fake HTTP/1.1\nHost:localhost"));
        assertEquals("localhost", request.getHeader("Host"));
    }

    @Test
    public void ParsesTwoHeadersCorrectly() throws Exception {
        Request request = new Request(getReader("GET /fake HTTP/1.1\nthisiswrong\nHost:localhost"));
        assertEquals(null, request.getHeader("thisiswrong"));
        assertEquals("localhost", request.getHeader("Host"));
    }

    @Test
    public void ParsesMultipleHeadersCorrectly() throws Exception {
        Request request = new Request(getReader("GET /fake HTTP/1.1\nHost:localhost\nAccept:text/html\nUser-agent:mozilla/5.0"));
        assertEquals("localhost", request.getHeader("Host"));
        assertEquals("text/html", request.getHeader("Accept"));
        assertEquals("mozilla/5.0", request.getHeader("User-agent"));
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
        assertEquals("{var=123}", request.getParametersMap().toString());
    }

    @Test
    public void getParam() throws Exception {
        Request request = new Request(getReader("GET /fake?var=123 HTTP/1.1"));
        assertEquals("123", request.getParam("var"));
    }

    @Test
    public void ParsesAndRetrievesCookies() throws Exception {
        Request request = new Request(getReader("GET /fake HTTP/1.1\nCookie:test=12345;test1=67890"));
        assertEquals("test=12345;test1=67890", request.getHeader("Cookie"));
        assertEquals("12345", request.getCookie("test"));
        assertEquals("67890", request.getCookie("test1"));
    }
}
