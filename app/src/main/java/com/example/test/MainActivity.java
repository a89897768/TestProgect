package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jarRepackageTest(new TwCaV1());
        jarRepackageTest(new TwCaV2());
    }

    private void jarRepackageTest(ITwCa twCa) {
        try {
            Class<?> c = twCa.getLibraryClass();
            Field field = c.getDeclaredField("libName");
            field.setAccessible(true);
            Log.i("Test", (String) field.get(c.newInstance()));

            Method m = c.getDeclaredMethod("Load", Context.class);
            m.setAccessible(true);
            Log.i("Test2", String.valueOf(m.invoke(c.newInstance(), getApplicationContext())));
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Error2", e.getCause().getMessage());
        }
    }
}
