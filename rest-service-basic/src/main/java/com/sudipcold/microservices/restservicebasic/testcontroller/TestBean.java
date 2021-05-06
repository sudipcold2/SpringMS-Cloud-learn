package com.sudipcold.microservices.restservicebasic.testcontroller;

public class TestBean {
    String message;

    public TestBean(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "message='" + message + '\'' +
                '}';
    }
}
