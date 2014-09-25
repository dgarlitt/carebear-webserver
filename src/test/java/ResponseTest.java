import com.carebears.Response;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ResponseTest {

    private StringWriter swriter;
    private PrintWriter writer;
    private Response response;

    @Before
    public void setup() {
        swriter = new StringWriter();
        writer = new PrintWriter(swriter);
        response = new Response(writer);
    }

    @Test
    public void ItGetsAWriter() throws Exception {
        assertEquals(writer, response.getWriter());
    }

    @Test
    public void ItAcceptsAStatusCode() throws Exception {
        response.setStatusCode(200);

        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void ItGetsAndSetsHeaders() throws Exception {
        assertEquals("CareBearServer/0.0.1", response.getHeader("Server"));
        assertEquals("text/html; charset=utf-8", response.getHeader("Content-Type"));
        assertEquals("en-US", response.getHeader("Accept-Language"));

        response.setHeader("Content-Type", "text/xml");
        assertEquals("text/xml", response.getHeader("Content-Type"));
    }

    @Test
    public void ItCanSetAndGetTheBody() throws Exception {
        String bodyMsg = "This is the body of the document\nIt has two lines. Yay!";

        assertEquals("", response.getBody());

        response.setBody(bodyMsg);
        assertEquals(bodyMsg, response.getBody());
    }

    @Test
    public void ItAcceptsANumberForTheStatusCode() throws Exception {
        response.setStatusCode(302);
        assertEquals(302, response.getStatusCode());
    }

    @Test
    public void ItWritesTheDefaultResponse() throws Exception {
        String expected = "HTTP/1.0 404\n";
        response.send();

        assertEquals(expected, swriter.getBuffer().toString());
    }

    @Test
    public void ItWritesALocationHeader() throws Exception {
        String loc = "http://localhost/somewhere";
        response.setHeader("Location", loc);
        assertEquals(loc, response.getHeader("Location"));
    }

    @Test
    public void ItWritesTheExpectedResponse() throws Exception {
        response.setStatusCode(200);
        String expected =   "HTTP/1.0 " + response.getStatusCode() + " OK\n" +
                            "Server: " + response.getHeader("Server") + "\n" +
                            "Accept-Language: " + response.getHeader("Accept-Language") + "\n" +
                            "Content-Type: " + response.getHeader("Content-Type") + "\n";
        response.send();

        assertEquals(expected, swriter.getBuffer().toString());
    }

}
