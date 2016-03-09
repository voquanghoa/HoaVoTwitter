package com.quanghoa.hoavotwitter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.quanghoa.hoavotwitter.R;
import com.quanghoa.hoavotwitter.model.TwitterData;
import com.quanghoa.hoavotwitter.presenter.TwitterPresenter;

/**
 * Created by voqua on 3/8/2016.
 */
public class TwitterAdapter extends BaseAdapter {

    private final Context context;
    private final TwitterPresenter twitterPresenter;
    private TwitterData twitterData;

    public TwitterAdapter(Context context) {
        this.context = context;
        this.twitterPresenter = new TwitterPresenter();
    }

    public void setTwitterData(TwitterData twitterData) {
        this.twitterData = twitterData;
    }

    public int getCount() {
        return this.twitterData == null ? 0 : this.twitterData.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.twitter_item_layout, parent, false);
        }

        twitterPresenter.showTwitterOnView(convertView, twitterData.get(position));

        int background = (position & 1) == 0 ? R.drawable.twitter_item_even : R.drawable.twitter_item_odd;
        convertView.setBackgroundResource(background);

        return convertView;
    }
}
