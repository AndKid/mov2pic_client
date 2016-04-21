package com.andkid.mov2pic.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuguan.chen on 4/15/16.
 */
public class MovieList implements Model, Parcelable{
    public String category;
    public String[] movie_des;
    public String[] movie_img;
    public String[] movie_title;
    public String[] movie_url;
    public Map<String, String> nav;

    protected MovieList(Parcel in) {
        category = in.readString();
        movie_des = in.createStringArray();
        movie_img = in.createStringArray();
        movie_title = in.createStringArray();
        movie_url = in.createStringArray();
        nav = in.readHashMap(Map.class.getClassLoader());
    }

    public static final Creator<MovieList> CREATOR = new Creator<MovieList>() {
        @Override
        public MovieList createFromParcel(Parcel in) {
            return new MovieList(in);
        }

        @Override
        public MovieList[] newArray(int size) {
            return new MovieList[size];
        }
    };

    @Override
    public int getCount() {
        if(movie_title != null)
            return movie_title.length;
        return 0;
    }

    public MovieList trim() {
        if(category != null)
            category = category.trim();
        for(int i = 0; movie_des != null && i < movie_des.length; i++) {
            movie_des[i] = movie_des[i].trim();
        }
        for(int i = 0; movie_title != null && i < movie_title.length; i++) {
            movie_title[i] = movie_title[i].trim();
        }
        return this;
    }

    public void removeOriginalTag() {
        nav.remove(nav.keySet().toArray()[0]);//remove '首页'
        nav.remove(nav.keySet().toArray()[0]);//remove '原创'
    }

    @Override
    public String toString() {
        return "key: " + nav.keySet().toString() + "entry: " + nav.entrySet().toString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(category);
        dest.writeStringArray(movie_des);
        dest.writeStringArray(movie_img);
        dest.writeStringArray(movie_title);
        dest.writeStringArray(movie_url);
        dest.writeMap(nav);
    }

}
