package com.andkid.mov2pic.callback;

import com.andkid.mov2pic.model.MovieContent;
import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by yuguan.chen on 4/16/16.
 */
public abstract class MovieContentCallback extends Callback<MovieContent> {
    @Override
    public MovieContent parseNetworkResponse(Response response) throws Exception {
        String string = response.body().string();
        MovieContent movieContent = new Gson().fromJson(string, MovieContent.class);
        return movieContent;
    }
}
