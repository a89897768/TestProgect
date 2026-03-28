package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText mEditText = findViewById(R.id.id_edittext);
        TextView mTextView = findViewById(R.id.id_textview);
        mTextView.setOnClickListener(v -> {
            if(mEditText.equals("1")){
                Toast.makeText(this,"登入",1);
            }else{
                Toast.makeText(this,"登出",1);
            }
        });
    }
}
