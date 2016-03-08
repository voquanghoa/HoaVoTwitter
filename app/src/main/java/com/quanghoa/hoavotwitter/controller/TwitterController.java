package com.quanghoa.hoavotwitter.controller;

import com.google.gson.Gson;
import com.quanghoa.hoavotwitter.config.WebConstant;
import com.quanghoa.hoavotwitter.model.LoginForm;
import com.quanghoa.hoavotwitter.model.TwitterData;

import java.io.IOException;

/**
 * Created by voqua on 3/8/2016.
 */
public class TwitterController {
    public interface APICallFeedback {
        void onResponseOK(Object response);
        void onResponseNotOK();
        void onFailed();
        void onDone();
    }

    private Gson gson;
    private WebController webController;

    private static TwitterController instance;
    private TwitterController() {
        gson = new Gson();
        webController = new WebController();
    }

    public static synchronized TwitterController getInstance() {
        if (instance == null) {
            instance = new TwitterController();
        }
        return instance;
    }

    public void logout(final APICallFeedback feedback){
        sendHttpRequest(WebConstant.LOGOUT_API, WebConstant.METHOD_DELETE, null, null, feedback);
    }

    public void loadTwitters(final APICallFeedback feedback){
        sendHttpRequest(WebConstant.TWEETS_API, WebConstant.METHOD_GET, null, TwitterData.class, feedback);
    }

    public void login(final LoginForm loginForm, final APICallFeedback feedback) {
        sendHttpRequest(WebConstant.LOGIN_API, WebConstant.METHOD_POST, loginForm, null, feedback);
    }

    private <T> void sendHttpRequest(final String apiName,
                                     final String requestMethod,
                                     final LoginForm loginForm,
                                     final Class<T> classOfResponse,
                                     final APICallFeedback feedback) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    String response = webController.request(apiName, requestMethod, loginForm);
                    if (classOfResponse != null) {
                        feedback.onResponseOK(gson.fromJson(response, classOfResponse));
                    } else {
                        feedback.onResponseOK(null);
                    }
                    if(apiName.equals(WebConstant.LOGOUT_API)){
                        webController.forceClearCookie();
                    }
                } catch (IOException e) {
                    feedback.onFailed();
                } catch (WebController.UnauthorizedException e) {
                    feedback.onResponseNotOK();
                } catch (WebController.UnexpectedException e) {
                    feedback.onFailed();
                }
                feedback.onDone();
            }
        }).start();
    }
}
