package com.carebears;

public class DirectoryReader {
    private Request request;

    public DirectoryReader(Request request) {
        this.request = request;
    }

    public String getFormattedListing() {
        return "<div>\n<a href=\"/\">..</a>\n</div>";
    }
}
