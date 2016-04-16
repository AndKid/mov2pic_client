package com.andkid.mov2pic.callback;

import com.andkid.mov2pic.model.MovieList;
import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by yuguan.chen on 4/16/16.
 */
public abstract class MovieListCallback extends Callback<MovieList> {
    @Override
    public MovieList parseNetworkResponse(Response response) throws Exception {
        String string = response.body().string();
        MovieList movieList = new Gson().fromJson(string, MovieList.class);
        return movieList;
    }
}
