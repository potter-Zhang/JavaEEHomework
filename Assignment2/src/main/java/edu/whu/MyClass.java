package edu.whu;

public class MyClass {
    private int flag;

    public MyClass() {
        flag = 0;
        System.out.println("instantiating " + getClass().getName());
    }

    public int getFlag() {
        return flag;
    }

    @initMethod
    public void init() {
        flag |= 1;
        System.out.println("init method called");
    }

    public void start() {
        flag |= 2;
        System.out.println("start method called");
    }


}
