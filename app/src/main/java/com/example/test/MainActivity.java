package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.multidex.MultiDex;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
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

        dexClassLoaderTest("TWCAcMobileCryptoV10140U20180601.jar", "com.twca.crypto.twcalib");
        dexClassLoaderTest("TWCAcMobileCryptoV10150U20191230.jar", "com.twca.crypto.twcalib");
    }

    private void dexClassLoaderTest(String jarName, String className) {
        try {
            String jarPath = "/" + jarName;
            InputStream inputStream = getAssets().open(jarName);
            File jarFile = new File(getFilesDir().getPath() + jarPath);
            OutputStream outputStream = new FileOutputStream(jarFile);
            byte[] buf = new byte[1024];
            int len;
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }

            outputStream.close();
            inputStream.close();

            ClassLoader classLoader;
            String libraryPath = getApplicationContext().getApplicationInfo().nativeLibraryDir;
            classLoader = new DexClassLoader(jarFile.getPath(), getFilesDir().getPath(), libraryPath, getClassLoader());
            Class<?> c = classLoader.loadClass(className);
            Field field = c.getDeclaredField("libName");
            field.setAccessible(true);
            String libraryName = (String) field.get(c.newInstance());
            Log.i("Test_libraryName", libraryName);

            Method m = c.getDeclaredMethod("Load", Context.class);
            m.setAccessible(true);
            Log.i("Test_LoadMethod", String.valueOf(m.invoke(c.newInstance(), getApplicationContext())));
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Test_error", e.getCause().getMessage());
        }
    }
}
