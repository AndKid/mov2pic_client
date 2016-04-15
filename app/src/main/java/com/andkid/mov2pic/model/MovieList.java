package com.andkid.mov2pic.model;

/**
 * Created by yuguan.chen on 4/15/16.
 */
public class MovieList implements Model{
    public String category;
    public String[] movie_des;
    public String[] movie_img;
    public String[] movie_title;
    public String[] movie_url;

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
}
