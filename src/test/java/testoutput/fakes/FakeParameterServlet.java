package testoutput.fakes;

import com.carebears.CareBearServlet;
import com.carebears.Request;
import com.carebears.Response;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class FakeParameterServlet extends CareBearServlet {
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
        HashMap<String, String> paramMap;
        try {
            paramMap = req.getParameters();
        } catch(Exception e) {
            paramMap = new HashMap<>();
        }
        PrintWriter writer = res.getWriter();
        writer.println("HTTP/1.1 200");

        for (Map.Entry<String, String>param: paramMap.entrySet()) {
            writer.println(param.getKey() + " = " + param.getValue());

        }

        writer.flush();
    }
}
