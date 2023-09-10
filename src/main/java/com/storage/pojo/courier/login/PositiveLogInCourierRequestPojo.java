package com.storage.pojo.courier.login;

public class PositiveLogInCourierRequestPojo {
    private String login;
    private String password;

    public PositiveLogInCourierRequestPojo(String login, String password){
        this.login = login;
        this.password = password;
    }

    public PositiveLogInCourierRequestPojo(){}

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
}
