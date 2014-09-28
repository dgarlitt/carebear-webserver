package com.carebears;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class InternetHttpHandler extends CareBearHttpHandler {
    private String documentRoot;
    private ArrayList<CareBearServlet> servlets = new ArrayList<CareBearServlet>();

    public void handle(String request, PrintWriter writer) {
        Request reqObj = new Request(request);
        Response resObj = new Response(writer);

        reqObj.setDocRoot(Server.CONFIG.getDocumentRoot());

        CareBearServlet servlet = getServletByPath(reqObj.getPath());

        if (servlet != null) {
            switch(reqObj.getMethod()) {
                case "GET":
                    servlet.doGet(reqObj, resObj);
                    break;
                case "POST":
                    servlet.doPost(reqObj, resObj);
                    break;
                case "PUT":
                    servlet.doPut(reqObj, resObj);
                    break;
                case "DELETE":
                    servlet.doDelete(reqObj, resObj);
                    break;
                case "OPTIONS":
                    servlet.doOptions(reqObj, resObj);
                    break;
                case "HEAD":
                    servlet.doHead(reqObj, resObj);
                    break;
            }
        }
        else {
            if (reqObj.getMethod().equals("GET")) {
                DocumentRetriever documentRetriever = new DocumentRetriever();
                try {
                    String documentContent = documentRetriever.getDocument(reqObj);
                    writer.write(documentContent);
                    writer.flush();
                } catch (FileNotFoundException ex) {
                    resObj.setStatusCode(404);
                    resObj.send();
                }

            } else {
                resObj.setStatusCode(405);
                resObj.send();
            }
        }
    }

    public void registerServlet(CareBearServlet servlet) {
        servlets.add(servlet);
    }

    public CareBearServlet getServletByPath(String path) {
        for(CareBearServlet serve: servlets) {
            if(serve.getPath().equals(path)) {
                return serve;
            }
        }

        return null;
    }

}
