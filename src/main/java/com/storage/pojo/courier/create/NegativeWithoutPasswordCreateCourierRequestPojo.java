package com.storage.pojo.courier.create;

public class NegativeWithoutPasswordCreateCourierRequestPojo {
    private String login;
    private String firstName;

    public NegativeWithoutPasswordCreateCourierRequestPojo(String login, String firstName){
        this.login = login;
        this.firstName = firstName;
    }

    public NegativeWithoutPasswordCreateCourierRequestPojo(){}

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
