package com.example.observermodel;

public class MessageBean {

    boolean fromOne;
    boolean fromTwo;
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getFromOne() {
        return fromOne;
    }

    public void setFromOne(boolean fromOne) {
        this.fromOne = fromOne;
    }

    public boolean getFromTwo() {
        return fromTwo;
    }

    public void setFromTwo(boolean fromTwo) {
        this.fromTwo = fromTwo;
    }
}
