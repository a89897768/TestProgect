package com.example.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.ComponentActivity;

public class MainActivity extends ComponentActivity {
    private static final int OVERLAY_PERMISSION_CODE = 101;
    private WindowManager windowManager;
    private View overlayView;
    private boolean isOverlayActive = false;
    private Button toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggleButton = findViewById(R.id.test_button);
        windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

        // 設置按鈕點擊事件
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOverlayActive) {
                    removeOverlay();
                } else {
                    checkOverlayPermission();
                }
            }
        });
    }

    // --- 步驟 1: 檢查並請求 SYSTEM_ALERT_WINDOW 權限 ---
    private void checkOverlayPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            // 需要用戶手動授權
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, OVERLAY_PERMISSION_CODE);
        } else {
            // 權限已授予或版本低於 Android M
            showOverlay();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OVERLAY_PERMISSION_CODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Settings.canDrawOverlays(this)) {
                // 用戶已授予權限
                showOverlay();
            } else {
                Toast.makeText(this, "需要授予「顯示在其他應用程式上層」權限才能測試。", Toast.LENGTH_LONG).show();
            }
        }
    }

    // --- 步驟 2: 創建並顯示透明 Overlay 視窗 ---
    private void showOverlay() {
        if (isOverlayActive) return;

        // 創建一個新的 View 作為 Overlay
        overlayView = new View(this);
        // ***修改 1: 設為半透明紅色 (alpha=50/255)，方便測試時看到 Overlay 範圍***
        overlayView.setBackgroundColor(Color.argb(50, 255, 0, 0));

        // 移除 OnTouchListener，因為我們希望事件直接穿透到下層

        // 設置 WindowManager 參數
        int type;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            // 舊版本使用這個類型
            type = WindowManager.LayoutParams.TYPE_PHONE;
        }

        // 這是 Overlay 視窗的關鍵參數設定
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                500, // 寬度改為 500 像素
                1800, // 高度改為 500 像素
                type,
                // ***關鍵修改: 移除 FLAG_LAYOUT_IN_SCREEN，只保留 FLAG_NOT_FOCUSABLE 和 FLAG_NOT_TOUCHABLE***
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, // <--- 點擊穿透的關鍵！
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 0;
        params.y = 0;

        try {
            windowManager.addView(overlayView, params);
            isOverlayActive = true;
            toggleButton.setText("點擊移除 Overlay (攻擊停止)");
            Toast.makeText(this, "半透明 Overlay 已啟動！請點擊它覆蓋的目標 App 按鈕。", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Log.e("OverlayAttacker", "添加 Overlay 失敗", e);
            Toast.makeText(this, "添加 Overlay 失敗: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    // --- 步驟 3: 移除 Overlay ---
    private void removeOverlay() {
        if (isOverlayActive && overlayView != null) {
            try {
                windowManager.removeView(overlayView);
                isOverlayActive = false;
                toggleButton.setText("點擊啟動透明 Overlay (模擬攻擊)");
                Toast.makeText(this, "Overlay 已移除。", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.e("OverlayAttacker", "移除 Overlay 失敗", e);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 確保 Activity 銷毀時移除 Overlay
        removeOverlay();
    }
}
