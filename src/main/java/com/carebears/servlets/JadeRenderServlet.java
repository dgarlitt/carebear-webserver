package com.carebears.servlets;

import com.carebears.*;
import com.carebears.models.Book;
import de.neuland.jade4j.Jade4J;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JadeRenderServlet extends CareBearServlet {

    public String getPath() {
        return "/hello.jade";
    }

    @Override
    public void doGet(Request req, Response res) {
        res.setStatusCode(200);
        AbsolutePathMapper mapper = new AbsolutePathMapper(req);
        List<Book> books = new ArrayList<Book>();
        Map<String, Object> model = new HashMap<>();

        books.add(new Book("The Hitchhiker's Guide to the Galaxy", 5.70, true));
        books.add(new Book("Life, the Universe and Everything", 5.60, false));
        books.add(new Book("The Restaurant at the End of the Universe", 5.40, true));
        books.add(new Book("Care Bears Rock!", 10.0, true));

        model.put("books", books);
        model.put("pageName", "My Bookshelf");

        try {
            String html = Jade4J.render(mapper.getAbsolutePath(), model);
            res.setBody(html);
            res.send();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
