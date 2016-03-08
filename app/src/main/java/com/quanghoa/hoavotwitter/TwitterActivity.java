package com.quanghoa.hoavotwitter;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.quanghoa.hoavotwitter.adapter.TwitterAdapter;
import com.quanghoa.hoavotwitter.control.BaseActivity;
import com.quanghoa.hoavotwitter.controller.TwitterController;
import com.quanghoa.hoavotwitter.model.TwitterData;

public class TwitterActivity extends BaseActivity{
    private ListView twitterContentListView;
    private TwitterAdapter twitterAdapter;

    private TwitterController.APICallFeedback getTwitterCallback = new TwitterController.APICallFeedback() {
        public void onResponseOK(Object response) {
            if(response != null && response instanceof TwitterData) {
                TwitterData twitterData = (TwitterData) response;
                twitterAdapter.setTwitterData(twitterData);
                TwitterActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        twitterAdapter.notifyDataSetChanged();
                    }
                });
            }else{
                showToastMessage(R.string.unknown_error_warning);
            }
        }

        public void onResponseNotOK() {
            showToastMessage(R.string.network_error_warning);
        }

        public void onFailed() {
            showToastMessage(R.string.unknown_error_warning);
        }

        public void onDone(){
            dismissLoadingDialog();
        }
    };

    private TwitterController.APICallFeedback logoutCallback = new TwitterController.APICallFeedback() {
        public void onResponseOK(Object response) {
            TwitterActivity.this.runOnUiThread(new Runnable() {
                public void run() {
                    finish();
                }
            });
        }

        public void onResponseNotOK() {
            showToastMessage(R.string.network_error_warning);
        }

        public void onFailed() {
            showToastMessage(R.string.unknown_error_warning);
        }

        public void onDone(){
            dismissLoadingDialog();
        }
    };

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_refresh:
                loadTwitterData();
                return true;
            case R.id.menu_logout:
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_twitter, menu);
        return true;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter_layout);

        twitterContentListView = (ListView)findViewById(R.id.lv_twitter_content);
        twitterAdapter = new TwitterAdapter(this, null);
        twitterContentListView.setAdapter(twitterAdapter);
        loadTwitterData();
    }

    private void loadTwitterData(){
        showLoadingDialog();
        TwitterController.getInstance().loadTwitters(getTwitterCallback);
    }

    private void logout(){
        showLoadingDialog();
        TwitterController.getInstance().logout(logoutCallback);
    }
}