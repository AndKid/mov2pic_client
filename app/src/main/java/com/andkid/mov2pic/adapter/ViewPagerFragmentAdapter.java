package com.andkid.mov2pic.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.andkid.mov2pic.fragment.RecyclerViewFragment;
import com.andkid.mov2pic.model.MovieList;
import com.andkid.mov2pic.okhttp.OkHttpManager;

/**
 * Created by yuguan.chen on 4/15/16.
 */
public class ViewPagerFragmentAdapter extends FragmentStatePagerAdapter {

    public ViewPagerFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position % 4) {
            case 0:
                RecyclerViewFragment recyclerViewFragment = RecyclerViewFragment.newInstance();
                try {
                    new OkHttpManager(recyclerViewFragment).run("http://120.76.115.38/", MovieList.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return recyclerViewFragment;
            case 1:
                return RecyclerViewFragment.newInstance();
            case 2:
                return RecyclerViewFragment.newInstance();
            default:
                return RecyclerViewFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position % 4) {
            case 0:
                return "Selection";
            case 1:
                return "Actualités";
            case 2:
                return "Professionnel";
            case 3:
                return "Divertissement";
        }
        return "";
    }
}
