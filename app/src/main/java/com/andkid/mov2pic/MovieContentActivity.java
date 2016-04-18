package com.andkid.mov2pic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
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
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        Log.i("cyg", "oncreate dou buzou ?");
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.list_movie_content);
        Log.i("cyg", "buxinxie");

        mTitleView = (TextView) findViewById(R.id.title);
        mSummaryView = (TextView) findViewById(R.id.summary);

        Intent intent = getIntent();
        String uri = intent.getStringExtra(WebSites.PARAMETER_URI);
        Log.i("cyg", "start get");
        OkHttpUtils.get()
                .url(WebSites.MOVIE_CONTENT_URL)
                .addParams(WebSites.PARAMETER_URI, uri)
                .build()
                .execute(new MovieContentCallback() {

                    @Override
                    public void onError(Call call, Exception e) {
                        Log.i("cyg", "title = " + e);
                    }

                    @Override
                    public void onResponse(MovieContent response) {
                        Log.i("cyg", "title = " + response.title);
                        mMovieContent = response;
                        initContentView();
                    }
                });
    }

    void initContentView() {
        mTitleView.setText(mMovieContent.title);
        mSummaryView.setText(mMovieContent.summary.toString());
    }


}
