import com.carebears.DocumentWriter;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static junit.framework.TestCase.assertEquals;

public class DocumentWriterTest {
    String dir;
    File file;
    DocumentWriter dw;

    @Before
    public void setup() {
        dir = this.getClass().getClassLoader().getResource("").getPath();
        file = new File(dir + "test-doc.txt");
        dw = new DocumentWriter(file);
    }

    @Test
    public void itAppendsToDocument() throws Exception {
        dw.appendToFile("Hello, World!");
        assertEquals("Hello, World!\n", dw.readFileToString());
    }

    @Test
    public void itFindsAndReplacesInDocument() throws Exception {
        dw.findAndReplaceFileContent("Hello", "Goodbye");
        assertEquals("Goodbye, World!\n", dw.readFileToString());
    }

    @Test
    public void itOverwritesFile() throws Exception {
        dw.replaceFileContent("Entirely new file content");
        assertEquals("Entirely new file content\n", dw.readFileToString());
    }

}
