package com.carebears;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocumentRetriever {
    static private final int MAX_BUFF_SIZE = 4096;

    public void getDocument(Request req, Response resp) throws FileNotFoundException {
        AbsolutePathMapper absolutePathMapper = new AbsolutePathMapper(req);
        File file = absolutePathMapper.getAbsolutePathFile();
        boolean isPartial = false;
        int partialStart = 0;
        int partialEnd = 0;

        if (!file.exists()) {
            throw new FileNotFoundException(file.toString() + " does not exist");
        }

        String filename = file.getName();
        String mimeType = MimeTypesStore.getInstance().getContentType(filename);

        String partialHeader = req.getHeader("Range");
        if (partialHeader != null) {
            Pattern pat = Pattern.compile("bytes=(\\d+)-(\\d+)");
            Matcher mat = pat.matcher(partialHeader);
            if (mat.find()) {
                try {
                    partialStart = Integer.parseInt(mat.group(1));
                    partialEnd = Integer.parseInt(mat.group(2));
                    isPartial = true;
                }
                catch(NumberFormatException ex) {
                    ex.printStackTrace();
                }
            }
        }

        if (true) {
            byte[] buf = new byte[MAX_BUFF_SIZE];
            int maxBytesToRead = MAX_BUFF_SIZE;
            ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();

            try {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));

                if (isPartial) {
                    maxBytesToRead = (partialEnd - partialStart) + 1;
                    if (partialStart > 0) {
                        bufferedInputStream.skip(partialStart);
                    }
                }

                int bytesRead = bufferedInputStream.read(buf, 0, maxBytesToRead);

                while (bytesRead != -1) {
                    byteOutputStream.write(buf, 0, bytesRead);

                    if (isPartial) {
                        if (bytesRead == maxBytesToRead) {
                            break;
                        }
                        else {
                            maxBytesToRead = maxBytesToRead - bytesRead;
                        }
                    }
                    bytesRead = bufferedInputStream.read(buf, 0, maxBytesToRead);
                }
                byteOutputStream.flush();

                buf = byteOutputStream.toByteArray();
                bufferedInputStream.close();
                resp.setStatusCode(isPartial? 206 : 200);
                resp.setHeader("Content-Type", mimeType);
                resp.setHeader("Content-Length", "" + buf.length);
                if (isPartial) {
                    resp.setHeader("Content-Range", String.format("bytes %d-%d", partialStart, partialEnd));
                }
                resp.setBody(buf);
            }
            catch(IOException ex) {
                resp.setStatusCode(500);
            }
        }
        resp.send();
    }
}
