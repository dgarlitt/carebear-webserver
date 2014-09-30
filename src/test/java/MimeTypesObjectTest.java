import com.carebears.MimeTypesStore;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MimeTypesObjectTest {
    private MimeTypesStore mimeTypesStore;

    @Before
    public void setup() {
        mimeTypesStore = MimeTypesStore.getInstance();
        mimeTypesStore.addMimeType("image/jpg jpg JPEG JPG jpeg", true);
        mimeTypesStore.addMimeType("text/html htm html", false);
        mimeTypesStore.addMimeType("text/plain txt TXT", false);
        mimeTypesStore.addMimeType("text/javascript js", false);
        mimeTypesStore.addMimeType("image/png png", true);
        mimeTypesStore.addMimeType("image/gif gif", true);

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

    @Test
    public void binaryTypeTest() throws Exception {
        assertTrue(mimeTypesStore.isBinaryType("image/jpg"));
        assertFalse(mimeTypesStore.isBinaryType("text/html"));
        assertTrue(mimeTypesStore.isBinaryType("application/octet-stream"));
    }
}
