package com.carebears.servlets;

import com.carebears.*;

import java.io.File;
import java.io.FileNotFoundException;
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
        HashMap<String, String> scores = session.getHashMap();
        List<String> sortedList = session.getSortList();
        int totalScore = 0;

        String scoreString = "";
        for (int i = 0; i < sortedList.size(); i++) {
             scoreString += sortedList.get(i) + ": " + scores.get(sortedList.get(i)) + "<br />";
            totalScore += Integer.parseInt(scores.get(sortedList.get(i)));
        }

         scoreString += "Total Score: " + totalScore + "<br />";

        return scoreString;
    }

    public void doGet(Request req, Response res) {
        String scores = getScores();

        req.setPath("putt-putt.html");
        AbsolutePathMapper apm = new AbsolutePathMapper(req);
        File file = apm.getAbsolutePathFile();
        DocumentWriter docWriter = new DocumentWriter(file);

        try {
            res.setBody(docWriter.findAndReplaceToString("{score}", scores));
        } catch (IOException e1) {

        } finally {
            res.setStatusCode(200);

            res.send();
        }
    }

    @Override
    public void doPost(Request req, Response res) {
        List<String> sortedList = session.getSortList();
        String atHole = "Hole " + (sortedList.size() + 1);
        session.setSortedList(atHole);
        session.setHashMap(atHole, req.getParam("score"));


        String scores = getScores();
        req.setPath("putt-putt.html");
        AbsolutePathMapper apm = new AbsolutePathMapper(req);
        File file = apm.getAbsolutePathFile();
        DocumentWriter docWriter = new DocumentWriter(file);


        try {
            res.setBody(docWriter.findAndReplaceToString("{score}", scores));
        } catch (IOException e1) {

        } finally {
            res.setStatusCode(200);

            res.send();
        }
    }

}
