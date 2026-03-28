package com.example.test;

public class Singleton {
    private static final Singleton mInstance = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return mInstance;
    }
}
