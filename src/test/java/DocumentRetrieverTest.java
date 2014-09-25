import com.carebears.DocumentRetriever;
import com.carebears.Request;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

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
}
