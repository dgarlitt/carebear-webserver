import com.carebears.DocumentWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class DocumentWriterTest {
    String dir;
    File file;
    DocumentWriter dw;

    @Test
    public void TrueIsTrue() throws Exception {
        assertTrue(true);
    }

//    @Before
    public void setup() {
        dir = this.getClass().getClassLoader().getResource("").getPath();
        file = new File(dir + "test-doc.txt");
        dw = new DocumentWriter(file);

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @After
    public void teardown() {
        file.delete();
    }

//    @Test
    public void itAppendsToDocument() throws Exception {
        dw.appendToFile("Hello, World!");
        assertEquals("Hello, World!\n", dw.readFileToString());
    }

//    @Test
    public void itFindsAndReplacesInDocument() throws Exception {
        dw.appendToFile("Hello, World!");
        dw.findAndReplaceFileContent("Hello", "Goodbye");
        assertEquals("Goodbye, World!\n", dw.readFileToString());
    }

//    @Test
    public void itOverwritesFile() throws Exception {
        dw.appendToFile("Hello, World!");
        dw.replaceFileContent("Entirely new file content");
        assertEquals("Entirely new file content\n", dw.readFileToString());
    }

}
