package com.andkid.mov2pic.callback;

import android.app.Activity;
import android.util.Log;

import com.andkid.mov2pic.MainActivity;
import com.andkid.mov2pic.R;
import com.andkid.mov2pic.model.HeaderBackground;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

/**
 * Created by yuguan.chen on 4/15/16.
 */
public class HomeListener implements MaterialViewPager.Listener {

    HeaderBackground mHeaderBackground;
    MainActivity mActivity;

    public HomeListener(MainActivity activity) {
        mActivity = activity;
    }
    @Override
    public HeaderDesign getHeaderDesign(int page) {
        if (mHeaderBackground == null) {
            return null;
        }
        return HeaderDesign.fromColorResAndUrl(
                android.R.color.holo_blue_light,
                mHeaderBackground.background[page]);

    }

    @Override
    public void onPageSelected(int page) {
        if (mHeaderBackground != null)
            mActivity.updateBackgroundName(mHeaderBackground.name[page]);
    }

    public void setHeaderBackground(HeaderBackground headerBackground) {
        this.mHeaderBackground = headerBackground;
    }
}
