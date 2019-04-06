package com.example.applogs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class AcitivityTwo extends AppCompatActivity {

    public static final String Tag = AcitivityTwo.class.getSimpleName();
    private String username;
    private TextView txtUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acitivity_two);
        Log.d(Tag , "OnCreate");
        txtUserName = findViewById(R.id.txt_username);
        txtUserName.setText(username);

        SharedPreferences prefs = this.getSharedPreferences(
                "com.example.applogs", Context.MODE_PRIVATE);

        username = prefs.getString("user_name", null);
        txtUserName.setText(username);


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(Tag, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(Tag, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(Tag, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(Tag, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(Tag, "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(Tag, "onRestart");
    }


    public void logout(View view) {
        SharedPreferences prefs = this.getSharedPreferences(
                "com.example.applogs", Context.MODE_PRIVATE);

        prefs.edit().putString("user_name", null).apply();

        Intent intent = new Intent(AcitivityTwo.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
