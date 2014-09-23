import com.carebears.DocumentRetriever;
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
            String document = docRetriever.getDocument("/foo", "/IDontExist");
        }
        catch(FileNotFoundException ex) {
            docFound = false;
        }

        assertEquals(false, docFound);
    }
}
