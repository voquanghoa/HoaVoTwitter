package com.quanghoa.hoavotwitter.config;

/**
 * Created by voqua on 3/8/2016.
 */
public interface WebConstant {
    //Define for Web API
    String WEB_HOST_URL = "https://salty-mesa-4348.herokuapp.com";

    String LOGIN_API = "/login";
    String LOGOUT_API = "/logout";
    String TWEETS_API = "/tweets";

    String METHOD_POST = "POST";
    String METHOD_GET = "GET";

    //Define for Web connection
    String COOKIE_SET_HEADER = "Set-Cookie";
    String COOKIE_GET_HEADER = "Cookie";
    String CONTENT_TYPE_HEADER = "Content-type";
    String CONTENT_TYPE_JSON_VALUE = "application/json";
}
