package com.quanghoa.hoavotwitter.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.net.URL;

/**
 * Created by voqua on 3/9/2016.
 * Contains some useful methods for working with bitmap
 */
public class BitmapUtil {
    /***
     * Download the bitmap from server
     * @param imageUrl The user of bitmap
     * @return  The downloaded bitmap
     * @throws IOException When we could not download the image
     */
    public static Bitmap downloadBitmap(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        return BitmapFactory.decodeStream(url.openConnection().getInputStream());
    }

    /***
     * Scale the bitmap in need to make sure the the width of bitmap will not bigger than a specific width
     * @param bitmap The bitmap need to scale
     * @param maxWidth The maximum bitmap's width
     * @return If the bitmap is need to scale, return the scaled bitmap, otherwise, return the original bitmap
     */
    public static Bitmap fixBitmapSize(Bitmap bitmap, int maxWidth) {
        if (bitmap.getWidth() > maxWidth) {
            int newHeight = bitmap.getHeight() * maxWidth / bitmap.getWidth();
            Bitmap originalBitmap = bitmap;
            bitmap = Bitmap.createScaledBitmap(bitmap, maxWidth, newHeight, false);
            originalBitmap.recycle();
        }

        return bitmap;
    }
}
