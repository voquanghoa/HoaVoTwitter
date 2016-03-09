package com.quanghoa.hoavotwitter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.quanghoa.hoavotwitter.control.BaseActivity;
import com.quanghoa.hoavotwitter.controller.TwitterController;
import com.quanghoa.hoavotwitter.model.LoginForm;


/***
 * Login screen
 */
public class LoginActivity extends BaseActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;

    private TwitterController.APICallFeedback loginApiCallFeedback = new TwitterController.APICallFeedback() {
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
    };

    /***
     * Create view when the activity was created
     *
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);

        editTextUsername = (EditText) findViewById(R.id.txt_username);
        editTextPassword = (EditText) findViewById(R.id.txt_password);
    }

    /***
     * When the login button clicked, we hide the keyboard, check user's input, call the login api
     * @param actionView dummy param from the system
     */
    public void onLoginButtonClick(View actionView) {
        final LoginForm loginForm = new LoginForm();
        loginForm.setLogin(editTextUsername.getText().toString());
        loginForm.setPassword(editTextPassword.getText().toString());
        hideKeyboard();

        if (loginForm.isValid()) {
            showLoadingDialog();
            TwitterController.getInstance().login(loginForm, loginApiCallFeedback);
        } else {
            showToastMessage(R.string.invalid_login_form_warning);
        }
    }
}
