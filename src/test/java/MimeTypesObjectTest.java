import com.carebears.MimeTypesStore;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MimeTypesObjectTest {
    private MimeTypesStore mimeTypesStore;

    @Before
    public void setup() {
        mimeTypesStore = MimeTypesStore.getInstance();
        mimeTypesStore.addMimeType("image/jpg jpg JPEG JPG jpeg");
        mimeTypesStore.addMimeType("text/html htm html");
        mimeTypesStore.addMimeType("text/plain txt TXT");
        mimeTypesStore.addMimeType("text/javascript js");
        mimeTypesStore.addMimeType("image/png png");
        mimeTypesStore.addMimeType("image/gif gif");

    }

    @Test
    public void itGetsProperContentTypeForUnknownType() throws Exception {
        String contentType = mimeTypesStore.getContentType("test.bin");
        assertEquals("application/octet-stream", contentType);
    }

    @Test
    public void itGetsProperContentTypeForJPG() throws Exception {
        String contentType = mimeTypesStore.getContentType("test.jpeg");
        assertEquals("image/jpg", contentType);
    }

    @Test
    public void itGetsProperContentTypeForPNG() throws Exception {
        String contentType = mimeTypesStore.getContentType("test.png");
        assertEquals("image/png", contentType);
    }
}
