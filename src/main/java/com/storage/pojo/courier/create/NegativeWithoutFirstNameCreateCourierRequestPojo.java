package com.storage.pojo.courier.create;

public class NegativeWithoutFirstNameCreateCourierRequestPojo {
    private String login;
    private String password;

    public NegativeWithoutFirstNameCreateCourierRequestPojo(String login, String password){
        this.login = login;
        this.password = password;
    }

    public NegativeWithoutFirstNameCreateCourierRequestPojo(){}

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
