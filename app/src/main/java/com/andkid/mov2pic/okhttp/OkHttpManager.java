package com.andkid.mov2pic.okhttp;

import com.andkid.mov2pic.fragment.FragmentBase;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yuguan.chen on 4/15/16.
 */
public class OkHttpManager {
    public static final String DOMAIN = "http://www.k165.com/";
    private final OkHttpClient client = new OkHttpClient();
    private final FragmentBase mFragmentBase;

    public OkHttpManager(FragmentBase fragmentBase) {
        mFragmentBase = fragmentBase;
    }

    public <T> void run(String url, final Class<T> json) throws Exception {

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                Gson gson = new Gson();
                T jsonObject = gson.fromJson(response.body().charStream(), (Type) json);
                mFragmentBase.refreshContent(jsonObject);
            }
        });
    }
}
