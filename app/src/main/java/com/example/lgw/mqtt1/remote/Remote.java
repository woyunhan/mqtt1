package com.example.lgw.mqtt1.remote;

/**
 * Created by Jne
 * Date: 2015/1/6.
 */
public class Remote {
    public int id;
    public String function;
    public String code;

    public Remote() {
    }

    public Remote(int id, String function, String code) {
        this.id = id;
        this.function = function;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
