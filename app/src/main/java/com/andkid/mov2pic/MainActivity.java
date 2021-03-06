package com.andkid.mov2pic;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.andkid.mov2pic.adapter.ViewPagerFragmentAdapter;
import com.andkid.mov2pic.callback.HeaderBackgroundCallback;
import com.andkid.mov2pic.callback.HomeListener;
import com.andkid.mov2pic.callback.MovieListCallback;
import com.andkid.mov2pic.model.HeaderBackground;
import com.andkid.mov2pic.model.MovieList;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.Call;

public class MainActivity extends AppCompatActivity {

    private MaterialViewPager mViewPager;

    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar toolbar;
    private ViewPagerFragmentAdapter mViewPagerFragmentAdapter;
    private HomeListener mHomeListener;
    private TextView mBGName;
    private Handler mHandler = new Handler();
    private MovieList mMovieList;
    private HeaderBackground mHeaderBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("");

        Intent intent = getIntent();
        mMovieList = intent.getParcelableExtra("movieList");
        mHeaderBackground = intent.getParcelableExtra("headerBackground");



        mBGName = (TextView) findViewById(R.id.bg_name);
        mViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);

        toolbar = mViewPager.getToolbar();
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (toolbar != null) {
            setSupportActionBar(toolbar);

            final ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setDisplayShowHomeEnabled(true);
                actionBar.setDisplayShowTitleEnabled(true);
                actionBar.setDisplayUseLogoEnabled(false);
                actionBar.setHomeButtonEnabled(true);
            }
        }

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, 0, 0);
        mDrawer.addDrawerListener(mDrawerToggle);

        mViewPagerFragmentAdapter = new ViewPagerFragmentAdapter(getSupportFragmentManager());
        mViewPagerFragmentAdapter.setMovieList(mMovieList);
        mViewPager.getViewPager().setAdapter(mViewPagerFragmentAdapter);
        mHomeListener = new HomeListener(this);
        mHomeListener.setHeaderBackground(mHeaderBackground);
        mViewPager.setMaterialViewPagerListener(mHomeListener);
        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());//viewpager tabs


        mViewPagerFragmentAdapter.notifyDataSetChanged();
//        View logo = findViewById(R.id.logo_white);
//        if (logo != null)
//            logo.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mViewPager.notifyHeaderChanged();
//                    Toast.makeText(getApplicationContext(), "Yes, the title is clickable", Toast.LENGTH_SHORT).show();
//                }
//            });
//        OkHttpUtils.get()
//                .url(WebSites.MOVIE_BACKGROUND_URL)
//                .build()
//                .execute(new HeaderBackgroundCallback() {
//
//                    @Override
//                    public void onError(Call call, Exception e) {
//                        Toast.makeText(MainActivity.this, "Access network failed.", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onResponse(HeaderBackground response) {
//                        mHomeListener.setHeaderBackground(response.trim());
//                        mViewPager.notifyHeaderChanged();
//                    }
//                });
//        OkHttpUtils.get()
//                .url(WebSites.MOVIE_LIST_URL)
//                .build()
//                .execute(new MovieListCallback() {
//
//                    @Override
//                    public void onError(Call call, Exception e) {
//                        Toast.makeText(MainActivity.this, "Access network failed.", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onResponse(MovieList response) {
//                        response.removeOriginalTag();
//                        mViewPagerFragmentAdapter.setMovieList(response);
//                        mViewPagerFragmentAdapter.notifyDataSetChanged();
//                    }
//                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mDrawerToggle.onOptionsItemSelected(item) ||
                super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    public void updateBackgroundName(String string) {
        if (string != null)
            mBGName.setText(string);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mViewPager.materialViewPagerHeader.finalTitleX = toolbar.getChildAt(0).getRight()
                        + mBGName.getMeasuredWidth() * mViewPager.materialViewPagerHeader.finalScale / 2;
            }
        }, 100);
    }
}
