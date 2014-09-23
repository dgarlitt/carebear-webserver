package com.carebears;

import java.io.FileNotFoundException;

public class DocumentRetriever {

    public String getDocument(String docRoot, String path) throws FileNotFoundException {
        if (!path.equals("foo")) {
            throw (new FileNotFoundException("Not found"));
        }
        return("");
    }
}
