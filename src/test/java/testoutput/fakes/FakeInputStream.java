package testoutput.fakes;

import java.io.ByteArrayInputStream;

public class FakeInputStream extends ByteArrayInputStream {

    public FakeInputStream(String strInit) {
        super(strInit.getBytes());
    }

}
