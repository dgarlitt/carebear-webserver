package testoutput.fakes;

import com.carebears.CareBearHttpHandler;

import java.io.PrintWriter;

public class FakeHttpHandler extends CareBearHttpHandler {
    private String request;

    public void handle(String request, PrintWriter writer) {
        writer.println(request);
        writer.flush();
    }

}
