package com.storage.pojo.courier.login;

public class NegativeWithoutPasswordLogInCourierRequestPojo {
    private String login;

    public NegativeWithoutPasswordLogInCourierRequestPojo(String login){
        this.login = login;
    }

    public NegativeWithoutPasswordLogInCourierRequestPojo(){}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
