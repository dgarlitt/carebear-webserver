package com.carebears.servlets;

import com.carebears.*;
import de.neuland.jade4j.Jade4J;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class GameFormServlet extends CareBearServlet {
    private Session session;

    public GameFormServlet() {
        session = Server.CONFIG.getSession();
    }

    @Override
    public String getPath() {
        return "/puttputt";
    }

    public int getTotalScores() {
        int totalScore = 0;

        List<String> sortedList = session.getSortList();
        for (int i = 0; i < sortedList.size(); i++) {
            totalScore += Integer.parseInt(sortedList.get(i));

        }
        return totalScore;
    }

    public void doGet(Request req, Response res) {
        HashMap<String, Object> model = new HashMap<>();
        model.put("scores", session.getSortList());

        if(model.containsKey("total")) {
            model.replace("total", getTotalScores());
        } else {
            model.put("total", getTotalScores());
        }

        req.setPath("putt-putt.jade");
        AbsolutePathMapper apm = new AbsolutePathMapper(req);

        try {
            String html = Jade4J.render(apm.getAbsolutePath(), model);
            res.setBody(html);
        } catch (IOException e1) {

        } finally {
            res.setStatusCode(200);
            res.send();
        }
    }

    @Override
    public void doPost(Request req, Response res) {
        session.setSortedList(req.getParam("score"));
        HashMap<String, Object> model = new HashMap<>();
        model.put("scores", session.getSortList());

        if(model.containsKey("total")) {
            model.replace("total", getTotalScores());
        } else {
            model.put("total", getTotalScores());
        }

        req.setPath("putt-putt.jade");
        AbsolutePathMapper apm = new AbsolutePathMapper(req);

        try {
            String html = Jade4J.render(apm.getAbsolutePath(), model);
            res.setBody(html);
        } catch (IOException e1) {

        } finally {
            res.setStatusCode(200);
            res.send();
        }
    }

}
