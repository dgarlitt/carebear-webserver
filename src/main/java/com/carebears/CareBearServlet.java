package com.carebears;

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

    public void listDirectory(Request req, Response res) {
        DirectoryReader reader = new DirectoryReader(req);
        String listing = reader.getFormattedListing();

        StringBuffer htmlOut = new StringBuffer();
        htmlOut.append("<html>\n<head>\n<title>Directory of ");
        htmlOut.append(req.getPath());
        htmlOut.append("</title>\n</head>\n<body>\n<h2>Directory of ");
        htmlOut.append(req.getPath());
        htmlOut.append("</h2>\n");
        htmlOut.append(listing);
        htmlOut.append("</html>\n");

        res.setBody(htmlOut.toString());
        res.setStatusCode(200);
        res.send();
    }

}
