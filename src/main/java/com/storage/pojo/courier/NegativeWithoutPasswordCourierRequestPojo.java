package com.storage.pojo.courier;

public class NegativeWithoutPasswordCourierRequestPojo {
    private String login;
    private String firstName;

    public NegativeWithoutPasswordCourierRequestPojo(String login, String firstName){
        this.login = login;
        this.firstName = firstName;
    }

    public NegativeWithoutPasswordCourierRequestPojo(){}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
