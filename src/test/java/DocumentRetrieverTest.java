import com.carebears.DocumentRetriever;
import com.carebears.Request;
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

            String document = docRetriever.getDocument(request);
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

        String document = docRetriever.getDocument(request);

        file.delete();

        assertEquals("test" + System.lineSeparator(), document);
    }
}
