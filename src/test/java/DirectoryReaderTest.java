import com.carebears.DirectoryReader;
import com.carebears.Request;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class DirectoryReaderTest {

    @Test
    public void emptyDirectoryTest() throws Exception {
        File file = new File("/tmp/drtest");
        if (!file.exists()) {
            file.mkdir();
        }

        Request request = new Request("GET drtest HTTP/1.1", "/tmp");
        DirectoryReader directoryReader = new DirectoryReader(request);

        String dirBody = directoryReader.getFormattedListing();

        assertEquals("<div>\n<a href=\"/\">..</a>\n</div>", dirBody);

    }
}
