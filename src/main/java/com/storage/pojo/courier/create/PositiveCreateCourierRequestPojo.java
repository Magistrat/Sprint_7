package com.storage.pojo.courier.create;

public class PositiveCreateCourierRequestPojo {
    private String login;
    private String password;
    private String firstName;

    public PositiveCreateCourierRequestPojo(String login, String password, String firstName){
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public PositiveCreateCourierRequestPojo(){}

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

}