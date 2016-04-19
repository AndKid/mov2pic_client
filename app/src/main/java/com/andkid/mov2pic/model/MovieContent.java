package com.andkid.mov2pic.model;

/**
 * Created by yuguan.chen on 4/18/16.
 */
public class MovieContent{
    public String[] summary;
    public String[] content;
    public String title;

    public MovieContent trim() {

        if(title != null)
            title = title.trim();
        for(int i = 0; summary != null && i < summary.length; i++) {
            summary[i] = summary[i].trim();
        }
        for(int i = 0; content != null && i < content.length; i++) {
            content[i] = content[i].trim();
        }
        return this;
    }

    public String getSummary() {
        StringBuilder summary = new StringBuilder();
        if(this.summary != null) {
            for(String s : this.summary) {
                summary.append(s.trim());
            }
        }
        return summary.toString();
    }

    public boolean isImageContent(String string) {
        return string.contains(".jpg");
    }
}
