package edu.whu;

public class SimpleClass {
    private int flag;
    public SimpleClass() {
        flag = 0;
        System.out.println("instantiating " + getClass().getName());
    }

    public int getFlag() {
        return flag;
    }

    @initMethod
    public void awake() {
        flag |= 1;
        System.out.println("awake method called");
    }

    @initMethod
    public void start() {
        flag |= 2;
        System.out.println("start method called");
    }



}
