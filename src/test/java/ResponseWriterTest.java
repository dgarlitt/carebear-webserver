import com.carebears.ResponseOutputWriter;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;

public class ResponseWriterTest {
    private ByteArrayOutputStream byteOutputStream;
    private ResponseOutputWriter responseOutputWriter;

    @Before
    public void setup() {
        byteOutputStream = new ByteArrayOutputStream();
        responseOutputWriter = new ResponseOutputWriter(byteOutputStream);
    }

    @Test
    public void itCanWriteAString() throws Exception {
        responseOutputWriter.write("This is a test string");
        responseOutputWriter.flush();
        String output = byteOutputStream.toString();
        assertEquals("This is a test string", output);
    }

    @Test
    public void itCanWriteALine() throws Exception {
        responseOutputWriter.writeln("This is a test string");
        responseOutputWriter.flush();
        String output = byteOutputStream.toString();
        assertEquals("This is a test string\n", output);
    }

    @Test
    public void itCanWriteBytes() throws Exception {
        byte[] bytes = ("This is a test string".getBytes());
        responseOutputWriter.writeBytes(bytes);
        responseOutputWriter.flush();
        String output = byteOutputStream.toString();
        assertEquals("This is a test string", output);

    }
}
