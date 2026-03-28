package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Factory f = new Factory();
        String name=""; //這邊不知道為什麼就填一個空字串
        f.createPhone(name);
    }
}

/*
class Singleton{
    //他一開始寫在這，我請他移出去獨立一個，不會移一直要用選單new一個fragment
    //問原因說自己之前的練習都是這樣做，也就是說，總是看著文件or範例做但「不知道自己在做什麼」
}
*/
