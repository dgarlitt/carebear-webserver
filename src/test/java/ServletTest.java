import com.carebears.Request;
import com.carebears.Response;
import org.junit.Before;
import org.junit.Test;
import testoutput.fakes.FakeParameterServlet;
import testoutput.fakes.FakeRedirectServlet;
import testoutput.fakes.FakeServlet;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ServletTest {
    private FakeServlet fakeServlet;
    private StringWriter sw;
    private PrintWriter pw;
    private Response response;

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
        Request request = new Request("GET / HTTP/1.0");
        fakeServlet.doGet(request, response);
        assertEquals("HTTP/1.0 200\n", getString());
    }

    @Test
    public void ItHandles404Request() throws Exception {
        Request request = new Request("GET /foobar HTTP/1.0");
        fakeServlet.doDelete(request, response);
        assertEquals("HTTP/1.0 404\n", getString());
    }

    @Test
    public void ItHandlesUnreconizedRequest() throws Exception {
        Request request = new Request("POST / HTTP/1.0");
        fakeServlet.doPost(request, response);
        assertEquals("HTTP/1.0 404\n", getString());
    }

    @Test
    public void ItHandlesRedirectRequest() throws Exception {
        FakeRedirectServlet redirectServlet = new FakeRedirectServlet();
        Request request = new Request("GET / HTTP/1.0");
        redirectServlet.doGet(request, response); 
        assertEquals("HTTP/1.0 302\nLocation: redirect here\n", getString());
    }
    @Test
    public void ItGetsMapOfRequestParameters() throws Exception {
        FakeParameterServlet parameterServlet = new FakeParameterServlet();
        Request request = new Request("GET /parameter?test=123 HTTP/1.0");
        Map<String, String> paramMap = parameterServlet.getParameters(request);
        assertEquals("123", paramMap.get("test"));
    }
    @Test
    public void ItHandlesRequestWithParameters() throws Exception {
        FakeParameterServlet parameterServlet = new FakeParameterServlet();
        Request request = new Request("GET /parameter?test=123&test1=345 HTTP/1.0");
        parameterServlet.doGet(request, response);
        assertEquals("HTTP/1.0 200\ntest = 123\ntest1 = 345\n", getString());
    }

}
