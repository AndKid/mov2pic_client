package com.andkid.mov2pic;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.andkid.mov2pic.adapter.ViewPagerFragmentAdapter;
import com.andkid.mov2pic.callback.HomeListener;
import com.andkid.mov2pic.callback.MovieListCallback;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("");

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
        mViewPager.getViewPager().setAdapter(mViewPagerFragmentAdapter);
        mViewPager.setMaterialViewPagerListener(new HomeListener());
        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());//viewpager tabs

        View logo = findViewById(R.id.logo_white);
        if (logo != null)
            logo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.notifyHeaderChanged();
                    Toast.makeText(getApplicationContext(), "Yes, the title is clickable", Toast.LENGTH_SHORT).show();
                }
            });
        OkHttpUtils.get()
                .url(WebSites.MOVIE_LIST_URL)
                .build()
                .execute(new MovieListCallback() {

                    @Override
                    public void onError(Call call, Exception e) {
                        Toast.makeText(MainActivity.this, "Access network failed.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(MovieList response) {
                        response.removeOriginalTag();
                        mViewPagerFragmentAdapter.setMovieList(response);
                        mViewPagerFragmentAdapter.notifyDataSetChanged();
                    }
                });
    }
}
