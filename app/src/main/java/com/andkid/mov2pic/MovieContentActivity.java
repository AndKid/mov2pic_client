package com.andkid.mov2pic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.andkid.mov2pic.adapter.ContentRecyclerViewAdapter;
import com.andkid.mov2pic.callback.MovieContentCallback;
import com.andkid.mov2pic.model.MovieContent;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.Call;

/**
 * Created by yuguan.chen on 4/18/16.
 */
public class MovieContentActivity extends Activity {
    MovieContent mMovieContent;
    RecyclerView mContentView;
    private ContentRecyclerViewAdapter mContentRecyclerViewAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_movie_content);

        mContentView = (RecyclerView) findViewById(R.id.movie_content);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mContentView.setLayoutManager(layoutManager);
        mContentRecyclerViewAdapter = new ContentRecyclerViewAdapter(this);
        mContentView.setAdapter(mContentRecyclerViewAdapter);

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
                        mContentRecyclerViewAdapter.setMovieContent(response);
                        mContentRecyclerViewAdapter.notifyDataSetChanged();
                    }
                });
    }

}
