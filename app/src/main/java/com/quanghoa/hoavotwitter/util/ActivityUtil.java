package com.quanghoa.hoavotwitter.util;

import android.content.res.Resources;

/**
 * Created by voqua on 3/9/2016.
 */

/***
 * Contains some useful methods for activities
 */
public class ActivityUtil {
    /***
     * Get the screen width
     * @return The screen width in pixel
     */
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }
}
