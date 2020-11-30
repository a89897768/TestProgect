package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import dalvik.system.DexClassLoader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test("TWCAcMobileCryptoV10140U20180601.jar","com.twca.crypto.twcalib");
        test("TWCAcMobileCryptoV10150U20191230.jar","com.twca.crypto.twcalib");
    }

    private void test(String jar, String className) {
        try {
            String jarPath = "/" + jar;
            AssetManager assetManager = getAssets();
            InputStream i = assetManager.open(jar);
            File f = new File(getFilesDir().getPath() + jarPath);
            FileOutputStream outputStream = new FileOutputStream(f);
            byte buf[] = new byte[1024];
            int len;
            while ((len = i.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }

            outputStream.close();
            i.close();

            File ff = new File(getFilesDir().getPath() + jarPath);
            ClassLoader classLoader;
            classLoader = new DexClassLoader(ff.getPath(), getFilesDir().getPath(), null, getClassLoader());
            Class<?> c = classLoader.loadClass(className);
            Field field = c.getDeclaredField("libName");
            field.setAccessible(true);
            Log.i("Test", (String) field.get(c.newInstance()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
