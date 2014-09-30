package testoutput.fakes;

import com.carebears.CareBearServlet;
import com.carebears.Request;
import com.carebears.Response;
import com.carebears.ResponseOutputWriter;

import java.io.IOException;
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
            paramMap = req.getParametersMap();
        } catch(Exception e) {
            paramMap = new HashMap<>();
        }

        ResponseOutputWriter writer = res.getResponseOutputWriter();

        try {
            writer.writeln("HTTP/1.1 200");

            for (Map.Entry<String, String> param : paramMap.entrySet()) {
                writer.writeln(param.getKey() + " = " + param.getValue());

            }

            writer.flush();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
