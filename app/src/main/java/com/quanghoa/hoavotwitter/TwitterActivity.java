package com.quanghoa.hoavotwitter;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.quanghoa.hoavotwitter.adapter.TwitterAdapter;
import com.quanghoa.hoavotwitter.control.BaseActivity;
import com.quanghoa.hoavotwitter.controller.TwitterController;
import com.quanghoa.hoavotwitter.model.TwitterData;


/***
 * Show the list of Twitters
 */
public class TwitterActivity extends BaseActivity{
    /***
     * The adapter to show the twitters
     */
    private TwitterAdapter twitterAdapter;

    /***
     * The callback object when call the twitters API
     */
    private final TwitterController.APICallFeedback getTwitterCallback = new TwitterController.APICallFeedback() {
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
            showToastMessage(R.string.login_request);
            TwitterActivity.this.runOnUiThread(new Runnable() {
                public void run() {
                    finish();
                }
            });
        }

        public void onFailed() {
            showToastMessage(R.string.network_error_warning);
        }

        public void onDone(){
            dismissLoadingDialog();
        }
    };

    /***
     * The callback object when call the Logout API
     */
    private final TwitterController.APICallFeedback logoutCallback = new TwitterController.APICallFeedback() {
        public void onResponseOK(Object response) {
            TwitterActivity.this.runOnUiThread(new Runnable() {
                public void run() {
                    finish();
                }
            });
        }

        public void onResponseNotOK() {
            showToastMessage(R.string.unknown_error_warning);
        }

        public void onFailed() {
            showToastMessage(R.string.network_error_warning);
        }

        public void onDone(){
            dismissLoadingDialog();
        }
    };

    /***
     * Create the user view, load the data when the activity was created
     * @param savedInstanceState the saved instance state if reused
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter_layout);

        twitterAdapter = new TwitterAdapter(this);
        ((ListView)findViewById(R.id.lv_twitter_content)).setAdapter(twitterAdapter);

        loadTwitterData();
    }

    /***
     * Event when user select a menu
     * @param item
     * @return
     */
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

    /***
     * To make the menu
     * @param menu the original menu
     * @return always is true to show the menu
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_twitter, menu);
        return true;
    }

    /***
     * Show the loading dialog and call the load twitters api
     */
    private void loadTwitterData(){
        showLoadingDialog();
        TwitterController.getInstance().loadTwitters(getTwitterCallback);
    }

    /***
     * Show the loading dialog and call the logout api
     */
    private void logout(){
        showLoadingDialog();
        TwitterController.getInstance().logout(logoutCallback);
    }
}
