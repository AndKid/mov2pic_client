package com.andkid.mov2pic.model;

/**
 * Created by yuguan.chen on 4/18/16.
 */
public class HeaderBackground {
    public String[] background;
    public String[] name;

    public HeaderBackground trim() {
        for(int i = 0; name != null && i < name.length; i++) {
            name[i] = name[i].trim();
        }
        return this;
    }
}
