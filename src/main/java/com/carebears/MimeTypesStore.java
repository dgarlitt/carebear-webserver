package com.carebears;

import javax.activation.MimetypesFileTypeMap;
import java.util.HashMap;

public class MimeTypesStore {
    private static MimeTypesStore ourInstance;
    private MimetypesFileTypeMap mimeTypeMap;
    private HashMap<String, Boolean> binaryTypeMap = new HashMap<String, Boolean>();

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

    public boolean isBinaryType(String mimeType) {
        Boolean flag = binaryTypeMap.get(mimeType);
        if (flag != null) {
            return(flag.booleanValue());
        }
        return(true);
    }

    public void addMimeType(String mimeTypeString, boolean isBinary) {
        mimeTypeMap.addMimeTypes(mimeTypeString);
        String[] parts = mimeTypeString.split(" ");
        binaryTypeMap.put(parts[0], new Boolean(isBinary));
    }
}
