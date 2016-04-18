package com.andkid.mov2pic.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;

import com.andkid.mov2pic.WebSites;
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
    SparseArray<RecyclerViewFragment> fragments = new SparseArray<>();

    public ViewPagerFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = fragments.get(position);
        if(fragment != null) return fragment;
        final RecyclerViewFragment recyclerViewFragment = RecyclerViewFragment.newInstance();
        OkHttpUtils.get()
                .url(WebSites.MOVIE_LIST_URL)
                .addParams(WebSites.PARAMETER_URI, (String) mMovieList.nav.keySet().toArray()[position])
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
        fragments.append(position, recyclerViewFragment);
        return recyclerViewFragment;
    }

    @Override
    public int getCount() {
        if(mMovieList != null)
            return mMovieList.nav.keySet().size();
        return 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return (String) mMovieList.nav.values().toArray()[position];
    }

    public void setMovieList(MovieList movieList) {
        mMovieList = movieList;
    }
}
