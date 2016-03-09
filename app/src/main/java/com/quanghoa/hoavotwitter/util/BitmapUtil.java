package com.quanghoa.hoavotwitter.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.net.URL;

/**
 * Created by voqua on 3/9/2016.
 */
public class BitmapUtil {
    public static Bitmap downloadBitmap(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        return BitmapFactory.decodeStream(url.openConnection().getInputStream());
    }

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
