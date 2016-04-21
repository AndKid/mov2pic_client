package com.andkid.mov2pic;

import android.util.Log;

/**
 * Created by CHEN on 2016/4/21.
 */
public class Util {

    public static void logDdg(String tag) {
        Log.d(tag, "stack: "
        + Thread.currentThread().getStackTrace()[2].getMethodName() + " - "
        + Thread.currentThread().getStackTrace()[3].getMethodName() + " - "
        + Thread.currentThread().getStackTrace()[4].getMethodName() + " - "
        + Thread.currentThread().getStackTrace()[5].getMethodName() + " - "
        + Thread.currentThread().getStackTrace()[6].getMethodName() + " - "
        + Thread.currentThread().getStackTrace()[7].getMethodName() + " - "
        + Thread.currentThread().getStackTrace()[8].getMethodName() + " - "
        + Thread.currentThread().getStackTrace()[9].getMethodName() + " - "
                        + Thread.currentThread().getStackTrace()[10].getMethodName() + " - "
                        + Thread.currentThread().getStackTrace()[11].getMethodName() + " - "
                        + Thread.currentThread().getStackTrace()[12].getMethodName() + " - "
        );

    }
}
