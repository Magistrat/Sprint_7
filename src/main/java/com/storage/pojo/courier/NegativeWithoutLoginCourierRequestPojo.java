package com.storage.pojo.courier;

public class NegativeWithoutLoginCourierRequestPojo {
    private String password;
    private String firstName;

    public NegativeWithoutLoginCourierRequestPojo(String password, String firstName){
        this.password = password;
        this.firstName = firstName;
    }

    public NegativeWithoutLoginCourierRequestPojo(){}

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
