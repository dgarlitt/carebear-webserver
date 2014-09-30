package testoutput.fakes;

import com.carebears.CareBearHttpHandler;
import com.carebears.Request;
import com.carebears.Response;
import com.carebears.ResponseOutputWriter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FakeHttpHandler extends CareBearHttpHandler {
    private String request;

    public void handle(InputStream inputStream, OutputStream outputStream) {
        Request request = new Request(inputStream);
        Response response = new Response(outputStream);
        ResponseOutputWriter writer = response.getResponseOutputWriter();

        try {
            writer.write(request.getFirstRequestLine());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
