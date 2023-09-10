package com.storage.pojo.courier.create;

public class PositiveWithoutFirstNameCourierRequestPojo {
    private String login;
    private String password;

    public PositiveWithoutFirstNameCourierRequestPojo(String login, String password){
        this.login = login;
        this.password = password;
    }

    public PositiveWithoutFirstNameCourierRequestPojo(){}

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
