package com.carebears;

import java.io.*;

public class DocumentRetriever {

    public String getDocument(Request req) throws FileNotFoundException {
        String docRoot = req.getDocRoot();
        String relPath = req.getPath();

        if (relPath.charAt(0) == '/') {;
            relPath = relPath.substring(1);
        }

        String fullPath = docRoot + File.separator + relPath;
        File file = new File(fullPath);

        if (!file.exists()) {
            throw new FileNotFoundException("File " + fullPath + " does not exist");
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
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }

        return(sb.toString());
    }
}
