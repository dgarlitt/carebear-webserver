package com.carebears.servlets;

import com.carebears.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PatchWithETagServlet extends CareBearServlet {

    public String getPath() {
       return  "/patch-content.txt";
    }

    public void doGet(Request req, Response res) {
        DocumentRetriever documentRetriever = new DocumentRetriever();
        try {
            documentRetriever.getDocument(req, res);

        } catch (FileNotFoundException ex) {
            res.setStatusCode(404);
            res.send();
        }
    }

    public void doPatch(Request req, Response res) {
        AbsolutePathMapper apm = new AbsolutePathMapper(req);
        File file = apm.getAbsolutePathFile();
        DocumentWriter docWriter = new DocumentWriter(file);
        DocumentRetriever docRetriever = new DocumentRetriever();

        try {
            docWriter.replaceFileContent(req.getBody());
            docRetriever.getDocument(req, res, 204);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
