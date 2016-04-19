package com.andkid.mov2pic.callback;

import com.andkid.mov2pic.model.HeaderBackground;
import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by yuguan.chen on 4/16/16.
 */
public abstract class HeaderBackgroundCallback extends Callback<HeaderBackground> {
    @Override
    public HeaderBackground parseNetworkResponse(Response response) throws Exception {
        String string = response.body().string();
        HeaderBackground headerBackground = new Gson().fromJson(string, HeaderBackground.class);
        return headerBackground;
    }
}
