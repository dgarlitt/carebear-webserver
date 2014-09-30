package com.carebears;

import java.io.*;

public class DocumentRetriever {

    public void getDocument(Request req, Response resp) throws FileNotFoundException {
        AbsolutePathMapper absolutePathMapper = new AbsolutePathMapper(req);
        File file = absolutePathMapper.getAbsolutePathFile();

        if (!file.exists()) {
            throw new FileNotFoundException(file.toString() + " does not exist");
        }

        StringBuffer sb = new StringBuffer();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            int charRead = br.read();
            while(charRead != -1) {
                sb.append((char)charRead);
                charRead = br.read();
            }
            br.close();

            resp.setStatusCode(200);
            resp.setBody(sb.toString());
            resp.setHeader("Content-length", "" + resp.getBodySize());
            resp.send();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }

        return;
    }
}
