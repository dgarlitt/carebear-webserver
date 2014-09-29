import com.carebears.AbsolutePathMapper;
import com.carebears.Request;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;

public class AbsolutePathMapperTest {
    private StringReader sr;
    private BufferedReader getReader(String input) {
        sr = null;
        sr = new StringReader(input);
        return new BufferedReader(sr);

    }

    @Test
    public void pathTest() throws Exception {
        Request request = new Request(getReader("GET /foo HTTP/1.1"), "/tmp");
        AbsolutePathMapper apm = new AbsolutePathMapper(request);

        String path = apm.getAbsolutePath();
        assertEquals("/tmp/foo", path);
    }

    public void pathFileTest() throws Exception {
        Request request = new Request(getReader("GET /foo HTTP/1.1"), "/tmp");
        AbsolutePathMapper apm = new AbsolutePathMapper(request);

       File file = apm.getAbsolutePathFile();
        assertEquals("/tmp/foo", file.toString());
    }

}
