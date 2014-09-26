import com.carebears.AbsolutePathMapper;
import com.carebears.Request;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class AbsolutePathMapperTest {
    @Test
    public void pathTest() throws Exception {
        Request request = new Request("GET /foo HTTP/1.1", "/tmp");
        AbsolutePathMapper apm = new AbsolutePathMapper(request);

        String path = apm.getAbsolutePath();
        assertEquals("/tmp/foo", path);
    }

    public void pathFileTest() throws Exception {
        Request request = new Request("GET /foo HTTP/1.1", "/tmp");
        AbsolutePathMapper apm = new AbsolutePathMapper(request);

       File file = apm.getAbsolutePathFile();
        assertEquals("/tmp/foo", file.toString());
    }

}
