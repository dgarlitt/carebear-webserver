package com.carebears;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class DirectoryReader {
    private Request request;
    private AbsolutePathMapper absolutePathMapper;

    public DirectoryReader(Request request) {
        this.request = request;
        absolutePathMapper = new AbsolutePathMapper(request);
    }

    public String getFormattedListing() {
        ArrayList<DirectoryObject> dirListing = getDirectoryListing();
        StringBuffer content = new StringBuffer("<div>\n");

        for (DirectoryObject dirObj : dirListing) {
            if (dirObj.isDirectory()) {
                content.append("&lt;");
            }
            content.append("<a href=\"");
            content.append(dirObj.getUrlPath());
            content.append("\">");
            content.append(dirObj.getEntryName());
            content.append("</a>");

            if (dirObj.isDirectory()) {
                content.append("&gt;");
            }

            content.append("<br />\n");
        }
        content.append("</div>\n");
        return(content.toString());
    }

    public ArrayList<DirectoryObject> getDirectoryListing() {
        ArrayList<DirectoryObject> retList = new ArrayList<DirectoryObject>();
        ArrayList<String> dirs = new ArrayList<String>();
        ArrayList<String> files = new ArrayList<String>();
        File file = absolutePathMapper.getAbsolutePathFile();

        if (file.isDirectory()) {
            String[] fileList = file.list();

            for (int xctr = 0; xctr < fileList.length; xctr++) {
                if (fileList[xctr].substring(0, 1).equals(".")) {
                    continue;
                }

                File entryFile = new File(file, fileList[xctr]);
                if (entryFile.isDirectory()) {
                    dirs.add(fileList[xctr]);
                }
                else {
                    files.add(fileList[xctr]);
                }
            }

            if (dirs.size() > 0) {
                Collections.sort(dirs);
            }

            for (String string : dirs) {
                String urlPath = request.getPath();
                StringBuffer sb = new StringBuffer(urlPath);

                if (!urlPath.endsWith("/")) {
                    sb.append("/");
                }
                sb.append(string);
                retList.add(new DirectoryObject(string, new File(file, string), sb.toString(), true));
            }

            if (files.size() > 0) {
                Collections.sort(files);
            }

            for (String string : files) {
                String urlPath = request.getPath();
                StringBuffer sb = new StringBuffer(urlPath);

                if (!urlPath.endsWith("/")) {
                    sb.append("/");
                }
                sb.append(string);
                retList.add(new DirectoryObject(string, new File(file, string), sb.toString(), false));
            }
        }
        return(retList);
    }
}
