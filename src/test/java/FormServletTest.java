import com.carebears.Request;
import com.carebears.Response;
import com.carebears.Server;
import com.carebears.Session;
import com.carebears.servlets.FormServlet;
import org.junit.Before;
import org.junit.Test;
import testoutput.fakes.FakeInputStream;

import java.io.ByteArrayOutputStream;

import static junit.framework.TestCase.assertEquals;

public class FormServletTest {
    private FormServlet formServlet;
    private Response response;
    private ByteArrayOutputStream byteOutputStream;

    public String getResponseText() {
        return byteOutputStream.toString();
    }


    @Before
    public void Setup() {
        byteOutputStream = new ByteArrayOutputStream();
        response = new Response(byteOutputStream);
        Server.CONFIG.setSession(new Session());
        formServlet = new FormServlet();
    }

    @Test
    public void ItSubmitsAGetRequest() throws Exception {
        Server.CONFIG.getSession().setData("cosby");
        Request request = new Request(new FakeInputStream("GET /form HTTP/1.1"));
        formServlet.doGet(request, response);

        assertEquals("HTTP/1.1 200 OK\n" +
                "Accept-Language: en-US\n" +
                "Content-Type: text/html; charset=utf-8\n" +
                "Server: CareBearServer/0.0.1\n\n" +
                "data = cosby", getResponseText());
    }

    @Test
     public void ItSubmitsAPostRequest() throws Exception {
        Server.CONFIG.setSession(new Session());
        Request request = new Request(new FakeInputStream("POST /form HTTP/1.1\nContent-Length:10\n\ndata=cosby\n"));

        formServlet.doPost(request, response);

        assertEquals("HTTP/1.1 200 OK\n" +
                "Accept-Language: en-US\n" +
                "Content-Type: text/html; charset=utf-8\n" +
                "Server: CareBearServer/0.0.1\n", getResponseText());
    }

    @Test
    public void ItSubmitsAPutRequest() throws Exception {
        Server.CONFIG.setSession(new Session());
        Request request = new Request(new FakeInputStream("PUT /form HTTP/1.1\nContent-Length:10\n\ndata=cosby\n"));

        formServlet.doPut(request, response);

        assertEquals("HTTP/1.1 200 OK\n" +
                "Accept-Language: en-US\n" +
                "Content-Type: text/html; charset=utf-8\n" +
                "Server: CareBearServer/0.0.1\n", getResponseText());
    }

    @Test
    public void ItSubmitsADeleteRequest() throws Exception {
        Server.CONFIG.setSession(new Session());
        Request request = new Request(new FakeInputStream("DELETE /form HTTP/1.1\nContent-Length:10\n\ndata=cosby\n"));

        formServlet.doDelete(request, response);

        assertEquals("HTTP/1.1 200 OK\n" +
                "Accept-Language: en-US\n" +
                "Content-Type: text/html; charset=utf-8\n" +
                "Server: CareBearServer/0.0.1\n", getResponseText());
    }

    @Test
    public void ItDoesAGetPath() throws Exception {
        assertEquals("/form", formServlet.getPath());
    }

}
