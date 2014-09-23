package com.carebears;

import java.util.PrimitiveIterator;

public class CareBearHttpHandler {
    public String handle(String request) {

        if (request.equals("GET /")) {
            return("HTTP/1.0 200");
        }
        else if (request.equals("GET /foobar")) {
            return("HTTP/1.0 404");
        }
        else if (request.equals("POST /")) {
            return("HTTP/1.0 404");
        }

        return null;
    }


}
