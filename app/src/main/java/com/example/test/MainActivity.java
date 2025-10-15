package com.example.test;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String json = null;
        try {
//            InputStream is = getAssets().open("10_14_15_100201");
//            InputStream is = getAssets().open("10_38_05_100201");
//            InputStream is = getAssets().open("10_38_58_100200");
            InputStream is = getAssets().open("10_39_34_100201");


            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Gson gson = new Gson();
        Map<String, Object> root = gson.fromJson(json, new TypeToken<Map<String, Object>>() {
        }.getType());
        List<TestItem> items = gson.fromJson(gson.toJson(root.get("r")), new TypeToken<List<TestItem>>() {
        }.getType());

        // 將資料轉成 Map<Integer, Map<String, Object>>
        Map<Integer, Map<String, Object>> ndxToV = new java.util.HashMap<>();
        for (TestItem item : items) {
            ndxToV.put(item.ndx, item.v);
        }

        Map<Integer, Map<String, Object>> sData = new java.util.HashMap<>();
        Map<Integer, Map<String, Object>> bData = new java.util.HashMap<>();
        Map<Integer, Map<String, Object>> sCanModifyDate = new java.util.HashMap<>();
        Map<Integer, Map<String, Object>> bCanModifyDate = new java.util.HashMap<>();
        Map<Integer, Map<String, Object>> sMatchedDate = new java.util.HashMap<>();
        Map<Integer, Map<String, Object>> bMatchedDate = new java.util.HashMap<>();
        Map<Integer, Map<String, Object>> sErrorMatchedDate = new java.util.HashMap<>();
        Map<Integer, Map<String, Object>> bErrorMatchedDate = new java.util.HashMap<>();
        double sTotalMatchQty = 0;
        double bTotalMatchQty = 0;
        double sTotalCurrentQty = 0;
        double bTotalCurrentQty = 0;
        for (TestItem item : items) {
            if (isBuy(item)) {
                bData.put(item.ndx, item.v);
                if (canDel(item) || canModifyQty(item) || canModifyPrice(item)) {
                    bCanModifyDate.put(item.ndx, item.v);
                }

                if (isMatched(item)) {
                    bMatchedDate.put(item.ndx, item.v);
                }

                bTotalCurrentQty += (Double) item.v.get("22");
                bTotalMatchQty += (Double) item.v.get("24");
            } else {
                sData.put(item.ndx, item.v);
                if (canDel(item) || canModifyQty(item) || canModifyPrice(item)) {
                    sCanModifyDate.put(item.ndx, item.v);
                }

                if (isMatched(item)) {
                    sMatchedDate.put(item.ndx, item.v);
                }

                sTotalCurrentQty += (Double) item.v.get("22");
                sTotalMatchQty += (Double) item.v.get("24");

            }
        }

        sData.get(0);
    }

    public boolean isSell(TestItem item) {
        return "S".equalsIgnoreCase(item.v.get("18").toString());
    }

    public boolean isBuy(TestItem item) {
        return "B".equalsIgnoreCase(item.v.get("18").toString());
    }

    public boolean canDel(TestItem item) {
        return (Double) item.v.get("1") == 1.0;
    }

    private boolean canModifyQty(TestItem item) {
        return (Double) item.v.get("2") == 1.0;
    }

    private boolean canModifyPrice(TestItem item) {
        return (Double) item.v.get("3") == 1.0;
    }

    private boolean isMatched(TestItem item) {
        return (Double) item.v.get("24") == 1.0;
    }

    private boolean isMatchedError(TestItem item) {
        return (Double) item.v.get("24") < 0;
    }

    public class TestItem {
        public int ndx;
        public Map<String, Object> v;
    }

}
