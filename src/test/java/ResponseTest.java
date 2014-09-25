import com.carebears.Response;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ResponseTest {

    @Test
    public void ItGetsAWriter() throws Exception {
        PrintWriter writer = new PrintWriter(new StringWriter());
        Response response = new Response(writer);

        assertEquals(writer, response.getWriter());
    }

}
