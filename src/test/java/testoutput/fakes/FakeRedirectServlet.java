package testoutput.fakes;

import com.carebears.CareBearServlet;
import com.carebears.Request;
import com.carebears.Response;
import com.carebears.ResponseOutputWriter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class FakeRedirectServlet extends CareBearServlet{
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
        StringBuffer sb = new StringBuffer();
        sb.append("HTTP/1.1 302\n");
        sb.append("Location: redirect here\n");

        try {
            writer.write(sb.toString());
            writer.flush();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
