package com.quanghoa.hoavotwitter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.quanghoa.hoavotwitter.control.BaseActivity;
import com.quanghoa.hoavotwitter.controller.TwitterController;
import com.quanghoa.hoavotwitter.model.LoginForm;


public class LoginActivity extends BaseActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);

        editTextUsername =(EditText) findViewById(R.id.txt_username);
        editTextPassword =(EditText) findViewById(R.id.txt_password);
    }

    public void onLoginButtonClick(View actionView){
        final LoginForm loginForm = new LoginForm();
        loginForm.setLogin(editTextUsername.getText().toString());
        loginForm.setPassword(editTextPassword.getText().toString());
        hideKeyboard();

        if(loginForm.isValid()){
            showLoadingDialog();
            TwitterController.getInstance().login(loginForm, new TwitterController.APICallFeedback() {
                public void onResponseOK(Object response) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            startActivity(new Intent(LoginActivity.this, TwitterActivity.class));
                        }
                    });
                }

                public void onResponseNotOK() {
                    showToastMessage(R.string.login_fail_warning);
                }

                public void onFailed() {
                    showToastMessage(R.string.network_error_warning);
                }

                public void onDone() {
                    dismissLoadingDialog();
                }
            });
        }else{
            showToastMessage(R.string.invalid_login_form_warning);
        }
    }
}
