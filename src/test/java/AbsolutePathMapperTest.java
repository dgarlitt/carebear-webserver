import com.carebears.AbsolutePathMapper;
import com.carebears.Request;
import org.junit.Test;
import testoutput.fakes.FakeInputStream;
import java.io.File;

import static org.junit.Assert.assertEquals;

public class AbsolutePathMapperTest {

    @Test
    public void pathTest() throws Exception {
        Request request = new Request(new FakeInputStream("GET /foo HTTP/1.1"), "/tmp");
        AbsolutePathMapper apm = new AbsolutePathMapper(request);

        String path = apm.getAbsolutePath();
        assertEquals("/tmp/foo", path);
    }

    @Test
    public void pathFileTest() throws Exception {
        Request request = new Request(new FakeInputStream("GET /foo HTTP/1.1"), "/tmp");
        AbsolutePathMapper apm = new AbsolutePathMapper(request);

        File file = apm.getAbsolutePathFile();
        assertEquals("/tmp/foo", file.toString());
    }

}
