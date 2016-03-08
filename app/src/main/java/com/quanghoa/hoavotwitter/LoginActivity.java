package com.quanghoa.hoavotwitter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.quanghoa.hoavotwitter.config.WebConstant;
import com.quanghoa.hoavotwitter.controller.WebController;
import com.quanghoa.hoavotwitter.model.LoginForm;

import java.io.IOException;
import java.net.URISyntaxException;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);

        editTextUsername =(EditText) findViewById(R.id.txt_username);
        editTextPassword =(EditText) findViewById(R.id.txt_password);
    }

    public void onLoginButtonClick(View view){
        final LoginForm loginForm = new LoginForm();
        loginForm.setLogin("foo");
        loginForm.setPassword("bar");

        new Thread(new Runnable() {
            public void run() {
                try {
                    String response2 = WebController.getInstance().request(WebConstant.LOGIN_API, WebConstant.METHOD_POST, loginForm);
                    Log.i("VOQUANGHOA","REQUEST 1" +response2);
                    String response1 = WebController.getInstance().request(WebConstant.TWEETS_API, WebConstant.METHOD_GET, null);
                    Log.i("VOQUANGHOA","REQUEST 2" +response1);
                    String a=response1;
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (WebController.UnauthorizedException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                } catch (WebController.UnexpectedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
