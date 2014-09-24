import com.carebears.Response;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.PrintWriter;

public class ResponseTest {

    @Test
    public void ItGetsAWriter() throws Exception {
        PrintWriter writer = new PrintWriter(new String("src/test/java/testoutput/blah"));
        Response response = new Response(writer);

        assertEquals(writer, response.getWriter());
    }

}
