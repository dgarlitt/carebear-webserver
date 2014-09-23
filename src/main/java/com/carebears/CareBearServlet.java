package com.carebears;

public abstract class CareBearServlet {

    private String path;

    public String getPath() {
        return path;
    }

    public void doPost(Request req, Response res) {
        res.send("HTTP/1.0 404");
    }

    public void doGet(Request req, Response res) {
        res.send("HTTP/1.0 404");
    }

    public void doPut(Request req, Response res) {
        res.send("HTTP/1.0 404");
    }

    public void doDelete(Request req, Response res) {
        res.send("HTTP/1.0 404");
    }

}
