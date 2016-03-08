package com.quanghoa.hoavotwitter.control;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

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

    public void showToastMessage(final String message){
        runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(BaseActivity.this,message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showToastMessage(int messageId){
        showToastMessage(getString(messageId));
    }

}
