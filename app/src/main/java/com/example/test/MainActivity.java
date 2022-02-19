package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetchRemoteConfig();
    }

    private void fetchRemoteConfig() {
        FirebaseRemoteConfig firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(720)
                .setFetchTimeoutInSeconds(15)
                .build();
        firebaseRemoteConfig.setConfigSettingsAsync(configSettings);
        firebaseRemoteConfig.setDefaultsAsync(R.xml.firebase_remote_config_defaults);
        firebaseRemoteConfig.fetchAndActivate().addOnCompleteListener(this, new OnCompleteListener<Boolean>() {
            private int mFailCount = 0;
            @Override
            public void onComplete(@NonNull Task<Boolean> task) {
                boolean isSuccessful = task.isSuccessful();
                if (isSuccessful) {
                    Log.i("Test fetchRemoteConfig", "" + task.getResult());
                } else {
                    Log.i("Test fetchRemoteConfig", "fail");
                    mFailCount++;
                    if (mFailCount < 3) {
                        firebaseRemoteConfig.fetchAndActivate().addOnCompleteListener(MainActivity.this, this);
                    } else {
                        //TODO:登出
                        return;
                    }
                }

                String appSign = firebaseRemoteConfig.getString("AppSign");
                Log.i("Test appsign",appSign);
                if (!"-1".equalsIgnoreCase(appSign) && !isAppSign(appSign)) {
                    Log.i("Test ", "登出");
                }else {
                    Log.i("Test ", "登入");
                }
            }
        });
    }

    private boolean isAppSign(String sign) {
        List<String> appSha1SignList = getAppSha1SignList(this);
        if (appSha1SignList.size() > 0) {
            for (String signatureHex : appSha1SignList) {
                if (sign.contains(signatureHex)) {
                    return true;
                }
            }
        }

        return false;
    }

    private List<String> getAppSha1SignList(Context context) {
        Signature[] signatures = null;
        try {
            PackageManager pm = context.getPackageManager();
            String packageName = context.getPackageName();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                PackageInfo packageInfo = pm.getPackageInfo(packageName, PackageManager.GET_SIGNING_CERTIFICATES);
                if (packageInfo == null || packageInfo.signingInfo == null) {
                    return new ArrayList<>();
                }

                if (packageInfo.signingInfo.hasMultipleSigners()) {
                    signatures = packageInfo.signingInfo.getApkContentsSigners();
                } else {
                    signatures = packageInfo.signingInfo.getSigningCertificateHistory();
                }
            } else {
                @SuppressLint("PackageManagerGetSignatures")
                PackageInfo packageInfo = pm.getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
                if (packageInfo == null) {
                    return new ArrayList<>();
                }
                signatures = packageInfo.signatures;
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.i("getAppSha1SignList", e.getLocalizedMessage());
        }

        if (signatures == null) {
            return new ArrayList<>();
        }

        List<String> resultList = new ArrayList<>();
        for (Signature signature : signatures) {
            resultList.add(getSha1(signature));
        }

        return resultList;
    }

    public String getSha1(Signature signature) {
        if (signature == null) {
            return "";
        }

        String fingerprint = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            byte[] digestBytes = messageDigest.digest(signature.toByteArray());
            StringBuilder stringBuilder = new StringBuilder();
            for (byte digestByte : digestBytes) {
                stringBuilder.append(((Integer.toHexString((digestByte & 0xFF) | 0x100)).substring(1, 3)).toUpperCase());
                stringBuilder.append(":");
            }

            fingerprint = stringBuilder.substring(0, stringBuilder.length() - 1);
        } catch (Exception e) {
            Log.e("getSha1", e.getLocalizedMessage());
        }

        return fingerprint;
    }
}
