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
        TextView mTextView1 = findViewById(R.id.id_textview1);
        mTextView.setOnClickListener(v -> {
            if(mEditText.getText().toString().equals("1")){
                mTextView1.setText("登入");
            }else{
                mTextView1.setText("登出");
            }
        });
    }
}
