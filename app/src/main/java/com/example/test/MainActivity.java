package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;

import dalvik.system.DexClassLoader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        test("gson.jar", "com.google.gson.DefaultDateTypeAdapter");
        test("TWCAcMobileCryptoV10140U20180601.jar","com.twca.crypto.twcalib");
//        test("TWCAcMobileCryptoV10140U20180601.jar","com.twca.crypto.twcalib");

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
            Log.i("Test", String.valueOf(ff.exists()));
            Log.i("Test2", String.valueOf(getMainLooper().getThread().getContextClassLoader() == null));
            Log.i("Test3", ff.toURI().toURL().toString());

            ClassLoader classLoader;
            classLoader = new URLClassLoader(new URL[]{ff.toURL()}, getMainLooper().getThread().getContextClassLoader());
//            classLoader = new PathClassLoader(ff.getPath(), getMainLooper().getThread().getContextClassLoader());
//            classLoader = new DexClassLoader(f.toURI().toURL().toString(), getDir("libs", MODE_PRIVATE).getAbsolutePath(), null, ClassLoader.getSystemClassLoader());
            Class<?> c = classLoader.loadClass(className);
//            Class<?> c = Class.forName(className, true, classLoader);
            Log.i("Test4 Class is null? ", String.valueOf(c == null));
//            Method m = c.getMethod("getVersion");
//            Log.i("Test", (String) m.invoke(c.newInstance()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
