package com.carebears;

import java.io.File;

public class AbsolutePathMapper {
    private Request request;

    public AbsolutePathMapper(Request request) {
        this.request = request;
    }

    public String getAbsolutePath() {
        return(getFullPath());
    }

    public File getAbsolutePathFile() {
        return(new File(getFullPath()));
    }

    protected String getFullPath() {
        String docRoot = request.getDocRoot();
        String relPath = request.getPath();

        if (relPath.charAt(0) == '/') {
            relPath = relPath.substring(1);
        }

        String fullPath = docRoot + File.separator + relPath;
        return(fullPath);
    }
}
