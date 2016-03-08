package com.quanghoa.hoavotwitter.model;

/**
 * Created by voqua on 3/8/2016.
 */
public class LoginForm {
    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValid(){
        return login!=null && login.length()>0;
    }
}
