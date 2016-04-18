package com.andkid.mov2pic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.andkid.mov2pic.callback.MovieContentCallback;
import com.andkid.mov2pic.model.MovieContent;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.Call;

/**
 * Created by yuguan.chen on 4/18/16.
 */
public class MovieContentActivity extends Activity {
    MovieContent mMovieContent;
    TextView mTitleView;
    TextView mSummaryView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_movie_content);

        mTitleView = (TextView) findViewById(R.id.title);
        mSummaryView = (TextView) findViewById(R.id.summary);

        Intent intent = getIntent();
        String uri = intent.getStringExtra(WebSites.PARAMETER_URI);
        OkHttpUtils.get()
                .url(WebSites.MOVIE_CONTENT_URL)
                .addParams(WebSites.PARAMETER_URI, uri)
                .build()
                .execute(new MovieContentCallback() {

                    @Override
                    public void onError(Call call, Exception e) {

                    }

                    @Override
                    public void onResponse(MovieContent response) {
                        mMovieContent = response;
                        initContentView();
                    }
                });
    }

    void initContentView() {
        mTitleView.setText(mMovieContent.title);
        StringBuilder summary = new StringBuilder();
        if(mMovieContent.summary != null) {
            for(String s : mMovieContent.summary) {
                summary.append(s.trim());
            }
        }
        mSummaryView.setText(summary);
    }


}
