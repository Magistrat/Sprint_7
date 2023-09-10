package com.storage.pojo.courier.create;

public class NegativeWithoutLoginCreateCourierRequestPojo {
    private String password;
    private String firstName;

    public NegativeWithoutLoginCreateCourierRequestPojo(String password, String firstName){
        this.password = password;
        this.firstName = firstName;
    }

    public NegativeWithoutLoginCreateCourierRequestPojo(){}

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
