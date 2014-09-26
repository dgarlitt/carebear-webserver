package com.carebears;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class CareBearServlet {
    private Map<String, String> parametersMap;

    public abstract String getPath();

    public Map<String, String> getParameters(Request req) throws UnsupportedEncodingException {
        parametersMap = new LinkedHashMap<>();
        String[] paramArray = req.getUrlParameters().split("&");

        for(String pair: paramArray) {
            int paramIndex = pair.indexOf("=");
            parametersMap.put(URLDecoder.decode(pair.substring(0, paramIndex), "UTF-8"), URLDecoder.decode(pair.substring(paramIndex + 1), "UTF-8"));

        }

        return parametersMap;
    }

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
