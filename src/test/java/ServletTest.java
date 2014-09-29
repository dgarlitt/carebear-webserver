import com.carebears.Request;
import com.carebears.Response;
import org.junit.Before;
import org.junit.Test;
import testoutput.fakes.FakeParameterServlet;
import testoutput.fakes.FakeRedirectServlet;
import testoutput.fakes.FakeServlet;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ServletTest {
    private FakeServlet fakeServlet;
    private StringWriter sw;
    private PrintWriter pw;
    private Response response;

    private StringReader sr;
    private BufferedReader getReader(String input) {
        sr = null;
        sr = new StringReader(input);
        return new BufferedReader(sr);

    }

    @Before
    public void CreateAServlet() {
        sw = new StringWriter();
        pw = new PrintWriter(sw);
        response = new Response(pw);
        fakeServlet = new FakeServlet();
    }

    public String getString() {
        return sw.getBuffer().toString();
    }

    @Test
    public void ItHandlesRequest() throws Exception {
        Request request = new Request(getReader("GET / HTTP/1.1"));
        fakeServlet.doGet(request, response);
        assertEquals("HTTP/1.1 200\n", getString());
    }

    @Test
    public void ItHandles404Request() throws Exception {
        Request request = new Request(getReader("GET /foobar HTTP/1.1"));
        fakeServlet.doDelete(request, response);
        assertEquals("HTTP/1.1 405\n", getString());
    }

    @Test
    public void ItHandlesUnreconizedRequest() throws Exception {
        Request request = new Request(getReader("POST / HTTP/1.1"));
        fakeServlet.doPost(request, response);
        assertEquals("HTTP/1.1 405\n", getString());
    }

    @Test
    public void ItHandlesRedirectRequest() throws Exception {
        FakeRedirectServlet redirectServlet = new FakeRedirectServlet();
        Request request = new Request(getReader("GET / HTTP/1.1"));
        redirectServlet.doGet(request, response); 
        assertEquals("HTTP/1.1 302\nLocation: redirect here\n", getString());
    }

    @Test
    public void ItGetsMapOfRequestParameters() throws Exception {
        FakeParameterServlet parameterServlet = new FakeParameterServlet();
        Request request = new Request(getReader("GET /parameter?test=123 HTTP/1.1"));
        HashMap<String, String> paramMap = request.getParameters();
        assertEquals("123", paramMap.get("test"));
    }

    @Test
    public void ItHandlesRequestWithParameters() throws Exception {
        FakeParameterServlet parameterServlet = new FakeParameterServlet();
        Request request = new Request(getReader("GET /parameter?test=123&test1=345 HTTP/1.1"));
        parameterServlet.doGet(request, response);
        assertEquals("HTTP/1.1 200\ntest = 123\ntest1 = 345\n", getString());
    }

    @Test
    public void DefaultDoOptionsResponseHasAllowHeader() throws Exception {
        Request request = new Request(getReader("OPTIONS / HTTP/1.1"));
        fakeServlet.doOptions(request, response);
        assertEquals("GET,HEAD,POST,OPTIONS,PUT", response.getHeader("Allow"));
    }


}
