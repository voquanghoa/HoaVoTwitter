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
public class BaseActivity extends AppCompatActivity {

    private ProgressDialog dialog;
    protected void showLoadingDialog(){
        runOnUiThread(new Runnable() {
            public void run() {
                if (dialog == null) {
                    dialog = new ProgressDialog(BaseActivity.this);
                    dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    dialog.setMessage(getString(R.string.loading));
                    dialog.setIndeterminate(false);
                    dialog.setCanceledOnTouchOutside(false);
                }
                dialog.show();
            }
        });
    }

    protected void dismissLoadingDialog(){
        runOnUiThread(new Runnable() {
            public void run() {
                if(dialog != null){
                    dialog.dismiss();
                }
            }
        });
    }

    protected void showToastMessage(final String message){
        runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(BaseActivity.this,message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void showToastMessage(int messageId){
        showToastMessage(getString(messageId));
    }

    protected void hideKeyboard(){
        runOnUiThread(new Runnable() {
            public void run() {
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
            }
        });
    }


}
