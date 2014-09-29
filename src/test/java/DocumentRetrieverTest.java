import com.carebears.DocumentRetriever;
import com.carebears.Request;
import com.carebears.Response;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class DocumentRetrieverTest {
    public DocumentRetriever docRetriever;

    private StringReader sr;
    private BufferedReader getReader(String input) {
        sr = null;
        sr = new StringReader(input);
        return new BufferedReader(sr);

    }

    @Before
    public void setup() {
        docRetriever = new DocumentRetriever();
    }
    
    @Test
    public void DocumentIsNotFound() throws Exception {
        boolean docFound = true;

        try {
            Request request = new Request(getReader("GET /IDontExist HTTP/1.1"), "/foo");

            docRetriever.getDocument(request, new Response(new PrintWriter(new StringWriter())));
        }
        catch(FileNotFoundException ex) {
            docFound = false;
        }

        assertEquals(false, docFound);
    }

    @Test
    public void DocumentIsReturned() throws Exception {
        File file = new File("/tmp/docret.txt");
        PrintWriter pw = new PrintWriter(new FileOutputStream(file));
        pw.println("test");
        pw.close();

        Request request = new Request(getReader("GET /docret.txt HTTP/1.1"), "/tmp");
        StringWriter sw = new StringWriter();
        pw = new PrintWriter(sw);
        Response response = new Response(pw);

        docRetriever.getDocument(request, response);
        StringBuffer wantContent = new StringBuffer("HTTP/1.1 200 OK\n");
        wantContent.append("Server: CareBearServer/0.0.1\n");
        wantContent.append("Accept-Language: en-US\n");
        wantContent.append("Content-Type: text/html; charset=utf-8\n\n");
        wantContent.append("test" + System.lineSeparator());

        file.delete();

        //assertEquals(wantContent.toString(), sw.toString());
    }
}
