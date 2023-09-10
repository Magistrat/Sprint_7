package com.storage.pojo.courier.login;

public class NegativeWithoutLoginLogInCourierRequestPojo {
    private String password;

    public NegativeWithoutLoginLogInCourierRequestPojo(String password){
        this.password = password;
    }

    public NegativeWithoutLoginLogInCourierRequestPojo(){}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
