package com.carebears;

import com.carebears.Request;
import com.carebears.Response;

import java.io.PrintWriter;

public abstract class CareBearServlet {

    public abstract String getPath();

    public void doPost(Request req, Response res) {
        res.setStatusCode(405);
        res.send();
    }

    public void doGet(Request req, Response res) {
        res.setStatusCode(405);
        res.send();
    }

    public void doPut(Request req, Response res) {
        res.setStatusCode(405);
        res.send();
    }

    public void doDelete(Request req, Response res) {
        res.setStatusCode(405);
        res.send();
    }

//    public void listDirectory(Request req, Response res) {
//        DirectoryReader reader = new DirectoryReader(req.getDocumentRoot());
//        res.write(reader.formatDirectoryListing());
//        res.flush();
//    }

}
