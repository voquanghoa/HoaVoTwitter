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

/***
 * The control to show the image from the internet
 */
public class InternetImageView extends ImageView implements Runnable {
    private String imageUrl;

    /***
     * Constructor with a context
     * @param context The context to hold the view
     */
    public InternetImageView(Context context) {
        super(context);
    }

    /***
     * Constructor with context and attributes
     * @param context The context to hold the view
     * @param attrs The attributes
     */
    public InternetImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /***
     * Constructor with context, attributes and a style define
     * @param context The context to hold the view
     * @param attrs The attributes
     * @param defStyleAttr The style define
     */
    public InternetImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /***
     * Set url of the image
     * @param imageUrl Url of the image
     */
    public void setImageUrl(String imageUrl){
        if(imageUrl != null){
            this.imageUrl = imageUrl;
            new Thread(this).start();
        }
    }

    /***
     * Load the bitmap
     */
    public void run() {
        try {
            Bitmap bitmap = BitmapUtil.downloadBitmap(imageUrl);

            bitmap = BitmapUtil.fixBitmapSize(bitmap, ActivityUtil.getScreenWidth());

            safeSetImageBitmap(bitmap);
        } catch (IOException e) {
            safeSetVisibility(false);
        }
    }

    /***
     * Show or hide the view in UI thread
     * @param isVisible show if true, otherwise hide the view
     */
    private void safeSetVisibility(final boolean isVisible) {
        ((Activity)getContext()).runOnUiThread(new Runnable() {
            public void run() {
                setVisibility(isVisible ? VISIBLE : GONE);
            }
        });
    }

    /***
     * Set the image in UI thread
     * @param bitmap the bitmap to show
     */
    private void safeSetImageBitmap(final Bitmap bitmap) {
        ((Activity)getContext()).runOnUiThread(new Runnable() {
            public void run() {
                setImageBitmap(bitmap);
            }
        });
    }
}
