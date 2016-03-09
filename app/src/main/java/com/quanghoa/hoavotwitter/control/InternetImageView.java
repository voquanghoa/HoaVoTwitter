package com.quanghoa.hoavotwitter.control;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.quanghoa.hoavotwitter.util.ActivityUtil;
import com.quanghoa.hoavotwitter.util.BitmapUtil;

import java.io.IOException;

/**
 * Created by voqua on 3/9/2016.
 */
public class InternetImageView extends ImageView implements Runnable {
    private String imageUrl;
    public InternetImageView(Context context) {
        super(context);
    }

    public InternetImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InternetImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setImageUrl(String imageUrl){
        if(imageUrl != null){
            this.imageUrl = imageUrl;
            new Thread(this).start();
        }
    }

    public void run() {
        try {
            Bitmap bitmap = BitmapUtil.downloadBitmap(imageUrl);

            bitmap = BitmapUtil.fixBitmapSize(bitmap, ActivityUtil.getScreenWidth());

            safeSetImageBitmap(bitmap);
        } catch (IOException e) {
            safeSetVisibility(false);
        }
    }

    private void safeSetVisibility(final boolean isVisible) {
        ((Activity)getContext()).runOnUiThread(new Runnable() {
            public void run() {
                setVisibility(isVisible ? VISIBLE : GONE);
            }
        });
    }

    private void safeSetImageBitmap(final Bitmap bmp) {
        ((Activity)getContext()).runOnUiThread(new Runnable() {
            public void run() {
                setImageBitmap(bmp);
            }
        });
    }
}
