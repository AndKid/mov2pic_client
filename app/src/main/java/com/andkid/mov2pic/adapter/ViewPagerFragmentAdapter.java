package com.andkid.mov2pic.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.andkid.mov2pic.callback.MovieListCallback;
import com.andkid.mov2pic.fragment.RecyclerViewFragment;
import com.andkid.mov2pic.model.MovieList;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.Call;

/**
 * Created by yuguan.chen on 4/15/16.
 */
public class ViewPagerFragmentAdapter extends FragmentStatePagerAdapter {
    MovieList mMovieList;

    public ViewPagerFragmentAdapter(FragmentManager fm, MovieList movieList) {
        super(fm);
        mMovieList = movieList;
    }

    @Override
    public Fragment getItem(int position) {
        final RecyclerViewFragment recyclerViewFragment = RecyclerViewFragment.newInstance();
        OkHttpUtils.get()
                .url("http://120.76.115.38/")
                .addParams("uri", (String) mMovieList.nav.keySet().toArray()[position])
                .build()
                .execute(new MovieListCallback() {

                    @Override
                    public void onError(Call call, Exception e) {

                    }

                    @Override
                    public void onResponse(MovieList response) {
                        recyclerViewFragment.refreshContent(response);
                    }
                });
        return recyclerViewFragment;
    }

    @Override
    public int getCount() {
        return mMovieList.nav.keySet().size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return (String) mMovieList.nav.values().toArray()[position];
    }

}
