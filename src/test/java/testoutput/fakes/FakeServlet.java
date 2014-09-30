package testoutput.fakes;

import com.carebears.CareBearServlet;
import com.carebears.Request;
import com.carebears.Response;
import com.carebears.ResponseOutputWriter;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class FakeServlet extends CareBearServlet{
    private String path;
    private String response;

    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public void doGet(Request req, Response res) {
        ResponseOutputWriter writer = res.getResponseOutputWriter();
        try {
            writer.writeln("HTTP/1.1 200");
            writer.flush();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
