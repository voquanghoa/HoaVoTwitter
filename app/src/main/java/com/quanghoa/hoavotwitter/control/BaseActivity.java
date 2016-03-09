package com.quanghoa.hoavotwitter.control;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.quanghoa.hoavotwitter.R;

/**
 * Created by voqua on 3/8/2016.
 */

/***
 * The base of all activities
 */
public class BaseActivity extends AppCompatActivity {

    /**
     * Create and show the dialog in UI thread
     */
    private ProgressDialog progressDialog;
    protected void showLoadingDialog(){
        runOnUiThread(new Runnable() {
            public void run() {
                if (progressDialog == null) {
                    progressDialog = new ProgressDialog(BaseActivity.this);
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.setMessage(getString(R.string.loading));
                    progressDialog.setIndeterminate(false);
                    progressDialog.setCanceledOnTouchOutside(false);
                }
                progressDialog.show();
            }
        });
    }

    /**
     * Dismiss the showed dialog in UI thread
     */
    protected void dismissLoadingDialog(){
        runOnUiThread(new Runnable() {
            public void run() {
                if(progressDialog != null){
                    progressDialog.dismiss();
                }
            }
        });
    }

    /***
     * Show the message to user in UI thread
     * @param message The message to show
     */
    protected void showToastMessage(final String message){
        runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(BaseActivity.this,message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /***
     * Show the message to user in UI thread
     * @param messageId the id of message string to show
     */
    protected void showToastMessage(int messageId){
        showToastMessage(getString(messageId));
    }

    /***
     * Hide the keyboard in UI thread
     */
    protected void hideKeyboard(){
        runOnUiThread(new Runnable() {
            public void run() {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
            }
        });
    }
}
