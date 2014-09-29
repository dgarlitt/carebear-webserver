package testoutput.fakes;

import com.carebears.CareBearHttpHandler;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class FakeHttpHandler extends CareBearHttpHandler {
    private String request;

    public void handle(BufferedReader request, PrintWriter writer) {
        writer.println(request);
        writer.flush();
    }

}
