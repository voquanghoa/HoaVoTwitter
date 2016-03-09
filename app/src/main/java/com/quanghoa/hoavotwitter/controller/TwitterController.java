package com.quanghoa.hoavotwitter.controller;

import com.google.gson.Gson;
import com.quanghoa.hoavotwitter.config.WebConstant;
import com.quanghoa.hoavotwitter.model.LoginForm;
import com.quanghoa.hoavotwitter.model.TwitterData;

import java.io.IOException;

/**
 * Created by voqua on 3/8/2016.
 */

/***
 * Call the api via WebController and send the response to view
 */
public class TwitterController {
    /***
     * The feedback when a request finish
     */
    public interface APICallFeedback {
        /***
         * Called when the request is finish successfully
         * @param response The response object
         */
        void onResponseOK(Object response);

        /***
         * Called when the request is finish but the server did not allow to get the data
         */
        void onResponseNotOK();

        /***
         * Called when the request is failed, could not connect to server
         */
        void onFailed();

        /***
         * Allways be called after the request finished
         */
        void onDone();
    }

    private final Gson gson;
    private final WebController webController;

    private static TwitterController instance;
    private TwitterController() {
        gson = new Gson();
        webController = new WebController();
    }

    /***
     * Get the singleton instance object
     * @return
     */
    public static synchronized TwitterController getInstance() {
        if (instance == null) {
            instance = new TwitterController();
        }
        return instance;
    }

    /***
     * Call the logout api
     * @param feedback The feedback
     */
    public void logout(final APICallFeedback feedback){
        sendHttpRequest(WebConstant.LOGOUT_API, WebConstant.METHOD_DELETE, null, null, feedback);
    }

    /***
     * Call the Twitters api
     * @param feedback The feedback
     */
    public void loadTwitters(final APICallFeedback feedback){
        sendHttpRequest(WebConstant.TWEETS_API, WebConstant.METHOD_GET, null, TwitterData.class, feedback);
    }

    /***
     * Call the login api
     * @param loginForm The data to login (with username, password)
     * @param feedback The feedback
     */
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
                }finally {
                    feedback.onDone();
                }
            }
        }).start();
    }
}
