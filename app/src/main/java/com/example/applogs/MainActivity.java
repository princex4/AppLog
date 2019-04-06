package com.example.applogs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String BUNDLE_USERNAME = "username";
    private EditText etxUserName;
    private EditText etxPassword;
    private Button btnSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etxUserName = findViewById(R.id.etx_username);
        etxPassword = findViewById(R.id.etx_password);
        btnSubmit = findViewById(R.id.btn_submit);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launch();
            }
        });
g
        etxPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    launch();
                }
                return false;
            }
        });
        Log.d(TAG, "OnCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    public void launch() {
        if ("admin".equals(etxUserName.getText().toString()) && ("pass123".equals(etxPassword.getText().toString()))) {
            SharedPreferences prefs = this.getSharedPreferences(
                    "com.example.applogs", Context.MODE_PRIVATE);

            String usrName = etxUserName.getText().toString();
            prefs.edit().putString("user_name", usrName).apply();

            Intent intent = new Intent(this, AcitivityTwo.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Credentials are not valid", Toast.LENGTH_SHORT).show();
        }

    }
}
