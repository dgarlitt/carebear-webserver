package com.carebears;

import java.io.*;
import java.nio.*;
import java.util.ArrayList;

public class DocumentRetriever {

    public void getDocument(Request req, Response resp) throws FileNotFoundException {
        AbsolutePathMapper absolutePathMapper = new AbsolutePathMapper(req);
        File file = absolutePathMapper.getAbsolutePathFile();

        if (!file.exists()) {
            throw new FileNotFoundException(file.toString() + " does not exist");
        }

        String filename = file.getName();
        String mimeType = MimeTypesStore.getInstance().getContentType(filename);
        boolean isBinary = MimeTypesStore.getInstance().isBinaryType(mimeType);

        if (isBinary) {
            byte[] buf = new byte[4096];
            ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
            try {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                int bytesRead = bufferedInputStream.read(buf, 0, 4096);

                while (bytesRead != -1) {
                    byteOutputStream.write(buf, 0, bytesRead);
                    bytesRead = bufferedInputStream.read(buf, 0, 4096);
                }
                byteOutputStream.flush();

                buf = byteOutputStream.toByteArray();
                bufferedInputStream.close();
                resp.setStatusCode(200);
                resp.setHeader("Content-Type", mimeType);
                resp.setHeader("Content-Length", "" + buf.length);
                resp.setBody(buf);
            }
            catch(IOException ex) {
                resp.setStatusCode(500);
            }
        }
        else {
            StringBuffer sb = new StringBuffer();

            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                int charRead = br.read();
                while (charRead != -1) {
                    sb.append((char) charRead);
                    charRead = br.read();
                }
                br.close();

                resp.setStatusCode(200);
                resp.setBody(sb.toString());
                resp.setHeader("Content-Type", mimeType);
                resp.setHeader("Content-Length", "" + resp.getBodySize());
            } catch (IOException ex) {
                resp.setStatusCode(500);
            }
        }
        resp.send();

        return;
    }
}
