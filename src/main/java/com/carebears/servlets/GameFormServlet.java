package com.carebears.servlets;

import com.carebears.*;
import de.neuland.jade4j.Jade4J;
import org.apache.commons.collections.ArrayStack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameFormServlet extends CareBearServlet {
    private Session session;

    public GameFormServlet() {
        session = Server.CONFIG.getSession();
    }

    @Override
    public String getPath() {
        return "/puttputt";
    }

    public void deleteScores(Request req, Response res) {
        req.setPath("putt-putt-score.txt");
        AbsolutePathMapper apm = new AbsolutePathMapper(req);
        File file = apm.getAbsolutePathFile();
        DocumentWriter docWriter = new DocumentWriter(file);

        try {
            docWriter.replaceFileContent("");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getScores() {

        HashMap<String, Object> scores = session.getHashMap();
        List<String> sortedList = session.getSortList();
        int totalScore = 0;

        String scoreString = "";
        for (int i = 0; i < sortedList.size(); i++) {
             scoreString += sortedList.get(i) + ": " + scores.get(sortedList.get(i)) + "<br />";
            totalScore += Integer.parseInt((String)scores.get(sortedList.get(i)));
        }

         scoreString += "Total Score: " + totalScore + "<br />";

        return scoreString;
    }

    public void doGet(Request req, Response res) {
        HashMap<String, Object> model = new HashMap<>();
        model.put("scores", session.getSortList());
        String scores = getScores();

        req.setPath("putt-putt.jade");
        AbsolutePathMapper apm = new AbsolutePathMapper(req);
        File file = apm.getAbsolutePathFile();
        DocumentWriter docWriter = new DocumentWriter(file);

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
        HashMap<String, Object> model = new HashMap<>();
        model.put("scores", session.getSortList());

//        List<String> sortedList = session.getSortList();
//        String atHole = "Hole " + (sortedList.size() + 1);
//        session.setSortedList(atHole);
//        session.setHashMap(atHole, req.getParam("score"));


//        String scores = getScores();
        req.setPath("putt-putt.jade");
        AbsolutePathMapper apm = new AbsolutePathMapper(req);
        File file = apm.getAbsolutePathFile();
        DocumentWriter docWriter = new DocumentWriter(file);


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
