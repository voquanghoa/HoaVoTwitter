package com.quanghoa.hoavotwitter.controller;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.quanghoa.hoavotwitter.config.WebConstant;
import com.quanghoa.hoavotwitter.util.IOUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by voqua on 3/8/2016.
 */

/***
 * Access the server using HttpConnection, use in the local package only
 */
class WebController implements WebConstant {

    /***
     * Exception when the server did not allow to access
     */
    public class UnauthorizedException extends Exception {
    }

    /***
     * Unexpected exception, maybe the network has problem, unexpected response from server
     */
    public class UnexpectedException extends Exception {
    }

    private final Gson gson;
    private List<String> cookie;

    /***
     * Create a WebController instance
     */
    public WebController() {
        gson = new Gson();
    }

    /***
     * Do a http request
     * @param requestUrl The url to request
     * @param method The method of request, can be GET/POST/DELETE
     * @param requestParam The request param
     * @return The response as string
     * @throws IOException When we could not access the internet
     * @throws UnauthorizedException When the server did not allow to access
     * @throws UnexpectedException When the server return an unexpected response
     */
    public String request(String requestUrl, String method, Object requestParam)
            throws IOException, UnauthorizedException, UnexpectedException {
        HttpURLConnection httpConn = createHttpConnection(requestUrl,method);
        writePostData(requestParam, httpConn);

        httpConn.connect();

        int responseCode = httpConn.getResponseCode();
        switch (responseCode) {
            case HttpURLConnection.HTTP_OK:
                storeCookie(httpConn);
                return IOUtil.readFullyInput(httpConn.getInputStream());
            case HttpURLConnection.HTTP_UNAUTHORIZED:
                throw new UnauthorizedException();
            default:
                throw new UnexpectedException();
        }
    }

    /***
     * Clear the cookie
     */
    public void forceClearCookie(){
        cookie = null;
    }

    /***
     * Store the cookie from the finished connection
     * @param httpConn The finished connection
     */
    private void storeCookie(HttpURLConnection httpConn) {
        List<String> newCookie = httpConn.getHeaderFields().get(COOKIE_SET_HEADER);

        if(newCookie != null){
            cookie = newCookie;
        }
    }

    /***
     * Write the post data param to the connection's input stream
     * @param requestParam
     * @param httpConn
     * @throws IOException
     */
    private void writePostData(Object requestParam, HttpURLConnection httpConn) throws IOException {
        if (requestParam != null) {
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            OutputStream outputStream = httpConn.getOutputStream();
            outputStream.write(gson.toJson(requestParam).getBytes());
            outputStream.flush();
        }
    }

    /***
     * Create a new http connection
     * @param requestUrl The url to connect
     * @param method The method to connect
     * @return The connection
     * @throws IOException When we could not create the connection
     */
    private HttpURLConnection createHttpConnection(String requestUrl, String method) throws IOException {
        URL url = new URL(WEB_HOST_URL + requestUrl);

        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestProperty(CONTENT_TYPE_HEADER, CONTENT_TYPE_JSON_VALUE);

        appendCookie(httpConn);

        httpConn.setRequestMethod(method);
        return httpConn;
    }

    /***
     * Append the saved cookie to the connection
     * @param httpConn The connection to append the cookie
     */
    private void appendCookie(HttpURLConnection httpConn) {
        if (cookie != null) {
            String stringCookie = TextUtils.join(";", cookie);
            httpConn.setRequestProperty(COOKIE_GET_HEADER, stringCookie);
        }
    }
}
