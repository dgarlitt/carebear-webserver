import com.carebears.Response;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ResponseTest {
    private Response response;
    private ByteArrayOutputStream outputStream;

    @Before
    public void setup() {
        outputStream = new ByteArrayOutputStream();
        response = new Response(outputStream);
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
        byte[] bytes = response.getBody();
        assertEquals(null, bytes);

        response.setBody(bodyMsg);
        assertEquals(bodyMsg, new String(response.getBody()));
    }

    @Test
    public void ItAcceptsANumberForTheStatusCode() throws Exception {
        response.setStatusCode(302);
        assertEquals(302, response.getStatusCode());
    }

    @Test
    public void ItWritesTheDefaultResponse() throws Exception {
        String expected = "HTTP/1.1 404\n";
        response.send();

        assertEquals(expected, outputStream.toString());
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
        String expected =   "HTTP/1.1 " + response.getStatusCode() + " OK\n" +
                "Accept-Language: " + response.getHeader("Accept-Language") + "\n" +
                "Content-Type: " + response.getHeader("Content-Type") + "\n" +
                "Server: " + response.getHeader("Server") + "\n";
        response.send();

        assertEquals(expected, outputStream.toString());
    }

    @Test
    public void ItSetsCookies() throws Exception {
        response.setStatusCode(200);
        response.setCookie("bob", "dole");
        String expected =   "HTTP/1.1 " + response.getStatusCode() + " OK\n" +
                "Accept-Language: " + response.getHeader("Accept-Language") + "\n" +
                "Content-Type: " + response.getHeader("Content-Type") + "\n" +
                "Server: " + response.getHeader("Server") + "\n" +
                "Set-Cookie: bob=dole;\n";
        response.send();

        assertEquals(expected, outputStream.toString());
    }

    @Test
    public void ItReturnsTheProperBodySize() throws Exception {
        String testOutput = "This is a string";
        response.setBody(testOutput);
        assertEquals(testOutput.length(), response.getBodySize());
    }

    @Test
    public void ItPutsTheBodyInTheResponse() throws Exception {
        response.setStatusCode(200);
        response.setCookie("bob", "dole");
        response.setBody("This is the body.\nAnd, it has two lines.\n");
        String expected =   "HTTP/1.1 " + response.getStatusCode() + " OK\n" +
                "Accept-Language: " + response.getHeader("Accept-Language") + "\n" +
                "Content-Type: " + response.getHeader("Content-Type") + "\n" +
                "Server: " + response.getHeader("Server") + "\n" +
                "Set-Cookie: bob=dole;\n" +
                "\nThis is the body.\nAnd, it has two lines.\n";
        response.send();

        assertEquals(expected, outputStream.toString());
    }

}
