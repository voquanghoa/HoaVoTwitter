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

/***
 * Make a bridge between the data and the user interface, making a view for each item in the data set
 */
public class TwitterAdapter extends BaseAdapter {

    //The context to hold the view
    private final Context context;
    //The presenter, see @TwitterPresenter
    private final TwitterPresenter twitterPresenter;

    //The twitter data, see @TwitterData
    private TwitterData twitterData;

    /***
     * The only one Constructor
     * @param context The context to hold the user interface
     */
    public TwitterAdapter(Context context) {
        this.context = context;
        this.twitterPresenter = new TwitterPresenter();
    }

    /***
     * Set data for the adapter, should call @notifyDataSetChanged later to apply the change
     * @param twitterData The new twitter data to show
     */
    public void setTwitterData(TwitterData twitterData) {
        this.twitterData = twitterData;
    }

    /***
     * Override from @BaseAdapter
     * @return the number of twitters
     */
    public int getCount() {
        return this.twitterData == null ? 0 : this.twitterData.size();
    }

    /***
     * Dummy method
     * @param position
     * @return
     */
    public Object getItem(int position) {
        return null;
    }

    /***
     * Dummy method
     * @param position
     * @return
     */
    public long getItemId(int position) {
        return 0;
    }

    /***
     * Create the view for each twitter item. The view can be reused.
     * @param position The position of the twitter item
     * @param convertView The view can be reused
     * @param parent The parent of view
     * @return The final view to hold the data
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        //If the convertView is null, create a new view, otherwise, reuse it
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.twitter_item_layout, parent, false);
        }

        //Display the data on the view
        twitterPresenter.showTwitterOnView(convertView, twitterData.get(position));

        //Display the background according the position is odd or even
        int background = (position & 1) == 0 ? R.drawable.twitter_item_even : R.drawable.twitter_item_odd;
        convertView.setBackgroundResource(background);

        //Return the final view to display
        return convertView;
    }
}
