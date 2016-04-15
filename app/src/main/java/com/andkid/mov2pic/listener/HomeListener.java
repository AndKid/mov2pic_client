package com.andkid.mov2pic.listener;

import com.andkid.mov2pic.R;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

/**
 * Created by yuguan.chen on 4/15/16.
 */
public class HomeListener implements MaterialViewPager.Listener {
    @Override
    public HeaderDesign getHeaderDesign(int page) {

//                BitmapFactory.Options opts = new BitmapFactory.Options();
//                opts.inSampleSize = 4;
//                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.wallpaper_51, opts);
//                BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
//                bitmap.recycle();

        switch (page) {
            case 0:
                return HeaderDesign.fromColorResAndUrl(
                        R.color.green,
                        "http://cdn1.tnwcdn.com/wp-content/blogs.dir/1/files/2014/06/wallpaper_51.jpg");
//                        return HeaderDesign.fromColorResAndDrawable(
//                                R.color.green,
//                                bitmapDrawable);
            case 1:
                return HeaderDesign.fromColorResAndUrl(
                        R.color.blue,
                        "http://cdn1.tnwcdn.com/wp-content/blogs.dir/1/files/2014/06/wallpaper_51.jpg");
            case 2:
                return HeaderDesign.fromColorResAndUrl(
                        R.color.cyan,
                        "http://www.droid-life.com/wp-content/uploads/2014/10/lollipop-wallpapers10.jpg");
            case 3:
                return HeaderDesign.fromColorResAndUrl(
                        R.color.red,
                        "http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg");
        }

        //execute others actions if needed (ex : modify your header logo)

        return null;
    }
}
