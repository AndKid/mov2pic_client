package com.andkid.mov2pic.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yuguan.chen on 4/18/16.
 */
public class HeaderBackground implements Parcelable{
    public String[] background;
    public String[] name;

    protected HeaderBackground(Parcel in) {
        background = in.createStringArray();
        name = in.createStringArray();
    }

    public static final Creator<HeaderBackground> CREATOR = new Creator<HeaderBackground>() {
        @Override
        public HeaderBackground createFromParcel(Parcel in) {
            return new HeaderBackground(in);
        }

        @Override
        public HeaderBackground[] newArray(int size) {
            return new HeaderBackground[size];
        }
    };

    public HeaderBackground trim() {
        for(int i = 0; name != null && i < name.length; i++) {
            name[i] = name[i].trim();
        }
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(background);
        dest.writeStringArray(name);
    }
}
