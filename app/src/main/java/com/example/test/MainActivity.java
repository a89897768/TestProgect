package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.multidex.MultiDex;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        test("TWCAcMobileCryptoV10140U20180601.jar", "com.twca.crypto.twcalib");
        test("TWCAcMobileCryptoV10150U20191230.jar", "com.twca.crypto.twcalib","libtwcajniV10150U20191230.so");

//        test2(new TwCaV1());
//        test2(new TwCaV2());
    }

    private void test(String jar, String className,String soName) {
        try {
            String soPath = "/" + soName;
            AssetManager assetManager = getAssets();
            InputStream i = assetManager.open(soName);
            File f = new File(getFilesDir().getPath() + soPath);
            File soF = f;
            FileOutputStream outputStream = new FileOutputStream(f);
            byte buf[] = new byte[1024];
            int len;
            while ((len = i.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }

            outputStream.close();
            i.close();

//            System.loadLibrary("twcajniV10150U20191230");
            Log.i("Test",soF.getPath());

            String jarPath = "/" + jar;
            i = assetManager.open(jar);
            f = new File(getFilesDir().getPath() + jarPath);
            outputStream = new FileOutputStream(f);
            buf = new byte[1024];
            while ((len = i.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }

            outputStream.close();
            i.close();

            File ff = new File(getFilesDir().getPath() + jarPath);
            ClassLoader classLoader;
            classLoader = new DexClassLoader(ff.getPath(), getFilesDir().getPath(), "/system/arm64-v8a", getClassLoader());
            Class<?> c = classLoader.loadClass(className);
            Field field = c.getDeclaredField("libName");
            field.setAccessible(true);
            String libraryName = (String) field.get(c.newInstance());
            Log.i("Test", libraryName);

            Method m = c.getDeclaredMethod("Load", Context.class);
            m.setAccessible(true);
            Log.i("Test2", String.valueOf(m.invoke(c.newInstance(), getApplicationContext())));
        } catch (InvocationTargetException e) {
            e.printStackTrace();

            Log.e("Error", e.getCause().getMessage());
        } catch (Exception e) {
            e.printStackTrace();

            Log.e("Error2", e.getCause().getMessage());
        }
    }

    private void test2(ITwCa twCa) {
        try {
            Class<?> c = twCa.getLibraryClass();
            Field field = c.getDeclaredField("libName");
            field.setAccessible(true);
            Log.i("Test", (String) field.get(c.newInstance()));

            Method m = c.getDeclaredMethod("Load", Context.class);
            m.setAccessible(true);
            Log.i("Test2", String.valueOf(m.invoke(c.newInstance(), getApplicationContext())));
        } catch (InvocationTargetException e) {
            e.printStackTrace();

            Log.e("Error", e.getCause().getMessage());
        } catch (Exception e) {
            e.printStackTrace();

            Log.e("Error2", e.getCause().getMessage());
        }
    }
}
