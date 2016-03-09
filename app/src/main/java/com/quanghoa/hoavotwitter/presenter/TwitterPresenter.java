package com.quanghoa.hoavotwitter.presenter;

import android.view.View;
import android.widget.TextView;

import com.quanghoa.hoavotwitter.R;
import com.quanghoa.hoavotwitter.control.InternetImageView;
import com.quanghoa.hoavotwitter.model.TwitterPost;

import java.util.List;

/**
 * Created by voqua on 3/8/2016.
 */

/***
 * Display a twitter data to a specific view
 */
public class TwitterPresenter {
    /***
     * Show a twitter data to a specific view
     * @param viewParent The view to hold the data
     * @param twitterPost The data to display
     */
    public void showTwitterOnView(View viewParent, TwitterPost twitterPost){
        showTextOnTextView(viewParent, R.id.txt_Id, twitterPost.getId());
        showTextOnTextView(viewParent, R.id.txt_author, twitterPost.getAuthor());
        showTextOnTextView(viewParent, R.id.txt_content, twitterPost.getContent());
        List<String> images = twitterPost.getImages();

        InternetImageView internetImageView = ((InternetImageView)viewParent.findViewById(R.id.iiv_image));
        if(images!=null && images.size()>0){
            internetImageView.setVisibility(View.VISIBLE);
            internetImageView.setImageUrl(images.get(0));
        }else{
            internetImageView.setVisibility(View.GONE);
            internetImageView.setImageUrl(null);
        }
    }

    /***
     * Display a text string to a TextView
     * @param viewParent The parent to find the TextView
     * @param textViewId The id of TextView
     * @param text  The text to display
     */
    private void showTextOnTextView(View viewParent, int textViewId, String text){
        ((TextView)viewParent.findViewById(textViewId)).setText(text);
    }
}
