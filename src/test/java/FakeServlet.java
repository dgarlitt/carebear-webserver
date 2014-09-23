import com.carebears.servlets.CareBearServlet;

public class FakeServlet extends CareBearServlet{
    private String path;
    private String response;

    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }


}
