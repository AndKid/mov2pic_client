package com.andkid.mov2pic.callback;

import com.andkid.mov2pic.R;
import com.andkid.mov2pic.model.HeaderBackground;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

/**
 * Created by yuguan.chen on 4/15/16.
 */
public class HomeListener implements MaterialViewPager.Listener {

    HeaderBackground mHeaderBackground;

    @Override
    public HeaderDesign getHeaderDesign(int page) {
        if (mHeaderBackground == null) {
            return null;
        }
        return HeaderDesign.fromColorResAndUrl(
                android.R.color.white,
                mHeaderBackground.background[page]);

    }

    public void setHeaderBackground(HeaderBackground headerBackground) {
        this.mHeaderBackground = headerBackground;
    }
}
