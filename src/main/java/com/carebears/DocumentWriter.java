package com.carebears;

import java.io.*;

public class DocumentWriter {

    private final File file;

    public DocumentWriter(File file) {
        this.file = file;
    }

    public String readFileToString() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        String fileContent = "";

        while ((line = reader.readLine()) != null) {
            fileContent += line + "\n";
        }

        reader.close();

        return fileContent;
    }

    public void findAndReplaceFileContent(String target, String replacement) throws IOException {
        String fileContent = readFileToString();
        String newFileContent = fileContent.replace(target, replacement);

        replaceFileContent(newFileContent);
    }

    public void replaceFileContent(String replacement) throws IOException {
        FileOutputStream out = new FileOutputStream(file, false);
        out.write(replacement.getBytes());
        out.close();
    }

    public void appendToFile(String output) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
        out.println(output);
        out.close();
    }

}
