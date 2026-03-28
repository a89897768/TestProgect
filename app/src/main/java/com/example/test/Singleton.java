package com.example.test;

public class Singleton {
    private static final Singleton mInstance = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return mInstance;
    }
}

interface CellPhone{
    void createMonitor();
}

class APhone implements CellPhone{

    @Override
    public void createMonitor() {
        System.out.println("製造螢幕！");
    }
}

class Factory{
    APhone mAPhone = new APhone();

    public void createPhone(){
        mAPhone.createMonitor();
    }
}

Factory f = new Factory();
f.createPhone();
