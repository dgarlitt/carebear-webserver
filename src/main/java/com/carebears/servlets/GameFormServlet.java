package com.carebears.servlets;

import com.carebears.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GameFormServlet extends CareBearServlet {
    private Session session;

    public GameFormServlet() {
        session = Server.CONFIG.getSession();
    }

    @Override
    public String getPath() {
        return "/puttputt";
    }


    public void doGet(Request req, Response res) {
        String scoreText ="";

        req.setPath("putt-putt-score.txt");



        AbsolutePathMapper apm = new AbsolutePathMapper(req);
        File file = apm.getAbsolutePathFile();
        DocumentWriter docWriter = new DocumentWriter(file);
        res.setStatusCode(200);

        try {
            scoreText = docWriter.readFileToString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        req.setPath("putt-putt.html");
        AbsolutePathMapper apm1 = new AbsolutePathMapper(req);
        File file1 = apm.getAbsolutePathFile();
        DocumentWriter docWriter1 = new DocumentWriter(file1);


        try {
            res.setBody(docWriter1.findAndReplaceToString("{score}", scoreText));
        } catch (IOException e1) {

        } finally {
            res.send();
        }
    }

    @Override
    public void doPost(Request req, Response res) {
        String scoreText ="";

        req.setPath("putt-putt-score.txt");



        AbsolutePathMapper apm = new AbsolutePathMapper(req);
        File file = apm.getAbsolutePathFile();
        DocumentWriter docWriter = new DocumentWriter(file);
        res.setStatusCode(200);

        try {
            docWriter.appendToFile("Hole: " + req.getParam("score") + "<br />");
            scoreText = docWriter.readFileToString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        req.setPath("putt-putt.html");
        AbsolutePathMapper apm1 = new AbsolutePathMapper(req);
        File file1 = apm.getAbsolutePathFile();
        DocumentWriter docWriter1 = new DocumentWriter(file1);


        try {
            res.setBody(docWriter1.findAndReplaceToString("{score}", scoreText));
        } catch (IOException e1) {

        } finally {
            res.send();
        }
    }

}
