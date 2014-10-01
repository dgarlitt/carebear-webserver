package com.carebears;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ResponseOutputWriter {
    private BufferedOutputStream bos;

    public ResponseOutputWriter(OutputStream out) {
        bos = new BufferedOutputStream(out);
    }

    public void write(String str) throws IOException {
        byte[] bytes = str.getBytes();
        bos.write(bytes);
    }

    public void writeln(String str) throws IOException {
        write(str + "\n");
    }

    public void writeBytes(byte[] bytes) throws IOException {
        bos.write(bytes);
    }

    public void flush() throws IOException {
        bos.flush();
    }
}
