package com.carebears;

import javax.activation.MimetypesFileTypeMap;

public class MimeTypesStore {
    private static MimeTypesStore ourInstance;

    private MimetypesFileTypeMap mimeTypeMap;

    private MimeTypesStore() {
        mimeTypeMap = new MimetypesFileTypeMap();
    }

    public static MimeTypesStore getInstance() {
        if (ourInstance == null) {
            ourInstance = new MimeTypesStore();
        }
        return(ourInstance);
    }

    public String getContentType(String filename) {
        String retType = mimeTypeMap.getContentType(filename);
        return(retType);
    }

    public void addMimeType(String mimeTypeString) {
        mimeTypeMap.addMimeTypes(mimeTypeString);
    }
}
