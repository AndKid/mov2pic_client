package com.andkid.mov2pic;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.andkid.mov2pic.callback.HeaderBackgroundCallback;
import com.andkid.mov2pic.callback.MovieListCallback;
import com.andkid.mov2pic.model.HeaderBackground;
import com.andkid.mov2pic.model.MovieList;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.Call;
import okhttp3.OkHttpClient;

/**
 * Created by CHEN on 2016/4/21.
 */
public class WelcomeActivity extends AppCompatActivity {
    private Intent mIntent = new Intent();
    private boolean mOneOk;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        OkHttpUtils.get().tag("andkid")
                .url(WebSites.MOVIE_BACKGROUND_URL)
                .build()
                .execute(new HeaderBackgroundCallback() {

                    @Override
                    public void onError(Call call, Exception e) {
                        Toast.makeText(WelcomeActivity.this, "Access network failed.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(HeaderBackground response) {
                        mIntent.setClass(WelcomeActivity.this, MainActivity.class);
                        mIntent.putExtra("headerBackground", response.trim());
                        if(mOneOk) {
                            startActivity(mIntent);
                            finish();
                        } else {
                            mOneOk = true;
                        }
                    }
                });

        OkHttpUtils.get().tag("andkid")
                .url(WebSites.MOVIE_LIST_URL)
                .build()
                .execute(new MovieListCallback() {

                    @Override
                    public void onError(Call call, Exception e) {
                        Toast.makeText(WelcomeActivity.this, "Access network failed.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(MovieList response) {
                        response.removeOriginalTag();
                        mIntent.setClass(WelcomeActivity.this, MainActivity.class);
                        mIntent.putExtra("movieList", response.trim());
                        if(mOneOk) {
                            startActivity(mIntent);
                            finish();
                        } else {
                            mOneOk = true;
                        }
                    }
                });

    }

    @Override
    protected void onStop() {
        super.onStop();
        OkHttpUtils.getInstance().cancelTag("andkid");
    }
}
