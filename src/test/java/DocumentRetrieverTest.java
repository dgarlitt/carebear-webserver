import com.carebears.DocumentRetriever;
import com.carebears.Request;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import static org.junit.Assert.assertEquals;

public class DocumentRetrieverTest {
    public DocumentRetriever docRetriever;
    
    @Before
    public void setup() {
        docRetriever = new DocumentRetriever();
    }
    
    @Test
    public void DocumentIsNotFound() throws Exception {
        boolean docFound = true;

        try {
            Request request = new Request("GET /IDontExist HTTP/1.1", "/foo");

            String document = docRetriever.getDocument(request);
        }
        catch(FileNotFoundException ex) {
            docFound = false;
        }

        assertEquals(false, docFound);
    }

    @Test
    public void DocumentIsReturned() throws Exception {
        File file = new File("/tmp/drtest.txt");
        PrintWriter pw = new PrintWriter(new FileOutputStream(file));
        pw.println("test");
        pw.close();

        Request request = new Request("GET /drtest.txt HTTP/1.1", "/tmp");

        String document = docRetriever.getDocument(request);

        file.delete();

        assertEquals("test" + System.lineSeparator(), document);
    }
}
