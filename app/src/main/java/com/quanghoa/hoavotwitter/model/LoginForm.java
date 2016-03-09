package com.quanghoa.hoavotwitter.model;

/**
 * Created by voqua on 3/8/2016.
 */

/***
 * Hold the data to login
 */
public class LoginForm {
    private String login;
    private String password;

    /***
     * Get the username
     * @return The given username
     */
    public String getLogin() {
        return login;
    }

    /***
     * Set the username
     * @param login The username to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /***
     * Get the given password
     * @return The given password
     */
    public String getPassword() {
        return password;
    }

    /***
     * Set the password
     * @param password The password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /***
     * Verify the data is valid, at this moments, we just verify the login information (username)
     * @return
     */
    public boolean isValid(){
        return login!=null && login.length()>0;
    }
}
