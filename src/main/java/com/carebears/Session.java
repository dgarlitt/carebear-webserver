package com.carebears;

public class Session {

    private String data = null;

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public String getLogData() {
        StringBuffer sb = new StringBuffer();

        for (String str : logData) {
            sb.append(str);
            sb.append("\n");
        }
        return(sb.toString());
    }

    public void addLogData(String dataString) {
        logData.add(dataString);
    }

    public boolean hasLogData(String dataString) {
        return(logData.contains(dataString));
    }

    public void setAuthorized() {
        authorized = true;
    }

    public boolean isAuthorized() {
        return authorized;
    }
}
