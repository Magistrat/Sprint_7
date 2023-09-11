package com.storage.pojo.order.create;

public class CreateOrderResponsePojo {
    private String track;

    public CreateOrderResponsePojo(String track) {
        this.track = track;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }
}
