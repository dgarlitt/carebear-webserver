package com.carebears;

import java.io.FileNotFoundException;

public class DocumentRetriever {

    public String getDocument(Request req) throws FileNotFoundException {
        if (!req.getPath().equals("foo")) {
            throw (new FileNotFoundException("Not found"));
        }
        return("");
    }
}
