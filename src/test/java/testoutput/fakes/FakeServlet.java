package testoutput.fakes;

import com.carebears.CareBearServlet;
import com.carebears.Request;
import com.carebears.Response;

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
        PrintWriter writer = res.getWriter();
        writer.println("HTTP/1.0 200");
        writer.flush();
    }




}
