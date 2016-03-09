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
public class TwitterPresenter {
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

    private void showTextOnTextView(View viewParent, int textViewId, String text){
        ((TextView)viewParent.findViewById(textViewId)).setText(text);
    }
}
