package com.example.test;

public class Singleton {
    public void instance = new Singleton();
    public Singleton(){}
    public void getInstance(){
        if(instance == null){
            instance = new Singleton();
        }

        return getInstance();
    }
}
