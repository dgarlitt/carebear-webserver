import com.carebears.Request;
import com.carebears.Response;
import com.carebears.servlets.FormServlet;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import testoutput.fakes.FakeInputStream;

import java.io.*;

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
        formServlet = new FormServlet();
    }

    //@Test
    public void ItSubmitsAGetRequest() throws Exception {
        Request request = new Request(new FakeInputStream("GET /form HTTP/1.1"));
        formServlet.doGet(request, response);

        assertEquals("HTTP/1.1 200 OK\n" +
                "Server: CareBearServer/0.0.1\n" +
                "Accept-Language: en-US\n" +
                "Content-Type: text/html; charset=utf-8\n", getResponseText());
    }

    //@Test
    public void ItSubmitsAPostRequest() throws Exception {
        Request request = new Request(new FakeInputStream("POST /form HTTP/1.1\n\n\ndata=cosby\n"));
        formServlet.doPost(request, response);

        assertEquals("HTTP/1.1 200 OK\n" +
                "Server: CareBearServer/0.0.1\n" +
                "Accept-Language: en-US\n" +
                "Content-Type: text/html; charset=utf-8\n\ndata = cosby\n", getResponseText());
    }
}
