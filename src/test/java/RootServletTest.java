import com.carebears.Request;
import com.carebears.Response;
import com.carebears.servlets.RootServlet;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class RootServletTest {

    private StringReader sr;
    private BufferedReader getReader(String input) {
        sr = null;
        sr = new StringReader(input);
        return new BufferedReader(sr);

    }

    @Test
    public void itReturnsDirectoryListing() throws Exception {
        File file = new File("/tmp/drtest/testdir");
        if (file.exists()) {
            file.delete();
        }

        file = new File("/tmp/drtest/dirread.txt");
        if (file.exists()) {
            file.delete();
        }

        file = new File("/tmp/drtest");

        if (!file.exists()) {
            file.mkdir();
        }

        File testFile1 = new File(file, "dirread.txt");

        PrintWriter pw = new PrintWriter(new FileOutputStream(testFile1));
        pw.println("test");
        pw.close();

        File testFile2 = new File(file, "testdir");
        if (!testFile2.exists()) {
            testFile2.mkdir();
        }

        Request request = new Request(getReader("GET / HTTP/1.1"), "/tmp/drtest");

        StringWriter sw = new StringWriter();
        pw = new PrintWriter(sw);
        Response response = new Response(pw);

        RootServlet rootServlet = new RootServlet();
        rootServlet.doGet(request, response);

        String output = sw.toString();

        StringBuffer wantContent = new StringBuffer("HTTP/1.1 200 OK\n");
        wantContent.append("Server: CareBearServer/0.0.1\n");
        wantContent.append("Accept-Language: en-US\n");
        wantContent.append("Content-Type: text/html; charset=utf-8\n\n");
        wantContent.append("<html>\n<head>\n");
        wantContent.append("<title>Directory of /</title>\n");
        wantContent.append("</head>\n<body>\n");
        wantContent.append("<h2>Directory of /</h2>\n");
        wantContent.append("<div>\n");
        wantContent.append("&lt;<a href=\"/testdir\">testdir</a>&gt;<br />\n");
        wantContent.append("<a href=\"/dirread.txt\">dirread.txt</a><br />\n");
        wantContent.append("</div>\n");
        wantContent.append("</html>\n");

        assertEquals(wantContent.toString(), output);

        testFile1.delete();
        testFile2.delete();
        file.delete();
    }
}
