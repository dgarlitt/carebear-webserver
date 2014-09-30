import com.carebears.DocumentRetriever;
import com.carebears.MimeTypesStore;
import com.carebears.Request;
import com.carebears.Response;
import org.junit.Before;
import org.junit.Test;
import testoutput.fakes.FakeInputStream;

import java.io.*;

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
            Request request = new Request(new FakeInputStream("GET /IDontExist HTTP/1.1"), "/foo");

            docRetriever.getDocument(request, new Response(new ByteArrayOutputStream()));
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

        Request request = new Request(new FakeInputStream("GET /docret.txt HTTP/1.1"), "/tmp");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Response response = new Response(outputStream);

        String mimeType = MimeTypesStore.getInstance().getContentType("docret.txt");

        docRetriever.getDocument(request, response);
        StringBuffer wantContent = new StringBuffer("HTTP/1.1 200 OK\n");
        wantContent.append("Accept-Language: en-US\n");
        wantContent.append("Content-Length: 5\n");
        wantContent.append("Content-Type: ");
        wantContent.append(mimeType + "\n");
        wantContent.append("Server: CareBearServer/0.0.1\n\n");
        wantContent.append("test" + System.lineSeparator());

        file.delete();

        assertEquals(wantContent.toString(), outputStream.toString());
    }
}
