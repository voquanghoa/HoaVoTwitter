package com.quanghoa.hoavotwitter.controller;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.quanghoa.hoavotwitter.config.WebConstant;
import com.quanghoa.hoavotwitter.util.IOUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by voqua on 3/8/2016.
 */
public class WebController implements WebConstant {

    public class UnauthorizedException extends Exception {
    }

    public class UnexpectedException extends Exception {
    }

    private Gson gson;
    private List<String> cookie;

    private static WebController instance;

    public static synchronized WebController getInstance() {
        if (instance == null) {
            instance = new WebController();
        }
        return instance;
    }

    private WebController() {
        gson = new Gson();
    }

    public String request(String requestUrl, String method, Object requestParam)
            throws IOException, UnauthorizedException, URISyntaxException, UnexpectedException {
        HttpURLConnection httpConn = createHttpConnection(requestUrl,method);
        writePostData(requestParam, httpConn);

        httpConn.connect();

        int responseCode = httpConn.getResponseCode();

        switch (responseCode) {
            case HttpURLConnection.HTTP_OK:
                cookie = httpConn.getHeaderFields().get(COOKIES_HEADER);
                return IOUtil.readFullyInput(httpConn.getInputStream());
            case HttpURLConnection.HTTP_UNAUTHORIZED:
                throw new UnauthorizedException();
            default:
                throw new UnexpectedException();
        }
    }

    private void writePostData(Object requestParam, HttpURLConnection httpConn) throws IOException {
        if (requestParam != null) {
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            OutputStream outputStream = httpConn.getOutputStream();
            outputStream.write(gson.toJson(requestParam).toString().getBytes());
            outputStream.flush();
        }
    }

    private HttpURLConnection createHttpConnection(String requestUrl, String method) throws IOException {
        URL url = new URL(WEB_HOST_URL + requestUrl);

        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestProperty(CONTENT_TYPE_HEADER, CONTENT_TYPE_JSON_VALUE);

        if (cookie != null) {
            String stringCookie = TextUtils.join(";", cookie);
            httpConn.setRequestProperty("Cookie", stringCookie);
        }

        httpConn.setRequestMethod(method);
        return httpConn;
    }
}
