import com.carebears.DirectoryObject;
import com.carebears.DirectoryReader;
import com.carebears.Request;
import org.junit.Before;
import org.junit.Test;
import testoutput.fakes.FakeInputStream;

import java.io.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class DirectoryReaderTest {
    private File file;

    @Before
    public void setup() {
        file = new File("/tmp/drtest/dirtest");
        if (file.exists()) {
            file.delete();
        }

        file = new File("/tmp/drtest/dirreader.txt");
        if (file.exists()) {
            file.delete();
        }

        file = new File("/tmp/drtest");

        if (!file.exists()) {
            file.mkdir();
        }
    }

    @Test
    public void emptyDirectoryTest() throws Exception {
        Request request = new Request(new FakeInputStream("GET /drtest HTTP/1.1"), "/tmp");
        DirectoryReader directoryReader = new DirectoryReader(request);
        ArrayList<DirectoryObject> dirList = directoryReader.getDirectoryListing();

        assertEquals(0, dirList.size());

        file.delete();
    }

    @Test
    public void singleFileTest() throws Exception {
        File testFile = new File(file, "dirreader.txt");
        PrintWriter pw = new PrintWriter(new FileOutputStream(testFile));
        pw.println("test");
        pw.close();

        Request request = new Request(new FakeInputStream("GET /drtest HTTP/1.1"), "/tmp");
        DirectoryReader directoryReader = new DirectoryReader(request);
        ArrayList<DirectoryObject> dirList = directoryReader.getDirectoryListing();

        assertEquals(1, dirList.size());

        DirectoryObject dirObj = dirList.get(0);
        assertEquals(false, dirObj.isDirectory());
        assertEquals("dirreader.txt", dirObj.getEntryName());
        assertEquals("/drtest/dirreader.txt", dirObj.getUrlPath());
        assertEquals("/tmp/drtest/dirreader.txt", dirObj.getFile().toString());

        testFile.delete();
        file.delete();
    }

    @Test
    public void formattedListingTest() throws Exception {
        File testFile1 = new File(file, "dirread.txt");

        PrintWriter pw = new PrintWriter(new FileOutputStream(testFile1));
        pw.println("test");
        pw.close();

        File testFile2 = new File(file, "testdir");
        if (!testFile2.exists()) {
            testFile2.mkdir();
        }

        Request request = new Request(new FakeInputStream("GET /drtest HTTP/1.1"), "/tmp");
        DirectoryReader directoryReader = new DirectoryReader(request);

        String content = directoryReader.getFormattedListing();
        StringBuffer wantContent = new StringBuffer("<div>\n");
        wantContent.append("&lt;<a href=\"/drtest/testdir\">testdir</a>&gt;<br />\n");
        wantContent.append("<a href=\"/drtest/dirread.txt\">dirread.txt</a><br />\n");
        wantContent.append("</div>\n");

        assertEquals(wantContent.toString(), content);

        testFile1.delete();
        testFile2.delete();
        file.delete();
    }
}
