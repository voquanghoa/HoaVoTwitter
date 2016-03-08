package com.quanghoa.hoavotwitter.presenter;

import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.quanghoa.hoavotwitter.R;
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

        WebView webView = ((WebView)viewParent.findViewById(R.id.wv_image));
        if(images!=null && images.size()>0){
            webView.setVisibility(View.VISIBLE);
            webView.loadUrl(images.get(0));
        }else{
            webView.stopLoading();
            webView.setVisibility(View.GONE);
        }

    }

    private void showTextOnTextView(View viewParent, int textViewId, String text){
        ((TextView)viewParent.findViewById(textViewId)).setText(text);
    }
}
