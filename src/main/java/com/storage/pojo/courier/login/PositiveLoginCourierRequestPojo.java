package com.storage.pojo.courier.login;

public class PositiveLoginCourierRequestPojo {
    private String login;
    private String password;

    public PositiveLoginCourierRequestPojo(String login, String password){
        this.login = login;
        this.password = password;
    }

    public PositiveLoginCourierRequestPojo(){}

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
