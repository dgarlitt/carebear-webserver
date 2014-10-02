import com.carebears.Request;
import com.carebears.Response;
import org.junit.Before;
import org.junit.Test;
import testoutput.fakes.FakeInputStream;
import testoutput.fakes.FakeParameterServlet;
import testoutput.fakes.FakeRedirectServlet;
import testoutput.fakes.FakeServlet;

import java.io.*;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ServletTest {
    private FakeServlet fakeServlet;
    private StringWriter sw;
    private PrintWriter pw;
    private Response response;
    private ByteArrayOutputStream byteOutputStream;

    @Before
    public void CreateAServlet() {
        byteOutputStream = new ByteArrayOutputStream();
        response = new Response(byteOutputStream);
        fakeServlet = new FakeServlet();
    }

    public String getResponseText() {
        return byteOutputStream.toString();
    }

    @Test
    public void ItHandlesRequest() throws Exception {
        Request request = new Request(new FakeInputStream("GET / HTTP/1.1"));
        fakeServlet.doGet(request, response);
        assertEquals("HTTP/1.1 200\n", getResponseText());
    }

    @Test
    public void ItHandles404Request() throws Exception {
        Request request = new Request(new FakeInputStream("GET /foobar HTTP/1.1"));
        fakeServlet.doDelete(request, response);
        assertEquals("HTTP/1.1 405\n", getResponseText());
    }

    @Test
    public void ItHandlesUnreconizedRequest() throws Exception {
        Request request = new Request(new FakeInputStream("POST / HTTP/1.1"));
        fakeServlet.doPost(request, response);
        assertEquals("HTTP/1.1 405\n", getResponseText());
    }

    @Test
    public void ItHandlesRedirectRequest() throws Exception {
        FakeRedirectServlet redirectServlet = new FakeRedirectServlet();
        Request request = new Request(new FakeInputStream("GET / HTTP/1.1"));
        redirectServlet.doGet(request, response); 
        assertEquals("HTTP/1.1 302\nLocation: redirect here\n", getResponseText());
    }

    @Test
    public void ItGetsMapOfRequestParameters() throws Exception {
        FakeParameterServlet parameterServlet = new FakeParameterServlet();
        Request request = new Request(new FakeInputStream("GET /parameter?test=123 HTTP/1.1"));
        HashMap<String, String> paramMap = request.getParametersMap();
        assertEquals("123", paramMap.get("test"));
    }

    @Test
    public void ItHandlesRequestWithParameters() throws Exception {
        FakeParameterServlet parameterServlet = new FakeParameterServlet();
        Request request = new Request(new FakeInputStream("GET /parameter?test=123&test1=345 HTTP/1.1"));
        parameterServlet.doGet(request, response);
        assertEquals("HTTP/1.1 200\ntest = 123\ntest1 = 345\n", getResponseText());
    }

    @Test
    public void DefaultDoOptionsResponseHasAllowHeader() throws Exception {
        Request request = new Request(new FakeInputStream("OPTIONS / HTTP/1.1"));
        fakeServlet.doOptions(request, response);
        assertEquals("GET,HEAD,POST,OPTIONS,PUT", response.getHeader("Allow"));
    }

    @Test
    public void DefaultDoPatchResponseIsFourOhFive() throws Exception {
        Request request = new Request(new FakeInputStream("PATCH / HTTP/1.1"));
        fakeServlet.doPatch(request, response);
        assertEquals("HTTP/1.1 405\n", getResponseText());
    }


}
