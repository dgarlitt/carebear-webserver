package com.carebears;

import java.io.File;

public class DirectoryObject {
    private File file;
    private String entryName;
    private String urlPath;
    private boolean directory;

    public DirectoryObject(String name) {
        entryName = name;
    }

    public DirectoryObject(String name, File file) {
        this(name);
        this.file = file;
    }

    public DirectoryObject(String name, File file, String urlPath) {
        this(name, file);
        this.urlPath = urlPath;
    }

    public DirectoryObject(String name, File file, String urlPath, boolean isDir) {
        this(name, file, urlPath);
        this.directory = isDir;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getEntryName() {
        return entryName;
    }

    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    public boolean isDirectory() {
        return directory;
    }

    public void setDirectory(boolean directory) {
        this.directory = directory;
    }
}
